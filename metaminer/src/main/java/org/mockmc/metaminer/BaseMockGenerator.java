package org.mockmc.metaminer;

import com.google.common.reflect.ClassPath;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.squareup.javapoet.*;
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

    private static final String SINCE = "since";
    private static final String RETURN_NULL = "return null";

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
                if (typeParameters[i].getName().equals(tv.getName())) {
                    return ourTypeVars[i];
                }
            }
        }
        if (typeMap != null && typeMap.containsKey(tv)) {
            return mapType(typeMap.get(tv), clazz, ourTypeVars, typeMap);
        }
        try {
            return TypeVariableName.get(tv);
        } catch (Exception _) {
            return TypeName.get(tv.getBounds()[0]);
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

            Collections.addAll(queue, raw.getGenericInterfaces());
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

    @Override
    public void generateData() throws java.io.IOException {
        try {
            File cacheDir = new File(jarDirectory, "cache");
            List<URL> urls = processJars(cacheDir);

            // 3. Add unbundled libraries recursively
            File libDir = new File(cacheDir, "libraries");
            if (libDir.exists()) {
                addJarsRecursively(libDir, urls);
            }

            URLClassLoader customLoader = new URLClassLoader(urls.toArray(new URL[0]), getClass().getClassLoader());
            ClassPath classPath = ClassPath.from(customLoader);
            Set<Class<?>> toGenerate = scanPackages(classPath);

            for (Class<?> c : toGenerate) {
                generateForClass(c);
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            throw new java.io.IOException("Generation interrupted", e);
        }
    }

    private List<URL> processJars(File cacheDir) throws java.io.IOException, InterruptedException {
        List<URL> urls = new ArrayList<>();
        LOGGER.log(Level.INFO, "Scanning JAR directory: {0}", jarDirectory.getAbsolutePath());
        File[] jarFiles = jarDirectory.listFiles((dir, name) -> name.endsWith(".jar"));

        if (jarFiles != null) {
            for (File jar : jarFiles) {
                File processedJar = jar;

                // 1. Crack Paper/Folia bundler if necessary
                if (jar.getName().contains("paper") || jar.getName().contains("folia")) {
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
        }
        return urls;
    }

    private Set<Class<?>> scanPackages(ClassPath classPath) {
        Set<Class<?>> toGenerate = new HashSet<>();
        String[] packagesToScan = {
                "org.bukkit",
                "org.spigotmc",
                "co.aikar.timings",
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
                    collectInterfaces(c, toGenerate);
                } catch (Exception | LinkageError _) {
                    // Ignore loading errors
                }
            }
        }
        return toGenerate;
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
        if (isGeneratableType(c)) {
            toGenerate.add(c);
        }
        try {
            for (Class<?> nested : c.getDeclaredClasses()) {
                collectInterfaces(nested, toGenerate);
            }
        } catch (Exception _) {
            // Ignore class inspection errors
        }
    }

    private void generateForClass(Class<?> clazz) throws java.io.IOException {
        if (!java.lang.reflect.Modifier.isPublic(clazz.getModifiers()) || clazz.isSealed()) {
            return;
        }

        String packageName = getGeneratedPackageName(clazz);
        String simpleName = getMockName(clazz);
        String version = getVersionForClass(clazz);

        TypeSpec.Builder typeSpec = initTypeSpec(clazz, simpleName, version);
        TypeVariableName[] typeVars = addTypeVariables(clazz, typeSpec);
        Map<TypeVariable<?>, java.lang.reflect.Type> typeMap = buildTypeMap(clazz);

        Set<String> classSuppressions = applyClassSuppressions(clazz, typeSpec);
        applySuperInterfaces(clazz, typeSpec, typeVars, typeMap);

        Map<String, List<Method>> methodsBySignature = collectMethodsBySignature(clazz, typeVars, typeMap);
        Set<Method> methodsToGenerate = selectMethodsToGenerate(clazz, methodsBySignature);

        generateMethods(typeSpec, clazz, typeVars, typeMap, methodsToGenerate, classSuppressions);

        JavaFile javaFile = JavaFile.builder(packageName, typeSpec.build())
                .addFileComment("MockMC: Unique Stub for $L", simpleName)
                .indent("    ")
                .skipJavaLangImports(true)
                .build();

        javaFile.writeTo(dataFolder);
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
                builder.addMember("forRemoval", "$L", true);
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
        TypeVariableName[] typeVars = new TypeVariableName[clazz.getTypeParameters().length];
        if (clazz.getTypeParameters().length > 0) {
            for (int i = 0; i < clazz.getTypeParameters().length; i++) {
                typeVars[i] = TypeVariableName.get(clazz.getTypeParameters()[i]);
                typeSpec.addTypeVariable(typeVars[i]);
            }
            if (clazz.isInterface()) {
                typeSpec.addSuperinterface(ParameterizedTypeName.get(ClassName.get(clazz), typeVars));
            }
        } else {
            if (clazz.isInterface()) {
                typeSpec.addSuperinterface(clazz);
            }
        }
        return typeVars;
    }

    private Set<String> applyClassSuppressions(Class<?> clazz, TypeSpec.Builder typeSpec) {
        Set<String> classSuppressions = new HashSet<>();
        // Removed hardcoded "all" and "deprecation" to allow for more surgical suppression at the method level.
        // We still collect suppressions based on the class and its interfaces.
        collectSuppressions(clazz, classSuppressions);
        if (clazz.isAnnotationPresent(Deprecated.class)) {
            classSuppressions.remove("deprecation");
        }

        Set<java.lang.reflect.Type> allInterfaces = getAllGenericInterfaces(clazz);
        for (java.lang.reflect.Type ifaceType : allInterfaces) {
            collectSuppressions(getRawType(ifaceType), classSuppressions);
        }

        if (quirks != null && quirks.has(clazz.getName())) {
            JsonObject classQuirks = quirks.getAsJsonObject(clazz.getName());
            if (classQuirks.has(ADDITIONAL_SUPPRESSIONS)) {
                classQuirks.getAsJsonArray(ADDITIONAL_SUPPRESSIONS).forEach(e -> classSuppressions.add(e.getAsString()));
            }
        }

        if (!classSuppressions.isEmpty()) {
            AnnotationSpec.Builder suppressBuilder = AnnotationSpec.builder(SuppressWarnings.class);
            for (String s : classSuppressions) {
                suppressBuilder.addMember("value", "$S", s);
            }
            typeSpec.addAnnotation(suppressBuilder.build());
        }
        return classSuppressions;
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
        for (java.lang.reflect.Type ifaceType : getAllGenericInterfaces(clazz)) {
            Class<?> iface = getRawType(ifaceType);
            if (iface != null && isGeneratableType(iface) && !iface.isSealed()) {
                ourInterfaces.add(ifaceType);
            }
        }
        return ourInterfaces;
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
        List<java.lang.reflect.Type> hierarchy = new ArrayList<>();
        hierarchy.add(clazz);
        hierarchy.addAll(getAllGenericInterfaces(clazz));

        for (java.lang.reflect.Type type : hierarchy) {
            Class<?> c = getRawType(type);
            if (c == null) continue;
            for (Method m : c.getDeclaredMethods()) {
                if (shouldSkipMethod(m, clazz)) continue;
                methodsBySignature.computeIfAbsent(getMethodSignature(m, clazz, typeVars, typeMap), _ -> new ArrayList<>()).add(m);
            }
        }
        return methodsBySignature;
    }

    private boolean shouldSkipMethod(Method m, Class<?> clazz) {
        if (java.lang.reflect.Modifier.isStatic(m.getModifiers())) return true;
        if (m.getDeclaringClass() == Object.class) return true;
        if (m.isBridge() || m.isSynthetic() || m.getName().contains("$")) return true;

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
        return name.contains("net.minecraft.") || name.contains("com.mojang.");
    }

    private boolean isAccessible(java.lang.reflect.Type type) {
        if (type == null) return true;
        if (type instanceof java.lang.reflect.TypeVariable) return true;
        if (type instanceof java.lang.reflect.WildcardType) return true;
        
        Class<?> raw = getRawType(type);
        if (raw == null) return true;
        if (raw.isPrimitive()) return true;
        if (raw.isArray()) return isAccessible(raw.getComponentType());
        
        if (!java.lang.reflect.Modifier.isPublic(raw.getModifiers())) return false;
        
        // Also check declaring class if it's an inner class
        Class<?> declaring = raw.getDeclaringClass();
        if (declaring != null && !isAccessible(declaring)) return false;

        if (type instanceof java.lang.reflect.ParameterizedType pt) {
            for (java.lang.reflect.Type arg : pt.getActualTypeArguments()) {
                if (!isAccessible(arg)) return false;
            }
        }
        return true;
    }

    private Set<Method> selectMethodsToGenerate(Class<?> clazz, Map<String, List<Method>> methodsBySignature) {
        Set<Method> methodsToGenerate = new HashSet<>();
        for (List<Method> providers : methodsBySignature.values()) {
            Method bestMethod = selectBestMethod(providers);

            boolean declaredInThis = false;
            Set<Class<?>> minimalProviders = new HashSet<>();
            boolean isMissingImplementation = false;

            for (Method m : providers) {
                if (m.getDeclaringClass() == clazz) {
                    declaredInThis = true;
                }
                if (isGeneratableType(m.getDeclaringClass())) {
                    minimalProviders.add(m.getDeclaringClass());
                } else if (!m.isDefault() && !java.lang.reflect.Modifier.isStatic(m.getModifiers())) {
                    isMissingImplementation = true;
                }
            }

            minimalProviders.removeIf(a -> minimalProviders.stream().anyMatch(b -> a != b && a.isAssignableFrom(b)));

            if (declaredInThis || minimalProviders.size() > 1 || isMissingImplementation) {
                methodsToGenerate.add(bestMethod);
            }
        }
        return methodsToGenerate;
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
        return java.lang.reflect.Modifier.isPublic(m.getModifiers())
                && !java.lang.reflect.Modifier.isStatic(m.getModifiers())
                && m.getDeclaringClass() != Object.class
                && !m.getName().equals("equals")
                && !m.getName().equals("hashCode")
                && !m.getName().equals("toString")
                && !m.getName().equals("clone");
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
        // Always add @Override for mirrored methods if we are in an interface (which we are)
        // and the method is actually overriding something. 
        // For simplicity and to satisfy the linter, we add it if the method exists in any super-interface.
        if (isOverriding(m, clazz)) {
            builder.addAnnotation(Override.class);
        }

        applyMethodAnnotations(builder, m, clazz, ourTypeVars, typeMap, classSuppressions);
        if (m.isVarArgs() && isGenericVarargs(m) && (java.lang.reflect.Modifier.isStatic(m.getModifiers()) || java.lang.reflect.Modifier.isFinal(m.getModifiers()) || java.lang.reflect.Modifier.isPrivate(m.getModifiers()))) {
            builder.addAnnotation(SafeVarargs.class);
        }
        builder.returns(mapType(m.getGenericReturnType(), clazz, ourTypeVars, typeMap));

        for (TypeVariable<Method> tv : m.getTypeParameters()) {
            builder.addTypeVariable(TypeVariableName.get(tv));
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
        Set<String> suppressions = collectMethodSuppressions(m, clazz, ourTypeVars, typeMap);
        suppressions.removeAll(classSuppressions); // Filter out class-level suppressions
        if (!suppressions.isEmpty()) {
            AnnotationSpec.Builder suppressBuilder = AnnotationSpec.builder(SuppressWarnings.class);
            suppressions.forEach(s -> suppressBuilder.addMember("value", "$S", s));
            builder.addAnnotation(suppressBuilder.build());
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
                depBuilder.addMember("forRemoval", "$L", true);
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
        if (m.isVarArgs()) {
            builder.varargs(true);
        }
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

    private void addQuirkSuppressions(JsonObject methodQuirks, String key, Set<String> suppressions) {
        if (methodQuirks.has(key)) {
            JsonObject mq = methodQuirks.getAsJsonObject(key);
            if (mq.has(ADDITIONAL_SUPPRESSIONS)) {
                mq.getAsJsonArray(ADDITIONAL_SUPPRESSIONS).forEach(e -> suppressions.add(e.getAsString()));
            }
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
        }
    }

    private void collectSuppressionsFromType(java.lang.reflect.Type type, Set<String> suppressions) {
        Class<?> raw = getRawType(type);
        if (raw != null) {
            collectSuppressions(raw, suppressions);
            if (raw.getTypeParameters().length > 0 && !(type instanceof java.lang.reflect.ParameterizedType)) {
                suppressions.add("rawtypes");
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
        if (methodQuirks.has(key)) {
            JsonObject mq = methodQuirks.getAsJsonObject(key);
            if (mq.has(REPLACEMENT)) {
                return mq.get(REPLACEMENT).getAsString();
            }
        }
        return null;
    }

    private boolean isOverriding(Method m, Class<?> clazz) {
        for (java.lang.reflect.Type iface : getAllGenericInterfaces(clazz)) {
            Class<?> raw = getRawType(iface);
            if (raw == null || !isGeneratableType(raw)) continue;
            try {
                raw.getMethod(m.getName(), m.getParameterTypes());
                return true;
            } catch (NoSuchMethodException _) {
                // Not in this interface
            }
        }
        return false;
    }

    private boolean isGeneratableType(Class<?> clazz) {
        if (clazz == null) return false;
        if (!clazz.isInterface() && !java.lang.reflect.Modifier.isAbstract(clazz.getModifiers())) return false;
        String name = clazz.getName();
        return (name.startsWith("org.bukkit.") || name.startsWith("org.spigotmc.") || name.startsWith("co.aikar.timings.") || name.startsWith("com.destroystokyo.paper.") || name.startsWith("io.papermc.paper.") || name.startsWith("com.velocitypowered.api.") || name.startsWith("net.md_5.bungee.") || name.startsWith("io.github.waterfallmc."))
                && !name.contains(".craftbukkit.");
    }
}
