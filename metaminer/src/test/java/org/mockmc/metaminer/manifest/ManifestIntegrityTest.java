package org.mockmc.metaminer.manifest;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Map;
import java.util.jar.JarEntry;
import java.util.jar.JarOutputStream;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ManifestIntegrityTest {

	@Test
	void testDeterministicSerializationProducesIdenticalBytes() {
		ApiManifest.Provenance provenance = new ApiManifest.Provenance(
				"paper",
				"26.3",
				178,
				"canonical",
				new ApiManifest.SourceJar("paper-26.3.jar", "e3b0c44298fc1c149afbf4c8996fb92427ae41e4649b934ca495991b7852b855", 1024),
				new ApiManifest.ScannerInfo("metaminer-2.2.0", "abcdef1234567890", false),
				new ApiManifest.RemappingInfo("none", "none", "ASM9_8")
		);

		ApiManifest.ScanAccounting accounting = new ApiManifest.ScanAccounting(
				"COMPLETE",
				List.of("org.bukkit"),
				List.of(),
				1,
				1,
				0,
				0,
				1,
				0,
				List.of(),
				Map.of("org.bukkit.TestClass", new ApiManifest.ClassDisposition(
						ApiManifest.DispositionType.ANALYZED,
						1,
						0,
						null,
						null
				)),
				List.of()
		);

		ApiManifest.MethodEntry method = new ApiManifest.MethodEntry(
				"getName",
				"()Ljava/lang/String;",
				null,
				"java.lang.String",
				List.of(),
				List.of(),
				List.of("PUBLIC"),
				false,
				false,
				false,
				List.of(),
				new ApiManifest.SymbolProvenance("paper", "26.3", "paper", "26.3", false, null)
		);

		ApiManifest.ClassEntry classEntry = new ApiManifest.ClassEntry(
				"CLASS",
				List.of("PUBLIC"),
				null,
				"java.lang.Object",
				List.of(),
				List.of(),
				Map.of("getName()Ljava/lang/String;", method),
				Map.of()
		);

		ApiManifest manifest1 = new ApiManifest(
				ApiManifest.CURRENT_SCHEMA,
				ApiManifest.CURRENT_SCHEMA_VERSION,
				provenance,
				accounting,
				Map.of("org.bukkit.TestClass", classEntry)
		);

		ApiManifest manifest2 = new ApiManifest(
				ApiManifest.CURRENT_SCHEMA,
				ApiManifest.CURRENT_SCHEMA_VERSION,
				provenance,
				accounting,
				Map.of("org.bukkit.TestClass", classEntry)
		);

		byte[] bytes1 = ManifestCanonicalSerializer.serializeToCanonicalBytes(manifest1);
		byte[] bytes2 = ManifestCanonicalSerializer.serializeToCanonicalBytes(manifest2);

		assertArrayEquals(bytes1, bytes2, "Serialization must produce bit-for-bit identical bytes");

		String sha1 = ManifestCanonicalSerializer.computeSha256(bytes1);
		String sha2 = ManifestCanonicalSerializer.computeSha256(bytes2);
		assertEquals(sha1, sha2, "Manifest digests must be identical");

		String jsonString = new String(bytes1, StandardCharsets.UTF_8);
		assertTrue(jsonString.endsWith("\n"), "Must terminate with a single LF newline");
		assertFalse(jsonString.contains("\r"), "Must contain zero CRLF characters");
	}

	@Test
	void testBytecodeScannerOnRealClassBytecode(@TempDir File tempDir) throws IOException {
		// Create a small synthetic JAR containing a valid compiled class
		File jarFile = new File(tempDir, "test-api.jar");
		try (JarOutputStream out = new JarOutputStream(new FileOutputStream(jarFile))) {
			// Include a basic compiled class from standard library / runtime
			// We use Object.class bytecode as fixture
			byte[] objectBytes = Object.class.getClassLoader() == null
					? ClassLoader.getSystemClassLoader().getResourceAsStream("java/lang/Object.class").readAllBytes()
					: Object.class.getClassLoader().getResourceAsStream("java/lang/Object.class").readAllBytes();

			JarEntry entry = new JarEntry("org/bukkit/entity/TestEntity.class");
			out.putNextEntry(entry);
			out.write(objectBytes);
			out.closeEntry();
		}

		BytecodeManifestScanner.ScanConfiguration config = new BytecodeManifestScanner.ScanConfiguration(
				"paper",
				"26.3",
				100,
				"canonical",
				"metaminer-2.2.0",
				"gitcommit123",
				false,
				"none",
				"none",
				"ASM9_8",
				List.of("org.bukkit"),
				List.of(),
				List.of(),
				false,
				null
		);

		ApiManifest manifest = BytecodeManifestScanner.scanJar(jarFile, config);

		assertNotNull(manifest);
		assertEquals("COMPLETE", manifest.scanAccounting().status());
		assertEquals(1, manifest.scanAccounting().totalClassesDiscovered());
		assertEquals(1, manifest.scanAccounting().totalClassesAnalyzed());
		assertEquals(0, manifest.scanAccounting().totalClassesExcluded());
		assertEquals(0, manifest.scanAccounting().totalClassesFailed());

		assertTrue(manifest.classes().containsKey("org.bukkit.entity.TestEntity"));
		ApiManifest.ClassEntry entity = manifest.classes().get("org.bukkit.entity.TestEntity");
		assertNotNull(entity);
		assertFalse(entity.methods().isEmpty(), "Methods must be fully enumerated");

		byte[] canonicalBytes = ManifestCanonicalSerializer.serializeToCanonicalBytes(manifest);
		assertNotNull(canonicalBytes);
		assertTrue(canonicalBytes.length > 0);
	}
}
