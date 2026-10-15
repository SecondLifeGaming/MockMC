package org.mockmc.metaminer.diff;

import org.mockmc.metaminer.manifest.ApiManifest;

import java.util.ArrayList;
import java.util.Collections;
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

	private static final String FLAG_PUBLIC = "PUBLIC";
	private static final String REASON_VISIBILITY_NARROWED = "visibility narrowed";
	private static final String DEPRECATED_ANNOTATION = "java.lang.Deprecated";

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

			if (fromClass != null && toClass == null) {
				// Class removed
				changes.add(new PairwiseDiff.ChangeEntry(
						className,
						PairwiseDiff.SymbolType.CLASS,
						PairwiseDiff.ChangeCategory.REMOVED,
						PairwiseDiff.CompatibilityImpact.BINARY_INCOMPATIBLE,
						new PairwiseDiff.Details(null, fromClass.accessFlags(), "Class removed upstream in " + toVer, null),
						null
				));
			} else if (fromClass == null && toClass != null) {
				// Class added
				changes.add(new PairwiseDiff.ChangeEntry(
						className,
						PairwiseDiff.SymbolType.CLASS,
						PairwiseDiff.ChangeCategory.ADDED,
						PairwiseDiff.CompatibilityImpact.COMPATIBLE_ADDITION,
						new PairwiseDiff.Details(null, toClass.accessFlags(), "Class added upstream in " + toVer, null),
						null
				));
			} else if (fromClass != null) {
				// Class present in both: compare class metadata, methods, and fields
				diffClassMetadata(className, fromClass, toClass, toVer, changes);
				diffClassMembers(className, fromClass, toClass, toVer, changes, removedMethodsByName, addedMethodsByName);
			}
		}

		// Link migration hints deterministically where overloads of the same name had old descriptors removed and new descriptors added
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
				case COMPATIBLE_MODIFICATION -> {
					// Compatible modifications are tracked in total changes but not in separate breaking counters
				}
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

	private static void diffClassMembers(
			String className,
			ApiManifest.ClassEntry fromClass,
			ApiManifest.ClassEntry toClass,
			String toVer,
			List<PairwiseDiff.ChangeEntry> changes,
			Map<String, List<PairwiseDiff.ChangeEntry>> removedMethodsByName,
			Map<String, List<PairwiseDiff.ChangeEntry>> addedMethodsByName
	) {
		boolean isInterface = "INTERFACE".equals(toClass.kind());
		MethodDiffContext ctx = new MethodDiffContext(className, isInterface, toVer, changes, removedMethodsByName, addedMethodsByName);
		diffMethods(fromClass, toClass, ctx);
		diffFields(className, fromClass, toClass, toVer, changes);
	}

	private static void diffClassMetadata(
			String className,
			ApiManifest.ClassEntry fromClass,
			ApiManifest.ClassEntry toClass,
			String toVer,
			List<PairwiseDiff.ChangeEntry> changes
	) {
		diffClassKindAndHierarchy(className, fromClass, toClass, toVer, changes);
		diffClassModifiersAndAnnotations(className, fromClass, toClass, toVer, changes);
	}

	private static void diffClassKindAndHierarchy(
			String className,
			ApiManifest.ClassEntry fromClass,
			ApiManifest.ClassEntry toClass,
			String toVer,
			List<PairwiseDiff.ChangeEntry> changes
	) {
		// 1. Kind changes (e.g. CLASS <-> INTERFACE <-> ENUM)
		if (!fromClass.kind().equals(toClass.kind())) {
			changes.add(new PairwiseDiff.ChangeEntry(
					className,
					PairwiseDiff.SymbolType.CLASS,
					PairwiseDiff.ChangeCategory.MODIFIED,
					PairwiseDiff.CompatibilityImpact.BINARY_INCOMPATIBLE,
					new PairwiseDiff.Details(
							null,
							toClass.accessFlags(),
							"Class kind changed from " + fromClass.kind() + " to " + toClass.kind() + " in " + toVer,
							fromClass.kind() + " -> " + toClass.kind()
					),
					null
			));
		}

		// 2. Superclass changes
		String fromSuper = fromClass.superClass();
		String toSuper = toClass.superClass();
		if (fromSuper != null && !fromSuper.equals(toSuper)) {
			changes.add(new PairwiseDiff.ChangeEntry(
					className,
					PairwiseDiff.SymbolType.CLASS,
					PairwiseDiff.ChangeCategory.MODIFIED,
					PairwiseDiff.CompatibilityImpact.BINARY_INCOMPATIBLE,
					new PairwiseDiff.Details(
							null,
							toClass.accessFlags(),
							"Superclass changed from " + fromSuper + " to " + toSuper + " in " + toVer,
							fromSuper + " -> " + toSuper
					),
					null
			));
		}

		// 3. Implemented interface changes
		Set<String> fromInterfaces = new HashSet<>(fromClass.interfaces());
		Set<String> toInterfaces = new HashSet<>(toClass.interfaces());
		for (String removedIface : fromInterfaces) {
			if (!toInterfaces.contains(removedIface)) {
				changes.add(new PairwiseDiff.ChangeEntry(
						className,
						PairwiseDiff.SymbolType.CLASS,
						PairwiseDiff.ChangeCategory.MODIFIED,
						PairwiseDiff.CompatibilityImpact.BINARY_INCOMPATIBLE,
						new PairwiseDiff.Details(
								null,
								toClass.accessFlags(),
								"No longer implements interface " + removedIface + " in " + toVer,
								"implements -" + removedIface
						),
						null
				));
			}
		}
		for (String addedIface : toInterfaces) {
			if (!fromInterfaces.contains(addedIface)) {
				changes.add(new PairwiseDiff.ChangeEntry(
						className,
						PairwiseDiff.SymbolType.CLASS,
						PairwiseDiff.ChangeCategory.MODIFIED,
						PairwiseDiff.CompatibilityImpact.COMPATIBLE_MODIFICATION,
						new PairwiseDiff.Details(
								null,
								toClass.accessFlags(),
								"Now implements interface " + addedIface + " in " + toVer,
								"implements +" + addedIface
						),
						null
				));
			}
		}
	}

	private static void diffClassModifiersAndAnnotations(
			String className,
			ApiManifest.ClassEntry fromClass,
			ApiManifest.ClassEntry toClass,
			String toVer,
			List<PairwiseDiff.ChangeEntry> changes
	) {
		// 4. Access flags / modifier changes (e.g. narrowed visibility or newly added ABSTRACT/FINAL)
		boolean wasPublic = fromClass.accessFlags().contains(FLAG_PUBLIC);
		boolean isPublic = toClass.accessFlags().contains(FLAG_PUBLIC);
		if (wasPublic && !isPublic) {
			changes.add(new PairwiseDiff.ChangeEntry(
					className,
					PairwiseDiff.SymbolType.CLASS,
					PairwiseDiff.ChangeCategory.MODIFIED,
					PairwiseDiff.CompatibilityImpact.BINARY_INCOMPATIBLE,
					new PairwiseDiff.Details(
							null,
							toClass.accessFlags(),
							"Class visibility narrowed from PUBLIC in " + toVer,
							REASON_VISIBILITY_NARROWED
					),
					null
			));
		}
		boolean wasAbstract = fromClass.accessFlags().contains("ABSTRACT");
		boolean isAbstract = toClass.accessFlags().contains("ABSTRACT");
		if (!wasAbstract && isAbstract && !"INTERFACE".equals(toClass.kind())) {
			changes.add(new PairwiseDiff.ChangeEntry(
					className,
					PairwiseDiff.SymbolType.CLASS,
					PairwiseDiff.ChangeCategory.MODIFIED,
					PairwiseDiff.CompatibilityImpact.BINARY_INCOMPATIBLE,
					new PairwiseDiff.Details(
							null,
							toClass.accessFlags(),
							"Class became ABSTRACT in " + toVer,
							"+ABSTRACT"
					),
					null
			));
		}

		// 5. Class deprecation
		boolean wasDeprecated = isAnnotatedWith(fromClass.annotations(), DEPRECATED_ANNOTATION);
		boolean isDeprecated = isAnnotatedWith(toClass.annotations(), DEPRECATED_ANNOTATION);
		if (!wasDeprecated && isDeprecated) {
			changes.add(new PairwiseDiff.ChangeEntry(
					className,
					PairwiseDiff.SymbolType.CLASS,
					PairwiseDiff.ChangeCategory.DEPRECATED,
					PairwiseDiff.CompatibilityImpact.NON_BREAKING_DEPRECATION,
					new PairwiseDiff.Details(null, toClass.accessFlags(), "Class marked @Deprecated in " + toVer, null),
					null
			));
		}
	}

	private record MethodDiffContext(
			String className,
			boolean isInterface,
			String toVer,
			List<PairwiseDiff.ChangeEntry> changes,
			Map<String, List<PairwiseDiff.ChangeEntry>> removedMethodsByName,
			Map<String, List<PairwiseDiff.ChangeEntry>> addedMethodsByName
	) {}

	private static void diffMethods(
			ApiManifest.ClassEntry fromClass,
			ApiManifest.ClassEntry toClass,
			MethodDiffContext ctx
	) {
		Map<String, ApiManifest.MethodEntry> fromMethods = filterApiMethods(fromClass.methods());
		Map<String, ApiManifest.MethodEntry> toMethods = filterApiMethods(toClass.methods());

		Set<String> allMethodKeys = new HashSet<>();
		allMethodKeys.addAll(fromMethods.keySet());
		allMethodKeys.addAll(toMethods.keySet());

		for (String methodKey : allMethodKeys) {
			ApiManifest.MethodEntry fromMethod = fromMethods.get(methodKey);
			ApiManifest.MethodEntry toMethod = toMethods.get(methodKey);
			diffSingleMethod(methodKey, fromMethod, toMethod, ctx);
		}
	}

	private static void diffSingleMethod(
			String methodKey,
			ApiManifest.MethodEntry fromMethod,
			ApiManifest.MethodEntry toMethod,
			MethodDiffContext ctx
	) {
		String symbol = ctx.className() + "#" + methodKey;

		if (fromMethod != null && toMethod == null) {
			PairwiseDiff.ChangeEntry entry = new PairwiseDiff.ChangeEntry(
					symbol,
					PairwiseDiff.SymbolType.METHOD,
					PairwiseDiff.ChangeCategory.REMOVED,
					PairwiseDiff.CompatibilityImpact.BINARY_INCOMPATIBLE,
					new PairwiseDiff.Details(fromMethod.descriptor(), fromMethod.accessFlags(), "Method removed upstream in " + ctx.toVer(), null),
					null
			);
			ctx.changes().add(entry);
			ctx.removedMethodsByName().computeIfAbsent(ctx.className() + "#" + fromMethod.name(), _ -> new ArrayList<>()).add(entry);
		} else if (fromMethod == null && toMethod != null) {
			PairwiseDiff.CompatibilityImpact impact = (ctx.isInterface() && !toMethod.isDefault())
					? PairwiseDiff.CompatibilityImpact.BINARY_INCOMPATIBLE
					: PairwiseDiff.CompatibilityImpact.COMPATIBLE_ADDITION;

			PairwiseDiff.ChangeEntry entry = new PairwiseDiff.ChangeEntry(
					symbol,
					PairwiseDiff.SymbolType.METHOD,
					PairwiseDiff.ChangeCategory.ADDED,
					impact,
					new PairwiseDiff.Details(toMethod.descriptor(), toMethod.accessFlags(), "Method added upstream in " + ctx.toVer(), null),
					null
			);
			ctx.changes().add(entry);
			ctx.addedMethodsByName().computeIfAbsent(ctx.className() + "#" + toMethod.name(), _ -> new ArrayList<>()).add(entry);
		} else if (fromMethod != null) {
			diffExistingMethodModifiers(symbol, fromMethod, toMethod, ctx);
		}
	}

	private static void diffExistingMethodModifiers(
			String symbol,
			ApiManifest.MethodEntry fromMethod,
			ApiManifest.MethodEntry toMethod,
			MethodDiffContext ctx
	) {
		boolean wasPublic = fromMethod.accessFlags().contains(FLAG_PUBLIC);
		boolean isPublic = toMethod.accessFlags().contains(FLAG_PUBLIC);
		if (wasPublic && !isPublic) {
			ctx.changes().add(new PairwiseDiff.ChangeEntry(
					symbol,
					PairwiseDiff.SymbolType.METHOD,
					PairwiseDiff.ChangeCategory.MODIFIED,
					PairwiseDiff.CompatibilityImpact.BINARY_INCOMPATIBLE,
					new PairwiseDiff.Details(toMethod.descriptor(), toMethod.accessFlags(), "Method visibility narrowed in " + ctx.toVer(), REASON_VISIBILITY_NARROWED),
					null
			));
		}

		boolean wasDeprecated = isAnnotatedWith(fromMethod.annotations(), DEPRECATED_ANNOTATION);
		boolean isDeprecated = isAnnotatedWith(toMethod.annotations(), DEPRECATED_ANNOTATION);
		if (!wasDeprecated && isDeprecated) {
			ctx.changes().add(new PairwiseDiff.ChangeEntry(
					symbol,
					PairwiseDiff.SymbolType.METHOD,
					PairwiseDiff.ChangeCategory.DEPRECATED,
					PairwiseDiff.CompatibilityImpact.NON_BREAKING_DEPRECATION,
					new PairwiseDiff.Details(toMethod.descriptor(), toMethod.accessFlags(), "Marked @Deprecated in " + ctx.toVer(), null),
					null
			));
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

		Map<String, List<ApiManifest.FieldEntry>> fromFieldsByName = groupFieldsByName(fromFields);
		Map<String, List<ApiManifest.FieldEntry>> toFieldsByName = groupFieldsByName(toFields);

		Set<String> allFieldNames = new HashSet<>();
		allFieldNames.addAll(fromFieldsByName.keySet());
		allFieldNames.addAll(toFieldsByName.keySet());

		for (String fieldName : allFieldNames) {
			List<ApiManifest.FieldEntry> fromList = fromFieldsByName.getOrDefault(fieldName, List.of());
			List<ApiManifest.FieldEntry> toList = toFieldsByName.getOrDefault(fieldName, List.of());
			diffFieldGroup(className, fieldName, fromList, toList, toVer, changes);
		}
	}

	private static Map<String, List<ApiManifest.FieldEntry>> groupFieldsByName(Map<String, ApiManifest.FieldEntry> fields) {
		Map<String, List<ApiManifest.FieldEntry>> map = new HashMap<>();
		for (ApiManifest.FieldEntry f : fields.values()) {
			map.computeIfAbsent(f.name(), _ -> new ArrayList<>()).add(f);
		}
		return map;
	}

	private static void diffFieldGroup(
			String className,
			String fieldName,
			List<ApiManifest.FieldEntry> fromList,
			List<ApiManifest.FieldEntry> toList,
			String toVer,
			List<PairwiseDiff.ChangeEntry> changes
	) {
		if (toList.isEmpty()) {
			for (ApiManifest.FieldEntry f : fromList) {
				String symbol = className + "#" + f.name() + ":" + f.descriptor();
				changes.add(new PairwiseDiff.ChangeEntry(
						symbol,
						PairwiseDiff.SymbolType.FIELD,
						PairwiseDiff.ChangeCategory.REMOVED,
						PairwiseDiff.CompatibilityImpact.BINARY_INCOMPATIBLE,
						new PairwiseDiff.Details(f.descriptor(), f.accessFlags(), "Field removed upstream in " + toVer, null),
						null
				));
			}
		} else if (fromList.isEmpty()) {
			for (ApiManifest.FieldEntry f : toList) {
				String symbol = className + "#" + f.name() + ":" + f.descriptor();
				changes.add(new PairwiseDiff.ChangeEntry(
						symbol,
						PairwiseDiff.SymbolType.FIELD,
						PairwiseDiff.ChangeCategory.ADDED,
						PairwiseDiff.CompatibilityImpact.COMPATIBLE_ADDITION,
						new PairwiseDiff.Details(f.descriptor(), f.accessFlags(), "Field added upstream in " + toVer, null),
						null
				));
			}
		} else {
			diffMatchingFieldsByDesc(className, fieldName, fromList, toList, toVer, changes);
		}
	}

	private static void diffMatchingFieldsByDesc(
			String className,
			String fieldName,
			List<ApiManifest.FieldEntry> fromList,
			List<ApiManifest.FieldEntry> toList,
			String toVer,
			List<PairwiseDiff.ChangeEntry> changes
	) {
		Map<String, ApiManifest.FieldEntry> fromByDesc = new HashMap<>();
		for (ApiManifest.FieldEntry f : fromList) fromByDesc.put(f.descriptor(), f);
		Map<String, ApiManifest.FieldEntry> toByDesc = new HashMap<>();
		for (ApiManifest.FieldEntry f : toList) toByDesc.put(f.descriptor(), f);

		Set<String> allDescs = new HashSet<>();
		allDescs.addAll(fromByDesc.keySet());
		allDescs.addAll(toByDesc.keySet());

		for (String desc : allDescs) {
			ApiManifest.FieldEntry fFrom = fromByDesc.get(desc);
			ApiManifest.FieldEntry fTo = toByDesc.get(desc);
			String symbol = className + "#" + fieldName + ":" + desc;
			diffSingleField(symbol, desc, fFrom, fTo, toVer, changes);
		}
	}

	private static void diffSingleField(
			String symbol,
			String desc,
			ApiManifest.FieldEntry fFrom,
			ApiManifest.FieldEntry fTo,
			String toVer,
			List<PairwiseDiff.ChangeEntry> changes
	) {
		if (fFrom != null && fTo == null) {
			changes.add(new PairwiseDiff.ChangeEntry(
					symbol,
					PairwiseDiff.SymbolType.FIELD,
					PairwiseDiff.ChangeCategory.REMOVED,
					PairwiseDiff.CompatibilityImpact.BINARY_INCOMPATIBLE,
					new PairwiseDiff.Details(fFrom.descriptor(), fFrom.accessFlags(), "Field descriptor removed in " + toVer, null),
					null
			));
		} else if (fFrom == null && fTo != null) {
			changes.add(new PairwiseDiff.ChangeEntry(
					symbol,
					PairwiseDiff.SymbolType.FIELD,
					PairwiseDiff.ChangeCategory.ADDED,
					PairwiseDiff.CompatibilityImpact.COMPATIBLE_ADDITION,
					new PairwiseDiff.Details(fTo.descriptor(), fTo.accessFlags(), "Field descriptor added in " + toVer, null),
					null
			));
		} else if (fFrom != null) {
			diffExistingFieldModifiers(symbol, desc, fFrom, fTo, toVer, changes);
		}
	}

	private static void diffExistingFieldModifiers(
			String symbol,
			String desc,
			ApiManifest.FieldEntry fFrom,
			ApiManifest.FieldEntry fTo,
			String toVer,
			List<PairwiseDiff.ChangeEntry> changes
	) {
		boolean wasPublic = fFrom.accessFlags().contains(FLAG_PUBLIC);
		boolean isPublic = fTo.accessFlags().contains(FLAG_PUBLIC);
		if (wasPublic && !isPublic) {
			changes.add(new PairwiseDiff.ChangeEntry(
					symbol,
					PairwiseDiff.SymbolType.FIELD,
					PairwiseDiff.ChangeCategory.MODIFIED,
					PairwiseDiff.CompatibilityImpact.BINARY_INCOMPATIBLE,
					new PairwiseDiff.Details(desc, fTo.accessFlags(), "Field visibility narrowed in " + toVer, REASON_VISIBILITY_NARROWED),
					null
			));
		}
		boolean wasStatic = fFrom.accessFlags().contains("STATIC");
		boolean isStatic = fTo.accessFlags().contains("STATIC");
		if (wasStatic != isStatic) {
			changes.add(new PairwiseDiff.ChangeEntry(
					symbol,
					PairwiseDiff.SymbolType.FIELD,
					PairwiseDiff.ChangeCategory.MODIFIED,
					PairwiseDiff.CompatibilityImpact.BINARY_INCOMPATIBLE,
					new PairwiseDiff.Details(desc, fTo.accessFlags(), "Field STATIC modifier changed in " + toVer, wasStatic ? "-STATIC" : "+STATIC"),
					null
			));
		}
		boolean wasFinal = fFrom.accessFlags().contains("FINAL");
		boolean isFinal = fTo.accessFlags().contains("FINAL");
		if (!wasFinal && isFinal) {
			changes.add(new PairwiseDiff.ChangeEntry(
					symbol,
					PairwiseDiff.SymbolType.FIELD,
					PairwiseDiff.ChangeCategory.MODIFIED,
					PairwiseDiff.CompatibilityImpact.BINARY_INCOMPATIBLE,
					new PairwiseDiff.Details(desc, fTo.accessFlags(), "Field became FINAL in " + toVer, "+FINAL"),
					null
			));
		}
		boolean wasDep = isAnnotatedWith(fFrom.annotations(), DEPRECATED_ANNOTATION);
		boolean isDep = isAnnotatedWith(fTo.annotations(), DEPRECATED_ANNOTATION);
		if (!wasDep && isDep) {
			changes.add(new PairwiseDiff.ChangeEntry(
					symbol,
					PairwiseDiff.SymbolType.FIELD,
					PairwiseDiff.ChangeCategory.DEPRECATED,
					PairwiseDiff.CompatibilityImpact.NON_BREAKING_DEPRECATION,
					new PairwiseDiff.Details(desc, fTo.accessFlags(), "Field marked @Deprecated in " + toVer, null),
					null
			));
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
			if (m.accessFlags().contains(FLAG_PUBLIC) || m.accessFlags().contains("PROTECTED")) {
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
			if (f.accessFlags().contains(FLAG_PUBLIC) || f.accessFlags().contains("PROTECTED")) {
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
		// Sort keys to ensure deterministic processing
		List<String> sortedMethodKeys = new ArrayList<>(removedMethods.keySet());
		Collections.sort(sortedMethodKeys);

		for (String methodNameKey : sortedMethodKeys) {
			List<PairwiseDiff.ChangeEntry> addedList = addedMethods.get(methodNameKey);
			if (addedList == null || addedList.isEmpty()) {
				continue;
			}

			// Sort candidate additions deterministically by symbol
			List<PairwiseDiff.ChangeEntry> sortedAdded = new ArrayList<>(addedList);
			sortedAdded.sort(Comparator.comparing(PairwiseDiff.ChangeEntry::symbol));

			List<PairwiseDiff.ChangeEntry> removedList = removedMethods.get(methodNameKey);
			for (int i = 0; i < removedList.size(); i++) {
				PairwiseDiff.ChangeEntry removed = removedList.get(i);
				// Unambiguous 1-to-1 replacement
				if (sortedAdded.size() == 1) {
					PairwiseDiff.ChangeEntry replacement = sortedAdded.get(0);
					PairwiseDiff.MigrationHint hint = new PairwiseDiff.MigrationHint("SUPERSEDED_BY", replacement.symbol());
					removedList.set(i, new PairwiseDiff.ChangeEntry(
							removed.symbol(),
							removed.type(),
							removed.category(),
							removed.compatibilityImpact(),
							removed.details(),
							hint
					));
				}
				// When ambiguous (multiple additions for same method name), do not arbitrarily link.
			}
		}
	}
}
