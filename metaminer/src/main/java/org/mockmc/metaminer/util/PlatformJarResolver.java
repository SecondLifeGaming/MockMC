package org.mockmc.metaminer.util;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.File;
import java.io.FileReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Shared utility for resolving version boundaries, target JARs, and build numbers across upstream platforms.
 */
public final class PlatformJarResolver {

	private static final Logger LOGGER = Logger.getLogger(PlatformJarResolver.class.getName());
	private static final Pattern BUILD_PATTERN = Pattern.compile("-(\\d+)\\.jar$");

	public static final String KEY_PLATFORMS = "platforms";
	public static final String PREFIX_REMAPPED_PAPER = "remapped-paper-";
	public static final String PREFIX_REMAPPED_FOLIA = "remapped-folia-";
	public static final String PREFIX_PAPER_API = "paper-api-";
	public static final String PREFIX_FOLIA_API = "folia-api-";

	private PlatformJarResolver() {}

	public record TargetVersions(String primary, String baseline) {}

	public static TargetVersions resolveTargetVersions(File rootDir) {
		File manifestFile = new File(rootDir, "jars/manifests/versions.json");
		String primaryVersion = "26.3";
		String baselineVersion = "26.2";

		if (manifestFile.exists()) {
			try (FileReader fr = new FileReader(manifestFile, StandardCharsets.UTF_8)) {
				JsonObject root = JsonParser.parseReader(fr).getAsJsonObject();
				if (root.has("targetRange")) {
					JsonObject range = root.getAsJsonObject("targetRange");
					if (range.has("end")) primaryVersion = range.get("end").getAsString();
					if (range.has("start")) baselineVersion = range.get("start").getAsString();
				}
			} catch (Exception e) {
				LOGGER.log(Level.WARNING, "Failed to parse targetRange from versions.json, falling back to defaults", e);
			}
		}
		return new TargetVersions(primaryVersion, baselineVersion);
	}

	public static List<String> resolvePlatformVersions(File rootDir, String platform) {
		File manifestFile = new File(rootDir, "jars/manifests/versions.json");
		List<String> versions = new ArrayList<>();
		if (manifestFile.exists()) {
			try (FileReader fr = new FileReader(manifestFile, StandardCharsets.UTF_8)) {
				JsonObject root = JsonParser.parseReader(fr).getAsJsonObject();
				if (root.has(KEY_PLATFORMS) && root.getAsJsonObject(KEY_PLATFORMS).has(platform)) {
					JsonArray verArr = root.getAsJsonObject(KEY_PLATFORMS).getAsJsonObject(platform).getAsJsonArray("versions");
					for (JsonElement el : verArr) {
						if (el.isJsonObject() && el.getAsJsonObject().has("version")) {
							versions.add(el.getAsJsonObject().get("version").getAsString());
						}
					}
				}
			} catch (Exception e) {
				LOGGER.log(Level.WARNING, e, () -> "Failed to parse versions for " + platform + " in versions.json");
			}
		}
		if (versions.isEmpty()) {
			versions.addAll(List.of("26.3", "26.2", "26.1.2", "1.21.11", "1.20.6"));
		}
		return versions;
	}

	public static String extractVersion(String jarName) {
		String name = jarName.replace(".jar", "");
		for (String prefix : List.of(PREFIX_REMAPPED_PAPER, PREFIX_REMAPPED_FOLIA, PREFIX_PAPER_API, PREFIX_FOLIA_API)) {
			if (name.startsWith(prefix)) {
				return name.substring(prefix.length());
			}
		}
		return name;
	}

	public static File findBestJar(File dir, String targetVersion, String... prefixes) {
		List<File> found = new ArrayList<>();
		collectJars(dir, prefixes, found);

		List<File> matchingVersion = found.stream()
				.filter(f -> f.getName().contains("-" + targetVersion + "-") || f.getName().contains("-" + targetVersion + "."))
				.toList();

		List<File> candidates = matchingVersion.isEmpty() ? found : matchingVersion;
		if (candidates.isEmpty()) {
			return null;
		}

		return candidates.stream().max(Comparator.comparingInt(PlatformJarResolver::extractBuildNumber)).orElse(null);
	}

	public static int extractBuildNumber(File file) {
		Matcher matcher = BUILD_PATTERN.matcher(file.getName());
		if (matcher.find()) {
			try {
				return Integer.parseInt(matcher.group(1));
			} catch (NumberFormatException _) {
				return 0;
			}
		}
		return 0;
	}

	public static void collectJars(File dir, String[] prefixes, List<File> found) {
		File[] files = dir.listFiles();
		if (files == null) {
			return;
		}
		for (File f : files) {
			if (f.isDirectory()) {
				collectJars(f, prefixes, found);
			} else if (f.getName().endsWith(".jar")) {
				for (String prefix : prefixes) {
					if (f.getName().startsWith(prefix)) {
						found.add(f);
						break;
					}
				}
			}
		}
	}
}
