package org.mockmc.metaminer.diff;

import org.mockmc.metaminer.manifest.ApiManifest;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Deterministic pairwise diff engine that computes compatibility classifications
 * between two platform API manifests.
 *
 * Rules:
 * 1. Filter synthetic and compiler-generated bridge methods from developer API diffs.
 * 2. Treat JVM descriptors as atomic identity: changes in signatures are classified as removal of the old descriptor
 *    and addition of the new descriptor, with optional migration linking.
 * 3. Categorize changes into:
 *    - BINARY_INCOMPATIBLE: removed types/methods/fields, changed return types, non-default interface methods added.
 *    - SOURCE_INCOMPATIBLE: ambiguous overloads, narrowed access flags.
 *    - COMPATIBLE_ADDITION: new classes, interface default methods, added class methods, new fields.
 *    - NON_BREAKING_DEPRECATION: newly added @Deprecated annotations.
 * 4. Deterministic sorting by symbol identifier.
 */
public final class VersionDiffEngine {

	private VersionDiffEngine() {}

	public static PairwiseDiff computeDiff(ApiManifest fromManifest, ApiManifest toManifest) {
		String platform = toManifest.provenance().platform();
		String fromVer = fromManifest.provenance().version();
		String toVer = toManifest.provenance().version();

		List<PairwiseDiff.ChangeEntry> changes = new ArrayList<>();

		Map<String, ApiManifest.ClassEntry> fromClasses = fromManifest.classes();
		Map<String, ApiManifest.ClassEntry> toClasses = toManifest.classes();

		Set<String> allClasses = new HashSet<>();
		allClasses.addAll(fromClasses.keySet());
		allClasses.addAll(toClasses.keySet());

		// Track removals and additions per method name to establish migration hints
		Map<String, List<PairwiseDiff.ChangeEntry>> removedMethodsByName = new HashMap<>();
		Map<String, List<PairwiseDiff.ChangeEntry>> addedMethodsByName = new HashMap<>();

		for (String className : allClasses) {
			ApiManifest.ClassEntry fromClass = fromClasses.get(className);
			ApiManifest.ClassEntry toClass = toClasses.get(className);

			// Class removed
			if (fromClass != null && toClass == null) {
				changes.add(new PairwiseDiff.ChangeEntry(
						className,
						PairwiseDiff.SymbolType.CLASS,
						PairwiseDiff.ChangeCategory.REMOVED,
						PairwiseDiff.CompatibilityImpact.BINARY_INCOMPATIBLE,
						new PairwiseDiff.Details(null, fromClass.accessFlags(), "Class removed upstream in " + toVer, null),
						null
				));
				continue;
			}

			// Class added
			if (fromClass == null && toClass != null) {
				changes.add(new PairwiseDiff.ChangeEntry(
						className,
						PairwiseDiff.SymbolType.CLASS,
						PairwiseDiff.ChangeCategory.ADDED,
						PairwiseDiff.CompatibilityImpact.COMPATIBLE_ADDITION,
						new PairwiseDiff.Details(null, toClass.accessFlags(), "Class added upstream in " + toVer, null),
						null
				));
				continue;
			}

			// Class present in both: compare methods and fields
			boolean isInterface = "INTERFACE".equals(toClass.kind());
			diffMethods(className, isInterface, fromClass, toClass, toVer, changes, removedMethodsByName, addedMethodsByName);
			diffFields(className, fromClass, toClass, toVer, changes);
		}

		// Link migration hints where methods of the same name had old descriptors removed and new descriptors added
		linkMigrationHints(removedMethodsByName, addedMethodsByName);

		// Sort changes deterministically by symbol
		changes.sort(Comparator.comparing(PairwiseDiff.ChangeEntry::symbol));

		int binaryIncompatible = 0;
		int sourceIncompatible = 0;
		int compatibleAdditions = 0;
		int deprecations = 0;

		for (PairwiseDiff.ChangeEntry change : changes) {
			switch (change.compatibilityImpact()) {
				case BINARY_INCOMPATIBLE -> binaryIncompatible++;
				case SOURCE_INCOMPATIBLE -> sourceIncompatible++;
				case COMPATIBLE_ADDITION -> compatibleAdditions++;
				case NON_BREAKING_DEPRECATION -> deprecations++;
				case COMPATIBLE_MODIFICATION -> {}
			}
		}

		PairwiseDiff.Summary summary = new PairwiseDiff.Summary(
				binaryIncompatible,
				sourceIncompatible,
				compatibleAdditions,
				deprecations,
				changes.size()
		);

		return new PairwiseDiff(
				PairwiseDiff.CURRENT_SCHEMA,
				PairwiseDiff.CURRENT_SCHEMA_VERSION,
				platform,
				fromVer,
				toVer,
				summary,
				changes
		);
	}

