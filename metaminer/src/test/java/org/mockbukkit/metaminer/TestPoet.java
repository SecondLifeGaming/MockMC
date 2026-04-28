package org.mockbukkit.metaminer;

import com.google.common.reflect.ClassPath;
import org.bukkit.Bukkit;
import org.junit.jupiter.api.Test;
import java.io.IOException;

public class TestPoet {
    @Test
    public void runTest() throws IOException {
        ClassPath cp = ClassPath.from(Bukkit.class.getClassLoader());
        int count = 0;
        for (ClassPath.ClassInfo info : cp.getTopLevelClassesRecursive("org.bukkit")) {
            try {
                Class<?> clazz = info.load();
                if (clazz.isInterface()) {
                    count++;
                }
            } catch (Throwable t) {}
        }
        System.out.println("Found " + count + " interfaces.");
    }
}
