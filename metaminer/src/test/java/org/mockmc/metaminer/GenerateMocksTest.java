package org.mockmc.metaminer;

import com.google.common.reflect.ClassPath;
import com.squareup.javapoet.*;
import org.bukkit.Bukkit;

import javax.lang.model.element.Modifier;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.*;
import java.util.*;

public class GenerateMocksTest {

    @org.junit.jupiter.api.Test
    public void test() throws IOException {
        File dataFolder = new File("build/generated-mocks");
        dataFolder.mkdirs();
        ClassPath cp = ClassPath.from(Bukkit.class.getClassLoader());
        for (ClassPath.ClassInfo info : cp.getTopLevelClassesRecursive("org.bukkit")) {
            try {
                if (info.getName().startsWith("org.bukkit.craftbukkit")) continue;
                if (info.getName().contains("BanList")) continue;
                if (info.getName().contains("OldEnum")) continue;
                if (info.getName().contains("UnsafeValues")) continue;
                if (info.getName().contains("ListPersistentDataType")) continue;
                if (info.getName().contains("org.bukkit.block.data")) continue;
                Class<?> clazz = info.load();
                if (clazz.isInterface()) {
                    generateForClass(clazz, dataFolder);
                }
                for (Class<?> inner : clazz.getDeclaredClasses()) {
                    if (inner.isInterface()) {
                        generateForClass(inner, dataFolder);
                    }
                }
            } catch (Throwable t) {}
        }
    }

