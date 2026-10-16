package org.mockmc.metaminer.diff;

import org.junit.jupiter.api.Test;
import org.mockmc.metaminer.manifest.ApiManifest;

import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

class VersionDiffEngineTest {

	@Test
	void testBinaryIncompatibleMethodRemovalAndCompatibleAddition() {
		ApiManifest.Provenance prov262 = new ApiManifest.Provenance(
				"paper", "26.2", 40, "canonical",
				new ApiManifest.SourceJar("paper-26.2.jar", "sha1", 100),
				new ApiManifest.ScannerInfo("metaminer", "commit1", false),
				new ApiManifest.RemappingInfo("none", "none", "ASM9_8")
		);

		ApiManifest.Provenance prov263 = new ApiManifest.Provenance(
				"paper", "26.3", 157, "canonical",
				new ApiManifest.SourceJar("paper-26.3.jar", "sha2", 100),
				new ApiManifest.ScannerInfo("metaminer", "commit2", false),
				new ApiManifest.RemappingInfo("none", "none", "ASM9_8")
		);

		ApiManifest.ScanAccounting emptyAccounting = new ApiManifest.ScanAccounting(
				"COMPLETE", List.of(), List.of(), 1, 1, 0, 0, 1, 0, List.of(), Map.of(), List.of()
		);

		// Legacy method in 26.2: sendTitle(String, String)
		ApiManifest.MethodEntry legacyMethod = new ApiManifest.MethodEntry(
				"sendTitle",
				"(Ljava/lang/String;Ljava/lang/String;)V",
				null,
				"void",
				List.of(),
				List.of(),
				List.of("PUBLIC"),
				false,
				false,
				false,
				List.of(),
				new ApiManifest.SymbolProvenance("paper", "26.2", "paper", "26.2", false, null)
		);

		// Modern method in 26.3: sendTitle(Title)
		ApiManifest.MethodEntry modernMethod = new ApiManifest.MethodEntry(
				"sendTitle",
				"(Lnet/kyori/adventure/title/Title;)V",
				null,
				"void",
				List.of(),
				List.of(),
				List.of("PUBLIC"),
				false,
				false,
				false,
				List.of(),
				new ApiManifest.SymbolProvenance("paper", "26.3", "paper", "26.3", false, null)
		);

		// Deprecated method present in both: getLegacyName() marked @Deprecated in 26.3
		ApiManifest.MethodEntry deprecatedIn262 = new ApiManifest.MethodEntry(
				"getLegacyName", "()Ljava/lang/String;", null, "java.lang.String",
				List.of(), List.of(), List.of("PUBLIC"), false, false, false, List.of(),
				new ApiManifest.SymbolProvenance("paper", "26.2", "paper", "26.2", false, null)
		);
		ApiManifest.MethodEntry deprecatedIn263 = new ApiManifest.MethodEntry(
				"getLegacyName", "()Ljava/lang/String;", null, "java.lang.String",
				List.of(), List.of(), List.of("PUBLIC"), false, false, false,
				List.of(new ApiManifest.AnnotationEntry("java.lang.Deprecated", Map.of())),
				new ApiManifest.SymbolProvenance("paper", "26.3", "paper", "26.3", false, null)
		);

		// Bridge method in 26.3 (must be filtered out)
		ApiManifest.MethodEntry bridgeMethod = new ApiManifest.MethodEntry(
				"sendTitle",
				"(Ljava/lang/Object;)V",
				null,
				"void",
				List.of(),
				List.of(),
				List.of("PUBLIC"),
				true, // isBridge = true
				false,
				false,
				List.of(),
				new ApiManifest.SymbolProvenance("paper", "26.3", "paper", "26.3", false, null)
		);

		ApiManifest.ClassEntry class262 = new ApiManifest.ClassEntry(
				"INTERFACE",
				List.of("PUBLIC", "INTERFACE", "ABSTRACT"),
				null,
				"java.lang.Object",
				List.of(),
				List.of(),
				Map.of(
						"sendTitle(Ljava/lang/String;Ljava/lang/String;)V", legacyMethod,
						"getLegacyName()Ljava/lang/String;", deprecatedIn262
				),
				Map.of()
		);

		ApiManifest.ClassEntry class263 = new ApiManifest.ClassEntry(
				"INTERFACE",
				List.of("PUBLIC", "INTERFACE", "ABSTRACT"),
				null,
				"java.lang.Object",
				List.of(),
				List.of(),
				Map.of(
						"sendTitle(Lnet/kyori/adventure/title/Title;)V", modernMethod,
						"getLegacyName()Ljava/lang/String;", deprecatedIn263,
						"sendTitle(Ljava/lang/Object;)V", bridgeMethod
				),
				Map.of()
		);

		ApiManifest manifest262 = new ApiManifest(
				ApiManifest.CURRENT_SCHEMA, ApiManifest.CURRENT_SCHEMA_VERSION, prov262, emptyAccounting,
				Map.of("org.bukkit.entity.Player", class262)
		);

		ApiManifest manifest263 = new ApiManifest(
				ApiManifest.CURRENT_SCHEMA, ApiManifest.CURRENT_SCHEMA_VERSION, prov263, emptyAccounting,
				Map.of("org.bukkit.entity.Player", class263)
		);

		PairwiseDiff diff = VersionDiffEngine.computeDiff(manifest262, manifest263);

		assertNotNull(diff);
		assertEquals("paper", diff.platform());
		assertEquals("26.2", diff.fromVersion());
		assertEquals("26.3", diff.toVersion());

		// Changes should include:
		// 1. sendTitle(String, String) -> REMOVED (BINARY_INCOMPATIBLE)
		// 2. sendTitle(Title) -> ADDED (to interface without default -> BINARY_INCOMPATIBLE)
		// 3. getLegacyName() -> DEPRECATED (NON_BREAKING_DEPRECATION)
		// Bridge method must NOT be present in changes
		boolean hasBridgeMethod = diff.changes().stream().anyMatch(c -> c.symbol().contains("(Ljava/lang/Object;)V"));
		assertEquals(false, hasBridgeMethod, "Compiler bridge methods must be filtered out");

		boolean hasLegacyRemoval = diff.changes().stream().anyMatch(c ->
				c.symbol().equals("org.bukkit.entity.Player#sendTitle(Ljava/lang/String;Ljava/lang/String;)V")
						&& c.category() == PairwiseDiff.ChangeCategory.REMOVED
						&& c.compatibilityImpact() == PairwiseDiff.CompatibilityImpact.BINARY_INCOMPATIBLE);
		assertTrue(hasLegacyRemoval, "Legacy method removal must be categorized as BINARY_INCOMPATIBLE");

		boolean hasModernAddition = diff.changes().stream().anyMatch(c ->
				c.symbol().equals("org.bukkit.entity.Player#sendTitle(Lnet/kyori/adventure/title/Title;)V")
						&& c.category() == PairwiseDiff.ChangeCategory.ADDED);
		assertTrue(hasModernAddition, "Modern method addition must be recorded");

		boolean hasDeprecation = diff.changes().stream().anyMatch(c ->
				c.symbol().equals("org.bukkit.entity.Player#getLegacyName()Ljava/lang/String;")
						&& c.category() == PairwiseDiff.ChangeCategory.DEPRECATED
						&& c.compatibilityImpact() == PairwiseDiff.CompatibilityImpact.NON_BREAKING_DEPRECATION);
		assertTrue(hasDeprecation, "Added deprecation must be categorized as NON_BREAKING_DEPRECATION");

		assertEquals(3, diff.changes().size());
	}

