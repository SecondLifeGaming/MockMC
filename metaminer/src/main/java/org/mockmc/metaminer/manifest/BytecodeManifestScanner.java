package org.mockmc.metaminer.manifest;

import org.objectweb.asm.ClassReader;
import org.objectweb.asm.Opcodes;
import org.objectweb.asm.Type;
import org.objectweb.asm.tree.AnnotationNode;
import org.objectweb.asm.tree.ClassNode;
import org.objectweb.asm.tree.FieldNode;
import org.objectweb.asm.tree.MethodNode;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Enumeration;
import java.util.HexFormat;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

/**
 * Bytecode manifest scanner that parses upstream platform JARs into canonical ApiManifest structures.
 * Enforces:
 * 1. Complete class disposition accounting (ANALYZED, JUSTIFIED_EXCLUSION, FAILED).
 * 2. Member-level completeness invariant (100% of declared members parsed or marked FAILED).
 * 3. Scoped fallback tagging (e.g., Folia 26.2 for threaded regions).
 */
public class BytecodeManifestScanner {

	public record ScanConfiguration(
			String platform,
			String version,
			int build,
			String tier,
			String scannerVersion,
			String gitCommit,
			boolean dirty,
			String mappingSource,
			String mappingSha256,
			String asmVersion,
			List<String> packageRoots,
			List<String> scopedPackagePrefixes,
			List<ApiManifest.ExclusionRule> justifiedExclusions,
			boolean isPinnedFallback,
			String fallbackReason
	) {}

	public static ApiManifest scanJar(File jarFile, ScanConfiguration config) throws IOException {
		String sourceSha256 = computeFileSha256(jarFile);
		long sourceByteLength = jarFile.length();

		ApiManifest.Provenance provenance = new ApiManifest.Provenance(
				config.platform(),
				config.version(),
				config.build(),
				config.tier(),
				new ApiManifest.SourceJar(jarFile.getName(), sourceSha256, sourceByteLength),
				new ApiManifest.ScannerInfo(config.scannerVersion(), config.gitCommit(), config.dirty()),
				new ApiManifest.RemappingInfo(config.mappingSource(), config.mappingSha256(), config.asmVersion())
		);

		Map<String, ApiManifest.ClassEntry> classes = new TreeMap<>();
		Map<String, ApiManifest.ClassDisposition> dispositionInventory = new TreeMap<>();
		List<ApiManifest.FailedEntry> failedEntries = new ArrayList<>();

		int totalDiscovered = 0;
		int totalAnalyzed = 0;
		int totalExcluded = 0;
		int totalMethodsAnalyzed = 0;
		int totalFieldsAnalyzed = 0;

		try (JarFile jar = new JarFile(jarFile)) {
			Enumeration<JarEntry> entries = jar.entries();
			while (entries.hasMoreElements()) {
				JarEntry entry = entries.nextElement();
				String name = entry.getName();
				if (!name.endsWith(".class") || name.startsWith("META-INF/")) {
					continue;
				}

				String className = name.substring(0, name.length() - ".class".length()).replace('/', '.');
				if (!isInScanRoots(className, config.packageRoots())) {
					continue;
				}

				totalDiscovered++;

				// Check exclusions
				ApiManifest.ExclusionRule matchedRule = findExclusionRule(className, config.justifiedExclusions());
				if (matchedRule != null) {
					totalExcluded++;
					dispositionInventory.put(className, new ApiManifest.ClassDisposition(
							ApiManifest.DispositionType.JUSTIFIED_EXCLUSION,
							null,
							null,
							matchedRule.pattern(),
							matchedRule.category()
					));
					continue;
				}

				// Check scoped prefixes (e.g. Folia extension scope)
				if (!config.scopedPackagePrefixes().isEmpty() && !isInScopedPrefixes(className, config.scopedPackagePrefixes())) {
					totalExcluded++;
					dispositionInventory.put(className, new ApiManifest.ClassDisposition(
							ApiManifest.DispositionType.JUSTIFIED_EXCLUSION,
							null,
							null,
							"scoped-prefix-filter",
							"OUT_OF_PLATFORM_SCOPE"
					));
					continue;
				}

				// Parse class
				try (InputStream in = jar.getInputStream(entry)) {
					ClassNode classNode = new ClassNode();
					ClassReader reader = new ClassReader(in);
					reader.accept(classNode, 0);

					Map<String, ApiManifest.MethodEntry> methods = new TreeMap<>();
					for (MethodNode mn : classNode.methods) {
						ApiManifest.MethodEntry methodEntry = parseMethod(classNode, mn, config);
						methods.put(mn.name + mn.desc, methodEntry);
					}

					Map<String, ApiManifest.FieldEntry> fields = new TreeMap<>();
					for (FieldNode fn : classNode.fields) {
						ApiManifest.FieldEntry fieldEntry = parseField(classNode, fn, config);
						fields.put(fn.name, fieldEntry);
					}

					String kind = determineClassKind(classNode.access);
					List<String> accessFlags = parseAccessFlags(classNode.access, true);
					String superClass = classNode.superName != null ? classNode.superName.replace('/', '.') : null;
					List<String> interfaces = new ArrayList<>();
					for (String iface : classNode.interfaces) {
						interfaces.add(iface.replace('/', '.'));
					}
					Collections.sort(interfaces);

					List<ApiManifest.AnnotationEntry> annotations = parseAnnotations(classNode.visibleAnnotations);

					ApiManifest.ClassEntry classEntry = new ApiManifest.ClassEntry(
							kind,
							accessFlags,
							classNode.signature,
							superClass,
							interfaces,
							annotations,
							methods,
							fields
					);

					classes.put(className, classEntry);
					totalAnalyzed++;
					totalMethodsAnalyzed += methods.size();
					totalFieldsAnalyzed += fields.size();

					dispositionInventory.put(className, new ApiManifest.ClassDisposition(
							ApiManifest.DispositionType.ANALYZED,
							methods.size(),
							fields.size(),
							null,
							null
					));
				} catch (Throwable t) {
					failedEntries.add(new ApiManifest.FailedEntry(
							className,
							"PARSE_ERROR",
							t.getClass().getName() + ": " + t.getMessage()
					));
					dispositionInventory.put(className, new ApiManifest.ClassDisposition(
							ApiManifest.DispositionType.FAILED,
							null,
							null,
							null,
							null
					));
				}
			}
		}

		int totalFailed = failedEntries.size();
		String status = totalFailed == 0 ? "COMPLETE" : "INCOMPLETE";

		ApiManifest.ScanAccounting accounting = new ApiManifest.ScanAccounting(
				status,
				config.packageRoots(),
				config.scopedPackagePrefixes(),
				totalDiscovered,
				totalAnalyzed,
				totalExcluded,
				totalFailed,
				totalMethodsAnalyzed,
				totalFieldsAnalyzed,
				config.justifiedExclusions(),
				dispositionInventory,
				failedEntries
		);

		return new ApiManifest(
				ApiManifest.CURRENT_SCHEMA,
				ApiManifest.CURRENT_SCHEMA_VERSION,
				provenance,
				accounting,
				classes
		);
	}

