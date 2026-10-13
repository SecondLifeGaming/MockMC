package org.mockmc.metaminer.util;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Installs a fully staged directory in place of a target directory using rename operations.
 * <p>
 * Invariant: after {@link #promote} or {@link #recover} returns or throws, {@code target} holds either the
 * previous complete tree or the new complete tree, never a partial one. The backup of the last known-good tree is
 * only deleted after the new tree is in place.
 */
public final class AtomicDirectorySwap {

    private static final Logger LOGGER = Logger.getLogger(AtomicDirectorySwap.class.getName());

    private AtomicDirectorySwap() {
        throw new UnsupportedOperationException("Utility class");
    }

    /**
     * Repairs state left by an interrupted run. If the target is missing but a backup exists, the backup is the last
     * known-good tree and is restored; otherwise a stale backup is discarded.
     */
    public static void recover(File target, File backup) throws IOException {
        if (!backup.exists()) {
            return;
        }
        if (!target.exists()) {
            move(backup, target);
        } else {
            FileUtils.deleteDirectory(backup);
        }
    }

    /**
     * Replaces {@code target} with {@code staged}, keeping the previous tree in {@code backup} until the swap is done.
     * All three paths must be on the same filesystem for the renames to be atomic.
     */
    public static void promote(File staged, File target, File backup) throws IOException {
        recover(target, backup);
        boolean hadTarget = target.exists();
        if (hadTarget) {
            move(target, backup);
        }
        try {
            move(staged, target);
        } catch (IOException e) {
            if (hadTarget) {
                try {
                    move(backup, target);
                } catch (IOException restore) {
                    e.addSuppressed(restore);
                }
            }
            throw e;
        }
        if (backup.exists()) {
            try {
                FileUtils.deleteDirectory(backup);
            } catch (IOException e) {
                // New tree is already installed; a leftover backup is discarded by the next recover().
                LOGGER.log(Level.WARNING, "Could not delete backup {0}: {1}", new Object[] {backup, e.getMessage()});
            }
        }
    }

    private static void move(File from, File to) throws IOException {
        try {
            Files.move(from.toPath(), to.toPath(), StandardCopyOption.ATOMIC_MOVE);
        } catch (IOException e) {
            throw new IOException("Failed to move " + from + " to " + to, e);
        }
    }
}
