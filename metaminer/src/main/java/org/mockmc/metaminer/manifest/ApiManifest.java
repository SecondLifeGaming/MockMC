package org.mockmc.metaminer.manifest;

import java.util.List;
import java.util.Map;

/**
 * Immutable data model representing the canonical upstream platform API manifest,
 * serialized according to the schema defined in docs/RFC_MANIFEST_SCHEMA.md.
 */
public record ApiManifest(
		String schema,
		String schemaVersion,
		Provenance provenance,
		ScanAccounting scanAccounting,
		Map<String, ClassEntry> classes
) {

	public static final String CURRENT_SCHEMA = "https://mockmc.org/schemas/api-manifest-v1.json";
	public static final String CURRENT_SCHEMA_VERSION = "1.0.0";

	public ApiManifest {
		if (schema == null) {
			schema = CURRENT_SCHEMA;
		}
		if (schemaVersion == null) {
			schemaVersion = CURRENT_SCHEMA_VERSION;
		}
		classes = classes == null ? Map.of() : Map.copyOf(classes);
	}

	public record Provenance(
			String platform,
			String version,
			int build,
			String tier,
			SourceJar sourceJar,
			ScannerInfo scanner,
			RemappingInfo remapping
	) {}

	public record SourceJar(
			String fileName,
			String sha256,
			long byteLength
	) {}

	public record ScannerInfo(
			String version,
			String gitCommit,
			boolean dirty
	) {}

	public record RemappingInfo(
			String source,
			String mappingSha256,
			String asmVersion
	) {}

	public record ScanAccounting(
			String status,
			List<String> scannedPackageRoots,
			List<String> scopedPackagePrefixes,
			int totalClassesDiscovered,
			int totalClassesAnalyzed,
			int totalClassesExcluded,
			int totalClassesFailed,
			int totalMethodsAnalyzed,
			int totalFieldsAnalyzed,
			List<ExclusionRule> justifiedExclusionRules,
			Map<String, ClassDisposition> dispositionInventory,
			List<FailedEntry> failedEntries
	) {
		public ScanAccounting {
			scannedPackageRoots = scannedPackageRoots == null ? List.of() : List.copyOf(scannedPackageRoots);
			scopedPackagePrefixes = scopedPackagePrefixes == null ? List.of() : List.copyOf(scopedPackagePrefixes);
			justifiedExclusionRules = justifiedExclusionRules == null ? List.of() : List.copyOf(justifiedExclusionRules);
			dispositionInventory = dispositionInventory == null ? Map.of() : Map.copyOf(dispositionInventory);
			failedEntries = failedEntries == null ? List.of() : List.copyOf(failedEntries);
		}
	}

	public record ExclusionRule(
			String pattern,
			String category,
			String justification
	) {}

	public record ClassDisposition(
			DispositionType disposition,
			Integer methodsCount,
			Integer fieldsCount,
			String matchedRule,
			String category
	) {}

	public enum DispositionType {
		ANALYZED,
		JUSTIFIED_EXCLUSION,
		FAILED
	}

	public record FailedEntry(
			String className,
			String reason,
			String errorDetails
	) {}

	public record ClassEntry(
			String kind,
			List<String> accessFlags,
			String signature,
			String superClass,
			List<String> interfaces,
			List<AnnotationEntry> annotations,
			Map<String, MethodEntry> methods,
			Map<String, FieldEntry> fields
	) {
		public ClassEntry {
			accessFlags = accessFlags == null ? List.of() : List.copyOf(accessFlags);
			interfaces = interfaces == null ? List.of() : List.copyOf(interfaces);
			annotations = annotations == null ? List.of() : List.copyOf(annotations);
			methods = methods == null ? Map.of() : Map.copyOf(methods);
			fields = fields == null ? Map.of() : Map.copyOf(fields);
		}
	}

	public record MethodEntry(
			String name,
			String descriptor,
			String signature,
			String returnType,
			List<ParameterEntry> parameters,
			List<String> exceptions,
			List<String> accessFlags,
			boolean isBridge,
			boolean isSynthetic,
			boolean isDefault,
			List<AnnotationEntry> annotations,
			SymbolProvenance symbolProvenance
	) {
		public MethodEntry {
			parameters = parameters == null ? List.of() : List.copyOf(parameters);
			exceptions = exceptions == null ? List.of() : List.copyOf(exceptions);
			accessFlags = accessFlags == null ? List.of() : List.copyOf(accessFlags);
			annotations = annotations == null ? List.of() : List.copyOf(annotations);
		}
	}

	public record ParameterEntry(
			int index,
			String name,
			String type
	) {}

	public record FieldEntry(
			String name,
			String descriptor,
			String signature,
			String type,
			List<String> accessFlags,
			boolean isSynthetic,
			Object constantValue,
			List<AnnotationEntry> annotations,
			SymbolProvenance symbolProvenance
	) {
		public FieldEntry {
			accessFlags = accessFlags == null ? List.of() : List.copyOf(accessFlags);
			annotations = annotations == null ? List.of() : List.copyOf(annotations);
		}
	}

	public record AnnotationEntry(
			String type,
			Map<String, Object> values
	) {
		public AnnotationEntry {
			values = values == null ? Map.of() : Map.copyOf(values);
		}
	}

	public record SymbolProvenance(
			String originPlatform,
			String originVersion,
			String targetPlatform,
			String targetVersion,
			boolean isPinnedFallback,
			String fallbackReason
	) {}
}
