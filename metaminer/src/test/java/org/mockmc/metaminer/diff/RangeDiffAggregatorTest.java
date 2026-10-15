package org.mockmc.metaminer.diff;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class RangeDiffAggregatorTest {

	@Test
	void testRangeAggregationNetAddedAndTransient() {
		// Delta(v1 -> v2)
		PairwiseDiff.ChangeEntry introduced = new PairwiseDiff.ChangeEntry(
				"org.bukkit.entity.Player#newMethod()V",
				PairwiseDiff.SymbolType.METHOD,
				PairwiseDiff.ChangeCategory.ADDED,
				PairwiseDiff.CompatibilityImpact.COMPATIBLE_ADDITION,
				null, null
		);
		PairwiseDiff.ChangeEntry transientAdded = new PairwiseDiff.ChangeEntry(
				"org.bukkit.entity.Player#shortLived()V",
				PairwiseDiff.SymbolType.METHOD,
				PairwiseDiff.ChangeCategory.ADDED,
				PairwiseDiff.CompatibilityImpact.COMPATIBLE_ADDITION,
				null, null
		);

		PairwiseDiff diff1 = new PairwiseDiff(
				PairwiseDiff.CURRENT_SCHEMA, PairwiseDiff.CURRENT_SCHEMA_VERSION,
				"paper", "26.1", "26.2",
				new PairwiseDiff.Summary(0, 0, 2, 0, 2),
				List.of(introduced, transientAdded)
		);

		// Delta(v2 -> v3)
		PairwiseDiff.ChangeEntry transientRemoved = new PairwiseDiff.ChangeEntry(
				"org.bukkit.entity.Player#shortLived()V",
				PairwiseDiff.SymbolType.METHOD,
				PairwiseDiff.ChangeCategory.REMOVED,
				PairwiseDiff.CompatibilityImpact.BINARY_INCOMPATIBLE,
				null, null
		);

		PairwiseDiff diff2 = new PairwiseDiff(
				PairwiseDiff.CURRENT_SCHEMA, PairwiseDiff.CURRENT_SCHEMA_VERSION,
				"paper", "26.2", "26.3",
				new PairwiseDiff.Summary(1, 0, 0, 0, 1),
				List.of(transientRemoved)
		);

		RangeDiffAggregator.RangeSummary summary = RangeDiffAggregator.aggregateRange("paper", List.of(diff1, diff2));

		assertNotNull(summary);
		assertEquals("26.1", summary.minVersion());
		assertEquals("26.3", summary.maxVersion());
		assertEquals(1, summary.netAdded());
		assertEquals(0, summary.netRemoved());
		assertEquals(1, summary.transientCount());

		RangeDiffAggregator.RangeChangeEntry netIntro = summary.netChanges().stream()
				.filter(c -> c.symbol().contains("newMethod"))
				.findFirst()
				.orElseThrow();
		assertEquals(RangeDiffAggregator.RangeLifecycleStatus.INTRODUCED_IN_RANGE, netIntro.status());

		RangeDiffAggregator.RangeChangeEntry netTransient = summary.netChanges().stream()
				.filter(c -> c.symbol().contains("shortLived"))
				.findFirst()
				.orElseThrow();
		assertEquals(RangeDiffAggregator.RangeLifecycleStatus.TRANSIENT_IN_RANGE, netTransient.status());
	}

	@Test
	void testRangeAggregationReintroducedSymbol() {
		// Delta(v1 -> v2): method removed
		PairwiseDiff.ChangeEntry removedInV2 = new PairwiseDiff.ChangeEntry(
				"org.bukkit.entity.Player#restoredMethod()V",
				PairwiseDiff.SymbolType.METHOD,
				PairwiseDiff.ChangeCategory.REMOVED,
				PairwiseDiff.CompatibilityImpact.BINARY_INCOMPATIBLE,
				null, null
		);
		PairwiseDiff diff1 = new PairwiseDiff(
				PairwiseDiff.CURRENT_SCHEMA, PairwiseDiff.CURRENT_SCHEMA_VERSION,
				"paper", "26.1", "26.2",
				new PairwiseDiff.Summary(1, 0, 0, 0, 1),
				List.of(removedInV2)
		);

		// Delta(v2 -> v3): method reintroduced
		PairwiseDiff.ChangeEntry reintroducedInV3 = new PairwiseDiff.ChangeEntry(
				"org.bukkit.entity.Player#restoredMethod()V",
				PairwiseDiff.SymbolType.METHOD,
				PairwiseDiff.ChangeCategory.ADDED,
				PairwiseDiff.CompatibilityImpact.COMPATIBLE_ADDITION,
				null, null
		);
		PairwiseDiff diff2 = new PairwiseDiff(
				PairwiseDiff.CURRENT_SCHEMA, PairwiseDiff.CURRENT_SCHEMA_VERSION,
				"paper", "26.2", "26.3",
				new PairwiseDiff.Summary(0, 0, 1, 0, 1),
				List.of(reintroducedInV3)
		);

		RangeDiffAggregator.RangeSummary summary = RangeDiffAggregator.aggregateRange("paper", List.of(diff1, diff2));
		assertNotNull(summary);
		assertEquals(1, summary.netChanges().size());

		RangeDiffAggregator.RangeChangeEntry reintroduced = summary.netChanges().get(0);
		assertEquals(RangeDiffAggregator.RangeLifecycleStatus.REINTRODUCED_IN_RANGE, reintroduced.status());
		assertEquals("26.2", reintroduced.removalVersion());
		assertEquals("26.3", reintroduced.introductionVersion());
	}
}
