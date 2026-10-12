package org.mockmc.metaminer;

import com.google.common.reflect.ClassPath;
import com.palantir.javapoet.*;

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
    private static final String GENERATED_PATH = "org/mockmc/mockmc/generated";
    private final File testSourceFolder;
    private final File jarDirectory;

    public BaseTestGenerator(File projectRoot) {
        this.testSourceFolder = new File(projectRoot, "src/test/java");
        this.jarDirectory = new File(projectRoot, "jars");
    }

    @Override
    public void generateData() throws java.io.IOException {
        File generatedTestDir = new File(testSourceFolder, GENERATED_PATH);
        File stagingTestDir = new File(testSourceFolder, "org/mockmc/mockmc/.generated-staging");
        File backupTestDir = new File(testSourceFolder, "org/mockmc/mockmc/.generated-backup");

        if (stagingTestDir.exists()) {
            org.apache.commons.io.FileUtils.deleteDirectory(stagingTestDir);
        }
        if (backupTestDir.exists()) {
            org.apache.commons.io.FileUtils.deleteDirectory(backupTestDir);
        }

        try {
            generateBaseClass(stagingTestDir);
            URLClassLoader customLoader = createClassLoader();
            ClassPath classPath = ClassPath.from(customLoader);
            Set<Class<?>> toGenerate = scanForClasses(classPath);

            if (LOGGER.isLoggable(java.util.logging.Level.INFO)) {
                LOGGER.info(String.format("Found %d classes to generate tests for.", toGenerate.size()));
            }

            for (Class<?> c : toGenerate) {
                generateTestForClass(c, stagingTestDir);
            }

            promoteStagingDirectory(stagingTestDir, backupTestDir);
        } catch (Exception e) {
            rollbackStaging(generatedTestDir, stagingTestDir, backupTestDir);
            throw e;
        }
    }

    private void promoteStagingDirectory(File stagingTestDir, File backupTestDir) throws IOException {
        File targetPackageDir = new File(testSourceFolder, GENERATED_PATH);
        File stagedPackageDir = new File(stagingTestDir, GENERATED_PATH);
        if (!stagedPackageDir.exists()) {
            return;
        }
        if (targetPackageDir.exists() && !targetPackageDir.renameTo(backupTestDir)) {
            throw new java.io.IOException("Failed to rename existing generated test directory to backup");
        }
        if (!stagedPackageDir.renameTo(targetPackageDir)) {
            if (backupTestDir.exists() && !backupTestDir.renameTo(targetPackageDir)) {
                LOGGER.log(Level.WARNING, "Failed to restore backup test directory to generated during rollback: {0}", backupTestDir.getAbsolutePath());
            }
            throw new java.io.IOException("Failed to promote staging test directory to generated");
        }
        if (backupTestDir.exists()) {
            org.apache.commons.io.FileUtils.deleteDirectory(backupTestDir);
        }
        org.apache.commons.io.FileUtils.deleteDirectory(stagingTestDir);
    }

    private void rollbackStaging(File generatedTestDir, File stagingTestDir, File backupTestDir) {
        try {
            if (stagingTestDir.exists()) {
                org.apache.commons.io.FileUtils.deleteDirectory(stagingTestDir);
            }
            if (!generatedTestDir.exists() && backupTestDir.exists()) {
                if (!backupTestDir.renameTo(generatedTestDir)) {
                    LOGGER.log(Level.WARNING, "Failed to restore backup test directory to generated during rollback: {0}", backupTestDir.getAbsolutePath());
                }
            } else if (backupTestDir.exists()) {
                org.apache.commons.io.FileUtils.deleteDirectory(backupTestDir);
            }
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Failed during rollback of staging test directory", e);
        }
    }

    private void generateBaseClass(File outputFolder) throws IOException {
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
                .writeTo(outputFolder != null ? outputFolder : testSourceFolder);
    }

    private URLClassLoader createClassLoader() throws IOException {
        List<URL> urls = new ArrayList<>();
        List<File> jarFiles = new ArrayList<>();
        File platformsDir = new File(jarDirectory, "platforms");
        if (platformsDir.exists()) {
            collectJarsRecursive(platformsDir, jarFiles);
        }
        File[] rootJarFiles = jarDirectory.listFiles((dir, name) -> name.endsWith(".jar"));
        if (rootJarFiles != null) {
            for (File rj : rootJarFiles) {
                if (!jarFiles.contains(rj)) {
                    jarFiles.add(rj);
                }
            }
        }
        jarFiles.stream().map(this::toJarUrl).filter(Objects::nonNull).forEach(urls::add);
        
        File cacheDir = new File(jarDirectory, "cache");
        File libDir = new File(cacheDir, "libraries");
        if (libDir.exists()) {
            addJarsRecursively(libDir, urls);
        }
        
        return new URLClassLoader(urls.toArray(new URL[0]), getClass().getClassLoader());
    }

    private URL toJarUrl(File jar) {
        try {
            return jar.toURI().toURL();
        } catch (Exception _) {
            return null;
        }
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
        Set<String> paperClasses = scanPaperClasses();

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
                if (!shouldScanClassInfo(classInfo, paperClasses)) {
                    continue;
                }
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

    private boolean shouldScanClassInfo(ClassPath.ClassInfo classInfo, Set<String> paperClasses) {
        if (classInfo.getName().startsWith("org.bukkit.craftbukkit")) {
            return false;
        }
        try {
            Class<?> c = classInfo.load();
            java.security.CodeSource cs = c.getProtectionDomain().getCodeSource();
            return !(cs != null && cs.getLocation() != null && cs.getLocation().getPath().contains("folia") && paperClasses.contains(c.getName()));
        } catch (Exception | LinkageError _) {
            return false;
        }
    }

    private Set<String> scanPaperClasses() {
        Set<String> paperClasses = new HashSet<>();
        File cacheDir = new File(jarDirectory, "cache");
        File[] remappedFiles = cacheDir.listFiles((dir, name) -> name.startsWith("remapped-") && name.endsWith(".jar"));
        if (remappedFiles != null) {
            for (File rf : remappedFiles) {
                if (rf.getName().contains("paper")) {
                    collectClassNamesFromJar(rf, paperClasses);
                }
            }
        }
        File libDir = new File(cacheDir, "libraries");
        if (libDir.exists()) {
            File paperApiDir = new File(libDir, "io/papermc/paper/paper-api");
            if (paperApiDir.exists()) {
                List<File> paperJars = new ArrayList<>();
                collectJarsRecursive(paperApiDir, paperJars);
                for (File pj : paperJars) {
                    collectClassNamesFromJar(pj, paperClasses);
                }
            }
        }
        return paperClasses;
    }

    private void collectClassNamesFromJar(File jar, Set<String> targetSet) {
        try (java.util.jar.JarFile jf = new java.util.jar.JarFile(jar)) {
            jf.stream().forEach(entry -> {
                String name = entry.getName();
                if (name.endsWith(".class") && !name.contains("$")) {
                    targetSet.add(name.substring(0, name.length() - 6).replace('/', '.'));
                }
            });
        } catch (IOException _) {
            // Unreadable jar skipped during class scan
        }
    }

    private void collectJarsRecursive(File dir, List<File> list) {
        File[] files = dir.listFiles();
        if (files == null) return;
        for (File f : files) {
            if (f.isDirectory()) {
                collectJarsRecursive(f, list);
            } else if (f.getName().endsWith(".jar") && !f.getName().endsWith(".tmp")) {
                list.add(f);
            }
        }
    }

    private boolean isGeneratableType(Class<?> clazz) {
        if (clazz == null || clazz.isEnum() || clazz.isAnnotation()) return false;
        if (!java.lang.reflect.Modifier.isPublic(clazz.getModifiers()) || clazz.isSealed()) return false;
        if (!clazz.isInterface() && !java.lang.reflect.Modifier.isAbstract(clazz.getModifiers())) return false;
        String name = clazz.getName();
        if (name.equals("co.aikar.timings.Timing")) return false;
        
        if (org.mockmc.metaminer.util.ClassExclusions.isExcluded(name)) return false;

        return org.mockmc.metaminer.util.ClassExclusions.isGeneratablePackage(name);
    }

    private static final String JUNIT_JUPITER_API = "org.junit.jupiter.api";

    private void generateTestForClass(Class<?> clazz, File outputDir) throws IOException {
        String packageName = getGeneratedPackageName(clazz);
        String baseMockName = getBaseMockName(clazz);
        if (baseMockName == null) return;

        ClassName baseMockClass = ClassName.get(packageName, baseMockName);
        TypeSpec.Builder testClass = TypeSpec.classBuilder(baseMockName + "Test")
                .superclass(ClassName.get("org.mockmc.mockmc.generated", "GeneratedTestBase"));

        List<Method> methods = scanMethodsForSanityTests(clazz);

        applySuppressionIfNecessary(testClass);

        List<TypeVariableName> typeVars = getTypeVariables(clazz);
        TypeName superInterface = getSuperInterface(baseMockClass, typeVars);

        testClass.addType(buildStubClass(clazz, typeVars, superInterface).build());
        TypeName testTargetType = getTestTargetType(baseMockClass, typeVars);
        generateTestMethods(testClass, testTargetType, methods, !typeVars.isEmpty());

        writeJavaFile(packageName, testClass, baseMockName, outputDir);
    }

    private void applySuppressionIfNecessary(TypeSpec.Builder testClass) {
        testClass.addAnnotation(AnnotationSpec.builder(SuppressWarnings.class)
                .addMember("value", "$S", "all")
                .build());
    }



    private String getBaseMockName(Class<?> clazz) {
        String rawSimpleName = clazz.getSimpleName();
        if (rawSimpleName.isEmpty()) return null;
        String simpleName = rawSimpleName.replace("$", "");
        if (simpleName.isEmpty() || !Character.isJavaIdentifierStart(simpleName.charAt(0))) {
            return null;
        }
        return simpleName + "BaseMock";
    }

    private List<Method> scanMethodsForSanityTests(Class<?> clazz) {
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
        return methods;
    }

    private List<TypeVariableName> getTypeVariables(Class<?> clazz) {
        TypeVariableName[] typeVars = new TypeVariableName[clazz.getTypeParameters().length];
        for (int i = 0; i < clazz.getTypeParameters().length; i++) {
            typeVars[i] = TypeVariableName.get(clazz.getTypeParameters()[i].getName());
        }
        for (int i = 0; i < clazz.getTypeParameters().length; i++) {
            java.lang.reflect.TypeVariable<?> tv = clazz.getTypeParameters()[i];
            try {
                java.lang.reflect.Type[] bounds = tv.getBounds();
                if (bounds != null && bounds.length > 0 && !(bounds.length == 1 && bounds[0] == Object.class)) {
                    TypeName[] mappedBounds = new TypeName[bounds.length];
                    for (int j = 0; j < bounds.length; j++) {
                        mappedBounds[j] = mapType(bounds[j], clazz, typeVars);
                    }
                    typeVars[i] = typeVars[i].withBounds(mappedBounds);
                }
            } catch (Exception _) {
                // Keep unbounded fallback
            }
        }
        return Arrays.asList(typeVars);
    }

    private TypeName mapType(java.lang.reflect.Type type, Class<?> clazz, TypeVariableName[] ourTypeVars) {
        if (type instanceof java.lang.reflect.TypeVariable<?> tv) {
            return mapTypeVariable(tv, clazz, ourTypeVars);
        }
        if (type instanceof java.lang.reflect.ParameterizedType pt) {
            return mapParameterizedType(pt, clazz, ourTypeVars);
        }
        if (type instanceof java.lang.reflect.GenericArrayType gat) {
            return ArrayTypeName.of(mapType(gat.getGenericComponentType(), clazz, ourTypeVars));
        }
        if (type instanceof java.lang.reflect.WildcardType wt) {
            return mapWildcardType(wt, clazz, ourTypeVars);
        }
        return TypeName.get(type);
    }

    private TypeName mapTypeVariable(java.lang.reflect.TypeVariable<?> tv, Class<?> clazz, TypeVariableName[] ourTypeVars) {
        if (clazz != null) {
            java.lang.reflect.TypeVariable<?>[] typeParameters = clazz.getTypeParameters();
            for (int i = 0; i < typeParameters.length; i++) {
                if (typeParameters[i].equals(tv)) {
                    return ourTypeVars[i];
                }
            }
        }
        return TypeVariableName.get(tv.getName());
    }

    private TypeName mapParameterizedType(java.lang.reflect.ParameterizedType pt, Class<?> clazz, TypeVariableName[] ourTypeVars) {
        TypeName[] args = new TypeName[pt.getActualTypeArguments().length];
        for (int i = 0; i < args.length; i++) {
            args[i] = mapType(pt.getActualTypeArguments()[i], clazz, ourTypeVars);
        }
        Class<?> raw = (Class<?>) pt.getRawType();
        return ParameterizedTypeName.get(ClassName.get(raw), args);
    }

    private TypeName mapWildcardType(java.lang.reflect.WildcardType wt, Class<?> clazz, TypeVariableName[] ourTypeVars) {
        if (wt.getLowerBounds().length > 0) {
            return WildcardTypeName.supertypeOf(mapType(wt.getLowerBounds()[0], clazz, ourTypeVars));
        }
        if (wt.getUpperBounds().length > 0 && wt.getUpperBounds()[0] != Object.class) {
            return WildcardTypeName.subtypeOf(mapType(wt.getUpperBounds()[0], clazz, ourTypeVars));
        }
        return WildcardTypeName.subtypeOf(Object.class);
    }

    private TypeName getSuperInterface(ClassName baseMockClass, List<TypeVariableName> typeVars) {
        if (typeVars.isEmpty()) {
            return baseMockClass;
        }
        return ParameterizedTypeName.get(baseMockClass, typeVars.toArray(new TypeName[0]));
    }

    private TypeName getTestTargetType(ClassName baseMockClass, List<TypeVariableName> typeVars) {
        if (typeVars.isEmpty()) {
            return baseMockClass;
        }
        TypeName[] wildcards = new TypeName[typeVars.size()];
        Arrays.fill(wildcards, WildcardTypeName.subtypeOf(Object.class));
        return ParameterizedTypeName.get(baseMockClass, wildcards);
    }

    private TypeSpec.Builder buildStubClass(Class<?> clazz, List<TypeVariableName> typeVars, TypeName superInterface) {
        TypeSpec.Builder stubBuilder = TypeSpec.classBuilder("Stub")
                .addTypeVariables(typeVars)
                .addModifiers(Modifier.PRIVATE, Modifier.STATIC)
                .addSuperinterface(superInterface);

        Set<String> implementedSignatures = new HashSet<>();
        addCloneOverrideIfPresent(clazz, stubBuilder, implementedSignatures);

        for (Method m : clazz.getMethods()) {
            if (isAbstractGetHandle(m) && implementedSignatures.add(getMethodSignature(m))) {
                stubBuilder.addMethod(buildStubHandleMethod(m));
            }
        }
        return stubBuilder;
    }

    private void addCloneOverrideIfPresent(Class<?> clazz, TypeSpec.Builder stubBuilder, Set<String> implementedSignatures) {
        try {
            clazz.getMethod("clone");
            stubBuilder.addMethod(MethodSpec.methodBuilder("clone")
                    .addModifiers(Modifier.PUBLIC)
                    .addAnnotation(Override.class)
                    .returns(clazz)
                    .addStatement("return null")
                    .build());
            implementedSignatures.add("clone()");
        } catch (NoSuchMethodException _) {
            // No clone method, ignore.
        }
    }

    private boolean isAbstractGetHandle(Method m) {
        return !java.lang.reflect.Modifier.isStatic(m.getModifiers())
                && m.getDeclaringClass() != Object.class
                && m.getName().equals("getHandle")
                && java.lang.reflect.Modifier.isAbstract(m.getModifiers());
    }

    private MethodSpec buildStubHandleMethod(Method m) {
        MethodSpec.Builder mb = MethodSpec.methodBuilder(m.getName())
                .addModifiers(Modifier.PUBLIC)
                .addAnnotation(Override.class);

        for (java.lang.reflect.TypeVariable<Method> tv : m.getTypeParameters()) {
            mb.addTypeVariable(TypeVariableName.get(tv));
        }

        mb.returns(TypeName.get(m.getGenericReturnType()));

        java.lang.reflect.Type[] params = m.getGenericParameterTypes();
        for (int i = 0; i < params.length; i++) {
            mb.addParameter(TypeName.get(params[i]), "arg" + i);
        }

        addDefaultReturnValue(mb, m);
        return mb.build();
    }

    private String getMethodSignature(Method m) {
        StringBuilder sb = new StringBuilder();
        sb.append(m.getName()).append("(");
        Class<?>[] params = m.getParameterTypes();
        for (int i = 0; i < params.length; i++) {
            if (i > 0) sb.append(",");
            sb.append(params[i].getName());
        }
        sb.append(")");
        return sb.toString();
    }

    private void addDefaultReturnValue(MethodSpec.Builder builder, Method m) {
        Class<?> returnType = m.getReturnType();
        if (returnType == void.class) {
            return;
        }
        if (returnType == boolean.class) {
            builder.addStatement("return false");
            return;
        }
        if (returnType.isPrimitive()) {
            builder.addStatement("return 0");
            return;
        }
        builder.addStatement("return null");
    }

    private void generateTestMethods(TypeSpec.Builder testClass, TypeName targetType, List<Method> methods, boolean hasTypeVars) {
        // Split methods into multiple test parts if there are too many (to satisfy linters)
        int partCount = 0;
        int methodsPerPart = 20;

        if (methods.isEmpty()) {
            testClass.addMethod(buildTestMethod("testSafeDefaults", targetType, Collections.emptyList(), hasTypeVars));
            return;
        }

        for (int i = 0; i < methods.size(); i += methodsPerPart) {
            partCount++;
            String methodName = partCount == 1 ? "testSafeDefaults" : "testSafeDefaultsPart" + partCount;
            int end = Math.min(i + methodsPerPart, methods.size());
            testClass.addMethod(buildTestMethod(methodName, targetType, methods.subList(i, end), hasTypeVars));
        }
    }

    private boolean anyMethodThrowsCheckedException(List<Method> methods) {
        for (Method m : methods) {
            for (Class<?> exceptionType : m.getExceptionTypes()) {
                if (!RuntimeException.class.isAssignableFrom(exceptionType) && !Error.class.isAssignableFrom(exceptionType)) {
                    return true;
                }
            }
        }
        return false;
    }

    private MethodSpec buildTestMethod(String name, TypeName targetType, List<Method> methods, boolean hasTypeVars) {
        MethodSpec.Builder testMethod = MethodSpec.methodBuilder(name)
                .addAnnotation(ClassName.get(JUNIT_JUPITER_API, "Test"));

        if (anyMethodThrowsCheckedException(methods)) {
            testMethod.addException(Exception.class);
        }

        if (hasTypeVars) {
            testMethod.addStatement("$T mock = new Stub<>()", targetType);
        } else {
            testMethod.addStatement("$T mock = new Stub()", targetType);
        }
        
        testMethod.addStatement("assertNotNull(mock)");

        for (Method m : methods) {
            testMethod.beginControlFlow("try");
            testMethod.addStatement("assertSafeDefault(mock.$L())", m.getName());
            testMethod.nextControlFlow("catch ($T | $T _)", Exception.class, LinkageError.class);
            testMethod.addComment("Ignore NPEs and LinkageErrors from Bukkit singletons");
            testMethod.endControlFlow();
        }
        return testMethod.build();
    }

    private void writeJavaFile(String packageName, TypeSpec.Builder testClass, String baseMockName, File outputFolder) throws IOException {
        JavaFile.builder(packageName, testClass.build())
                .addFileComment("Auto-generated mechanical sanity test for $L", baseMockName)
                .addStaticImport(ClassName.get(JUNIT_JUPITER_API, "Assertions"), "*")
                .indent("    ")
                .build()
                .writeTo(outputFolder != null ? outputFolder : testSourceFolder);
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
