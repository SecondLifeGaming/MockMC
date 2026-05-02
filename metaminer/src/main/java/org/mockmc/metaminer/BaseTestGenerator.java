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
    public void generateData() throws java.io.IOException {
        URLClassLoader customLoader = createClassLoader();
        ClassPath classPath = ClassPath.from(customLoader);
        Set<Class<?>> toGenerate = scanForClasses(classPath);

        if (LOGGER.isLoggable(java.util.logging.Level.INFO)) {
            LOGGER.info(String.format("Found %d classes to generate tests for.", toGenerate.size()));
        }

        for (Class<?> c : toGenerate) {
            generateTestForClass(c);
        }
    }

    private URLClassLoader createClassLoader() throws IOException {
        List<URL> urls = new ArrayList<>();
        File[] jarFiles = jarDirectory.listFiles((dir, name) -> name.endsWith(".jar"));
        if (jarFiles != null) {
            for (File jar : jarFiles) {
                urls.add(jar.toURI().toURL());
            }
        }
        return new URLClassLoader(urls.toArray(new URL[0]), getClass().getClassLoader());
    }

    private Set<Class<?>> scanForClasses(ClassPath classPath) {
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
                if (classInfo.getName().startsWith("org.bukkit.craftbukkit")) continue;
                try {
                    Class<?> c = classInfo.load();
                    if (isGeneratableType(c)) {
                        toGenerate.add(c);
                    }
                } catch (Exception | LinkageError _) {
                    // Ignore loading errors
                }
            }
        }
        return toGenerate;
    }

    private boolean isGeneratableType(Class<?> clazz) {
        if (clazz == null || clazz.isSealed() || !java.lang.reflect.Modifier.isPublic(clazz.getModifiers())) return false;
        String name = clazz.getName();
        return (name.startsWith("org.bukkit.") || name.startsWith("com.destroystokyo.paper.") || name.startsWith("io.papermc.paper.") || name.startsWith("com.velocitypowered.api.") || name.startsWith("net.md_5.bungee.") || name.startsWith("io.github.waterfallmc."))
                && !name.contains(".craftbukkit.");
    }

    private void generateTestForClass(Class<?> clazz) throws IOException {
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
        testMethod.addStatement("assertNotNull(mock)");

        // Scan methods for safe default checks
        Set<String> checkedMethods = new HashSet<>();
        try {
            for (Method m : clazz.getDeclaredMethods()) {
                if (shouldGenerateSafeDefaultCheck(m, checkedMethods)) {
                    testMethod.addStatement("assertSafeDefault(mock.$L())", m.getName());
                }
            }
        } catch (Exception | LinkageError e) {
            if (LOGGER.isLoggable(java.util.logging.Level.WARNING)) {
                LOGGER.warning(String.format("Could not fully inspect methods for %s: %s", clazz.getName(), e.getMessage()));
            }
        }

        testClass.addMethod(testMethod.build());

        JavaFile.builder(packageName, testClass.build())
                .addFileComment("Auto-generated mechanical sanity test for $L", baseMockName)
                .indent("    ")
                .build()
                .writeTo(testSourceFolder);
    }
    private boolean shouldGenerateSafeDefaultCheck(Method m, Set<String> checkedMethods) {
        if (!java.lang.reflect.Modifier.isPublic(m.getModifiers()) || java.lang.reflect.Modifier.isStatic(m.getModifiers())) {
            return false;
        }
        if (m.getParameterCount() != 0 || !checkedMethods.add(m.getName())) {
            return false;
        }
        Class<?> rt = m.getReturnType();
        return !rt.isPrimitive() && rt != void.class;
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