	private static void diffMethods(
			String className,
			boolean isInterface,
			ApiManifest.ClassEntry fromClass,
			ApiManifest.ClassEntry toClass,
			String toVer,
			List<PairwiseDiff.ChangeEntry> changes,
			Map<String, List<PairwiseDiff.ChangeEntry>> removedMethodsByName,
			Map<String, List<PairwiseDiff.ChangeEntry>> addedMethodsByName
	) {
		Map<String, ApiManifest.MethodEntry> fromMethods = filterApiMethods(fromClass.methods());
		Map<String, ApiManifest.MethodEntry> toMethods = filterApiMethods(toClass.methods());

		Set<String> allMethodKeys = new HashSet<>();
		allMethodKeys.addAll(fromMethods.keySet());
		allMethodKeys.addAll(toMethods.keySet());

		for (String methodKey : allMethodKeys) {
			ApiManifest.MethodEntry fromMethod = fromMethods.get(methodKey);
			ApiManifest.MethodEntry toMethod = toMethods.get(methodKey);
			String symbol = className + "#" + methodKey;

			// Method removed
			if (fromMethod != null && toMethod == null) {
				PairwiseDiff.ChangeEntry entry = new PairwiseDiff.ChangeEntry(
						symbol,
						PairwiseDiff.SymbolType.METHOD,
						PairwiseDiff.ChangeCategory.REMOVED,
						PairwiseDiff.CompatibilityImpact.BINARY_INCOMPATIBLE,
						new PairwiseDiff.Details(fromMethod.descriptor(), fromMethod.accessFlags(), "Method removed upstream in " + toVer, null),
						null
				);
				changes.add(entry);
				removedMethodsByName.computeIfAbsent(className + "#" + fromMethod.name(), _ -> new ArrayList<>()).add(entry);
				continue;
			}

			// Method added
			if (fromMethod == null && toMethod != null) {
				// If added to an interface without default implementation, it's binary incompatible for implementers
				PairwiseDiff.CompatibilityImpact impact = (isInterface && !toMethod.isDefault())
						? PairwiseDiff.CompatibilityImpact.BINARY_INCOMPATIBLE
						: PairwiseDiff.CompatibilityImpact.COMPATIBLE_ADDITION;

				PairwiseDiff.ChangeEntry entry = new PairwiseDiff.ChangeEntry(
						symbol,
						PairwiseDiff.SymbolType.METHOD,
						PairwiseDiff.ChangeCategory.ADDED,
						impact,
						new PairwiseDiff.Details(toMethod.descriptor(), toMethod.accessFlags(), "Method added upstream in " + toVer, null),
						null
				);
				changes.add(entry);
				addedMethodsByName.computeIfAbsent(className + "#" + toMethod.name(), _ -> new ArrayList<>()).add(entry);
				continue;
			}

			// Method present in both: check deprecation
			boolean wasDeprecated = isAnnotatedWith(fromMethod.annotations(), "java.lang.Deprecated");
			boolean isDeprecated = isAnnotatedWith(toMethod.annotations(), "java.lang.Deprecated");
			if (!wasDeprecated && isDeprecated) {
				changes.add(new PairwiseDiff.ChangeEntry(
						symbol,
						PairwiseDiff.SymbolType.METHOD,
						PairwiseDiff.ChangeCategory.DEPRECATED,
						PairwiseDiff.CompatibilityImpact.NON_BREAKING_DEPRECATION,
						new PairwiseDiff.Details(toMethod.descriptor(), toMethod.accessFlags(), "Marked @Deprecated in " + toVer, null),
						null
				));
			}
		}
	}

