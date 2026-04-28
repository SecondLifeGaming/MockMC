package org.mockbukkit.metaminer;
import org.bukkit.entity.TNTPrimed;
import org.junit.jupiter.api.Test;
import java.lang.reflect.Method;
import java.util.*;

public class TestMethods {
    @Test
    public void runTest() {
        Map<String, Method> methodMap = new HashMap<>();
        for (Method m : TNTPrimed.class.getMethods()) {
            if (java.lang.reflect.Modifier.isStatic(m.getModifiers())) continue;
            if (m.getDeclaringClass() == Object.class) continue;

            String sig = m.getName() + java.util.Arrays.toString(m.getParameterTypes());
            Method existing = methodMap.get(sig);
            if (existing != null) {
                if (existing.getReturnType().isAssignableFrom(m.getReturnType())) {
                    methodMap.put(sig, m);
                }
            } else {
                methodMap.put(sig, m);
            }
        }
        for (Method m : methodMap.values()) {
            if (m.getName().equals("spigot")) {
                System.out.println("FOUND: " + m.getReturnType().getName() + " " + m.getName() + "()");
            }
        }
    }
}
