package org.mockmc.metaminer.util;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class AtomicDirectorySwapTest
{

	private static File tree(Path root, String name, String marker) throws IOException
	{
		Path dir = root.resolve(name);
		Files.createDirectories(dir);
		Files.writeString(dir.resolve("marker.txt"), marker);
		return dir.toFile();
	}

	private static String marker(File dir) throws IOException
	{
		return Files.readString(dir.toPath().resolve("marker.txt"));
	}

	@Test
	void promoteReplacesTargetAndRemovesBackup(@TempDir Path root) throws IOException
	{
		File target = tree(root, "target", "old");
		File staged = tree(root, "staged", "new");
		File backup = root.resolve("backup").toFile();

		AtomicDirectorySwap.promote(staged, target, backup);

		assertEquals("new", marker(target));
		assertFalse(backup.exists());
		assertFalse(staged.exists());
	}

	@Test
	void failedInstallRestoresPreviousTree(@TempDir Path root) throws IOException
	{
		File target = tree(root, "target", "old");
		File staged = root.resolve("missing-staged").toFile(); // install step fails
		File backup = root.resolve("backup").toFile();

		assertThrows(IOException.class, () -> AtomicDirectorySwap.promote(staged, target, backup));

		assertEquals("old", marker(target));
		assertFalse(backup.exists());
	}

	@Test
	void interruptedRunWithOnlyBackupIsRecovered(@TempDir Path root) throws IOException
	{
		File target = root.resolve("target").toFile(); // crashed between the two renames
		File backup = tree(root, "backup", "last-good");

		AtomicDirectorySwap.recover(target, backup);

		assertEquals("last-good", marker(target));
		assertFalse(backup.exists());
	}

	@Test
	void staleBackupIsDiscardedWhenTargetExists(@TempDir Path root) throws IOException
	{
		File target = tree(root, "target", "current");
		File backup = tree(root, "backup", "stale");

		AtomicDirectorySwap.recover(target, backup);

		assertEquals("current", marker(target));
		assertFalse(backup.exists());
	}

	@Test
	void promoteAfterInterruptedRunKeepsLastGoodUntilSwap(@TempDir Path root) throws IOException
	{
		File target = root.resolve("target").toFile();
		File backup = tree(root, "backup", "last-good");
		File staged = tree(root, "staged", "new");

		AtomicDirectorySwap.promote(staged, target, backup);

		assertEquals("new", marker(target));
		assertTrue(target.exists());
		assertFalse(backup.exists());
	}

	@Test
	void promoteWithNoExistingTargetInstallsStagedTree(@TempDir Path root) throws IOException
	{
		File target = root.resolve("target").toFile();
		File staged = tree(root, "staged", "new");

		AtomicDirectorySwap.promote(staged, target, root.resolve("backup").toFile());

		assertEquals("new", marker(target));
	}
}
