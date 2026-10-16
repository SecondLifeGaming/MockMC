package org.mockmc.metaminer;

import com.google.common.reflect.ClassPath;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.palantir.javapoet.*;
import javax.lang.model.element.Modifier;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.*;
import java.lang.reflect.AnnotatedElement;
import java.net.URL;
import java.net.URLClassLoader;
import java.security.CodeSource;
import java.util.*;
import java.util.jar.JarFile;
import java.util.logging.Level;
import java.util.logging.Logger;

public class BaseMockGenerator implements DataGenerator {
    private static final Logger LOGGER = Logger.getLogger(BaseMockGenerator.class.getName());
    private static final String ADDITIONAL_SUPPRESSIONS = "additionalSuppressions";
    private static final String EXCLUDE_INTERFACES = "excludeInterfaces";
    private static final String EXCLUDE_METHODS = "excludeMethods";
    private static final String METHOD_QUIRKS = "methodQuirks";
    private static final String REPLACEMENT = "replacement";
    private static final String SUPPRESS_RAWTYPES = "rawtypes";

    private static final String SINCE = "since";
    private static final String FOR_REMOVAL = "forRemoval";
    private static final String FOLIA = "folia";
    private static final String PAPER = "paper";
    private static final String GENERATED_PATH = "org/mockmc/mockmc/generated";
    private static final String RETURN_NULL = "return null";
    private static final String BRIGADIER_PACKAGE = "com.mojang.brigadier";

    private final File dataFolder;
    private final File jarDirectory;
    private JsonObject quirks;

    public BaseMockGenerator(File pluginFolder) {
        this.dataFolder = new File(pluginFolder, "src/main/java");
        this.jarDirectory = new File(pluginFolder, "jars");
        loadQuirks(pluginFolder);
    }

    private void loadQuirks(File projectRoot) {
        File quirksFile = new File(projectRoot, "metaminer/src/main/resources/quirks.json");
        if (quirksFile.exists()) {
            try (FileReader reader = new FileReader(quirksFile)) {
                this.quirks = new Gson().fromJson(reader, JsonObject.class);
            } catch (IOException e) {
                LOGGER.log(Level.SEVERE, "Failed to load quirks.json", e);
            }
        }
    }

    private String getMockName(Class<?> raw) {
        if (raw.getEnclosingClass() != null) {
            return getMockName(raw.getEnclosingClass()) + raw.getSimpleName();
        }
        return raw.getSimpleName().replace("$", "");
    }

    private TypeName mapType(java.lang.reflect.Type type, Class<?> clazz, TypeVariableName[] ourTypeVars, Map<TypeVariable<?>, java.lang.reflect.Type> typeMap) {
        if (type instanceof TypeVariable<?> tv) {
            return mapTypeVariable(tv, clazz, ourTypeVars, typeMap);
        }
        if (type instanceof java.lang.reflect.ParameterizedType pt) {
            return mapParameterizedType(pt, clazz, ourTypeVars, typeMap);
        }
        if (type instanceof java.lang.reflect.WildcardType wt) {
            return mapWildcardType(wt, clazz, ourTypeVars, typeMap);
        }
        if (type instanceof java.lang.reflect.GenericArrayType gat) {
            return ArrayTypeName.of(mapType(gat.getGenericComponentType(), clazz, ourTypeVars, typeMap));
        }
        return mapRawOrOther(type);
    }

    private TypeName mapTypeVariable(TypeVariable<?> tv, Class<?> clazz, TypeVariableName[] ourTypeVars, Map<TypeVariable<?>, java.lang.reflect.Type> typeMap) {
        if (clazz != null) {
            TypeVariable<?>[] typeParameters = clazz.getTypeParameters();
            for (int i = 0; i < typeParameters.length; i++) {
                if (typeParameters[i].equals(tv)) {
                    return ourTypeVars[i];
                }
            }
        }
        if (typeMap != null && typeMap.containsKey(tv)) {
            return mapType(typeMap.get(tv), clazz, ourTypeVars, typeMap);
        }
        
        // Unknown or method-level type variable. Map its bounds recursively to handle class-level substitution.
        try {
            java.lang.reflect.Type[] bounds = tv.getBounds();
            if (bounds == null || bounds.length == 0 || (bounds.length == 1 && bounds[0] == Object.class)) {
                return TypeVariableName.get(tv.getName());
            }
            TypeName[] mappedBounds = new TypeName[bounds.length];
            for (int i = 0; i < bounds.length; i++) {
                mappedBounds[i] = mapType(bounds[i], clazz, ourTypeVars, typeMap);
            }
            return TypeVariableName.get(tv.getName(), mappedBounds);
        } catch (Exception _) {
            return TypeVariableName.get(tv.getName());
        }
    }

    private TypeName mapParameterizedType(java.lang.reflect.ParameterizedType pt, Class<?> clazz, TypeVariableName[] ourTypeVars, Map<TypeVariable<?>, java.lang.reflect.Type> typeMap) {
        TypeName[] args = new TypeName[pt.getActualTypeArguments().length];
        for (int i = 0; i < args.length; i++) {
            args[i] = mapType(pt.getActualTypeArguments()[i], clazz, ourTypeVars, typeMap);
        }
        Class<?> raw = getRawType(pt);
        return ParameterizedTypeName.get(ClassName.get(raw), args);
    }

    private TypeName mapWildcardType(java.lang.reflect.WildcardType wt, Class<?> clazz, TypeVariableName[] ourTypeVars, Map<TypeVariable<?>, java.lang.reflect.Type> typeMap) {
        if (wt.getLowerBounds().length > 0) {
            return WildcardTypeName.supertypeOf(mapType(wt.getLowerBounds()[0], clazz, ourTypeVars, typeMap));
        }
        if (wt.getUpperBounds().length > 0 && wt.getUpperBounds()[0] != Object.class) {
            return WildcardTypeName.subtypeOf(mapType(wt.getUpperBounds()[0], clazz, ourTypeVars, typeMap));
        }
        return WildcardTypeName.subtypeOf(Object.class);
    }

    private TypeName mapRawOrOther(java.lang.reflect.Type type) {
        Class<?> rawType = getRawType(type);
        if (rawType != null && isGeneratableType(rawType)) {
            if (org.bukkit.entity.HumanEntity.class.isAssignableFrom(rawType)) {
                LOGGER.log(Level.FINE, "mapType for HumanEntity returning: {0}", TypeName.get(type));
            }
            return TypeName.get(type);
        }
        try {
            return TypeName.get(type);
        } catch (IllegalArgumentException _) {
            return ClassName.get(Object.class);
        }
    }

    private Map<TypeVariable<?>, java.lang.reflect.Type> buildTypeMap(Class<?> clazz) {
        Map<TypeVariable<?>, java.lang.reflect.Type> map = new HashMap<>();
        java.util.Queue<java.lang.reflect.Type> queue = new java.util.LinkedList<>();
        queue.add(clazz);
        while (!queue.isEmpty()) {
            java.lang.reflect.Type t = queue.poll();
            Class<?> raw = getRawType(t);
            if (raw == null) continue;

            if (t instanceof java.lang.reflect.ParameterizedType pt) {
                TypeVariable<?>[] vars = raw.getTypeParameters();
                java.lang.reflect.Type[] args = pt.getActualTypeArguments();
                for (int i = 0; i < Math.min(vars.length, args.length); i++) {
                    map.put(vars[i], args[i]);
                }
            }

            java.lang.reflect.Type[] interfaces = new java.lang.reflect.Type[0];
            try {
                interfaces = raw.getGenericInterfaces();
            } catch (Exception _) {
                // Linkage errors can happen if optional dependencies (like Brigadier) are missing.
                // We ignore these as we only care about interfaces we can actually load.
            }
            Collections.addAll(queue, interfaces);
            if (raw.getGenericSuperclass() != null) {
                queue.add(raw.getGenericSuperclass());
            }
        }
        return map;
    }

