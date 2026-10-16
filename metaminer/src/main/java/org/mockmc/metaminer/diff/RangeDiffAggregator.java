package org.mockmc.metaminer.diff;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Computes derived version range views by dynamically folding adjacent pairwise diffs:
 * Delta(v0 -> vn) = Delta(v0 -> v1) + Delta(v1 -> v2) + ... + Delta(vn-1 -> vn)
 *
 * Evaluates net lifecycle status:
 * 1. INTRODUCED_IN_RANGE: added at vi (i > 0) and remains present at vn.
 * 2. REMOVED_IN_RANGE: present at v0 and removed at vj (j <= n).
 * 3. TRANSIENT: added at vi and removed at vj (i < j <= n).
 * 4. STABLE_THROUGHOUT: present throughout range without breaking signature changes.
 */
public final class RangeDiffAggregator {

	private RangeDiffAggregator() {}

	public record RangeSummary(
			String platform,
			String minVersion,
			String maxVersion,
			int netAdded,
			int netRemoved,
			int transientCount,
			int deprecationsCount,
			List<RangeChangeEntry> netChanges
	) {}

	public record RangeChangeEntry(
			String symbol,
			PairwiseDiff.SymbolType type,
			RangeLifecycleStatus status,
			PairwiseDiff.CompatibilityImpact impact,
			String introductionVersion,
			String removalVersion,
			String details
	) {}

	public enum RangeLifecycleStatus {
		INTRODUCED_IN_RANGE,
		REMOVED_IN_RANGE,
		TRANSIENT_IN_RANGE,
		REINTRODUCED_IN_RANGE,
		DEPRECATED_IN_RANGE
	}

	public static RangeSummary aggregateRange(String platform, List<PairwiseDiff> chronologicalDiffs) {
		if (chronologicalDiffs == null || chronologicalDiffs.isEmpty()) {
			throw new IllegalArgumentException("chronologicalDiffs must not be empty");
		}

		String minVersion = chronologicalDiffs.get(0).fromVersion();
		String maxVersion = chronologicalDiffs.get(chronologicalDiffs.size() - 1).toVersion();

		// Track sequence of lifecycle events for each symbol across versions
		Map<String, SymbolTracker> trackers = new HashMap<>();

		for (PairwiseDiff diff : chronologicalDiffs) {
			String stepTo = diff.toVersion();

			for (PairwiseDiff.ChangeEntry change : diff.changes()) {
				SymbolTracker tracker = trackers.computeIfAbsent(change.symbol(), _ -> new SymbolTracker(change.symbol(), change.type()));
				tracker.events.add(new SymbolEvent(change.category(), stepTo, change.compatibilityImpact()));
			}
		}

		List<RangeChangeEntry> netChanges = new ArrayList<>();
		int netAdded = 0;
		int netRemoved = 0;
		int transientCount = 0;
		int deprecationsCount = 0;

		for (SymbolTracker tracker : trackers.values()) {
			RangeChangeEntry entry = evaluateSymbolLifecycle(tracker);
			if (entry == null) {
				continue;
			}
			netChanges.add(entry);
			switch (entry.status()) {
				case INTRODUCED_IN_RANGE -> netAdded++;
				case REMOVED_IN_RANGE -> netRemoved++;
				case TRANSIENT_IN_RANGE -> transientCount++;
				case DEPRECATED_IN_RANGE -> deprecationsCount++;
				case REINTRODUCED_IN_RANGE -> { /* Reintroduced symbols are captured in netChanges list */ }
			}
		}

		netChanges.sort(Comparator.comparing(RangeChangeEntry::symbol));

		return new RangeSummary(
				platform,
				minVersion,
				maxVersion,
				netAdded,
				netRemoved,
				transientCount,
				deprecationsCount,
				netChanges
		);
	}

	private static RangeChangeEntry evaluateSymbolLifecycle(SymbolTracker tracker) {
		List<SymbolEvent> events = tracker.events;
		if (events.isEmpty()) {
			return null;
		}

		List<SymbolEvent> lifeEvents = events.stream()
				.filter(e -> e.category == PairwiseDiff.ChangeCategory.ADDED || e.category == PairwiseDiff.ChangeCategory.REMOVED)
				.toList();

		if (!lifeEvents.isEmpty()) {
			return buildLifecycleChange(tracker, lifeEvents);
		}

		String deprecatedAt = findDeprecatedAt(events);
		if (deprecatedAt != null) {
			return new RangeChangeEntry(
					tracker.symbol,
					tracker.type,
					RangeLifecycleStatus.DEPRECATED_IN_RANGE,
					PairwiseDiff.CompatibilityImpact.NON_BREAKING_DEPRECATION,
					null,
					null,
					"Deprecated in " + deprecatedAt
			);
		}
		return null;
	}

	private static RangeChangeEntry buildLifecycleChange(SymbolTracker tracker, List<SymbolEvent> lifeEvents) {
		SymbolEvent first = lifeEvents.get(0);
		SymbolEvent last = lifeEvents.get(lifeEvents.size() - 1);

		if (first.category == PairwiseDiff.ChangeCategory.REMOVED && last.category == PairwiseDiff.ChangeCategory.ADDED) {
			return new RangeChangeEntry(
					tracker.symbol,
					tracker.type,
					RangeLifecycleStatus.REINTRODUCED_IN_RANGE,
					PairwiseDiff.CompatibilityImpact.BINARY_INCOMPATIBLE,
					last.version,
					first.version,
					"Removed in " + first.version + " and reintroduced in " + last.version
			);
		}
		if (first.category == PairwiseDiff.ChangeCategory.ADDED && last.category == PairwiseDiff.ChangeCategory.REMOVED) {
			return new RangeChangeEntry(
					tracker.symbol,
					tracker.type,
					RangeLifecycleStatus.TRANSIENT_IN_RANGE,
					PairwiseDiff.CompatibilityImpact.BINARY_INCOMPATIBLE,
					first.version,
					last.version,
					"Introduced in " + first.version + " and removed in " + last.version
			);
		}
		if (last.category == PairwiseDiff.ChangeCategory.ADDED) {
			return new RangeChangeEntry(
					tracker.symbol,
					tracker.type,
					RangeLifecycleStatus.INTRODUCED_IN_RANGE,
					last.impact != null ? last.impact : PairwiseDiff.CompatibilityImpact.COMPATIBLE_ADDITION,
					last.version,
					null,
					"Introduced in " + last.version
			);
		}
		return new RangeChangeEntry(
				tracker.symbol,
				tracker.type,
				RangeLifecycleStatus.REMOVED_IN_RANGE,
				PairwiseDiff.CompatibilityImpact.BINARY_INCOMPATIBLE,
				null,
				last.version,
				"Removed in " + last.version
		);
	}

	private static String findDeprecatedAt(List<SymbolEvent> events) {
		for (SymbolEvent ev : events) {
			if (ev.category == PairwiseDiff.ChangeCategory.DEPRECATED) {
				return ev.version;
			}
		}
		return null;
	}

	private record SymbolEvent(
			PairwiseDiff.ChangeCategory category,
			String version,
			PairwiseDiff.CompatibilityImpact impact
	) {}

	private static class SymbolTracker {
		final String symbol;
		final PairwiseDiff.SymbolType type;
		final List<SymbolEvent> events = new ArrayList<>();

		SymbolTracker(String symbol, PairwiseDiff.SymbolType type) {
			this.symbol = symbol;
			this.type = type;
		}
	}
}