	private static ApiManifest.MethodEntry parseMethod(ClassNode cn, MethodNode mn, ScanConfiguration config) {
		String returnType = Type.getReturnType(mn.desc).getClassName();
		Type[] argTypes = Type.getArgumentTypes(mn.desc);
		List<ApiManifest.ParameterEntry> parameters = new ArrayList<>();
		for (int i = 0; i < argTypes.length; i++) {
			String paramName = "arg" + i;
			if (mn.parameters != null && i < mn.parameters.size() && mn.parameters.get(i).name != null) {
				paramName = mn.parameters.get(i).name;
			}
			parameters.add(new ApiManifest.ParameterEntry(i, paramName, argTypes[i].getClassName()));
		}

		List<String> exceptions = new ArrayList<>();
		if (mn.exceptions != null) {
			for (String ex : mn.exceptions) {
				exceptions.add(ex.replace('/', '.'));
			}
			Collections.sort(exceptions);
		}

		List<String> accessFlags = parseAccessFlags(mn.access, false);
		boolean isBridge = (mn.access & Opcodes.ACC_BRIDGE) != 0;
		boolean isSynthetic = (mn.access & Opcodes.ACC_SYNTHETIC) != 0;
		boolean isDefault = (cn.access & Opcodes.ACC_INTERFACE) != 0
				&& (mn.access & (Opcodes.ACC_ABSTRACT | Opcodes.ACC_STATIC)) == 0;

		List<ApiManifest.AnnotationEntry> annotations = parseAnnotations(mn.visibleAnnotations);

		ApiManifest.SymbolProvenance provenance = new ApiManifest.SymbolProvenance(
				config.platform(),
				config.version(),
				config.platform(),
				config.version(),
				config.isPinnedFallback(),
				config.fallbackReason()
		);

		return new ApiManifest.MethodEntry(
				mn.name,
				mn.desc,
				mn.signature,
				returnType,
				parameters,
				exceptions,
				accessFlags,
				isBridge,
				isSynthetic,
				isDefault,
				annotations,
				provenance
		);
	}

