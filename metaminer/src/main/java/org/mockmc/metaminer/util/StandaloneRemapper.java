package org.mockmc.metaminer.util;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;

public class StandaloneRemapper {
    private static final Logger LOGGER = Logger.getLogger(StandaloneRemapper.class.getName());

    public static File remap(File inputJar, File mappingFile, File vanillaJar, File outputJar) throws Exception {
        if (outputJar.exists()) return outputJar;

        LOGGER.log(Level.INFO, "Remapping {0} to Mojang names via isolated process with prioritized ASM 9.7...", inputJar.getName());
        
        File tsrgFile = new File(mappingFile.getParentFile(), mappingFile.getName().replace(".txt", ".tsrg"));
        ProGuardToTsrg.convert(mappingFile, tsrgFile);

        // Prioritize ASM 9.7 in the classpath to support Java 25 classes
        String originalClasspath = System.getProperty("java.class.path");
        String separator = System.getProperty("path.separator");
        List<String> cpEntries = new ArrayList<>(Arrays.asList(originalClasspath.split(separator)));
        
        // Move all ASM entries to the front
        List<String> asmEntries = cpEntries.stream()
                .filter(s -> s.contains("asm-"))
                .collect(Collectors.toList());
        cpEntries.removeAll(asmEntries);
        
        List<String> finalCp = new ArrayList<>();
        finalCp.addAll(asmEntries);
        finalCp.addAll(cpEntries);
        String prioritizedClasspath = String.join(separator, finalCp);

        List<String> command = new ArrayList<>();
        command.add("java"); // Use system java (25) since we have modern ASM now
        command.add("-cp");
        command.add(prioritizedClasspath);
        command.add("net.md_5.specialsource.SpecialSource");
        command.add("--in-jar");
        command.add(inputJar.getAbsolutePath());
        command.add("--out-jar");
        command.add(outputJar.getAbsolutePath());
        command.add("--srg-in");
        command.add(tsrgFile.getAbsolutePath());
        command.add("--live");

        ProcessBuilder pb = new ProcessBuilder(command);
        pb.inheritIO();
        Process process = pb.start();
        int exitCode = process.waitFor();
        
        if (exitCode != 0) {
            throw new IOException("SpecialSource failed with exit code " + exitCode);
        }
        
        return outputJar;
    }
}