	@Test
	void testClassMetadataAndFieldDiffing() {
		ApiManifest.Provenance prov1 = new ApiManifest.Provenance(
				"paper", "1.0", 1, "canonical",
				new ApiManifest.SourceJar("paper-1.0.jar", "sha1", 100),
				new ApiManifest.ScannerInfo("metaminer", "commit1", false),
				new ApiManifest.RemappingInfo("none", "none", "ASM9_8")
		);
		ApiManifest.Provenance prov2 = new ApiManifest.Provenance(
				"paper", "2.0", 2, "canonical",
				new ApiManifest.SourceJar("paper-2.0.jar", "sha2", 100),
				new ApiManifest.ScannerInfo("metaminer", "commit2", false),
				new ApiManifest.RemappingInfo("none", "none", "ASM9_8")
		);
		ApiManifest.ScanAccounting emptyAccounting = new ApiManifest.ScanAccounting(
				"COMPLETE", List.of(), List.of(), 1, 1, 0, 0, 0, 2, List.of(), Map.of(), List.of()
		);

		// Field in v1: int COUNT
		ApiManifest.FieldEntry countFieldV1 = new ApiManifest.FieldEntry(
				"COUNT", "I", null, "int", List.of("PUBLIC", "STATIC"), false, 0, List.of(),
				new ApiManifest.SymbolProvenance("paper", "1.0", "paper", "1.0", false, null)
		);
		// Field in v2: long COUNT (type/descriptor changed)
		ApiManifest.FieldEntry countFieldV2 = new ApiManifest.FieldEntry(
				"COUNT", "J", null, "long", List.of("PUBLIC", "STATIC"), false, 0L, List.of(),
				new ApiManifest.SymbolProvenance("paper", "2.0", "paper", "2.0", false, null)
		);

		// Field in v1: public String NAME
		ApiManifest.FieldEntry nameFieldV1 = new ApiManifest.FieldEntry(
				"NAME", "Ljava/lang/String;", null, "java.lang.String", List.of("PUBLIC"), false, null, List.of(),
				new ApiManifest.SymbolProvenance("paper", "1.0", "paper", "1.0", false, null)
		);
		// Field in v2: public final String NAME (became final)
		ApiManifest.FieldEntry nameFieldV2 = new ApiManifest.FieldEntry(
				"NAME", "Ljava/lang/String;", null, "java.lang.String", List.of("PUBLIC", "FINAL"), false, null, List.of(),
				new ApiManifest.SymbolProvenance("paper", "2.0", "paper", "2.0", false, null)
		);

		// Class in v1: class TestClass implements AutoCloseable
		ApiManifest.ClassEntry classV1 = new ApiManifest.ClassEntry(
				"CLASS",
				List.of("PUBLIC"),
				null,
				"java.lang.Object",
				List.of("java.lang.AutoCloseable"),
				List.of(),
				Map.of(),
				Map.of("COUNT", countFieldV1, "NAME", nameFieldV1)
		);

		// Class in v2: class TestClass extends Number (superclass changed, interface removed)
		ApiManifest.ClassEntry classV2 = new ApiManifest.ClassEntry(
				"CLASS",
				List.of("PUBLIC"),
				null,
				"java.lang.Number",
				List.of(), // removed AutoCloseable
				List.of(),
				Map.of(),
				Map.of("COUNT", countFieldV2, "NAME", nameFieldV2)
		);

		ApiManifest manifest1 = new ApiManifest(ApiManifest.CURRENT_SCHEMA, ApiManifest.CURRENT_SCHEMA_VERSION, prov1, emptyAccounting, Map.of("org.bukkit.TestClass", classV1));
		ApiManifest manifest2 = new ApiManifest(ApiManifest.CURRENT_SCHEMA, ApiManifest.CURRENT_SCHEMA_VERSION, prov2, emptyAccounting, Map.of("org.bukkit.TestClass", classV2));

		PairwiseDiff diff = VersionDiffEngine.computeDiff(manifest1, manifest2);
		assertNotNull(diff);

		// Verify superclass change detected
		boolean hasSuperChange = diff.changes().stream().anyMatch(c ->
				c.symbol().equals("org.bukkit.TestClass")
						&& c.details() != null && c.details().reason().contains("Superclass changed")
						&& c.compatibilityImpact() == PairwiseDiff.CompatibilityImpact.BINARY_INCOMPATIBLE);
		assertTrue(hasSuperChange, "Superclass change must be detected as BINARY_INCOMPATIBLE");

		// Verify interface removal detected
		boolean hasIfaceRemoval = diff.changes().stream().anyMatch(c ->
				c.symbol().equals("org.bukkit.TestClass")
						&& c.details() != null && c.details().reason().contains("No longer implements interface")
						&& c.compatibilityImpact() == PairwiseDiff.CompatibilityImpact.BINARY_INCOMPATIBLE);
		assertTrue(hasIfaceRemoval, "Interface removal must be detected as BINARY_INCOMPATIBLE");

		// Verify field descriptor change (COUNT: int -> long) detected as removal + addition
		boolean hasOldFieldRemoved = diff.changes().stream().anyMatch(c ->
				c.symbol().equals("org.bukkit.TestClass#COUNT:I")
						&& c.category() == PairwiseDiff.ChangeCategory.REMOVED
						&& c.compatibilityImpact() == PairwiseDiff.CompatibilityImpact.BINARY_INCOMPATIBLE);
		assertTrue(hasOldFieldRemoved, "Old field descriptor removal must be detected as BINARY_INCOMPATIBLE");

		boolean hasNewFieldAdded = diff.changes().stream().anyMatch(c ->
				c.symbol().equals("org.bukkit.TestClass#COUNT:J")
						&& c.category() == PairwiseDiff.ChangeCategory.ADDED
						&& c.compatibilityImpact() == PairwiseDiff.CompatibilityImpact.COMPATIBLE_ADDITION);
		assertTrue(hasNewFieldAdded, "New field descriptor addition must be detected as COMPATIBLE_ADDITION");

		// Verify field final modifier change detected
		boolean hasFinalChange = diff.changes().stream().anyMatch(c ->
				c.symbol().equals("org.bukkit.TestClass#NAME:Ljava/lang/String;")
						&& c.category() == PairwiseDiff.ChangeCategory.MODIFIED
						&& c.compatibilityImpact() == PairwiseDiff.CompatibilityImpact.BINARY_INCOMPATIBLE);
		assertTrue(hasFinalChange, "Field becoming FINAL must be detected as BINARY_INCOMPATIBLE");
	}