    private TypeName getMirroredTypeName(java.lang.reflect.Type type, Class<?> clazz, TypeVariableName[] ourTypeVars, Map<TypeVariable<?>, java.lang.reflect.Type> typeMap) {
        Class<?> raw = getRawType(type);
        if (raw == null) {
            return TypeName.get(type);
        }
        if (raw.isSealed()) {
            return TypeName.get(type);
        }
        String packageName = getGeneratedPackageName(raw);
        String name = getMockName(raw);
        ClassName mirroredRaw = ClassName.get(packageName, name + "BaseMock");
        if (type instanceof java.lang.reflect.ParameterizedType pt) {
            TypeName[] args = new TypeName[pt.getActualTypeArguments().length];
            for (int i = 0; i < args.length; i++) {
                args[i] = mapType(pt.getActualTypeArguments()[i], clazz, ourTypeVars, typeMap);
            }
            return ParameterizedTypeName.get(mirroredRaw, args);
        }
        return mirroredRaw;
    }

    private ClassLoader primaryLoader;
    private ClassLoader customLoader;

    private final Set<String> paperClassNames = new HashSet<>();
    private final Set<String> foliaClassNames = new HashSet<>();

    @Override
    public void generateData() throws java.io.IOException {
        File generatedDir = new File(dataFolder, GENERATED_PATH);
        File stagingDir = new File(dataFolder, "org/mockmc/mockmc/.generated-staging");
        File backupDir = new File(dataFolder, "org/mockmc/mockmc/.generated-backup");

        if (stagingDir.exists()) {
            org.apache.commons.io.FileUtils.deleteDirectory(stagingDir);
        }
        // An interrupted previous run may have left the last known-good tree only in the backup directory.
        org.mockmc.metaminer.util.AtomicDirectorySwap.recover(generatedDir, backupDir);

        try {
            File cacheDir = new File(jarDirectory, "cache");
            List<URL> urls = processJars(cacheDir);

            sanitizeApiJars(cacheDir);
            populateBytecodeClassNames(cacheDir);

            File libDir = new File(cacheDir, "libraries");
            if (libDir.exists()) {
                addJarsRecursively(libDir, urls);
            }

            urls.sort(Comparator.comparingInt(this::calculateUrlPriority).thenComparing(URL::getPath));

            this.customLoader = new URLClassLoader(urls.toArray(new URL[0]), getClass().getClassLoader());
            List<URL> primaryUrls = urls.stream().filter(u -> !u.getPath().contains(FOLIA)).toList();
            this.primaryLoader = new URLClassLoader(primaryUrls.toArray(new URL[0]), this.customLoader);
            ClassPath classPath = ClassPath.from(customLoader);
            Set<Class<?>> toGenerate = scanPackages(classPath, customLoader);

            for (Class<?> c : toGenerate) {
                generateForClass(c, toGenerate, stagingDir);
            }

            promoteStagingDirectory(stagingDir, backupDir);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            rollbackStaging(generatedDir, stagingDir, backupDir);
            throw new java.io.IOException("Generation interrupted", e);
        } catch (Exception t) {
            rollbackStaging(generatedDir, stagingDir, backupDir);
            throw t;
        }
    }

    private int calculateUrlPriority(URL u) {
        String p = u.getPath();
        if (p.contains("paper-26.3") || p.contains("paper-api-26.3")) return 0;
        if (p.contains("paper-26.2") || p.contains("paper-api-26.2")) return 1;
        if (p.contains(PAPER)) return 2;
        if (p.contains(FOLIA)) return 10;
        return 5;
    }

    private void promoteStagingDirectory(File stagingDir, File backupDir) throws IOException {
        File targetPackageDir = new File(dataFolder, GENERATED_PATH);
        File stagedPackageDir = new File(stagingDir, GENERATED_PATH);
        if (!stagedPackageDir.exists()) {
            return;
        }
        org.mockmc.metaminer.util.AtomicDirectorySwap.promote(stagedPackageDir, targetPackageDir, backupDir);
        org.apache.commons.io.FileUtils.deleteDirectory(stagingDir);
    }

    private void rollbackStaging(File generatedDir, File stagingDir, File backupDir) {
        try {
            if (stagingDir.exists()) {
                org.apache.commons.io.FileUtils.deleteDirectory(stagingDir);
            }
            org.mockmc.metaminer.util.AtomicDirectorySwap.recover(generatedDir, backupDir);
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Failed during rollback of staging directory", e);
        }
    }

    private void populateBytecodeClassNames(File cacheDir) {
        paperClassNames.clear();
        foliaClassNames.clear();

        File[] remappedFiles = cacheDir.listFiles((dir, name) -> name.startsWith("remapped-") && name.endsWith(".jar"));
        if (remappedFiles != null) {
            for (File rf : remappedFiles) {
                if (rf.getName().contains(PAPER)) {
                    collectClassNamesFromJar(rf, paperClassNames);
                } else if (rf.getName().contains(FOLIA)) {
                    collectClassNamesFromJar(rf, foliaClassNames);
                }
            }
        }

        File libDir = new File(cacheDir, "libraries");
        if (libDir.exists()) {
            scanApiJars(new File(libDir, "io/papermc/paper/paper-api"), paperClassNames);
            scanApiJars(new File(libDir, "dev/folia/folia-api"), foliaClassNames);
        }
    }

    private void scanApiJars(File apiDir, Set<String> targetSet) {
        if (apiDir.exists()) {
            List<File> jars = new ArrayList<>();
            collectJarsRecursive(apiDir, jars);
            for (File j : jars) {
                collectClassNamesFromJar(j, targetSet);
            }
        }
    }

    private void collectClassNamesFromJar(File jar, Set<String> targetSet) {
        try (JarFile jf = new JarFile(jar)) {
            jf.stream().forEach(entry -> {
                String name = entry.getName();
                if (name.endsWith(".class") && !name.contains("$")) {
                    String className = name.substring(0, name.length() - 6).replace('/', '.');
                    targetSet.add(className);
                }
            });
        } catch (IOException e) {
            LOGGER.log(Level.WARNING, e, () -> "Failed reading jar " + jar.getName());
        }
    }

    private List<URL> processJars(File cacheDir) throws java.io.IOException, InterruptedException {
        List<URL> urls = new ArrayList<>();
        LOGGER.log(Level.INFO, "Scanning JAR directory: {0}", jarDirectory.getAbsolutePath());
        
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

        for (File jar : jarFiles) {
            File processedJar = jar;

            // 1. Crack Paper/Folia bundler if necessary
            if (jar.getName().contains(PAPER) || jar.getName().contains(FOLIA)) {
                org.mockmc.metaminer.util.JarCracker.CrackResult result = org.mockmc.metaminer.util.JarCracker.crack(jar, cacheDir);

                if (result != null) {
                    File mappingFile = org.mockmc.metaminer.util.MappingProvider.getMappings(result.mcVersion, cacheDir);
                    File remappedJar = new File(cacheDir, "remapped-" + jar.getName());
                    processedJar = org.mockmc.metaminer.util.StandaloneRemapper.remap(result.patchedJar, mappingFile, remappedJar);
                }
            }

            urls.add(processedJar.toURI().toURL());
            extractVersionInfo(jar);
        }
        return urls;
    }

