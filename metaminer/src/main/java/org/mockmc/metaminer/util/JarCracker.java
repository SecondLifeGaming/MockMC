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
        
        if (finalJar.exists()) {
            return new CrackResult(finalJar, mcVersion);
        }

        LOGGER.log(Level.INFO, "Autonomous Unbundling {0}...", bundlerJar.getName());
        
        File tempDir = new File(cacheDir, "unbundle_" + System.currentTimeMillis());
        tempDir.mkdirs();
        
        try {
            // Run the bundler to let it self-extract and patch
            ProcessBuilder pb = new ProcessBuilder(JAVA_BIN, "-Xmx512M", "-jar", bundlerJar.getAbsolutePath(), "--help");
            pb.directory(tempDir);
            Process process = pb.start();
            process.waitFor();
            
            // Find the patched jar in versions/ directory
            File versionsDir = new File(tempDir, "versions");
            if (!versionsDir.exists()) {
                throw new IOException("Bundler did not create versions directory");
            }
            
            File[] versionDirs = versionsDir.listFiles(File::isDirectory);
            if (versionDirs == null || versionDirs.length == 0) {
                 throw new IOException("No version directories found in " + versionsDir.getAbsolutePath());
            }
            
            // Pick the first one (usually only one)
            File patchedJar = findJarRecursive(versionDirs[0]);
            if (patchedJar == null) {
                throw new IOException("Could not find patched jar in " + versionDirs[0].getAbsolutePath());
            }

            // Move libraries to a stable location
            File libDir = new File(tempDir, "libraries");
            if (libDir.exists()) {
                if (finalLibDir.exists()) FileUtils.deleteQuietly(finalLibDir);
                FileUtils.moveDirectory(libDir, finalLibDir);
            }

            // REPACK the jar to ensure it has standard ZIP headers that SpecialSource likes
            File repackDir = new File(tempDir, "repack");
            repackDir.mkdirs();
            
            // Extract
            ProcessBuilder extractPb = new ProcessBuilder(JAR_BIN, "xf", patchedJar.getAbsolutePath());
            extractPb.directory(repackDir);
            extractPb.start().waitFor();

            // Re-create
            ProcessBuilder repackPb = new ProcessBuilder(JAR_BIN, "cf", finalJar.getAbsolutePath(), ".");
            repackPb.directory(repackDir);
            repackPb.start().waitFor();

            LOGGER.log(Level.INFO, "Successfully unbundled and repacked: {0}", finalJar.getName());
            
            return new CrackResult(finalJar, mcVersion);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            throw new IOException("Unbundling interrupted", e);
        } finally {
            FileUtils.deleteQuietly(tempDir);
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