    private static void generateForClass(Class<?> clazz, File dataFolder) throws IOException {
        if (!java.lang.reflect.Modifier.isPublic(clazz.getModifiers())) return;
        if (clazz.isSealed()) return;
        
        String name = clazz.getName().replace(clazz.getPackageName() + ".", "").replace("$", "_");
        TypeSpec.Builder typeSpec = TypeSpec.interfaceBuilder(name + "BaseMock")
                .addModifiers(Modifier.PUBLIC);

        TypeVariableName[] typeVars = new TypeVariableName[clazz.getTypeParameters().length];
        if (clazz.getTypeParameters().length > 0) {
            for (int i = 0; i < clazz.getTypeParameters().length; i++) {
                typeVars[i] = TypeVariableName.get(clazz.getTypeParameters()[i]);
                typeSpec.addTypeVariable(typeVars[i]);
            }
            typeSpec.addSuperinterface(ParameterizedTypeName.get(ClassName.get(clazz), typeVars));
        } else {
            typeSpec.addSuperinterface(clazz);
        }

        Set<Class<?>> superIfaces = new HashSet<>();
        for (java.lang.reflect.Type genericIface : clazz.getGenericInterfaces()) {
            Class<?> iface = getRawType(genericIface);
            if (isOurInterface(iface)) {
                superIfaces.add(iface);
            }
        }

        // Prune redundant interfaces
        Set<Class<?>> pruned = new HashSet<>(superIfaces);
        for (Class<?> a : superIfaces) {
            for (Class<?> b : superIfaces) {
                if (a != b && a.isAssignableFrom(b)) {
                    pruned.remove(a);
                }
            }
        }

        for (java.lang.reflect.Type genericIface : clazz.getGenericInterfaces()) {
            Class<?> iface = getRawType(genericIface);
            if (pruned.contains(iface)) {
                typeSpec.addSuperinterface(getMirroredTypeName(genericIface));
            }
        }

        Set<String> generatedSignatures = new HashSet<>();
        
        // Comprehensive conflict resolution:
        // We need to generate a method if:
        // 1. It is declared in the current interface.
        // 2. It is provided as a default by multiple parents (diamond conflict).
        // 3. It is abstract in a parent but a default is provided by another parent (abstract/default collision).
        
        Map<String, List<Method>> allHierarchyMethods = new HashMap<>();
        Set<Class<?>> allInterfaces = getAllInterfaces(clazz);
        for (Class<?> iface : allInterfaces) {
            for (Method m : iface.getMethods()) {
                if (java.lang.reflect.Modifier.isStatic(m.getModifiers())) continue;
                if (m.getDeclaringClass() == Object.class) continue;
                allHierarchyMethods.computeIfAbsent(getSignature(m), k -> new ArrayList<>()).add(m);
            }
        }

        Map<String, Method> mostSpecificMethods = new HashMap<>();
        for (Map.Entry<String, List<Method>> entry : allHierarchyMethods.entrySet()) {
            Method mostSpecific = null;
            for (Method m : entry.getValue()) {
                if (mostSpecific == null || mostSpecific.getReturnType().isAssignableFrom(m.getReturnType())) {
                    mostSpecific = m;
                }
            }
            mostSpecificMethods.put(entry.getKey(), mostSpecific);
        }

        Set<Method> methodsToGenerate = new HashSet<>();
        
        // We MUST generate anything declared in the current class to ensure correct return types
        Collections.addAll(methodsToGenerate, clazz.getDeclaredMethods());
        
        for (Map.Entry<String, Method> entry : mostSpecificMethods.entrySet()) {
            String sig = entry.getKey();
            Method bestMethod = entry.getValue();
            List<Method> providers = allHierarchyMethods.get(sig);
            
            int ourDefaultProviders = 0;
            boolean hasAbstractFromBukkit = false;
            
            for (Method m : providers) {
                if (isOurInterface(m.getDeclaringClass())) {
                    ourDefaultProviders++;
                } else if (!m.isDefault() && !java.lang.reflect.Modifier.isStatic(m.getModifiers())) {
                    hasAbstractFromBukkit = true;
                }
            }
            
            if (ourDefaultProviders > 1 || (ourDefaultProviders == 1 && hasAbstractFromBukkit)) {
                methodsToGenerate.add(bestMethod);
            } else if (ourDefaultProviders == 0) {
                boolean bukkitHasDefault = false;
                for (Method m : providers) {
                    if (m.isDefault()) {
                        bukkitHasDefault = true;
                        break;
                    }
                }
                if (!bukkitHasDefault) {
                    methodsToGenerate.add(bestMethod);
                }
            }
        }

        for (Method m : methodsToGenerate) {
            if (java.lang.reflect.Modifier.isStatic(m.getModifiers())) continue;
            if (m.getDeclaringClass() == Object.class) continue;
            if (m.getName().equals("compareTo")) continue;
            if (m.getName().equals("forEach")) continue;
            if (m.getName().equals("spliterator")) continue;
            if (m.getName().equals("toString")) continue;
            if (m.getName().equals("equals")) continue;
            if (m.getName().equals("hashCode")) continue;
            if (m.isBridge()) continue;
            if (hasUnresolvedTypeVars(m, clazz)) continue;

            String signature = getSignature(m);
            if (generatedSignatures.contains(signature)) continue;
            generatedSignatures.add(signature);

            MethodSpec.Builder builder = MethodSpec.methodBuilder(m.getName())
                    .addModifiers(Modifier.PUBLIC, Modifier.DEFAULT)
                    .addAnnotation(Override.class)
                    .returns(mapType(m.getGenericReturnType(), clazz, typeVars));
            
            if (m.getTypeParameters().length > 0) {
                for (TypeVariable<Method> tv : m.getTypeParameters()) {
                    builder.addTypeVariable(TypeVariableName.get(tv));
                }
            }

            for (int i = 0; i < m.getParameters().length; i++) {
                builder.addParameter(mapType(m.getGenericParameterTypes()[i], clazz, typeVars), "arg" + i);
            }

            for (java.lang.reflect.Type exp : m.getGenericExceptionTypes()) {
                builder.addException(TypeName.get(exp));
            }

            builder.addStatement("throw new org.mockmc.mockmc.exception.UnimplementedOperationException()");
            typeSpec.addMethod(builder.build());
        }

        String packageName = "org.mockmc.mockmc.generated." + clazz.getPackageName();
        JavaFile javaFile = JavaFile.builder(packageName, typeSpec.build())
                .indent("    ")
                .build();
        
        javaFile.writeTo(dataFolder);
    }

