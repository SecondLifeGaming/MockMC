package org.mockmc.metaminer.util;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

public class JarCracker {

    private static final Logger LOGGER = Logger.getLogger(JarCracker.class.getName());
    private static final String DEFAULT_MC_VERSION = "1.21.1";
    private static final String JAVA_BIN = getExecutable("java");
    private static final String JAR_BIN = getExecutable("jar");

    private JarCracker() {
        throw new UnsupportedOperationException("Utility class");
    }

    private static String getExecutable(String name) {
        String extension = System.getProperty("os.name").toLowerCase().contains("win") ? ".exe" : "";
        return System.getProperty("java.home") + File.separator + "bin" + File.separator + name + extension;
    }

    public static class CrackResult {
        public final File patchedJar;
        public final String mcVersion;

        public CrackResult(File patchedJar, String mcVersion) {
            this.patchedJar = patchedJar;
            this.mcVersion = mcVersion;
        }
    }

    public static CrackResult crack(File bundlerJar, File cacheDir) throws IOException {
        if (!cacheDir.exists()) cacheDir.mkdirs();
        
        String mcVersion = detectMcVersion(bundlerJar);
        File finalJar = new File(cacheDir, "patched-" + bundlerJar.getName());
        File finalLibDir = new File(cacheDir, "libraries");
        
        if (isValidCache(finalJar, finalLibDir)) {
            return new CrackResult(finalJar, mcVersion);
        }

        LOGGER.log(Level.INFO, "Autonomous Unbundling {0}...", bundlerJar.getName());
        
        File tempDir = new File(cacheDir, "unbundle_" + System.currentTimeMillis());
        tempDir.mkdirs();
        
        try {
            // Run the bundler to let it self-extract and patch
            ProcessBuilder pb = new ProcessBuilder(JAVA_BIN, "-Xmx512M", "-jar", bundlerJar.getAbsolutePath(), "--help");
            pb.directory(tempDir);
            runProcessSafely(pb, "Bundler self-extraction");
            
            File patchedJar = extractPatchedJar(tempDir);
            syncLibraries(tempDir, finalLibDir);
            extractBundlerInternalLibraries(bundlerJar, finalLibDir);
            repackJar(tempDir, patchedJar, finalJar);

            LOGGER.log(Level.INFO, "Successfully unbundled and repacked: {0}", finalJar.getName());
            
            return new CrackResult(finalJar, mcVersion);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            throw new IOException("Unbundling interrupted", e);
        } finally {
            FileUtils.deleteQuietly(tempDir);
        }
    }

    private static File extractPatchedJar(File tempDir) throws IOException {
        File versionsDir = new File(tempDir, "versions");
        if (!versionsDir.exists()) {
            throw new IOException("Bundler did not create versions directory");
        }
        
        File[] versionDirs = versionsDir.listFiles(File::isDirectory);
        if (versionDirs == null || versionDirs.length == 0) {
            throw new IOException("No version directories found in " + versionsDir.getAbsolutePath());
        }
        
        File patchedJar = findJarRecursive(versionDirs[0]);
        if (patchedJar == null) {
            throw new IOException("Could not find patched jar in " + versionDirs[0].getAbsolutePath());
        }
        return patchedJar;
    }

    private static void syncLibraries(File tempDir, File finalLibDir) throws IOException {
        File libDir = new File(tempDir, "libraries");
        if (libDir.exists()) {
            if (!finalLibDir.exists()) finalLibDir.mkdirs();
            FileUtils.copyDirectory(libDir, finalLibDir);
        }
    }

    private static void extractBundlerInternalLibraries(File bundlerJar, File finalLibDir) throws IOException {
        try (ZipFile bundlerZip = new ZipFile(bundlerJar)) {
            var entries = bundlerZip.entries();
            while (entries.hasMoreElements()) {
                ZipEntry entry = entries.nextElement();
                if (!entry.isDirectory() && entry.getName().startsWith("META-INF/libraries/") && entry.getName().endsWith(".jar")) {
                    String relPath = entry.getName().substring("META-INF/libraries/".length());
                    File targetLib = new File(finalLibDir, relPath);
                    if (!targetLib.toPath().normalize().startsWith(finalLibDir.toPath().normalize())) {
                        throw new IOException("Zip slip vulnerability detected in entry: " + entry.getName());
                    }
                    if (!targetLib.exists() || !isReadableZip(targetLib)) {
                        targetLib.getParentFile().mkdirs();
                        // Write to a private temp file, then move into place so concurrent readers never see partial output.
                        java.nio.file.Path tmp = java.nio.file.Files.createTempFile(targetLib.getParentFile().toPath(), ".lib-", ".tmp");
                        try (InputStream is = bundlerZip.getInputStream(entry);
                             OutputStream os = java.nio.file.Files.newOutputStream(tmp)) {
                            IOUtils.copy(is, os);
                            os.flush();
                            java.nio.file.Files.move(tmp, targetLib.toPath(), java.nio.file.StandardCopyOption.REPLACE_EXISTING, java.nio.file.StandardCopyOption.ATOMIC_MOVE);
                        } finally {
                            java.nio.file.Files.deleteIfExists(tmp);
                        }
                    }
                }
            }
        }
    }

