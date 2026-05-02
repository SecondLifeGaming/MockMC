package org.mockmc.metaminer;

import net.md_5.specialsource.Jar;
import java.io.File;
import java.io.InputStream;

public class debug_jar {
    public static void main(String[] args) throws Exception {
        File jarFile = new File(args[0]);
        System.out.println("Testing Jar: " + jarFile.getAbsolutePath());
        Jar jar = Jar.init(jarFile);
        int total = 0;
        int nulls = 0;
        for (String entry : jar.getEntryNames()) {
            total++;
            try (InputStream is = jar.getResource(entry)) {
                if (is == null) {
                    if (entry.endsWith(".class")) {
                        System.err.println("NULL CLASS RESOURCE: " + entry);
                        nulls++;
                    }
                }
            } catch (Exception e) {
                System.err.println("ERROR READING: " + entry + " - " + e.getMessage());
                nulls++;
            }
        }
        System.out.println("Total entries: " + total);
        System.out.println("Null class resources: " + nulls);
        if (nulls > 0) System.exit(1);
    }
}
