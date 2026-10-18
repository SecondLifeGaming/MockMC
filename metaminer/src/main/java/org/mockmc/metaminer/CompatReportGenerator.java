package org.mockmc.metaminer;

import org.mockmc.metaminer.diff.CompatMarkdownRenderer;
import org.mockmc.metaminer.diff.PairwiseDiff;
import org.mockmc.metaminer.diff.VersionDiffEngine;
import org.mockmc.metaminer.manifest.ApiManifest;
import org.mockmc.metaminer.manifest.BytecodeManifestScanner;

import org.mockmc.metaminer.util.PlatformJarResolver;

import java.io.File;
import java.io.IOException;
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
		PlatformJarResolver.TargetVersions versions = PlatformJarResolver.resolveTargetVersions(dataFolder);

		File targetPaper = PlatformJarResolver.findBestJar(cacheDir, versions.primary(), PlatformJarResolver.PREFIX_REMAPPED_PAPER, PlatformJarResolver.PREFIX_PAPER_API);
		File baselinePaper = PlatformJarResolver.findBestJar(cacheDir, versions.baseline(), PlatformJarResolver.PREFIX_REMAPPED_PAPER, PlatformJarResolver.PREFIX_PAPER_API);

		if (targetPaper == null || baselinePaper == null) {
			LOGGER.log(Level.WARNING, "Skipping CompatReportGenerator: target paper ({0}) or baseline paper ({1}) not found",
					new Object[]{targetPaper, baselinePaper});
			return;
		}

		LOGGER.log(Level.INFO, "Scanning and comparing baseline Paper {0} against target Paper {1}",
				new Object[]{baselinePaper.getName(), targetPaper.getName()});

		BytecodeManifestScanner.ScanConfiguration baselineConfig = new BytecodeManifestScanner.ScanConfiguration(
				"paper",
				PlatformJarResolver.extractVersion(baselinePaper.getName()),
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
				PlatformJarResolver.extractVersion(targetPaper.getName()),
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
}
