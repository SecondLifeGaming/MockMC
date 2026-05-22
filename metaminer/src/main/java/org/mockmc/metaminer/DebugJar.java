package org.mockmc.metaminer;

import net.md_5.specialsource.Jar;
import java.io.File;
import java.io.InputStream;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DebugJar {
    private static final Logger LOGGER = Logger.getLogger(DebugJar.class.getName());

    public static void main(String[] args) throws Exception {
        File jarFile = new File(args[0]);
        LOGGER.log(Level.INFO, "Testing Jar: {0}", jarFile.getAbsolutePath());
        Jar jar = Jar.init(jarFile);
        int total = 0;
        int nulls = 0;
        for (String entry : jar.getEntryNames()) {
            total++;
            try (InputStream is = jar.getResource(entry)) {
                if (is == null && entry.endsWith(".class")) {
                    LOGGER.log(Level.SEVERE, "NULL CLASS RESOURCE: {0}", entry);
                    nulls++;
                }
            } catch (Exception e) {
                LOGGER.log(Level.SEVERE, e, () -> "ERROR READING: " + entry);
                nulls++;
            }
        }
        LOGGER.log(Level.INFO, "Total entries: {0}", total);
        LOGGER.log(Level.INFO, "Null class resources: {0}", nulls);
        if (nulls > 0) System.exit(1);
    }
}
