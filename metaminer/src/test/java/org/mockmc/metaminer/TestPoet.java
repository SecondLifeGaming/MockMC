package org.mockmc.metaminer;

import com.google.common.reflect.ClassPath;
import org.bukkit.Bukkit;
import org.junit.jupiter.api.Test;
import java.io.IOException;

class TestPoet {
    @Test
    void runTest() throws IOException {
        ClassPath cp = ClassPath.from(Bukkit.class.getClassLoader());
        int count = 0;
        for (ClassPath.ClassInfo info : cp.getTopLevelClassesRecursive("org.bukkit")) {
            try {
                Class<?> clazz = info.load();
                if (clazz.isInterface()) {
                    count++;
                }
            } catch (Throwable _) {
                // Ignore classes that cannot be loaded
            }
        }
        org.junit.jupiter.api.Assertions.assertTrue(count > 0, "Expected Bukkit interfaces to be found");
    }
}
