package org.mockmc.metaminer.util;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

public class JarCracker {

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

        System.out.println("Autonomous Unbundling " + bundlerJar.getName() + "...");
        
        File tempDir = new File(cacheDir, "unbundle_" + System.currentTimeMillis());
        tempDir.mkdirs();
        
        try {
            // Run the bundler to let it self-extract and patch
            ProcessBuilder pb = new ProcessBuilder("java", "-Xmx512M", "-jar", bundlerJar.getAbsolutePath(), "--help");
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
            ProcessBuilder extractPb = new ProcessBuilder("jar", "xf", patchedJar.getAbsolutePath());
            extractPb.directory(repackDir);
            extractPb.start().waitFor();
            
            // Re-create
            ProcessBuilder repackPb = new ProcessBuilder("jar", "cf", finalJar.getAbsolutePath(), ".");
            repackPb.directory(repackDir);
            repackPb.start().waitFor();
            
            System.out.println("Successfully unbundled and repacked: " + finalJar.getName());
            
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
                        if (line.contains("1.21.1")) return "1.21.1";
                        if (line.contains("1.21")) return "1.21";
                    }
                }
            }
        }
        return "1.21.1"; // Default
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