    private static TypeName mapType(java.lang.reflect.Type type, Class<?> clazz, TypeVariableName[] ourTypeVars) {
        if (type instanceof TypeVariable) {
            TypeVariable<?> tv = (TypeVariable<?>) type;
            for (int i = 0; i < clazz.getTypeParameters().length; i++) {
                if (clazz.getTypeParameters()[i].getName().equals(tv.getName())) {
                    return ourTypeVars[i];
                }
            }
            return TypeVariableName.get(tv);
        }
        if (type instanceof java.lang.reflect.ParameterizedType) {
            java.lang.reflect.ParameterizedType pt = (java.lang.reflect.ParameterizedType) type;
            TypeName[] args = new TypeName[pt.getActualTypeArguments().length];
            for (int i = 0; i < args.length; i++) {
                args[i] = mapType(pt.getActualTypeArguments()[i], clazz, ourTypeVars);
            }
            return ParameterizedTypeName.get(ClassName.get(getRawType(pt)), args);
        }
        if (type instanceof java.lang.reflect.WildcardType) {
            java.lang.reflect.WildcardType wt = (java.lang.reflect.WildcardType) type;
            if (wt.getLowerBounds().length > 0) {
                return WildcardTypeName.supertypeOf(mapType(wt.getLowerBounds()[0], clazz, ourTypeVars));
            }
            if (wt.getUpperBounds().length > 0 && wt.getUpperBounds()[0] != Object.class) {
                // Heuristic: If upper bound matches a type variable's bound, try to use the type variable
                TypeName bound = mapType(wt.getUpperBounds()[0], clazz, ourTypeVars);
                for (TypeVariableName tv : ourTypeVars) {
                    if (tv.bounds.contains(bound)) return tv;
                }
                return WildcardTypeName.subtypeOf(bound);
            }
            return WildcardTypeName.subtypeOf(Object.class);
        }
        if (type instanceof java.lang.reflect.GenericArrayType) {
            return ArrayTypeName.of(mapType(((java.lang.reflect.GenericArrayType) type).getGenericComponentType(), clazz, ourTypeVars));
        }
        return TypeName.get(type);
    }

    private static String getSignature(Method m) {
        StringBuilder sb = new StringBuilder();
        sb.append(m.getName());
        for (Class<?> p : m.getParameterTypes()) {
            sb.append(p.getName());
        }
        return sb.toString();
    }

    private static Class<?> getRawType(java.lang.reflect.Type type) {
        if (type instanceof Class) return (Class<?>) type;
        if (type instanceof java.lang.reflect.ParameterizedType) return (Class<?>) ((java.lang.reflect.ParameterizedType) type).getRawType();
        return null;
    }

    private static TypeName getMirroredTypeName(java.lang.reflect.Type type) {
        Class<?> raw = getRawType(type);
        String name = raw.getName().replace(raw.getPackageName() + ".", "").replace("$", "_");
        ClassName mirroredRaw = ClassName.get("org.mockmc.mockmc.generated." + raw.getPackageName(), name + "BaseMock");
        if (type instanceof java.lang.reflect.ParameterizedType) {
            java.lang.reflect.ParameterizedType pt = (java.lang.reflect.ParameterizedType) type;
            TypeName[] args = new TypeName[pt.getActualTypeArguments().length];
            for (int i = 0; i < args.length; i++) {
                args[i] = TypeName.get(pt.getActualTypeArguments()[i]);
            }
            return ParameterizedTypeName.get(mirroredRaw, args);
        }
        return mirroredRaw;
    }


    private static Set<Class<?>> getAllInterfaces(Class<?> clazz) {
        Set<Class<?>> res = new HashSet<>();
        if (clazz == null) return res;
        if (clazz.isInterface()) res.add(clazz);
        for (Class<?> i : clazz.getInterfaces()) {
            res.addAll(getAllInterfaces(i));
        }
        return res;
    }

    private static boolean isOurInterface(Class<?> clazz) {
        if (clazz == null) return false;
        if (!clazz.isInterface()) return false;
        String name = clazz.getName();
        return name.startsWith("org.bukkit.") && 
               !name.contains(".craftbukkit.") && 
               !name.contains("OldEnum") && 
               !name.contains("UnsafeValues");
    }

    private static boolean hasUnresolvedTypeVars(Method m, Class<?> clazz) {
        Set<String> known = new HashSet<>();
        for (TypeVariable<?> tv : clazz.getTypeParameters()) known.add(tv.getName());
        for (TypeVariable<?> tv : m.getTypeParameters()) known.add(tv.getName());
        
        if (isUnresolved(m.getGenericReturnType(), known)) return true;
        for (java.lang.reflect.Type t : m.getGenericParameterTypes()) {
            if (isUnresolved(t, known)) return true;
        }
        return false;
    }

    private static boolean isUnresolved(java.lang.reflect.Type t, Set<String> known) {
        if (t instanceof TypeVariable) return !known.contains(((TypeVariable<?>) t).getName());
        if (t instanceof ParameterizedType) {
            for (java.lang.reflect.Type arg : ((ParameterizedType) t).getActualTypeArguments()) {
                if (isUnresolved(arg, known)) return true;
            }
        }
        if (t instanceof GenericArrayType) {
            return isUnresolved(((GenericArrayType) t).getGenericComponentType(), known);
        }
        return false;
    }
}
