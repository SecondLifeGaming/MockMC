package org.mockmc.metaminer;

import com.google.common.reflect.ClassPath;
import com.squareup.javapoet.*;

import javax.lang.model.element.Modifier;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Generates mechanical sanity tests for all BaseMock interfaces.
 */
public class BaseTestGenerator implements DataGenerator {
    private static final Logger LOGGER = Logger.getLogger(BaseTestGenerator.class.getName());
    private final File testSourceFolder;
    private final File jarDirectory;

    public BaseTestGenerator(File projectRoot) {
        this.testSourceFolder = new File(projectRoot, "src/test/java");
        this.jarDirectory = new File(projectRoot, "jars");
    }

    @Override
    public void generateData() throws IOException {
        List<URL> urls = new ArrayList<>();
        File[] jarFiles = jarDirectory.listFiles((dir, name) -> name.endsWith(".jar"));
        if (jarFiles != null) {
            for (File jar : jarFiles) {
                urls.add(jar.toURI().toURL());
            }
        }

        URLClassLoader customLoader = new URLClassLoader(urls.toArray(new URL[0]), getClass().getClassLoader());
        ClassPath classPath = ClassPath.from(customLoader);
        Set<Class<?>> toGenerate = new HashSet<>();
        String[] packagesToScan = {
                "org.bukkit",
                "com.destroystokyo.paper",
                "io.papermc.paper",
                "com.velocitypowered.api",
                "net.md_5.bungee",
                "io.github.waterfallmc"
        };
        for (String pkg : packagesToScan) {
            for (ClassPath.ClassInfo classInfo : classPath.getTopLevelClassesRecursive(pkg)) {
                try {
                    if (classInfo.getName().startsWith("org.bukkit.craftbukkit")) continue;
                    Class<?> c = classInfo.load();
                    if (isGeneratableType(c)) {
                        toGenerate.add(c);
                    }
                } catch (Exception | LinkageError _) {
                }
            }
        }

        LOGGER.info("Found " + toGenerate.size() + " classes to generate tests for.");
        for (Class<?> c : toGenerate) {
            generateTestForClass(c);
        }
    }

    private boolean isGeneratableType(Class<?> clazz) {
        if (clazz == null || clazz.isSealed() || !java.lang.reflect.Modifier.isPublic(clazz.getModifiers())) return false;
        String name = clazz.getName();
        return (name.startsWith("org.bukkit.") || name.startsWith("com.destroystokyo.paper.") || name.startsWith("io.papermc.paper.") || name.startsWith("com.velocitypowered.api.") || name.startsWith("net.md_5.bungee.") || name.startsWith("io.github.waterfallmc."))
                && !name.contains(".craftbukkit.");
    }

    private void generateTestForClass(Class<?> clazz) throws IOException {
        String bukkitPackage = clazz.getPackageName();
        String packageName = getGeneratedPackageName(clazz);
        String simpleName = clazz.getSimpleName().replace("$", "");
        String baseMockName = simpleName + "BaseMock";
        ClassName baseMockClass = ClassName.get(packageName, baseMockName);
        
        TypeSpec.Builder testClass = TypeSpec.classBuilder(baseMockName + "Test")
                .superclass(ClassName.get("org.mockmc.mockmc.generated", "GeneratedTestBase"))
                .addModifiers(Modifier.PUBLIC);

        MethodSpec.Builder testMethod = MethodSpec.methodBuilder("testSafeDefaults")
                .addAnnotation(ClassName.get("org.junit.jupiter.api", "Test"))
                .addModifiers(Modifier.PUBLIC);

        // Instantiate via anonymous class if it's an interface (which BaseMocks are)
        testMethod.addStatement("$T mock = new $T() {}", baseMockClass, baseMockClass);

        // Scan methods for safe default checks
        Set<String> checkedMethods = new HashSet<>();
        try {
            for (Method m : clazz.getDeclaredMethods()) {
                if (!java.lang.reflect.Modifier.isPublic(m.getModifiers())) continue;
                if (java.lang.reflect.Modifier.isStatic(m.getModifiers())) continue;
                if (checkedMethods.add(m.getName()) && m.getParameterCount() == 0) {
                    Class<?> rt = m.getReturnType();
                    if (!rt.isPrimitive() && rt != void.class) {
                        testMethod.addStatement("assertSafeDefault(mock.$L())", m.getName());
                    }
                }
            }
        } catch (Throwable t) {
            LOGGER.warning("Could not fully inspect methods for " + clazz.getName() + ": " + t.getMessage());
        }

        testClass.addMethod(testMethod.build());

        JavaFile.builder(packageName, testClass.build())
                .addFileComment("Auto-generated mechanical sanity test for $L", baseMockName)
                .indent("    ")
                .build()
                .writeTo(testSourceFolder);
    }
    private String getGeneratedPackageName(Class<?> clazz) {
        String pkg = clazz.getPackageName();
        String platform = "server";
        if (pkg.startsWith("com.velocitypowered") || pkg.startsWith("net.md_5.bungee") || pkg.startsWith("io.github.waterfallmc")) {
            platform = "proxy";
        }
        return "org.mockmc.mockmc.generated." + platform + "." + pkg;
    }
}
