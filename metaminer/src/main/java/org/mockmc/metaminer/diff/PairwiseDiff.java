package org.mockmc.metaminer.diff;

import java.util.List;

/**
 * Immutable data model representing deterministic pairwise compatibility diffs
 * between two platform API manifests according to docs/RFC_DEVELOPER_WIKI.md.
 */
public record PairwiseDiff(
		String schema,
		String schemaVersion,
		String platform,
		String fromVersion,
		String toVersion,
		Summary summary,
		List<ChangeEntry> changes
) {

	public static final String CURRENT_SCHEMA = "https://mockmc.org/schemas/api-diff-v1.json";
	public static final String CURRENT_SCHEMA_VERSION = "1.0.0";

	public PairwiseDiff {
		if (schema == null) {
			schema = CURRENT_SCHEMA;
		}
		if (schemaVersion == null) {
			schemaVersion = CURRENT_SCHEMA_VERSION;
		}
		changes = changes == null ? List.of() : List.copyOf(changes);
	}

	public record Summary(
			int binaryIncompatibleChanges,
			int sourceIncompatibleChanges,
			int compatibleAdditions,
			int deprecations,
			int totalChanges
	) {}

	public record ChangeEntry(
			String symbol,
			SymbolType type,
			ChangeCategory category,
			CompatibilityImpact compatibilityImpact,
			Details details,
			MigrationHint migrationHint
	) {}

	public enum SymbolType {
		CLASS,
		METHOD,
		FIELD
	}

	public enum ChangeCategory {
		ADDED,
		REMOVED,
		MODIFIED,
		DEPRECATED
	}

	public enum CompatibilityImpact {
		BINARY_INCOMPATIBLE,
		SOURCE_INCOMPATIBLE,
		COMPATIBLE_ADDITION,
		NON_BREAKING_DEPRECATION,
		COMPATIBLE_MODIFICATION
	}

	public record Details(
			String descriptor,
			List<String> accessFlags,
			String reason,
			String diffDescription
	) {}

	public record MigrationHint(
			String relation,
			String replacementSymbol
	) {}
}
