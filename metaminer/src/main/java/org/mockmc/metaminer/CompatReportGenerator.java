package org.mockmc.metaminer;

import org.mockmc.metaminer.diff.CompatMarkdownRenderer;
import org.mockmc.metaminer.diff.PairwiseDiff;
import org.mockmc.metaminer.diff.VersionDiffEngine;
import org.mockmc.metaminer.manifest.ApiManifest;
import org.mockmc.metaminer.manifest.BytecodeManifestScanner;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Modernized compatibility report generator that scans upstream platform JARs
 * into canonical ApiManifest structures and computes deterministic diffs via
 * VersionDiffEngine, emitting COMPAT.md.
 */
public class CompatReportGenerator implements DataGenerator {

	private static final Logger LOGGER = Logger.getLogger(CompatReportGenerator.class.getName());

	private static final List<String> API_PREFIXES = List.of(
			"org.bukkit",
			"io.papermc.paper",
			"com.destroystokyo.paper",
			"co.aikar.timings"
	);

	private final File dataFolder;

	public CompatReportGenerator(File dataFolder) {
		this.dataFolder = dataFolder;
	}

	private record TargetVersions(String primary, String baseline) {}

	private TargetVersions resolveTargetVersions() {
		File manifestFile = new File(dataFolder, "jars/manifests/versions.json");
		String primaryVersion = "26.3";
		String baselineVersion = "26.2";

		if (manifestFile.exists()) {
			try (java.io.FileReader fr = new java.io.FileReader(manifestFile, StandardCharsets.UTF_8)) {
				com.google.gson.JsonObject root = com.google.gson.JsonParser.parseReader(fr).getAsJsonObject();
				if (root.has("targetRange")) {
					com.google.gson.JsonObject range = root.getAsJsonObject("targetRange");
					if (range.has("end")) primaryVersion = range.get("end").getAsString();
					if (range.has("start")) baselineVersion = range.get("start").getAsString();
				}
			} catch (Exception e) {
				LOGGER.log(Level.WARNING, "Failed to parse versions.json for CompatReportGenerator", e);
			}
		}
		return new TargetVersions(primaryVersion, baselineVersion);
	}

	@Override
	public void generateData() throws IOException {
		File cacheDir = new File(dataFolder, "jars/cache");
		TargetVersions versions = resolveTargetVersions();

		File targetPaper = findBestJar(cacheDir, versions.primary(), "remapped-paper-", "paper-api-");
		File baselinePaper = findBestJar(cacheDir, versions.baseline(), "remapped-paper-", "paper-api-");

		if (targetPaper == null || baselinePaper == null) {
			LOGGER.log(Level.WARNING, "Skipping CompatReportGenerator: target paper ({0}) or baseline paper ({1}) not found",
					new Object[]{targetPaper, baselinePaper});
			return;
		}

		LOGGER.log(Level.INFO, "Scanning and comparing baseline Paper {0} against target Paper {1}",
				new Object[]{baselinePaper.getName(), targetPaper.getName()});

		BytecodeManifestScanner.ScanConfiguration baselineConfig = new BytecodeManifestScanner.ScanConfiguration(
				"paper",
				extractVersion(baselinePaper.getName()),
				0,
				"baseline",
				"metaminer",
				"unknown",
				false,
				"none",
				"none",
				"ASM9_8",
				API_PREFIXES,
				List.of(),
				List.of(),
				false,
				null
		);

		BytecodeManifestScanner.ScanConfiguration targetConfig = new BytecodeManifestScanner.ScanConfiguration(
				"paper",
				extractVersion(targetPaper.getName()),
				0,
				"canonical",
				"metaminer",
				"unknown",
				false,
				"none",
				"none",
				"ASM9_8",
				API_PREFIXES,
				List.of(),
				List.of(),
				false,
				null
		);

		ApiManifest baselineManifest = BytecodeManifestScanner.scanJar(baselinePaper, baselineConfig);
		ApiManifest targetManifest = BytecodeManifestScanner.scanJar(targetPaper, targetConfig);

		PairwiseDiff diff = VersionDiffEngine.computeDiff(baselineManifest, targetManifest);

		File report = new File(dataFolder, "COMPAT.md");
		CompatMarkdownRenderer.renderToMarkdown(diff, report, targetPaper.getName(), baselinePaper.getName());

		LOGGER.log(Level.INFO, "Deterministic compatibility report generated at {0}", report.getAbsolutePath());
	}

	private static String extractVersion(String jarName) {
		String name = jarName.replace(".jar", "");
		for (String prefix : List.of("remapped-paper-", "remapped-folia-", "paper-api-", "folia-api-")) {
			if (name.startsWith(prefix)) {
				return name.substring(prefix.length());
			}
		}
		return name;
	}

	private static final Pattern BUILD_PATTERN = Pattern.compile("-(\\d+)\\.jar$");

	private static File findBestJar(File dir, String targetVersion, String... prefixes) {
		List<File> found = new ArrayList<>();
		collectJars(dir, prefixes, found);

		List<File> matchingVersion = found.stream()
				.filter(f -> f.getName().contains("-" + targetVersion + "-") || f.getName().contains("-" + targetVersion + "."))
				.toList();

		List<File> candidates = matchingVersion.isEmpty() ? found : matchingVersion;
		if (candidates.isEmpty()) {
			return null;
		}

		return candidates.stream().max(Comparator.comparingInt(CompatReportGenerator::extractBuildNumber)).orElse(null);
	}

	private static int extractBuildNumber(File file) {
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

	private static void collectJars(File dir, String[] prefixes, List<File> found) {
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