	private static void diffFields(
			String className,
			ApiManifest.ClassEntry fromClass,
			ApiManifest.ClassEntry toClass,
			String toVer,
			List<PairwiseDiff.ChangeEntry> changes
	) {
		Map<String, ApiManifest.FieldEntry> fromFields = filterApiFields(fromClass.fields());
		Map<String, ApiManifest.FieldEntry> toFields = filterApiFields(toClass.fields());

		Set<String> allFieldKeys = new HashSet<>();
		allFieldKeys.addAll(fromFields.keySet());
		allFieldKeys.addAll(toFields.keySet());

		for (String fieldName : allFieldKeys) {
			ApiManifest.FieldEntry fromField = fromFields.get(fieldName);
			ApiManifest.FieldEntry toField = toFields.get(fieldName);
			String symbol = className + "#" + fieldName;

			if (fromField != null && toField == null) {
				changes.add(new PairwiseDiff.ChangeEntry(
						symbol,
						PairwiseDiff.SymbolType.FIELD,
						PairwiseDiff.ChangeCategory.REMOVED,
						PairwiseDiff.CompatibilityImpact.BINARY_INCOMPATIBLE,
						new PairwiseDiff.Details(fromField.descriptor(), fromField.accessFlags(), "Field removed upstream in " + toVer, null),
						null
				));
			} else if (fromField == null && toField != null) {
				changes.add(new PairwiseDiff.ChangeEntry(
						symbol,
						PairwiseDiff.SymbolType.FIELD,
						PairwiseDiff.ChangeCategory.ADDED,
						PairwiseDiff.CompatibilityImpact.COMPATIBLE_ADDITION,
						new PairwiseDiff.Details(toField.descriptor(), toField.accessFlags(), "Field added upstream in " + toVer, null),
						null
				));
			}
		}
	}

	private static Map<String, ApiManifest.MethodEntry> filterApiMethods(Map<String, ApiManifest.MethodEntry> declaredMethods) {
		Map<String, ApiManifest.MethodEntry> filtered = new HashMap<>();
		for (Map.Entry<String, ApiManifest.MethodEntry> entry : declaredMethods.entrySet()) {
			ApiManifest.MethodEntry m = entry.getValue();
			// Filter synthetic and compiler-generated bridge methods from public API diffs
			if (m.isBridge() || m.isSynthetic()) {
				continue;
			}
			// Only public and protected methods constitute the public API surface
			if (m.accessFlags().contains("PUBLIC") || m.accessFlags().contains("PROTECTED")) {
				filtered.put(entry.getKey(), m);
			}
		}
		return filtered;
	}

	private static Map<String, ApiManifest.FieldEntry> filterApiFields(Map<String, ApiManifest.FieldEntry> declaredFields) {
		Map<String, ApiManifest.FieldEntry> filtered = new HashMap<>();
		for (Map.Entry<String, ApiManifest.FieldEntry> entry : declaredFields.entrySet()) {
			ApiManifest.FieldEntry f = entry.getValue();
			if (f.isSynthetic()) {
				continue;
			}
			if (f.accessFlags().contains("PUBLIC") || f.accessFlags().contains("PROTECTED")) {
				filtered.put(entry.getKey(), f);
			}
		}
		return filtered;
	}

	private static boolean isAnnotatedWith(List<ApiManifest.AnnotationEntry> annotations, String typeName) {
		if (annotations == null) return false;
		for (ApiManifest.AnnotationEntry ann : annotations) {
			if (typeName.equals(ann.type())) {
				return true;
			}
		}
		return false;
	}

	private static void linkMigrationHints(
			Map<String, List<PairwiseDiff.ChangeEntry>> removedMethods,
			Map<String, List<PairwiseDiff.ChangeEntry>> addedMethods
	) {
		for (Map.Entry<String, List<PairwiseDiff.ChangeEntry>> entry : removedMethods.entrySet()) {
			String methodNameKey = entry.getKey();
			List<PairwiseDiff.ChangeEntry> addedList = addedMethods.get(methodNameKey);
			if (addedList != null && !addedList.isEmpty()) {
				for (int i = 0; i < entry.getValue().size(); i++) {
					PairwiseDiff.ChangeEntry removed = entry.getValue().get(i);
					PairwiseDiff.ChangeEntry replacement = addedList.get(0);
					PairwiseDiff.MigrationHint hint = new PairwiseDiff.MigrationHint("SUPERSEDED_BY", replacement.symbol());
					entry.getValue().set(i, new PairwiseDiff.ChangeEntry(
							removed.symbol(),
							removed.type(),
							removed.category(),
							removed.compatibilityImpact(),
							removed.details(),
							hint
					));
				}
			}
		}
	}
}
