package org.mockmc.metaminer;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import java.io.File;

class GenerateMocksTest {
    @Test
    void testFullGenerationCycle() throws Exception {
        File projectRoot = new File(".."); // Assuming we are in metaminer/
        if (!new File(projectRoot, "jars").exists()) {
             projectRoot = new File(".");
        }
        
        // Run the full standalone cycle to cover all generators
        StandaloneRunner.main(new String[]{projectRoot.getAbsolutePath()});

        File generatedDir = new File(projectRoot, "src/main/java/org/mockmc/mockmc/generated");
        Assertions.assertTrue(generatedDir.exists() && generatedDir.isDirectory(), "Generated directory should exist");
        
        File coverageReport = new File(projectRoot, "COVERAGE.md");
        Assertions.assertTrue(coverageReport.exists(), "Coverage report should be generated");
    }
}
