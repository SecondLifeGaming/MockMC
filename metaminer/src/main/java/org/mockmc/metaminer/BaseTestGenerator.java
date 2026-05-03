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
        generateBaseClass();
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

    private void generateBaseClass() throws IOException {
        TypeSpec baseClass = TypeSpec.classBuilder("GeneratedTestBase")
                .addModifiers(Modifier.PUBLIC, Modifier.ABSTRACT)
                .addMethod(MethodSpec.methodBuilder("assertSafeDefault")
                        .addModifiers(Modifier.PROTECTED)
                        .addParameter(Object.class, "value")
                        .addComment("Sanity check for default values. For stubs, we mostly just care that they don't throw.")
                        .build())
                .build();

        JavaFile.builder("org.mockmc.mockmc.generated", baseClass)
                .addFileComment("Base class for mechanical sanity tests.")
                .indent("    ")
                .build()
                .writeTo(testSourceFolder);
    }

    private URLClassLoader createClassLoader() throws IOException {
        List<URL> urls = new ArrayList<>();
        File[] jarFiles = jarDirectory.listFiles((dir, name) -> name.endsWith(".jar"));
        if (jarFiles != null) {
            for (File jar : jarFiles) {
                urls.add(jar.toURI().toURL());
            }
        }
        
        File cacheDir = new File(jarDirectory, "cache");
        File libDir = new File(cacheDir, "libraries");
        if (libDir.exists()) {
            addJarsRecursively(libDir, urls);
        }
        
        return new URLClassLoader(urls.toArray(new URL[0]), getClass().getClassLoader());
    }

    private void addJarsRecursively(File dir, List<URL> urls) throws java.io.IOException {
        File[] files = dir.listFiles();
        if (files == null) return;
        for (File file : files) {
            if (file.isDirectory()) {
                addJarsRecursively(file, urls);
            } else if (file.getName().endsWith(".jar")) {
                urls.add(file.toURI().toURL());
            }
        }
    }

    private Set<Class<?>> scanForClasses(ClassPath classPath) {
        Set<Class<?>> toGenerate = new HashSet<>();
        String[] packagesToScan = {
                "org.bukkit",
                "org.spigotmc",
                "co.aikar.timings",
                "com.destroystokyo.paper",
                "io.papermc.paper",
                "com.velocitypowered.api",
                "net.md_5.bungee",
                "io.github.waterfallmc",
                "com.mojang.brigadier"
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
        if (clazz == null) return false;
        if (!java.lang.reflect.Modifier.isPublic(clazz.getModifiers()) || clazz.isSealed()) return false;
        if (!clazz.isInterface() && !java.lang.reflect.Modifier.isAbstract(clazz.getModifiers())) return false;
        String name = clazz.getName();
        if (name.equals("co.aikar.timings.Timing")) return false;
        return (name.startsWith("org.bukkit.") || name.startsWith("org.spigotmc.") || name.startsWith("co.aikar.timings.") || name.startsWith("com.destroystokyo.paper.") || name.startsWith("io.papermc.paper.") || name.startsWith("com.velocitypowered.api.") || name.startsWith("net.md_5.bungee.") || name.startsWith("io.github.waterfallmc.") || name.startsWith("com.mojang.brigadier."))
                && !name.contains(".craftbukkit.");
    }

    private void generateTestForClass(Class<?> clazz) throws IOException {
        String packageName = getGeneratedPackageName(clazz);
        String rawSimpleName = clazz.getSimpleName();
        if (rawSimpleName.isEmpty()) return;
        String simpleName = rawSimpleName.replace("$", "");
        if (simpleName.isEmpty() || !Character.isJavaIdentifierStart(simpleName.charAt(0))) {
            return;
        }
        String baseMockName = simpleName + "BaseMock";
        ClassName baseMockClass = ClassName.get(packageName, baseMockName);
        
        TypeSpec.Builder testClass = TypeSpec.classBuilder(baseMockName + "Test")
                .superclass(ClassName.get("org.mockmc.mockmc.generated", "GeneratedTestBase"))
                .addModifiers(Modifier.PUBLIC);

        // Scan methods for safe default checks
        List<Method> methods = new ArrayList<>();
        Set<String> checkedMethods = new HashSet<>();
        try {
            for (Method m : clazz.getDeclaredMethods()) {
                if (shouldGenerateSafeDefaultCheck(m, checkedMethods)) {
                    methods.add(m);
                }
            }
        } catch (Exception | LinkageError e) {
            if (LOGGER.isLoggable(java.util.logging.Level.WARNING)) {
                LOGGER.warning(String.format("Could not fully inspect methods for %s: %s", clazz.getName(), e.getMessage()));
            }
        }

        List<TypeVariableName> typeVars = new ArrayList<>();
        for (java.lang.reflect.TypeVariable<? extends Class<?>> tv : clazz.getTypeParameters()) {
            typeVars.add(TypeVariableName.get(tv));
        }

        TypeName superInterface = baseMockClass;
        if (!typeVars.isEmpty()) {
            superInterface = ParameterizedTypeName.get(baseMockClass, typeVars.toArray(new TypeName[0]));
        }

        TypeSpec.Builder stubBuilder = TypeSpec.classBuilder("Stub")
                .addTypeVariables(typeVars)
                .addModifiers(Modifier.PRIVATE, Modifier.STATIC)
                .addSuperinterface(superInterface);

        // Object.clone() is protected. If an interface makes it public, 
        // the class must explicitly override it because the protected Object.clone() 
        // "wins" over the interface default method but doesn't satisfy the public requirement.
        try {
            clazz.getMethod("clone");
            stubBuilder.addMethod(MethodSpec.methodBuilder("clone")
                    .addModifiers(Modifier.PUBLIC)
                    .addAnnotation(Override.class)
                    .returns(clazz)
                    .addStatement("return null")
                    .build());
        } catch (NoSuchMethodException ignored) {
        }

        testClass.addType(stubBuilder.build());

        // Split methods into multiple test parts if there are too many (to satisfy linters)
        int partCount = 0;
        int methodsPerPart = 20;
        for (int i = 0; i < methods.size(); i += methodsPerPart) {
            partCount++;
            String methodName = partCount == 1 ? "testSafeDefaults" : "testSafeDefaultsPart" + partCount;
            MethodSpec.Builder testMethod = MethodSpec.methodBuilder(methodName)
                    .addAnnotation(ClassName.get("org.junit.jupiter.api", "Test"))
                    .addModifiers(Modifier.PUBLIC)
                    .addException(Exception.class);

            String diamond = typeVars.isEmpty() ? "" : "<>";
            testMethod.addStatement("$T mock = new Stub" + diamond + "()", baseMockClass);
            testMethod.addStatement("assertNotNull(mock)");

            int end = Math.min(i + methodsPerPart, methods.size());
            for (int j = i; j < end; j++) {
                testMethod.addStatement("assertSafeDefault(mock.$L())", methods.get(j).getName());
            }
            testClass.addMethod(testMethod.build());
        }

        // Handle case with no methods
        if (methods.isEmpty()) {
            MethodSpec.Builder testMethod = MethodSpec.methodBuilder("testSafeDefaults")
                    .addAnnotation(ClassName.get("org.junit.jupiter.api", "Test"))
                    .addModifiers(Modifier.PUBLIC)
                    .addException(Exception.class);
            String diamond = typeVars.isEmpty() ? "" : "<>";
            testMethod.addStatement("$T mock = new Stub" + diamond + "()", baseMockClass);
            testMethod.addStatement("assertNotNull(mock)");
            testClass.addMethod(testMethod.build());
        }

        JavaFile.builder(packageName, testClass.build())
                .addFileComment("Auto-generated mechanical sanity test for $L", baseMockName)
                .addStaticImport(ClassName.get("org.junit.jupiter.api", "Assertions"), "*")
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
        if (isNmsType(m.getGenericReturnType()) || !isAccessible(m.getGenericReturnType())) {
            return false;
        }
        Class<?> rt = m.getReturnType();
        return !rt.isPrimitive() && rt != void.class;
    }

    private boolean isNmsType(java.lang.reflect.Type type) {
        Class<?> raw = getRawType(type);
        if (raw == null) return false;
        String name = raw.getName();
        return name.contains(".nms.") || name.contains(".internal.");
    }

    private boolean isAccessible(java.lang.reflect.Type type) {
        Class<?> raw = getRawType(type);
        if (raw == null) return true;
        if (raw.isPrimitive()) return true;
        if (raw.isArray()) return isAccessible(raw.getComponentType());

        String name = raw.getName();
        if (name.startsWith("java.") || name.startsWith("javax.") || name.startsWith("com.google.common.") || name.startsWith("com.mojang.brigadier")) return true;

        if (!java.lang.reflect.Modifier.isPublic(raw.getModifiers())) return false;

        Class<?> declaring = raw.getDeclaringClass();
        if (declaring != null && !isAccessible(declaring)) return false;

        if (type instanceof java.lang.reflect.ParameterizedType pt) {
            for (java.lang.reflect.Type arg : pt.getActualTypeArguments()) {
                if (!isAccessible(arg)) return false;
            }
        }
        return true;
    }

    private Class<?> getRawType(java.lang.reflect.Type type) {
        if (type instanceof Class<?> clazz) return clazz;
        if (type instanceof java.lang.reflect.ParameterizedType pt) return getRawType(pt.getRawType());
        if (type instanceof java.lang.reflect.GenericArrayType gat) return getRawType(gat.getGenericComponentType());
        if (type instanceof java.lang.reflect.TypeVariable<?> tv) {
            if (tv.getBounds().length > 0) return getRawType(tv.getBounds()[0]);
            return Object.class;
        }
        if (type instanceof java.lang.reflect.WildcardType wt) {
            if (wt.getUpperBounds().length > 0) return getRawType(wt.getUpperBounds()[0]);
            return Object.class;
        }
        return null;
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
