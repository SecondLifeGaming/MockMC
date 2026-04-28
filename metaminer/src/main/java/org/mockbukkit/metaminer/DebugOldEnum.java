package org.mockbukkit.metaminer;

import org.bukkit.util.OldEnum;
import java.lang.reflect.Method;

public class DebugOldEnum {
    private static final java.util.logging.Logger LOGGER = java.util.logging.Logger.getLogger(DebugOldEnum.class.getName());

    @SuppressWarnings("removal")
    public static void main(String[] args) {
        Class<?> clazz = OldEnum.class;
        LOGGER.info(() -> "Methods in " + clazz.getName());
        for (Method m : clazz.getMethods()) {
            if (m.getName().equals("compareTo")) {
                LOGGER.info(() -> "Method: " + m);
                LOGGER.info(() -> "  Declaring Class: " + m.getDeclaringClass().getName());
                LOGGER.info("  Parameter types:");
                for (Class<?> p : m.getParameterTypes()) {
                    LOGGER.info(() -> "    " + p.getName());
                }
            }
        }
    }
}