    private Set<Class<?>> scanPackages(ClassPath classPath, ClassLoader fallbackLoader) {
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
                BRIGADIER_PACKAGE
        };
        for (String pkg : packagesToScan) {
            for (ClassPath.ClassInfo classInfo : classPath.getTopLevelClassesRecursive(pkg)) {
                processClassInfo(classInfo, fallbackLoader, toGenerate);
            }
        }
        return toGenerate;
    }

    private void processClassInfo(ClassPath.ClassInfo classInfo, ClassLoader fallbackLoader, Set<Class<?>> toGenerate) {
        if (classInfo.getName().startsWith("org.bukkit.craftbukkit")) {
            return;
        }
        try {
            Class<?> c = Class.forName(classInfo.getName(), false, primaryLoader != null ? primaryLoader : fallbackLoader);
            CodeSource cs = c.getProtectionDomain().getCodeSource();
            if (cs != null && cs.getLocation() != null && cs.getLocation().getPath().contains(FOLIA) && paperClassNames.contains(c.getName())) {
                return;
            }
            collectInterfaces(c, toGenerate);
        } catch (Exception | LinkageError _) {
            // Ignore loading errors
        }
    }

    private void addJarsRecursively(File dir, List<URL> urls) throws java.io.IOException {
        File[] files = dir.listFiles();
        if (files == null) return;
        for (File f : files) {
            if (f.isDirectory()) {
                addJarsRecursively(f, urls);
            } else if (f.getName().endsWith(".jar")) {
                urls.add(f.toURI().toURL());
            }
        }
    }

    private void extractVersionInfo(File jar) {
        try (JarFile jarFile = new JarFile(jar)) {
            // Map all classes in this jar to this version
            jarFile.stream().forEach(entry -> {
                if (entry.getName().endsWith(".class")) {
                    // entry iteration placeholder
                }
            });
            // Actually, we can just use the class's protection domain if loaded
        } catch (IOException _) {
            // Ignore jar reading errors
        }
    }

    private String getVersionForClass(Class<?> clazz) {
        try {
            CodeSource codeSource = clazz.getProtectionDomain().getCodeSource();
            if (codeSource != null) {
                File jarFile = new File(codeSource.getLocation().toURI());
                // Try to find a better version from manifest if we haven't already
                return jarFile.getName().replace(".jar", "");
            }
        } catch (Exception _) {
            // Ignore version detection errors
        }
        return "Unknown";
    }

    private void collectInterfaces(Class<?> c, Set<Class<?>> toGenerate) {
        if (isGeneratableType(c) && isAccessible(c)) {
            toGenerate.add(c);
        }
        try {
            for (Class<?> nested : c.getDeclaredClasses()) {
                if (isAccessible(nested)) {
                    collectInterfaces(nested, toGenerate);
                }
            }
        } catch (Exception _) {
            // Ignore class inspection errors
        }
    }

    private void generateForClass(Class<?> clazz, Set<Class<?>> allGeneratable, File outputDir) throws java.io.IOException {
        String packageName = getGeneratedPackageName(clazz);
        String simpleName = getMockName(clazz);
        String version = getVersionForClass(clazz);

        TypeSpec.Builder typeSpec = initTypeSpec(clazz, simpleName, version);
        TypeVariableName[] typeVars = addTypeVariables(clazz, typeSpec);
        Map<TypeVariable<?>, java.lang.reflect.Type> typeMap = buildTypeMap(clazz);

        Set<String> classSuppressions = applyClassSuppressions(typeSpec, clazz);
        applySuperInterfaces(clazz, typeSpec, typeVars, typeMap);

        Map<String, List<Method>> methodsBySignature = collectMethodsBySignature(clazz, typeVars, typeMap);
        Set<Method> methodsToGenerate = selectMethodsToGenerate(clazz, methodsBySignature, allGeneratable);

        generateMethods(typeSpec, clazz, typeVars, typeMap, methodsToGenerate, classSuppressions);

        JavaFile javaFile = JavaFile.builder(packageName, typeSpec.build())
                .addFileComment("MockMC: Unique Stub for $L", simpleName)
                .indent("    ")
                .skipJavaLangImports(true)
                .build();

        javaFile.writeTo(outputDir != null ? outputDir : dataFolder);
    }

    private TypeSpec.Builder initTypeSpec(Class<?> clazz, String simpleName, String version) {
        String sourceName = clazz.getProtectionDomain().getCodeSource() != null
                ? new File(clazz.getProtectionDomain().getCodeSource().getLocation().getPath()).getName()
                : "Unknown";

        TypeSpec.Builder typeSpec = TypeSpec.interfaceBuilder(simpleName + "BaseMock")
                .addJavadoc("""
                        Auto-generated by BaseMockGenerator for {@link $T}.
                        This interface mirrors the Bukkit/Paper API to ensure 100% IDE completion.

                        Source: $L (Version: $L)

                        Reason: Suppressed to prevent legacy API noise from interfering with modern build cycles.
                        """,
                        clazz, sourceName, version)
                .addModifiers(Modifier.PUBLIC);

        Deprecated d = clazz.getAnnotation(Deprecated.class);
        if (d != null) {
            AnnotationSpec.Builder builder = AnnotationSpec.builder(Deprecated.class);
            if (!d.since().isEmpty()) {
                builder.addMember(SINCE, "$S", d.since());
            } else {
                builder.addMember(SINCE, "$S", "1.0");
            }
            if (d.forRemoval()) {
                builder.addMember(FOR_REMOVAL, "$L", true);
            }
            typeSpec.addAnnotation(builder.build());
            
            String replacement = getQuirkReplacement(clazz);
            if (replacement != null) {
                typeSpec.addJavadoc("@deprecated $L\n", replacement);
            } else {
                typeSpec.addJavadoc("@deprecated Suppressed to prevent legacy API noise from interfering with modern build cycles.\n");
            }
        }

        return typeSpec;
    }

    private TypeVariableName[] addTypeVariables(Class<?> clazz, TypeSpec.Builder typeSpec) {
        TypeVariable<?>[] typeParameters = clazz.getTypeParameters();
        TypeVariableName[] typeVars = new TypeVariableName[typeParameters.length];
        if (typeParameters.length == 0) {
            if (clazz.isInterface()) {
                typeSpec.addSuperinterface(clazz);
            }
            return typeVars;
        }

        for (int i = 0; i < typeParameters.length; i++) {
            typeVars[i] = TypeVariableName.get(typeParameters[i].getName());
        }
        for (int i = 0; i < typeParameters.length; i++) {
            typeVars[i] = bindTypeVariableBounds(typeParameters[i], clazz, typeVars);
            typeSpec.addTypeVariable(typeVars[i]);
        }
        if (clazz.isInterface()) {
            TypeName[] typeVarNames = new TypeName[typeVars.length];
            System.arraycopy(typeVars, 0, typeVarNames, 0, typeVars.length);
            typeSpec.addSuperinterface(ParameterizedTypeName.get(ClassName.get(clazz), typeVarNames));
        }
        return typeVars;
    }

    private TypeVariableName bindTypeVariableBounds(TypeVariable<?> tv, Class<?> clazz, TypeVariableName[] typeVars) {
        TypeVariableName typeVar = TypeVariableName.get(tv.getName());
        try {
            java.lang.reflect.Type[] bounds = tv.getBounds();
            if (bounds != null && bounds.length > 0 && !(bounds.length == 1 && bounds[0] == Object.class)) {
                TypeName[] mappedBounds = new TypeName[bounds.length];
                for (int j = 0; j < bounds.length; j++) {
                    mappedBounds[j] = mapType(bounds[j], clazz, typeVars, null);
                }
                return typeVar.withBounds(mappedBounds);
            }
        } catch (Exception _) {
            // Keep unbounded fallback
        }
        return typeVar;
    }

    private Set<String> applyClassSuppressions(TypeSpec.Builder typeSpec, Class<?> clazz) {
        // Precise suppressions only: no blanket @SuppressWarnings("all").
        // Specific warnings are attached at the method or class level based on actual API signatures.
        Set<String> suppressions = new LinkedHashSet<>();
        for (TypeVariable<?> tv : clazz.getTypeParameters()) {
            for (java.lang.reflect.Type bound : tv.getBounds()) {
                Class<?> raw = getRawType(bound);
                if (raw != null && raw.getTypeParameters().length > 0 && !(bound instanceof java.lang.reflect.ParameterizedType)) {
                    suppressions.add(SUPPRESS_RAWTYPES);
                }
            }
        }
        for (java.lang.reflect.Type iface : clazz.getGenericInterfaces()) {
            Class<?> raw = getRawType(iface);
            if (raw != null && raw.getTypeParameters().length > 0 && !(iface instanceof java.lang.reflect.ParameterizedType)) {
                suppressions.add(SUPPRESS_RAWTYPES);
            }
        }
        if (!suppressions.isEmpty()) {
            AnnotationSpec.Builder suppressBuilder = AnnotationSpec.builder(SuppressWarnings.class);
            suppressions.forEach(s -> suppressBuilder.addMember("value", "$S", s));
            typeSpec.addAnnotation(suppressBuilder.build());
        }
        return suppressions;
    }

    private void applySuperInterfaces(Class<?> clazz, TypeSpec.Builder typeSpec, TypeVariableName[] typeVars, Map<TypeVariable<?>, java.lang.reflect.Type> typeMap) {
        Set<java.lang.reflect.Type> ourInterfaces = collectOurInterfaces(clazz);
        pruneRedundantInterfaces(ourInterfaces);
        applyQuirksToInterfaces(clazz, ourInterfaces);

        for (java.lang.reflect.Type iface : ourInterfaces) {
            typeSpec.addSuperinterface(getMirroredTypeName(iface, clazz, typeVars, typeMap));
        }
    }

    private Set<java.lang.reflect.Type> collectOurInterfaces(Class<?> clazz) {
        Set<java.lang.reflect.Type> ourInterfaces = new HashSet<>();
        Class<?> primaryClazz = primaryLoader != null ? loadPrimaryClass(clazz) : clazz;
        for (java.lang.reflect.Type ifaceType : primaryClazz.getGenericInterfaces()) {
            Class<?> iface = getRawType(ifaceType);
            if (iface != null && isGeneratableType(iface) && !iface.isSealed() && isClassInSameProtectionDomainOrPrimary(iface)) {
                ourInterfaces.add(ifaceType);
            }
        }
        return ourInterfaces;
    }

    private boolean isClassInSameProtectionDomainOrPrimary(Class<?> iface) {
        if (primaryLoader == null) return true;
        try {
            Class<?> primaryIface = primaryLoader.loadClass(iface.getName());
            return primaryIface != null;
        } catch (ClassNotFoundException | NoClassDefFoundError _) {
            if (customLoader != null) {
                try {
                    return customLoader.loadClass(iface.getName()) != null;
                } catch (ClassNotFoundException | NoClassDefFoundError _) {
                    return false;
                }
            }
            return false;
        }
    }

    private Class<?> loadPrimaryClass(Class<?> clazz) {
        try {
            return primaryLoader.loadClass(clazz.getName());
        } catch (ClassNotFoundException | NoClassDefFoundError _) {
            return clazz;
        }
    }

    private void pruneRedundantInterfaces(Set<java.lang.reflect.Type> interfaces) {
        Set<java.lang.reflect.Type> redundant = new HashSet<>();
        for (java.lang.reflect.Type aType : interfaces) {
            for (java.lang.reflect.Type bType : interfaces) {
                Class<?> a = getRawType(aType);
                Class<?> b = getRawType(bType);
                if (a != null && b != null && a != b && a.isAssignableFrom(b)) {
                    redundant.add(aType);
                }
            }
        }
        interfaces.removeAll(redundant);
    }

    private void applyQuirksToInterfaces(Class<?> clazz, Set<java.lang.reflect.Type> interfaces) {
        if (quirks != null && quirks.has(clazz.getName())) {
            JsonObject classQuirks = quirks.getAsJsonObject(clazz.getName());
            if (classQuirks.has(EXCLUDE_INTERFACES)) {
                Set<String> toExclude = new HashSet<>();
                classQuirks.getAsJsonArray(EXCLUDE_INTERFACES).forEach(e -> toExclude.add(e.getAsString()));
                interfaces.removeIf(iface -> toExclude.contains(getRawType(iface).getName()));
            }
        }
    }

    private Map<String, List<Method>> collectMethodsBySignature(Class<?> clazz, TypeVariableName[] typeVars, Map<TypeVariable<?>, java.lang.reflect.Type> typeMap) {
        Map<String, List<Method>> methodsBySignature = new HashMap<>();
        List<Class<?>> classVariants = new ArrayList<>();
        classVariants.add(clazz);
        if (customLoader != null) {
            try {
                Class<?> altClass = customLoader.loadClass(clazz.getName());
                if (altClass != clazz) {
                    classVariants.add(altClass);
                }
            } catch (ClassNotFoundException | NoClassDefFoundError _) {
                // Ignore if not found in customLoader
            }
        }

        for (Class<?> targetClass : classVariants) {
            collectTargetClassMethods(targetClass, clazz, typeVars, typeMap, methodsBySignature);
        }
        return methodsBySignature;
    }

    private void collectTargetClassMethods(Class<?> targetClass, Class<?> clazz, TypeVariableName[] typeVars,
            Map<TypeVariable<?>, java.lang.reflect.Type> typeMap, Map<String, List<Method>> methodsBySignature) {
        List<java.lang.reflect.Type> hierarchy = new ArrayList<>();
        hierarchy.add(targetClass);
        hierarchy.addAll(getAllGenericInterfaces(targetClass));

        for (java.lang.reflect.Type type : hierarchy) {
            Class<?> c = getRawType(type);
            if (c == null) continue;
            for (Method m : c.getDeclaredMethods()) {
                if (shouldSkipMethod(m, clazz)) continue;
                methodsBySignature.computeIfAbsent(getMethodSignature(m, clazz, typeVars, typeMap), _ -> new ArrayList<>()).add(m);
            }
        }
    }

    private boolean shouldSkipMethod(Method m, Class<?> clazz) {
        if (java.lang.reflect.Modifier.isStatic(m.getModifiers())) return true;
        if (m.getDeclaringClass() == Object.class) return true;
        if (m.isBridge() || m.isSynthetic() || m.getName().contains("$")) return true;
        if (m.getName().equals("getHandle") && isNmsType(m.getGenericReturnType())) return true;

        if (isNmsType(m.getGenericReturnType()) || !isAccessible(m.getGenericReturnType())) return true;
        for (java.lang.reflect.Type t : m.getGenericParameterTypes()) {
            if (isNmsType(t) || !isAccessible(t)) return true;
        }

        return isQuirkExcluded(m, clazz);
    }

    private boolean isQuirkExcluded(Method m, Class<?> clazz) {
        if (quirks != null && quirks.has(clazz.getName())) {
            JsonObject classQuirks = quirks.getAsJsonObject(clazz.getName());
            if (classQuirks.has(EXCLUDE_METHODS)) {
                for (var e : classQuirks.getAsJsonArray(EXCLUDE_METHODS)) {
                    if (e.getAsString().equals(m.getName())) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    private String getGeneratedPackageName(Class<?> clazz) {
        String pkg = clazz.getPackageName();
        String platform = "server";
        if (pkg.startsWith("com.velocitypowered") || pkg.startsWith("net.md_5.bungee") || pkg.startsWith("io.github.waterfallmc")) {
            platform = "proxy";
        }
        return "org.mockmc.mockmc.generated." + platform + "." + pkg;
    }

    private boolean isNmsType(java.lang.reflect.Type type) {
        if (type == null) return false;
        String name = type.toString();
        if (name.contains(BRIGADIER_PACKAGE)) return false;
        return name.contains("net.minecraft.") || name.contains("com.mojang.") || name.contains(".craftbukkit.");
    }

    private boolean isAccessible(java.lang.reflect.Type type) {
        if (isIgnoredType(type)) return true;

        Class<?> raw = getRawType(type);
        if (raw == null || raw.isPrimitive()) return true;
        if (raw.isArray()) return isAccessible(raw.getComponentType());

        if (isStandardPackage(raw.getName())) return true;
        if (!isRawClassAccessible(raw)) return false;



        if (type instanceof java.lang.reflect.ParameterizedType pt) {
            return areTypeArgumentsAccessible(pt);
        }
        return true;
    }

    private boolean isIgnoredType(java.lang.reflect.Type type) {
        return type == null || type instanceof java.lang.reflect.TypeVariable || type instanceof java.lang.reflect.WildcardType;
    }

    private boolean isRawClassAccessible(Class<?> raw) {
        if (!java.lang.reflect.Modifier.isPublic(raw.getModifiers())) return false;
        Class<?> declaring = raw.getDeclaringClass();
        return declaring == null || (java.lang.reflect.Modifier.isPublic(declaring.getModifiers()) && isRawClassAccessible(declaring));
    }

    private boolean areTypeArgumentsAccessible(java.lang.reflect.ParameterizedType pt) {
        for (java.lang.reflect.Type arg : pt.getActualTypeArguments()) {
            if (!isAccessible(arg)) return false;
        }
        return true;
    }

    private boolean isStandardPackage(String name) {
        return name.startsWith("java.") || name.startsWith("javax.") || name.startsWith("com.google.common.") || name.startsWith(BRIGADIER_PACKAGE);
    }

    private Set<Method> selectMethodsToGenerate(Class<?> clazz, Map<String, List<Method>> methodsBySignature, Set<Class<?>> allGeneratable) {
        Set<Method> methodsToGenerate = new HashSet<>();
        for (List<Method> providers : methodsBySignature.values()) {
            Method bestMethod = selectBestMethod(providers);
            if (shouldGenerateMethod(clazz, providers, allGeneratable)) {
                methodsToGenerate.add(bestMethod);
            }
        }
        return methodsToGenerate;
    }

    private boolean shouldGenerateMethod(Class<?> clazz, List<Method> providers, Set<Class<?>> allGeneratable) {
        boolean declaredInThis = false;
        boolean hasDefaultImplementationInParents = false;
        boolean isMissingImplementation = false;

        Set<Class<?>> declaringClasses = new HashSet<>();
        for (Method m : providers) {
            declaringClasses.add(m.getDeclaringClass());
            String declaringClassName = m.getDeclaringClass().getName();
            if (declaringClassName.equals(clazz.getName())) {
                declaredInThis = true;
            }

            if (m.isDefault()) {
                if (!declaringClassName.equals(clazz.getName())) {
                    hasDefaultImplementationInParents = true;
                }
            } else if (!java.lang.reflect.Modifier.isStatic(m.getModifiers())) {
                isMissingImplementation = true;
            }
        }

        if (declaredInThis) return true;

        Set<Class<?>> independentDeclaring = filterIndependentDeclaringClasses(declaringClasses);
        // If multiple unrelated interfaces declare this method (e.g. LivingEntity and Leashable for isLeashed),
        // each branch defines its own default stub, so clazzBaseMock must override it to disambiguate unrelated defaults.
        if (independentDeclaring.size() > 1) {
            return true;
        }

        if (providers.stream().anyMatch(m -> isProvidedByGeneratableAncestor(clazz, m, allGeneratable))) {
            return false;
        }
        return isMissingImplementation && !hasDefaultImplementationInParents;
    }

    private Set<Class<?>> filterIndependentDeclaringClasses(Set<Class<?>> declaringClasses) {
        Set<Class<?>> independent = new HashSet<>(declaringClasses);
        for (Class<?> p : declaringClasses) {
            for (Class<?> c : declaringClasses) {
                if (p != c && p.isAssignableFrom(c)) {
                    independent.remove(p);
                }
            }
        }
        return independent;
    }

    private boolean isProvidedByGeneratableAncestor(Class<?> clazz, Method m, Set<Class<?>> allGeneratable) {
        Class<?> declaring = m.getDeclaringClass();
        for (java.lang.reflect.Type ifaceType : getAllGenericInterfaces(clazz)) {
            Class<?> a = getRawType(ifaceType);
            if (a != null && a != clazz && allGeneratable.contains(a) && declaring.isAssignableFrom(a)) {
                return true;
            }
        }
        return false;
    }

    private Method selectBestMethod(List<Method> providers) {
        Method bestMethod = null;
        for (Method m : providers) {
            if (bestMethod == null) {
                bestMethod = m;
            } else {
                bestMethod = pickBetterMethod(bestMethod, m);
            }
        }
        return bestMethod;
    }

    private Method pickBetterMethod(Method current, Method candidate) {
        Class<?> currentReturn = getRawType(current.getGenericReturnType());
        Class<?> candidateReturn = getRawType(candidate.getGenericReturnType());

        if (currentReturn != null && candidateReturn != null && currentReturn != candidateReturn && currentReturn.isAssignableFrom(candidateReturn)) {
            return candidate;
        }

        Class<?> currentDecl = current.getDeclaringClass();
        Class<?> candidateDecl = candidate.getDeclaringClass();

        if (currentReturn == candidateReturn && currentDecl != null && candidateDecl != null && currentDecl != candidateDecl && currentDecl.isAssignableFrom(candidateDecl)) {
            return candidate;
        }
        return current;
    }

    private void generateMethods(TypeSpec.Builder typeSpec, Class<?> clazz, TypeVariableName[] typeVars, Map<TypeVariable<?>, java.lang.reflect.Type> typeMap, Set<Method> methodsToGenerate, Set<String> classSuppressions) {
        Set<String> generatedSignatures = new HashSet<>();
        for (Method m : methodsToGenerate) {
            if (isMethodGeneratable(m)) {
                String signature = getMethodSignature(m, clazz, typeVars, typeMap);
                if (generatedSignatures.add(signature)) {
                    generateMethodSpec(typeSpec, m, clazz, typeVars, typeMap, classSuppressions);
                }
            }
        }
    }

    private boolean isMethodGeneratable(Method m) {
        if (!java.lang.reflect.Modifier.isPublic(m.getModifiers()) || java.lang.reflect.Modifier.isStatic(m.getModifiers())) {
            return false;
        }
        if (m.getDeclaringClass() == Object.class) {
            return false;
        }
        if (!isAccessible(m.getGenericReturnType())) {
            return false;
        }
        for (java.lang.reflect.Type paramType : m.getGenericParameterTypes()) {
            if (!isAccessible(paramType)) {
                return false;
            }
        }

        String name = m.getName();
        Class<?>[] params = m.getParameterTypes();
        // Skip standard Object methods that interfaces CANNOT override with default implementations
        return !(name.equals("equals") && params.length == 1 && params[0] == Object.class)
                && !(name.equals("hashCode") && params.length == 0)
                && !(name.equals("toString") && params.length == 0);
    }

    private String getMethodSignature(Method m, Class<?> clazz, TypeVariableName[] typeVars, Map<TypeVariable<?>, java.lang.reflect.Type> typeMap) {
        StringBuilder sb = new StringBuilder();
        sb.append(m.getName()).append("(");
        java.lang.reflect.Type[] params = m.getGenericParameterTypes();
        for (int i = 0; i < params.length; i++) {
            if (i > 0) sb.append(",");
            sb.append(mapType(params[i], clazz, typeVars, typeMap).toString());
        }
        sb.append(")");
        return sb.toString();
    }

    private void generateMethodSpec(TypeSpec.Builder typeSpec, Method m, Class<?> clazz, TypeVariableName[] ourTypeVars, Map<TypeVariable<?>, java.lang.reflect.Type> typeMap, Set<String> classSuppressions) {
        MethodSpec.Builder builder = MethodSpec.methodBuilder(m.getName())
                .addModifiers(Modifier.PUBLIC, Modifier.DEFAULT);

        boolean isPrimary = isPrimaryApiMethod(m, clazz);
        if (isPrimary) {
            if (shouldAddOverride(m, clazz)) {
                builder.addAnnotation(Override.class);
            }
        } else {
            builder.addAnnotation(AnnotationSpec.builder(Deprecated.class)
                    .addMember(SINCE, "$S", "legacy")
                    .addMember(FOR_REMOVAL, "$L", true)
                    .build());
            builder.addJavadoc("Legacy or cross-server API method.\n@mockmc.version legacy\n");
        }

        applyMethodAnnotations(builder, m, clazz, ourTypeVars, typeMap, classSuppressions);
        if (m.isVarArgs() && isGenericVarargs(m) && (java.lang.reflect.Modifier.isStatic(m.getModifiers()) || java.lang.reflect.Modifier.isFinal(m.getModifiers()) || java.lang.reflect.Modifier.isPrivate(m.getModifiers()))) {
            builder.addAnnotation(SafeVarargs.class);
        }
        builder.returns(mapType(m.getGenericReturnType(), clazz, ourTypeVars, typeMap));

        for (TypeVariable<Method> tv : m.getTypeParameters()) {
            builder.addTypeVariable((TypeVariableName) mapTypeVariable(tv, clazz, ourTypeVars, typeMap));
        }

        addMethodParameters(builder, m, clazz, ourTypeVars, typeMap);
        addMethodExceptions(builder, m);

        if (m.getName().equals("key") && m.getParameterCount() == 0 && org.bukkit.Keyed.class.isAssignableFrom(clazz)) {
            builder.addComment("MockMC: $L#$L", clazz.getSimpleName(), m.getName());
            builder.addStatement("return getKey()");
        } else {
            builder.addComment("MockMC: $L#$L", clazz.getSimpleName(), m.getName());
            addDefaultReturnValue(builder, m);
        }
        typeSpec.addMethod(builder.build());
    }

    private void addDefaultReturnValue(MethodSpec.Builder builder, Method m) {
        if (m.getGenericReturnType() instanceof java.lang.reflect.TypeVariable) {
            builder.addStatement(RETURN_NULL);
            return;
        }
        if (addPrimitiveReturnValue(builder, m)) return;
        if (addCollectionReturnValue(builder, m)) return;
        if (addOptionalReturnValue(builder, m)) return;
        if (addSpecialReturnValue(builder, m)) return;
        builder.addStatement(RETURN_NULL);
    }

    private boolean addPrimitiveReturnValue(MethodSpec.Builder builder, Method m) {
        Class<?> returnType = m.getReturnType();
        if (returnType == void.class) return true;
        if (returnType == double.class || returnType == Double.class) {
            builder.addStatement("return 0.0d");
            return true;
        }
        if (returnType == boolean.class || returnType == Boolean.class) {
            builder.addStatement("return false");
            return true;
        }
        if (returnType == int.class || returnType == Integer.class || returnType == byte.class || returnType == Byte.class || returnType == short.class || returnType == Short.class || returnType == char.class || returnType == Character.class) {
            builder.addStatement("return 0");
            return true;
        }
        if (returnType == long.class || returnType == Long.class) {
            builder.addStatement("return 0L");
            return true;
        }
        if (returnType == float.class || returnType == Float.class) {
            builder.addStatement("return 0.0f");
            return true;
        }
        if (returnType == String.class) {
            builder.addStatement("return \"\"");
            return true;
        }
        if (returnType.isEnum()) {
            builder.addStatement(RETURN_NULL);
            return true;
        }
        return false;
    }

    private boolean addCollectionReturnValue(MethodSpec.Builder builder, Method m) {
        Class<?> returnType = m.getReturnType();
        if (returnType.isArray()) {
            if (m.getGenericReturnType() instanceof java.lang.reflect.GenericArrayType) {
                builder.addStatement(RETURN_NULL);
            } else {
                builder.addStatement("return new $T[0]", returnType.getComponentType());
            }
            return true;
        }
        if (returnType == java.util.List.class || returnType == java.util.Collection.class || returnType == Iterable.class) {
            builder.addStatement("return java.util.Collections.emptyList()");
            return true;
        }
        if (returnType == java.util.Set.class) {
            builder.addStatement("return java.util.Collections.emptySet()");
            return true;
        }
        if (returnType == java.util.Map.class) {
            builder.addStatement("return java.util.Collections.emptyMap()");
            return true;
        }
        return false;
    }

    private boolean addOptionalReturnValue(MethodSpec.Builder builder, Method m) {
        Class<?> returnType = m.getReturnType();
        if (returnType == java.util.Optional.class) {
            builder.addStatement("return java.util.Optional.empty()");
            return true;
        }
        if (returnType == java.util.OptionalInt.class) {
            builder.addStatement("return java.util.OptionalInt.empty()");
            return true;
        }
        if (returnType == java.util.OptionalLong.class) {
            builder.addStatement("return java.util.OptionalLong.empty()");
            return true;
        }
        if (returnType == java.util.OptionalDouble.class) {
            builder.addStatement("return java.util.OptionalDouble.empty()");
            return true;
        }
        if (returnType == java.util.concurrent.CompletableFuture.class) {
            builder.addStatement("return java.util.concurrent.CompletableFuture.completedFuture(null)");
            return true;
        }
        return false;
    }

    private boolean addSpecialReturnValue(MethodSpec.Builder builder, Method m) {
        Class<?> returnType = m.getReturnType();
        if (net.kyori.adventure.text.Component.class.isAssignableFrom(returnType) || net.kyori.adventure.text.ComponentLike.class.isAssignableFrom(returnType)) {
            if (m.getGenericReturnType() instanceof java.lang.reflect.TypeVariable) {
                builder.addStatement(RETURN_NULL);
            } else {
                builder.addStatement("return net.kyori.adventure.text.Component.empty()");
            }
            return true;
        }
        if (returnType == org.bukkit.NamespacedKey.class) {
            builder.addStatement("return org.bukkit.NamespacedKey.minecraft(\"mock\")");
            return true;
        }
        if (returnType == org.bukkit.inventory.ItemStack.class) {
            builder.addStatement("return new org.bukkit.inventory.ItemStack(org.bukkit.Material.AIR)");
            return true;
        }
        if (returnType == org.bukkit.Location.class) {
            builder.addStatement("return new org.bukkit.Location(null, 0, 0, 0)");
            return true;
        }
        if (returnType == org.bukkit.util.Vector.class) {
            builder.addStatement("return new org.bukkit.util.Vector(0, 0, 0)");
            return true;
        }
        return false;
    }

    private void applyMethodAnnotations(MethodSpec.Builder builder, Method m, Class<?> clazz, TypeVariableName[] ourTypeVars, Map<TypeVariable<?>, java.lang.reflect.Type> typeMap, Set<String> classSuppressions) {
        if (!classSuppressions.contains("all")) {
            Set<String> suppressions = collectMethodSuppressions(m, clazz, ourTypeVars, typeMap);
            suppressions.removeAll(classSuppressions); // Filter out class-level suppressions
            if (!suppressions.isEmpty()) {
                AnnotationSpec.Builder suppressBuilder = AnnotationSpec.builder(SuppressWarnings.class);
                suppressions.forEach(s -> suppressBuilder.addMember("value", "$S", s));
                builder.addAnnotation(suppressBuilder.build());
            }
        }

        Deprecated methodDep = m.getAnnotation(Deprecated.class);
        Deprecated classDep = clazz.getAnnotation(Deprecated.class);
        Deprecated toApply = methodDep != null ? methodDep : classDep;

        if (toApply != null) {
            AnnotationSpec.Builder depBuilder = AnnotationSpec.builder(Deprecated.class);
            if (!toApply.since().isEmpty()) {
                depBuilder.addMember(SINCE, "$S", toApply.since());
            } else {
                depBuilder.addMember(SINCE, "$S", "1.0");
            }
            if (toApply.forRemoval()) {
                depBuilder.addMember(FOR_REMOVAL, "$L", true);
            }
            builder.addAnnotation(depBuilder.build());

            // We use a simplified signature for quirks if possible, but must handle generic types
            String signature = getMethodSignature(m, clazz, ourTypeVars, typeMap);
            String replacement = getQuirkReplacement(m, clazz, signature);
            if (replacement != null) {
                builder.addJavadoc("@deprecated $L\n", replacement);
            } else {
                builder.addJavadoc("@deprecated Suppressed to prevent legacy API noise from interfering with modern build cycles.\n");
            }
        }
    }

    private void addMethodParameters(MethodSpec.Builder builder, Method m, Class<?> clazz, TypeVariableName[] ourTypeVars, Map<TypeVariable<?>, java.lang.reflect.Type> typeMap) {
        java.lang.reflect.Parameter[] params = m.getParameters();
        for (int i = 0; i < params.length; i++) {
            builder.addParameter(mapType(params[i].getParameterizedType(), clazz, ourTypeVars, typeMap), "arg" + i);
        }
        if (m.isVarArgs() || isSuperMethodVarArgs(m, clazz)) {
            builder.varargs(true);
        }
    }

    private boolean isSuperMethodVarArgs(Method m, Class<?> clazz) {
        if (m.getParameterCount() == 0) return false;
        Class<?> lastParam = m.getParameterTypes()[m.getParameterCount() - 1];
        if (!lastParam.isArray()) return false;
        for (Class<?> iface : clazz.getInterfaces()) {
            for (Method sm : iface.getMethods()) {
                if (isMethodSignatureEquivalent(sm, m) && sm.isVarArgs()) {
                    return true;
                }
            }
        }
        return false;
    }

    private void addMethodExceptions(MethodSpec.Builder builder, Method m) {
        for (java.lang.reflect.Type exp : m.getGenericExceptionTypes()) {
            builder.addException(TypeName.get(exp));
        }
    }

    private Set<String> collectMethodSuppressions(Method m, Class<?> clazz, TypeVariableName[] typeVars, Map<TypeVariable<?>, java.lang.reflect.Type> typeMap) {
        Set<String> methodSuppressions = new LinkedHashSet<>();
        // NOTE: We do NOT call collectSuppressions(m, ...) or loop over providers here because we don't want to 
        // suppress "deprecation" just because the method itself is marked @Deprecated. 
        // We only care about types in the signature.
    
        collectSuppressionsFromSignature(m, methodSuppressions);
        
        for (Class<?> exceptionType : m.getExceptionTypes()) {
            if (exceptionType == Exception.class) {
                methodSuppressions.add("Exception");
                methodSuppressions.add("java:S112"); // Sonar rule for generic exceptions
            }
        }


        for (TypeVariable<Method> tv : m.getTypeParameters()) {
            if (typeVars != null) {
                for (TypeVariableName enclosingTv : typeVars) {
                    if (enclosingTv.name().equals(tv.getName())) {
                        methodSuppressions.add("hiding");
                        break;
                    }
                }
            }
        }

        applyMethodQuirks(m, clazz, typeVars, typeMap, methodSuppressions);
    
        return methodSuppressions;
    }

    private void collectSuppressionsFromSignature(Method m, Set<String> suppressions) {
        collectSuppressionsFromType(m.getGenericReturnType(), suppressions);
        for (java.lang.reflect.Type paramType : m.getGenericParameterTypes()) {
            collectSuppressionsFromType(paramType, suppressions);
        }
        if (m.isVarArgs() && isGenericVarargs(m) && !java.lang.reflect.Modifier.isStatic(m.getModifiers()) && !java.lang.reflect.Modifier.isFinal(m.getModifiers()) && !java.lang.reflect.Modifier.isPrivate(m.getModifiers())) {
            suppressions.add("unchecked");
            suppressions.add("varargs");
        }
    }

    private void applyMethodQuirks(Method m, Class<?> clazz, TypeVariableName[] typeVars, Map<TypeVariable<?>, java.lang.reflect.Type> typeMap, Set<String> suppressions) {
        if (quirks == null || !quirks.has(clazz.getName())) return;

        JsonObject classQuirks = quirks.getAsJsonObject(clazz.getName());
        if (!classQuirks.has(METHOD_QUIRKS)) return;

        JsonObject methodQuirks = classQuirks.getAsJsonObject(METHOD_QUIRKS);
        String signature = getMethodSignature(m, clazz, typeVars, typeMap);

        addQuirkSuppressions(methodQuirks, signature, suppressions);
        addQuirkSuppressions(methodQuirks, m.getName(), suppressions);
    }

    private JsonObject getQuirkObject(JsonObject methodQuirks, String key) {
        return methodQuirks.has(key) ? methodQuirks.getAsJsonObject(key) : null;
    }

    private void addQuirkSuppressions(JsonObject methodQuirks, String key, Set<String> suppressions) {
        JsonObject mq = getQuirkObject(methodQuirks, key);
        if (mq != null && mq.has(ADDITIONAL_SUPPRESSIONS)) {
            mq.getAsJsonArray(ADDITIONAL_SUPPRESSIONS).forEach(e -> suppressions.add(e.getAsString()));
        }
    }

    private Class<?> getRawType(java.lang.reflect.Type type) {
        if (type instanceof Class) return (Class<?>) type;
        if (type instanceof java.lang.reflect.ParameterizedType) return (Class<?>) ((java.lang.reflect.ParameterizedType) type).getRawType();
        return null;
    }

    private Set<java.lang.reflect.Type> getAllGenericInterfaces(java.lang.reflect.Type type) {
        Set<java.lang.reflect.Type> res = new HashSet<>();
        Class<?> raw = getRawType(type);
        if (raw == null) return res;

        for (java.lang.reflect.Type iface : raw.getGenericInterfaces()) {
            res.add(iface);
            res.addAll(getAllGenericInterfaces(iface));
        }
        return res;
    }

    private void collectSuppressions(AnnotatedElement element, Set<String> suppressions) {
        if (element == null) return;
        Deprecated d = element.getAnnotation(Deprecated.class);
        if (d != null) {
            if (d.forRemoval()) {
                suppressions.add("removal");
            } else {
                suppressions.add("deprecation");
            }
            suppressions.add("java:S1133");
        }
    }

    private void collectSuppressionsFromType(java.lang.reflect.Type type, Set<String> suppressions) {
        Class<?> raw = getRawType(type);
        if (raw != null) {
            collectSuppressions(raw, suppressions);
            if (raw.getTypeParameters().length > 0 && !(type instanceof java.lang.reflect.ParameterizedType)) {
                suppressions.add(SUPPRESS_RAWTYPES);
            }
            if (type instanceof java.lang.reflect.ParameterizedType pt) {
                for (java.lang.reflect.Type arg : pt.getActualTypeArguments()) {
                    collectSuppressionsFromType(arg, suppressions);
                }
            }
        }
    }

    private boolean isGenericVarargs(Method m) {
        if (!m.isVarArgs()) return false;
        java.lang.reflect.Type[] params = m.getGenericParameterTypes();
        java.lang.reflect.Type lastParam = params[params.length - 1];
        if (lastParam instanceof java.lang.reflect.GenericArrayType) return true;
        if (lastParam instanceof Class<?> cl && cl.isArray()) {
            return cl.getComponentType().getTypeParameters().length > 0;
        }
        return false;
    }

    private String getQuirkReplacement(Class<?> clazz) {
        if (quirks != null && quirks.has(clazz.getName())) {
            JsonObject classQuirks = quirks.getAsJsonObject(clazz.getName());
            if (classQuirks.has(REPLACEMENT)) {
                return classQuirks.get(REPLACEMENT).getAsString();
            }
        }
        return null;
    }

    private String getQuirkReplacement(Method m, Class<?> clazz, String signature) {
        if (quirks == null || !quirks.has(clazz.getName())) return null;

        JsonObject classQuirks = quirks.getAsJsonObject(clazz.getName());
        if (!classQuirks.has(METHOD_QUIRKS)) return null;

        JsonObject methodQuirks = classQuirks.getAsJsonObject(METHOD_QUIRKS);
        String replacement = getReplacementFromQuirk(methodQuirks, signature);
        if (replacement == null) {
            replacement = getReplacementFromQuirk(methodQuirks, m.getName());
        }
        return replacement;
    }

    private String getReplacementFromQuirk(JsonObject methodQuirks, String key) {
        JsonObject mq = getQuirkObject(methodQuirks, key);
        if (mq != null && mq.has(REPLACEMENT)) {
            return mq.get(REPLACEMENT).getAsString();
        }
        return null;
    }

    private boolean shouldAddOverride(Method m, Class<?> clazz) {
        if (!clazz.isInterface()) {
            return false;
        }
        try {
            Class<?> primaryClazz = primaryLoader != null ? primaryLoader.loadClass(clazz.getName()) : clazz;
            for (Method pm : primaryClazz.getMethods()) {
                if (isMethodSignatureEquivalent(pm, m)) {
                    return true;
                }
            }
            return false;
        } catch (ClassNotFoundException | NoClassDefFoundError _) {
            return false;
        }
    }

    private boolean isMethodSignatureEquivalent(Method pm, Method m) {
        if (!pm.getName().equals(m.getName()) || pm.getParameterCount() != m.getParameterCount()) {
            return false;
        }
        for (int i = 0; i < pm.getParameterCount(); i++) {
            Class<?> p1 = pm.getParameterTypes()[i];
            Class<?> p2 = m.getParameterTypes()[i];
            if (!p1.isAssignableFrom(p2)) {
                return false;
            }
        }
        return true;
    }

    private boolean isPrimaryApiMethod(Method m, Class<?> clazz) {
        if (!isAccessible(clazz)) return false;
        try {
            Class<?> primaryClazz = primaryLoader != null ? primaryLoader.loadClass(clazz.getName()) : clazz;
            for (Method pm : primaryClazz.getMethods()) {
                if (isMethodSignatureEquivalent(pm, m)) {
                    return true;
                }
            }
            return false;
        } catch (ClassNotFoundException | NoClassDefFoundError _) {
            return false;
        }
    }

    private boolean isGeneratableType(Class<?> clazz) {
        if (clazz == null || clazz.isEnum() || clazz.isAnnotation()) return false;
        if (!java.lang.reflect.Modifier.isPublic(clazz.getModifiers()) || clazz.isSealed()) return false;
        if (!clazz.isInterface() && !java.lang.reflect.Modifier.isAbstract(clazz.getModifiers())) return false;
        if (!isAccessible(clazz)) return false;
        String name = clazz.getName();
        if (name.equals("co.aikar.timings.Timing")) return false;
        if (org.mockmc.metaminer.util.ClassExclusions.isExcluded(name)) return false;

        return org.mockmc.metaminer.util.ClassExclusions.isGeneratablePackage(name);
    }

    private void sanitizeApiJars(File cacheDir) {
        File paperDir = new File(cacheDir, "libraries/io/papermc/paper/paper-api");
        if (!paperDir.exists()) {
            return;
        }

        List<File> paperJars = new ArrayList<>();
        collectJarsRecursive(paperDir, paperJars);
        if (paperJars.isEmpty()) {
            return;
        }

        // Determine primary Paper API jar (marked as primary in versions.json or highest version)
        File primaryPaperJar = findPrimaryPaperJar(paperJars);
        if (primaryPaperJar == null) {
            return;
        }

        Set<String> primaryEntries = readPaperEntries(primaryPaperJar);
        if (primaryEntries.isEmpty()) {
            return;
        }

        // 1. Sanitize Folia API jars
        File foliaDir = new File(cacheDir, "libraries/dev/folia/folia-api");
        if (foliaDir.exists()) {
            List<File> foliaJars = new ArrayList<>();
            collectJarsRecursive(foliaDir, foliaJars);
            for (File foliaJar : foliaJars) {
                sanitizeSingleJar(foliaJar, primaryEntries, "Folia API");
            }
        }

        // 2. Sanitize older Paper API fallback jars so only removed/differential classes remain
        for (File paperJar : paperJars) {
            if (!paperJar.equals(primaryPaperJar)) {
                sanitizeSingleJar(paperJar, primaryEntries, "Fallback Paper API");
            }
        }
    }

    private File findPrimaryPaperJar(List<File> paperJars) {
        File versionsJson = new File(jarDirectory, "manifests/versions.json");
        if (versionsJson.exists()) {
            File primaryFromManifest = findPrimaryFromManifest(versionsJson, paperJars);
            if (primaryFromManifest != null) {
                return primaryFromManifest;
            }
        }
        // Fallback: pick the latest jar by version
        return paperJars.stream()
                .max(Comparator.comparing(File::getName))
                .orElse(paperJars.get(0));
    }

    private File findPrimaryFromManifest(File versionsJson, List<File> paperJars) {
        try (java.io.FileReader reader = new java.io.FileReader(versionsJson)) {
            com.google.gson.JsonObject root = com.google.gson.JsonParser.parseReader(reader).getAsJsonObject();
            com.google.gson.JsonObject platforms = root.getAsJsonObject("platforms");
            if (platforms == null || !platforms.has(PAPER)) {
                return null;
            }
            com.google.gson.JsonArray versions = platforms.getAsJsonObject(PAPER).getAsJsonArray("versions");
            for (var elem : versions) {
                com.google.gson.JsonObject vObj = elem.getAsJsonObject();
                if (vObj.has("primary") && vObj.get("primary").getAsBoolean()) {
                    String v = vObj.get("version").getAsString();
                    int b = vObj.get("build").getAsInt();
                    return matchJarVersion(paperJars, v, b);
                }
            }
        } catch (Exception e) {
            LOGGER.log(Level.WARNING, "Failed to parse versions.json for primary paper-api jar: {0}", e.getMessage());
        }
        return null;
    }

    private File matchJarVersion(List<File> paperJars, String version, int build) {
        for (File pj : paperJars) {
            if (pj.getName().contains(version) && pj.getName().contains(String.valueOf(build))) {
                return pj;
            }
        }
        return null;
    }

    private Set<String> readPaperEntries(File paperApiJar) {
        Set<String> paperEntries = new HashSet<>();
        try (java.util.zip.ZipFile paperZip = new java.util.zip.ZipFile(paperApiJar)) {
            var entries = paperZip.entries();
            while (entries.hasMoreElements()) {
                paperEntries.add(entries.nextElement().getName());
            }
        } catch (IOException e) {
            LOGGER.log(Level.WARNING, "Failed to read paper-api jar for sanitization: {0}", e.getMessage());
        }
        return paperEntries;
    }

    private void sanitizeSingleJar(File targetJar, Set<String> paperEntries, String jarTypeName) {
        File tmpJar = new File(targetJar.getParentFile(), targetJar.getName() + ".tmp");
        boolean modified = false;
        try (java.util.zip.ZipFile zip = new java.util.zip.ZipFile(targetJar);
             java.util.zip.ZipOutputStream zos = new java.util.zip.ZipOutputStream(new java.io.FileOutputStream(tmpJar))) {
            var entries = zip.entries();
            while (entries.hasMoreElements()) {
                java.util.zip.ZipEntry entry = entries.nextElement();
                String name = entry.getName();
                if (paperEntries.contains(name)) {
                    modified = true;
                    continue;
                }
                java.util.zip.ZipEntry newEntry = new java.util.zip.ZipEntry(name);
                zos.putNextEntry(newEntry);
                if (!entry.isDirectory()) {
                    try (java.io.InputStream is = zip.getInputStream(entry)) {
                        org.apache.commons.io.IOUtils.copy(is, zos);
                    }
                }
                zos.closeEntry();
            }
        } catch (IOException e) {
            LOGGER.log(Level.WARNING, "Failed sanitizing " + jarTypeName + " jar: {0}", e.getMessage());
            org.apache.commons.io.FileUtils.deleteQuietly(tmpJar);
            return;
        }

        if (modified) {
            LOGGER.log(Level.INFO, "Sanitized {0} ({1}) to exclude classes already present in Primary Paper API.", new Object[]{targetJar.getName(), jarTypeName});
            try {
                java.nio.file.Files.delete(targetJar.toPath());
                if (!tmpJar.renameTo(targetJar)) {
                    LOGGER.log(Level.WARNING, "Failed replacing {0} with sanitized version.", targetJar.getName());
                }
            } catch (IOException ex) {
                LOGGER.log(Level.WARNING, "Failed replacing {0} with sanitized version: {1}", new Object[]{targetJar.getName(), ex.getMessage()});
            }
        } else {
            org.apache.commons.io.FileUtils.deleteQuietly(tmpJar);
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
}


