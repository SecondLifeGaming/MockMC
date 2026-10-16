package org.mockmc.metaminer;

import org.mockmc.metaminer.diff.CompatMarkdownRenderer;
import org.mockmc.metaminer.diff.PairwiseDiff;
import org.mockmc.metaminer.diff.VersionDiffEngine;
import org.mockmc.metaminer.manifest.ApiManifest;
import org.mockmc.metaminer.manifest.BytecodeManifestScanner;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

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

	@Override
	public void generateData() throws IOException {
		File cacheDir = new File(dataFolder, "jars/cache");
		File primary = findLatestJar(cacheDir, "remapped-paper-", "paper-api-");
		File fallback = findLatestJar(cacheDir, "remapped-folia-", "folia-api-");

		if (primary == null || fallback == null) {
			LOGGER.log(Level.WARNING, "Skipping CompatReportGenerator: paper ({0}) or folia ({1}) not found",
					new Object[]{primary, fallback});
			return;
		}

		LOGGER.log(Level.INFO, "Scanning and comparing primary {0} against fallback {1}",
				new Object[]{primary.getName(), fallback.getName()});

		BytecodeManifestScanner.ScanConfiguration fallbackConfig = new BytecodeManifestScanner.ScanConfiguration(
				"folia",
				extractVersion(fallback.getName()),
				0,
				"extension",
				"metaminer",
				"unknown",
				false,
				"none",
				"none",
				"ASM9_8",
				API_PREFIXES,
				List.of(),
				List.of(),
				true,
				"Fallback extension"
		);

		BytecodeManifestScanner.ScanConfiguration primaryConfig = new BytecodeManifestScanner.ScanConfiguration(
				"paper",
				extractVersion(primary.getName()),
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

		ApiManifest fallbackManifest = BytecodeManifestScanner.scanJar(fallback, fallbackConfig);
		ApiManifest primaryManifest = BytecodeManifestScanner.scanJar(primary, primaryConfig);

		PairwiseDiff diff = VersionDiffEngine.computeDiff(fallbackManifest, primaryManifest);

		File report = new File(dataFolder, "COMPAT.md");
		CompatMarkdownRenderer.renderToMarkdown(diff, report, primary.getName(), fallback.getName());

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

	private static File findLatestJar(File dir, String... prefixes) {
		List<File> found = new ArrayList<>();
		collectJars(dir, prefixes, found);
		return found.stream().max(Comparator.comparing(File::getPath)).orElse(null);
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
