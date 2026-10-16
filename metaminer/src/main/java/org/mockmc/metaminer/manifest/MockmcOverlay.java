package org.mockmc.metaminer.manifest;

import java.util.Map;

/**
 * Immutable data model representing MockMC's behavioral implementation and unit test overlay,
 * decoupled from the upstream platform API manifest according to docs/RFC_MANIFEST_SCHEMA.md.
 */
public record MockmcOverlay(
		String schema,
		String schemaVersion,
		Provenance provenance,
		Map<String, SymbolOverlay> symbols
) {

	public static final String CURRENT_SCHEMA = "https://mockmc.org/schemas/mockmc-overlay-v1.json";
	public static final String CURRENT_SCHEMA_VERSION = "1.0.0";

	public MockmcOverlay {
		if (schema == null) {
			schema = CURRENT_SCHEMA;
		}
		if (schemaVersion == null) {
			schemaVersion = CURRENT_SCHEMA_VERSION;
		}
		symbols = symbols == null ? Map.of() : Map.copyOf(symbols);
	}

	public record Provenance(
			String platform,
			String targetVersion,
			String manifestSha256,
			String mockmcCommit,
			boolean dirty
	) {}

	public record SymbolOverlay(
			Status status,
			String mockClass,
			boolean isImplemented,
			String exclusionReason,
			Verification verification
	) {}

	public enum Status {
		SMART_STUB,
		BEHAVIORAL_OVERRIDE,
		JUSTIFIED_EXCLUSION,
		DEPRECATED_FALLBACK,
		UNANALYZED
	}

	public record Verification(
			boolean hasTest,
			String testClass,
			String testMethod,
			TestOutcome outcome,
			String scopeNote
	) {}

	public enum TestOutcome {
		PASSED,
		FAILED,
		SKIPPED
	}
}
