package org.mockmc.metaminer.diff;

import org.junit.jupiter.api.Test;
import org.mockmc.metaminer.manifest.ApiManifest;

import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

class VersionDiffEngineTest {

	@Test
	void testBinaryIncompatibleMethodRemovalAndCompatibleAddition() {
		ApiManifest.Provenance prov26_2 = new ApiManifest.Provenance(
				"paper", "26.2", 40, "canonical",
				new ApiManifest.SourceJar("paper-26.2.jar", "sha1", 100),
				new ApiManifest.ScannerInfo("metaminer", "commit1", false),
				new ApiManifest.RemappingInfo("none", "none", "ASM9_8")
		);

		ApiManifest.Provenance prov26_3 = new ApiManifest.Provenance(
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
		ApiManifest.MethodEntry deprecatedIn26_2 = new ApiManifest.MethodEntry(
				"getLegacyName", "()Ljava/lang/String;", null, "java.lang.String",
				List.of(), List.of(), List.of("PUBLIC"), false, false, false, List.of(),
				new ApiManifest.SymbolProvenance("paper", "26.2", "paper", "26.2", false, null)
		);
		ApiManifest.MethodEntry deprecatedIn26_3 = new ApiManifest.MethodEntry(
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

		ApiManifest.ClassEntry class26_2 = new ApiManifest.ClassEntry(
				"INTERFACE",
				List.of("PUBLIC", "INTERFACE", "ABSTRACT"),
				null,
				"java.lang.Object",
				List.of(),
				List.of(),
				Map.of(
						"sendTitle(Ljava/lang/String;Ljava/lang/String;)V", legacyMethod,
						"getLegacyName()Ljava/lang/String;", deprecatedIn26_2
				),
				Map.of()
		);

		ApiManifest.ClassEntry class26_3 = new ApiManifest.ClassEntry(
				"INTERFACE",
				List.of("PUBLIC", "INTERFACE", "ABSTRACT"),
				null,
				"java.lang.Object",
				List.of(),
				List.of(),
				Map.of(
						"sendTitle(Lnet/kyori/adventure/title/Title;)V", modernMethod,
						"getLegacyName()Ljava/lang/String;", deprecatedIn26_3,
						"sendTitle(Ljava/lang/Object;)V", bridgeMethod
				),
				Map.of()
		);

		ApiManifest manifest26_2 = new ApiManifest(
				ApiManifest.CURRENT_SCHEMA, ApiManifest.CURRENT_SCHEMA_VERSION, prov26_2, emptyAccounting,
				Map.of("org.bukkit.entity.Player", class26_2)
		);

		ApiManifest manifest26_3 = new ApiManifest(
				ApiManifest.CURRENT_SCHEMA, ApiManifest.CURRENT_SCHEMA_VERSION, prov26_3, emptyAccounting,
				Map.of("org.bukkit.entity.Player", class26_3)
		);

		PairwiseDiff diff = VersionDiffEngine.computeDiff(manifest26_2, manifest26_3);

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
}