	private static ApiManifest.FieldEntry parseField(ClassNode cn, FieldNode fn, ScanConfiguration config) {
		String fieldType = Type.getType(fn.desc).getClassName();
		List<String> accessFlags = parseAccessFlags(fn.access, false);
		boolean isSynthetic = (fn.access & Opcodes.ACC_SYNTHETIC) != 0;
		List<ApiManifest.AnnotationEntry> annotations = parseAnnotations(fn.visibleAnnotations);

		ApiManifest.SymbolProvenance provenance = new ApiManifest.SymbolProvenance(
				config.platform(),
				config.version(),
				config.platform(),
				config.version(),
				config.isPinnedFallback(),
				config.fallbackReason()
		);

		return new ApiManifest.FieldEntry(
				fn.name,
				fn.desc,
				fn.signature,
				fieldType,
				accessFlags,
				isSynthetic,
				fn.value,
				annotations,
				provenance
		);
	}

	private static List<ApiManifest.AnnotationEntry> parseAnnotations(List<AnnotationNode> visibleAnnotations) {
		if (visibleAnnotations == null || visibleAnnotations.isEmpty()) {
			return List.of();
		}
		List<ApiManifest.AnnotationEntry> list = new ArrayList<>();
		for (AnnotationNode an : visibleAnnotations) {
			String annType = Type.getType(an.desc).getClassName();
			Map<String, Object> values = new LinkedHashMap<>();
			if (an.values != null) {
				for (int i = 0; i < an.values.size(); i += 2) {
					String key = (String) an.values.get(i);
					Object val = an.values.get(i + 1);
					values.put(key, val);
				}
			}
			list.add(new ApiManifest.AnnotationEntry(annType, values));
		}
		list.sort((a, b) -> a.type().compareTo(b.type()));
		return list;
	}

	private static boolean isInScanRoots(String className, List<String> packageRoots) {
		if (packageRoots == null || packageRoots.isEmpty()) {
			return true;
		}
		for (String root : packageRoots) {
			if (className.startsWith(root + ".") || className.equals(root)) {
				return true;
			}
		}
		return false;
	}

	private static boolean isInScopedPrefixes(String className, List<String> scopedPrefixes) {
		for (String prefix : scopedPrefixes) {
			if (className.startsWith(prefix + ".") || className.equals(prefix)) {
				return true;
			}
		}
		return false;
	}

	private static ApiManifest.ExclusionRule findExclusionRule(String className, List<ApiManifest.ExclusionRule> rules) {
		if (rules == null) {
			return null;
		}
		for (ApiManifest.ExclusionRule rule : rules) {
			String pattern = rule.pattern();
			if (pattern.endsWith(".**")) {
				String prefix = pattern.substring(0, pattern.length() - 3);
				if (className.startsWith(prefix + ".") || className.equals(prefix)) {
					return rule;
				}
			} else if (pattern.endsWith(".*")) {
				String prefix = pattern.substring(0, pattern.length() - 2);
				if (className.startsWith(prefix + ".") && !className.substring(prefix.length() + 1).contains(".")) {
					return rule;
				}
			} else if (pattern.equals(className)) {
				return rule;
			}
		}
		return null;
	}

	private static String determineClassKind(int access) {
		if ((access & Opcodes.ACC_ANNOTATION) != 0) {
			return "ANNOTATION";
		}
		if ((access & Opcodes.ACC_INTERFACE) != 0) {
			return "INTERFACE";
		}
		if ((access & Opcodes.ACC_ENUM) != 0) {
			return "ENUM";
		}
		if ((access & Opcodes.ACC_RECORD) != 0) {
			return "RECORD";
		}
		return "CLASS";
	}

	private static List<String> parseAccessFlags(int access, boolean isClass) {
		List<String> flags = new ArrayList<>();
		if ((access & Opcodes.ACC_PUBLIC) != 0) flags.add("PUBLIC");
		if ((access & Opcodes.ACC_PROTECTED) != 0) flags.add("PROTECTED");
		if ((access & Opcodes.ACC_PRIVATE) != 0) flags.add("PRIVATE");
		if ((access & Opcodes.ACC_STATIC) != 0) flags.add("STATIC");
		if ((access & Opcodes.ACC_FINAL) != 0) flags.add("FINAL");
		if ((access & Opcodes.ACC_ABSTRACT) != 0 && (!isClass || (access & Opcodes.ACC_INTERFACE) == 0)) flags.add("ABSTRACT");
		if (isClass && (access & Opcodes.ACC_INTERFACE) != 0) flags.add("INTERFACE");
		Collections.sort(flags);
		return flags;
	}

	private static String computeFileSha256(File file) throws IOException {
		try (InputStream in = new FileInputStream(file)) {
			MessageDigest digest = MessageDigest.getInstance("SHA-256");
			byte[] buffer = new byte[8192];
			int read;
			while ((read = in.read(buffer)) != -1) {
				digest.update(buffer, 0, read);
			}
			return HexFormat.of().formatHex(digest.digest());
		} catch (NoSuchAlgorithmException e) {
			throw new IllegalStateException("SHA-256 algorithm missing", e);
		}
	}
}