    private static void repackJar(File tempDir, File patchedJar, File finalJar) throws IOException, InterruptedException {
        File repackDir = new File(tempDir, "repack");
        repackDir.mkdirs();
        
        ProcessBuilder extractPb = new ProcessBuilder(JAR_BIN, "xf", patchedJar.getAbsolutePath());
        extractPb.directory(repackDir);
        runProcessSafely(extractPb, "JAR extraction");

        ProcessBuilder repackPb = new ProcessBuilder(JAR_BIN, "cf", finalJar.getAbsolutePath(), ".");
        repackPb.directory(repackDir);
        runProcessSafely(repackPb, "JAR repack");

        if (!isValidJar(finalJar)) {
            throw new IOException("Repacked JAR failed verification: " + finalJar.getAbsolutePath());
        }
    }

    static boolean isValidCache(File finalJar, File finalLibDir) {
        if (!finalJar.exists() || !isValidJar(finalJar)) {
            return false;
        }
        if (!finalLibDir.exists() || !finalLibDir.isDirectory()) {
            return false;
        }
        try (var walk = java.nio.file.Files.walk(finalLibDir.toPath())) {
            var jars = walk.filter(p -> p.toString().endsWith(".jar")).toList();
            // Every cached library must be a readable archive; a truncated entry invalidates the cache.
            return !jars.isEmpty() && jars.stream().allMatch(p -> isReadableZip(p.toFile()));
        } catch (IOException _) {
            return false;
        }
    }

    private static boolean isReadableZip(File file) {
        try (ZipFile zipFile = new ZipFile(file)) {
            return zipFile.size() > 0;
        } catch (IOException _) {
            return false;
        }
    }

    private static boolean isValidJar(File jarFile) {
        try (ZipFile zipFile = new ZipFile(jarFile)) {
            ZipEntry manifest = zipFile.getEntry("META-INF/MANIFEST.MF");
            return manifest != null;
        } catch (IOException _) {
            return false;
        }
    }

    static void runProcessSafely(ProcessBuilder pb, String stepName) throws IOException, InterruptedException {
        Process process = pb.start();
        ByteArrayOutputStream stderrBuffer = new ByteArrayOutputStream();
        
        java.util.concurrent.CompletableFuture<Void> stdoutDrain = java.util.concurrent.CompletableFuture.runAsync(() -> {
            try (InputStream is = process.getInputStream()) {
                IOUtils.copy(is, OutputStream.nullOutputStream());
            } catch (IOException _) {
                // Best-effort stdout discarding; ignore EOF or broken pipe upon process exit
            }
        });

        java.util.concurrent.CompletableFuture<Void> stderrDrain = java.util.concurrent.CompletableFuture.runAsync(() -> {
            try (InputStream is = process.getErrorStream()) {
                IOUtils.copy(is, stderrBuffer);
            } catch (IOException _) {
                // Best-effort stderr capturing; ignore EOF upon process exit
            }
        });

        int exitCode = process.waitFor();
        try {
            stdoutDrain.get(5, java.util.concurrent.TimeUnit.SECONDS);
            stderrDrain.get(5, java.util.concurrent.TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            throw e;
        } catch (java.util.concurrent.ExecutionException | java.util.concurrent.TimeoutException _) {
            // Stream draining best-effort timeout/error after process exit
        }

        if (exitCode != 0) {
            String err = stderrBuffer.toString(StandardCharsets.UTF_8);
            throw new IOException(stepName + " failed with exit code " + exitCode + ": " + err);
        }
    }

    private static String detectMcVersion(File bundlerJar) throws IOException {
        try (ZipFile zipFile = new ZipFile(bundlerJar)) {
            ZipEntry entry = zipFile.getEntry("META-INF/versions.list");
            if (entry != null) {
                try (InputStream is = zipFile.getInputStream(entry)) {
                    String content = IOUtils.toString(is, StandardCharsets.UTF_8);
                    String[] lines = content.split("\n");
                    if (lines.length > 0) {
                        String line = lines[0].trim();
                        if (line.contains(DEFAULT_MC_VERSION)) return DEFAULT_MC_VERSION;
                        if (line.contains("1.21")) return "1.21";
                    }
                }
            }
        }
        return DEFAULT_MC_VERSION; // Default
    }

    private static File findJarRecursive(File dir) {
        File[] files = dir.listFiles();
        if (files == null) return null;
        for (File f : files) {
            if (f.isDirectory()) {
                File found = findJarRecursive(f);
                if (found != null) return found;
            } else if (f.getName().endsWith(".jar") && !f.getName().contains("mojang")) {
                return f;
            }
        }
        return null;
    }
}
