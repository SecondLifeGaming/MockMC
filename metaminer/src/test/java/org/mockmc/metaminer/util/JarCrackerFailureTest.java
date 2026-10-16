package org.mockmc.metaminer.util;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class JarCrackerFailureTest
{

	private static void writeJar(File file, boolean withManifest) throws IOException
	{
		file.getParentFile().mkdirs();
		try (ZipOutputStream zos = new ZipOutputStream(Files.newOutputStream(file.toPath())))
		{
			zos.putNextEntry(new ZipEntry(withManifest ? "META-INF/MANIFEST.MF" : "x.txt"));
			zos.write("Manifest-Version: 1.0\n".getBytes());
			zos.closeEntry();
		}
	}

	private static void truncate(File file) throws IOException
	{
		byte[] all = Files.readAllBytes(file.toPath());
		Files.write(file.toPath(), java.util.Arrays.copyOf(all, all.length / 2));
	}

	@Test
	void validCacheIsAccepted(@TempDir Path root) throws IOException
	{
		File jar = root.resolve("patched.jar").toFile();
		File libs = root.resolve("libraries").toFile();
		writeJar(jar, true);
		writeJar(new File(libs, "a/b.jar"), true);

		assertTrue(JarCracker.isValidCache(jar, libs));
	}

	@Test
	void missingLibrariesRejectCache(@TempDir Path root) throws IOException
	{
		File jar = root.resolve("patched.jar").toFile();
		writeJar(jar, true);

		assertFalse(JarCracker.isValidCache(jar, root.resolve("libraries").toFile()));
		Files.createDirectories(root.resolve("libraries"));
		assertFalse(JarCracker.isValidCache(jar, root.resolve("libraries").toFile()));
	}

	@Test
	void truncatedPatchedJarRejectsCache(@TempDir Path root) throws IOException
	{
		File jar = root.resolve("patched.jar").toFile();
		File libs = root.resolve("libraries").toFile();
		writeJar(jar, true);
		writeJar(new File(libs, "b.jar"), true);
		truncate(jar);

		assertFalse(JarCracker.isValidCache(jar, libs));
	}

	@Test
	void truncatedLibraryRejectsCache(@TempDir Path root) throws IOException
	{
		File jar = root.resolve("patched.jar").toFile();
		File libs = root.resolve("libraries").toFile();
		writeJar(jar, true);
		writeJar(new File(libs, "ok.jar"), true);
		File bad = new File(libs, "bad.jar");
		writeJar(bad, true);
		truncate(bad);

		assertFalse(JarCracker.isValidCache(jar, libs));
	}

	@Test
	void jarWithoutManifestRejectsCache(@TempDir Path root) throws IOException
	{
		File jar = root.resolve("patched.jar").toFile();
		File libs = root.resolve("libraries").toFile();
		writeJar(jar, false);
		writeJar(new File(libs, "b.jar"), true);

		assertFalse(JarCracker.isValidCache(jar, libs));
	}

	@Test
	void nonzeroExitReportsStepAndStderr()
	{
		ProcessBuilder pb = new ProcessBuilder("sh", "-c", "echo boom-details 1>&2; exit 3");
		IOException e = assertThrows(IOException.class, () -> JarCracker.runProcessSafely(pb, "Bundler self-extraction"));

		assertTrue(e.getMessage().contains("Bundler self-extraction"));
		assertTrue(e.getMessage().contains("exit code 3"));
		assertTrue(e.getMessage().contains("boom-details"));
	}

	@Test
	void heavyOutputOnBothStreamsDoesNotDeadlock()
	{
		// ~8 MB on each stream, far beyond any OS pipe buffer.
		ProcessBuilder pb = new ProcessBuilder("sh", "-c",
				"head -c 8000000 /dev/zero | tr '\\0' 'a'; head -c 8000000 /dev/zero | tr '\\0' 'b' 1>&2");
		org.junit.jupiter.api.Assertions.assertTimeoutPreemptively(java.time.Duration.ofSeconds(60),
				() -> JarCracker.runProcessSafely(pb, "noisy"));
	}

	@Test
	void detectMcVersionExtractsVersionFromVersionsList(@TempDir Path root) throws IOException
	{
		File jar = root.resolve("paper-test.jar").toFile();
		try (ZipOutputStream zos = new ZipOutputStream(Files.newOutputStream(jar.toPath())))
		{
			zos.putNextEntry(new ZipEntry("META-INF/versions.list"));
			zos.write("07349f2a63fe23db4e5edd5ad44f0359f700f67f4afad8272ac2cfaf5f6fc1d7\t1.20.6\t1.20.6/paper-1.20.6.jar\n".getBytes());
			zos.closeEntry();
		}

		assertEquals("1.20.6", JarCracker.detectMcVersion(jar));
	}

	@Test
	void detectMcVersionFallbackWhenMissing(@TempDir Path root) throws IOException
	{
		File jar = root.resolve("no-versions.jar").toFile();
		writeJar(jar, true);

		assertEquals("1.21.1", JarCracker.detectMcVersion(jar));
	}
}

