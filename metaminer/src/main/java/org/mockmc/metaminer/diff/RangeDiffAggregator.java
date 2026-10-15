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
		DEPRECATED_IN_RANGE
	}

	public static RangeSummary aggregateRange(String platform, List<PairwiseDiff> chronologicalDiffs) {
		if (chronologicalDiffs == null || chronologicalDiffs.isEmpty()) {
			throw new IllegalArgumentException("chronologicalDiffs must not be empty");
		}

		String minVersion = chronologicalDiffs.get(0).fromVersion();
		String maxVersion = chronologicalDiffs.get(chronologicalDiffs.size() - 1).toVersion();

		// Track state of each symbol across versions: symbol -> SymbolTracker
		Map<String, SymbolTracker> trackers = new HashMap<>();

		for (PairwiseDiff diff : chronologicalDiffs) {
			String stepTo = diff.toVersion();

			for (PairwiseDiff.ChangeEntry change : diff.changes()) {
				SymbolTracker tracker = trackers.computeIfAbsent(change.symbol(), _ -> new SymbolTracker(change.symbol(), change.type()));

				switch (change.category()) {
					case ADDED -> {
						tracker.introducedAt = stepTo;
						tracker.presentAtEnd = true;
						tracker.impact = change.compatibilityImpact();
					}
					case REMOVED -> {
						tracker.removedAt = stepTo;
						tracker.presentAtEnd = false;
						tracker.impact = change.compatibilityImpact();
					}
					case DEPRECATED -> {
						tracker.deprecatedAt = stepTo;
					}
					case MODIFIED -> {}
				}
			}
		}

		List<RangeChangeEntry> netChanges = new ArrayList<>();
		int netAdded = 0;
		int netRemoved = 0;
		int transientCount = 0;
		int deprecationsCount = 0;

		for (SymbolTracker tracker : trackers.values()) {
			if (tracker.introducedAt != null && tracker.removedAt != null) {
				// Added then removed within range
				transientCount++;
				netChanges.add(new RangeChangeEntry(
						tracker.symbol,
						tracker.type,
						RangeLifecycleStatus.TRANSIENT_IN_RANGE,
						PairwiseDiff.CompatibilityImpact.BINARY_INCOMPATIBLE,
						tracker.introducedAt,
						tracker.removedAt,
						"Introduced in " + tracker.introducedAt + " and removed in " + tracker.removedAt
				));
			} else if (tracker.introducedAt != null && tracker.presentAtEnd) {
				netAdded++;
				netChanges.add(new RangeChangeEntry(
						tracker.symbol,
						tracker.type,
						RangeLifecycleStatus.INTRODUCED_IN_RANGE,
						tracker.impact != null ? tracker.impact : PairwiseDiff.CompatibilityImpact.COMPATIBLE_ADDITION,
						tracker.introducedAt,
						null,
						"Introduced in " + tracker.introducedAt
				));
			} else if (tracker.removedAt != null && !tracker.presentAtEnd) {
				netRemoved++;
				netChanges.add(new RangeChangeEntry(
						tracker.symbol,
						tracker.type,
						RangeLifecycleStatus.REMOVED_IN_RANGE,
						PairwiseDiff.CompatibilityImpact.BINARY_INCOMPATIBLE,
						null,
						tracker.removedAt,
						"Removed in " + tracker.removedAt
				));
			} else if (tracker.deprecatedAt != null) {
				deprecationsCount++;
				netChanges.add(new RangeChangeEntry(
						tracker.symbol,
						tracker.type,
						RangeLifecycleStatus.DEPRECATED_IN_RANGE,
						PairwiseDiff.CompatibilityImpact.NON_BREAKING_DEPRECATION,
						null,
						null,
						"Deprecated in " + tracker.deprecatedAt
				));
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

	private static class SymbolTracker {
		final String symbol;
		final PairwiseDiff.SymbolType type;
		String introducedAt;
		String removedAt;
		String deprecatedAt;
		boolean presentAtEnd;
		PairwiseDiff.CompatibilityImpact impact;

		SymbolTracker(String symbol, PairwiseDiff.SymbolType type) {
			this.symbol = symbol;
			this.type = type;
		}
	}
}