	@Test
	void testCanonicalPrecedencePaperOverFolia() {
		// Paper canonical provenance
		ApiManifest.Provenance paperProv = new ApiManifest.Provenance(
				"paper", "26.3", 178, "canonical",
				new ApiManifest.SourceJar("paper-26.3-178.jar", "shaPaper", 1024),
				new ApiManifest.ScannerInfo("metaminer", "commit1", false),
				new ApiManifest.RemappingInfo("none", "none", "ASM9_8")
		);

		ApiManifest.ScanAccounting emptyAccounting = new ApiManifest.ScanAccounting(
				"COMPLETE", List.of(), List.of(), 1, 1, 0, 0, 1, 0, List.of(), Map.of(), List.of()
		);

		// Shared class: org.bukkit.entity.Player in Paper 26.3
		ApiManifest.MethodEntry modernPaperMethod = new ApiManifest.MethodEntry(
				"sendTitle", "(Lnet/kyori/adventure/title/Title;)V", null, "void",
				List.of(), List.of(), List.of("PUBLIC"), false, false, false, List.of(),
				new ApiManifest.SymbolProvenance("paper", "26.3", "paper", "26.3", false, null)
		);
		ApiManifest.ClassEntry paperPlayer = new ApiManifest.ClassEntry(
				"INTERFACE", List.of("PUBLIC", "INTERFACE", "ABSTRACT"), null, "java.lang.Object",
				List.of(), List.of(), Map.of("sendTitle(Lnet/kyori/adventure/title/Title;)V", modernPaperMethod), Map.of()
		);

		// Older Folia 26.2 contains an older version of Player without Title overload
		ApiManifest.MethodEntry legacyFoliaMethod = new ApiManifest.MethodEntry(
				"sendTitle", "(Ljava/lang/String;Ljava/lang/String;)V", null, "void",
				List.of(), List.of(), List.of("PUBLIC"), false, false, false, List.of(),
				new ApiManifest.SymbolProvenance("folia", "26.2", "folia", "26.2", false, null)
		);
		ApiManifest.ClassEntry foliaPlayer = new ApiManifest.ClassEntry(
				"INTERFACE", List.of("PUBLIC", "INTERFACE", "ABSTRACT"), null, "java.lang.Object",
				List.of(), List.of(), Map.of("sendTitle(Ljava/lang/String;Ljava/lang/String;)V", legacyFoliaMethod), Map.of()
		);

		// Folia-exclusive extension class: RegionScheduler
		ApiManifest.MethodEntry executeMethod = new ApiManifest.MethodEntry(
				"execute", "(Ljava/lang/Runnable;)V", null, "void",
				List.of(), List.of(), List.of("PUBLIC"), false, false, false, List.of(),
				new ApiManifest.SymbolProvenance("folia", "26.2", "folia", "26.2", true, "io.papermc.paper.threadedregions")
		);
		ApiManifest.ClassEntry foliaScheduler = new ApiManifest.ClassEntry(
				"INTERFACE", List.of("PUBLIC", "INTERFACE", "ABSTRACT"), null, "java.lang.Object",
				List.of(), List.of(), Map.of("execute(Ljava/lang/Runnable;)V", executeMethod), Map.of()
		);

		ApiManifest paperManifest = new ApiManifest(
				ApiManifest.CURRENT_SCHEMA, ApiManifest.CURRENT_SCHEMA_VERSION, paperProv, emptyAccounting,
				Map.of("org.bukkit.entity.Player", paperPlayer)
		);

		// Simulated multi-platform map overlay: Paper wins on org.bukkit.*, Folia contributes threadedregions.*
		java.util.Map<String, ApiManifest.ClassEntry> mergedClasses = new java.util.HashMap<>(paperManifest.classes());
		if (!mergedClasses.containsKey("io.papermc.paper.threadedregions.RegionScheduler")) {
			mergedClasses.put("io.papermc.paper.threadedregions.RegionScheduler", foliaScheduler);
		}
		// Folia Player must NOT overwrite Paper Player
		if (!mergedClasses.containsKey("org.bukkit.entity.Player")) {
			mergedClasses.put("org.bukkit.entity.Player", foliaPlayer);
		}

		assertEquals(2, mergedClasses.size());
		ApiManifest.ClassEntry resolvedPlayer = mergedClasses.get("org.bukkit.entity.Player");
		assertTrue(resolvedPlayer.methods().containsKey("sendTitle(Lnet/kyori/adventure/title/Title;)V"),
				"Paper canonical API must take precedence over older Folia classes");
		assertFalse(resolvedPlayer.methods().containsKey("sendTitle(Ljava/lang/String;Ljava/lang/String;)V"));

		ApiManifest.ClassEntry resolvedScheduler = mergedClasses.get("io.papermc.paper.threadedregions.RegionScheduler");
		assertNotNull(resolvedScheduler, "Folia platform-exclusive namespace must be preserved");
		assertEquals("folia", resolvedScheduler.methods().get("execute(Ljava/lang/Runnable;)V").symbolProvenance().originPlatform());
	}
}
