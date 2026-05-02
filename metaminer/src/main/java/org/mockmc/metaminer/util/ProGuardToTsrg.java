package org.mockmc.metaminer.util;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ProGuardToTsrg {

    private static final Pattern CLASS_PATTERN = Pattern.compile("^(.+?) -> (.+?):$");
    private static final Pattern FIELD_PATTERN = Pattern.compile("^    (?:\\d+:\\d+:)?(.+?) (.+?) -> (.+)$");
    private static final Pattern METHOD_PATTERN = Pattern.compile("^    (?:\\d+:\\d+:)?(.+?) (.+?)\\((.*?)\\) -> (.+)$");

    public static File convert(File proguardFile, File tsrgFile) throws IOException {
        if (tsrgFile.exists()) return tsrgFile;

        Map<String, String> classMap = new HashMap<>();
        List<String> output = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(proguardFile), StandardCharsets.UTF_8))) {
            String line;
            String currentMojangClass = null;
            String currentObfsClass = null;

            while ((line = reader.readLine()) != null) {
                if (line.startsWith("#") || line.trim().isEmpty()) continue;

                Matcher classMatcher = CLASS_PATTERN.matcher(line);
                if (classMatcher.matches()) {
                    currentMojangClass = classMatcher.group(1).replace('.', '/');
                    currentObfsClass = classMatcher.group(2).replace('.', '/');
                    output.add(currentObfsClass + " " + currentMojangClass);
                    classMap.put(classMatcher.group(1), currentMojangClass);
                    continue;
                }

                if (currentObfsClass == null) continue;

                Matcher methodMatcher = METHOD_PATTERN.matcher(line);
                if (methodMatcher.matches()) {
                    String returnType = methodMatcher.group(1);
                    String mojangName = methodMatcher.group(2);
                    String args = methodMatcher.group(3);
                    String obfsName = methodMatcher.group(4);

                    String descriptor = getDescriptor(returnType, args, classMap);
                    output.add("\t" + obfsName + " " + descriptor + " " + mojangName);
                    continue;
                }

                Matcher fieldMatcher = FIELD_PATTERN.matcher(line);
                if (fieldMatcher.matches()) {
                    // String type = fieldMatcher.group(1);
                    String mojangName = fieldMatcher.group(2);
                    String obfsName = fieldMatcher.group(3);
                    output.add("\t" + obfsName + " " + mojangName);
                }
            }
        }

        try (PrintWriter writer = new PrintWriter(new OutputStreamWriter(new FileOutputStream(tsrgFile), StandardCharsets.UTF_8))) {
            for (String line : output) {
                writer.println(line);
            }
        }

        return tsrgFile;
    }

    private static String getDescriptor(String returnType, String args, Map<String, String> classMap) {
        StringBuilder sb = new StringBuilder("(");
        if (!args.isEmpty()) {
            for (String arg : args.split(",")) {
                sb.append(toBinaryType(arg.trim(), classMap));
            }
        }
        sb.append(")");
        sb.append(toBinaryType(returnType, classMap));
        return sb.toString();
    }

    private static String toBinaryType(String type, Map<String, String> classMap) {
        int arrayDim = 0;
        while (type.endsWith("[]")) {
            arrayDim++;
            type = type.substring(0, type.length() - 2);
        }

        String base;
        switch (type) {
            case "void": base = "V"; break;
            case "boolean": base = "Z"; break;
            case "byte": base = "B"; break;
            case "char": base = "C"; break;
            case "short": base = "S"; break;
            case "int": base = "I"; break;
            case "long": base = "J"; break;
            case "float": base = "F"; break;
            case "double": base = "D"; break;
            default:
                base = "L" + type.replace('.', '/') + ";";
                break;
        }

        return "[".repeat(arrayDim) + base;
    }
}
