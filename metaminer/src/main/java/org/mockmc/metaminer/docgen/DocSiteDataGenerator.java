package org.mockmc.metaminer.docgen;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.mockmc.metaminer.DataGenerator;
import org.mockmc.metaminer.diff.PairwiseDiff;
import org.mockmc.metaminer.diff.VersionDiffEngine;
import org.mockmc.metaminer.manifest.ApiManifest;
import org.mockmc.metaminer.manifest.BytecodeManifestScanner;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Generates pre-chunked, indexed static JSON datasets for the VitePress API Explorer:
 * 1. `explorer-meta.json`: Summary stats, total count, package lists, and index of page chunks.
 * 2. `pages/page-N.json`: Compact pre-paginated chunks (50 symbols per chunk, ~15 KB each) for instant initial render.
 * 3. `search-index.json`: Lightweight inverted search index for instantaneous sub-millisecond client-side filtering.
 */
public class DocSiteDataGenerator implements DataGenerator {

	private static final Logger LOGGER = Logger.getLogger(DocSiteDataGenerator.class.getName());
	private static final Gson GSON = new GsonBuilder().create();
	private static final int PAGE_SIZE = 50;

	private static final List<String> API_PREFIXES = List.of(
			"org.bukkit",
			"io.papermc.paper",
			"com.destroystokyo.paper",
			"co.aikar.timings"
	);

	private static final String STATUS_INTRODUCED = "INTRODUCED";
	private static final String STATUS_REMOVED = "REMOVED";
	private static final String STATUS_DEPRECATED = "DEPRECATED";
	private static final String STATUS_EXTENSION = "EXTENSION";
	private static final String STATUS_STABLE = "STABLE";

	private static final String TEXT_INTRODUCED_26_3 = "Introduced in 26.3";
	private static final String TEXT_STABLE_RANGE = "Stable throughout 26.2 - 26.3";
	private static final String TEXT_FOLIA_EXTENSION = "Folia Threaded Regions Extension (26.2)";

	private static final String CSS_ADDED = "status-added";
	private static final String CSS_STABLE = "status-stable";
	private static final String CSS_DEPRECATED = "status-deprecated";
	private static final String CSS_EXTENSION = "status-extension";

	private static final String SCOPE_FOLIA_EXTENSION = "Folia Extension (io.papermc.paper.threadedregions.*)";
	private static final String ACCESS_PUBLIC = "PUBLIC";
	private static final String ACCESS_PROTECTED = "PROTECTED";

	private static final String PREFIX_REMAPPED_PAPER = "remapped-paper-";
	private static final String PREFIX_PAPER_API = "paper-api-";

	private final File rootDir;

	public DocSiteDataGenerator(File rootDir) {
		this.rootDir = rootDir;
	}

	private record TargetVersions(String primary, String baseline) {}

	private TargetVersions resolveTargetVersions() {
		File manifestFile = new File(rootDir, "jars/manifests/versions.json");
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
				LOGGER.log(Level.WARNING, "Failed to parse versions.json, falling back to defaults", e);
			}
		}
		return new TargetVersions(primaryVersion, baselineVersion);
	}

	@Override
	public void generateData() throws IOException {
		File cacheDir = new File(rootDir, "jars/cache");
		TargetVersions versions = resolveTargetVersions();

		File primaryPaper = findBestJar(cacheDir, versions.primary(), PREFIX_REMAPPED_PAPER, PREFIX_PAPER_API);
		File baselinePaper = findBestJar(cacheDir, versions.baseline(), PREFIX_REMAPPED_PAPER, PREFIX_PAPER_API);
		File foliaJar = findBestJar(cacheDir, versions.baseline(), "remapped-folia-", "folia-api-");

		if (primaryPaper == null) {
			LOGGER.log(Level.WARNING, "Skipping DocSiteDataGenerator: Required primary Paper JAR for version {0} not found in {1}",
					new Object[]{versions.primary(), cacheDir.getAbsolutePath()});
			return;
		}

		BytecodeManifestScanner.ScanConfiguration primaryConfig = createScanConfig("paper", primaryPaper.getName(), "canonical", false, null);
		ApiManifest primaryManifest = BytecodeManifestScanner.scanJar(primaryPaper, primaryConfig);

		Map<String, PairwiseDiff.ChangeEntry> changeMap = new HashMap<>();
		PairwiseDiff paperDiff = null;

		if (baselinePaper != null) {
			BytecodeManifestScanner.ScanConfiguration baselineConfig = createScanConfig("paper", baselinePaper.getName(), "baseline", false, null);
			ApiManifest baselineManifest = BytecodeManifestScanner.scanJar(baselinePaper, baselineConfig);
			paperDiff = VersionDiffEngine.computeDiff(baselineManifest, primaryManifest);
			for (PairwiseDiff.ChangeEntry entry : paperDiff.changes()) {
				changeMap.put(entry.symbol(), entry);
			}
		}

		List<ClientSymbol> clientSymbols = new ArrayList<>();
		int idCounter = collectPrimarySymbols(primaryManifest, changeMap, clientSymbols, 1);

		if (paperDiff != null) {
			idCounter = collectRemovedSymbols(paperDiff, clientSymbols, idCounter);
		}

		// Process Folia extensions separately to identify platform-exclusive symbols
		if (foliaJar != null) {
			collectFoliaExtensions(foliaJar, primaryManifest, clientSymbols, idCounter);
		}

		// Sort symbols deterministically by name
		clientSymbols.sort(Comparator.comparing(ClientSymbol::name));

		// Reassign sequential IDs after sorting
		List<ClientSymbol> reindexedSymbols = new ArrayList<>(clientSymbols.size());
		for (int i = 0; i < clientSymbols.size(); i++) {
			ClientSymbol s = clientSymbols.get(i);
			reindexedSymbols.add(new ClientSymbol(
					String.valueOf(i + 1),
					s.kind(),
					s.name(),
					s.descriptor(),
					s.status(),
					s.statusText(),
					s.statusClass(),
					s.scope(),
					s.testLink(),
					s.testMethod(),
					s.testOutcome(),
					s.testScopeNote(),
					s.migrationHint()
			));
		}

		writeExplorerDatasets(reindexedSymbols);
	}

	private static BytecodeManifestScanner.ScanConfiguration createScanConfig(
			String platform,
			String jarName,
			String tier,
			boolean isFallback,
			String fallbackNote
	) {
		return new BytecodeManifestScanner.ScanConfiguration(
				platform,
				extractVersion(jarName),
				0,
				tier,
				"metaminer",
				"unknown",
				false,
				"none",
				"none",
				"ASM9_8",
				API_PREFIXES,
				List.of(),
				List.of(),
				isFallback,
				fallbackNote
		);
	}

	private int collectPrimarySymbols(
			ApiManifest primaryManifest,
			Map<String, PairwiseDiff.ChangeEntry> changeMap,
			List<ClientSymbol> clientSymbols,
			int startId
	) {
		int idCounter = startId;
		for (Map.Entry<String, ApiManifest.ClassEntry> classEntry : primaryManifest.classes().entrySet()) {
			String className = classEntry.getKey();
			ApiManifest.ClassEntry clazz = classEntry.getValue();

			// Only expose public and protected classes in public developer reference
			if (!clazz.accessFlags().contains(ACCESS_PUBLIC) && !clazz.accessFlags().contains(ACCESS_PROTECTED)) {
				continue;
			}

			clientSymbols.add(createClassClientSymbol(String.valueOf(idCounter++), className, clazz, changeMap.get(className)));
			idCounter = collectClassMethods(className, clazz, changeMap, clientSymbols, idCounter);
			idCounter = collectClassFields(className, clazz, changeMap, clientSymbols, idCounter);
		}
		return idCounter;
	}

	private ClientSymbol createClassClientSymbol(
			String id,
			String className,
			ApiManifest.ClassEntry clazz,
			PairwiseDiff.ChangeEntry classChange
	) {
		boolean isAdded = classChange != null && classChange.category() == PairwiseDiff.ChangeCategory.ADDED;
		String classStatus = isAdded ? STATUS_INTRODUCED : STATUS_STABLE;
		String classStatusText = isAdded ? TEXT_INTRODUCED_26_3 : TEXT_STABLE_RANGE;
		String classStatusClass = isAdded ? CSS_ADDED : CSS_STABLE;

		return new ClientSymbol(
				id,
				clazz.kind(),
				className,
				"L" + className.replace('.', '/') + ";",
				classStatus,
				classStatusText,
				classStatusClass,
				determineScope(className),
				null, null, null, null, null
		);
	}

	private int collectClassMethods(
			String className,
			ApiManifest.ClassEntry clazz,
			Map<String, PairwiseDiff.ChangeEntry> changeMap,
			List<ClientSymbol> clientSymbols,
			int startId
	) {
		int idCounter = startId;
		for (Map.Entry<String, ApiManifest.MethodEntry> methodEntry : clazz.methods().entrySet()) {
			ApiManifest.MethodEntry m = methodEntry.getValue();
			if (m.isBridge() || m.isSynthetic() || (!m.accessFlags().contains(ACCESS_PUBLIC) && !m.accessFlags().contains(ACCESS_PROTECTED))) {
				continue;
			}

			String fullSymbol = className + "#" + methodEntry.getKey();
			PairwiseDiff.ChangeEntry methodChange = changeMap.get(fullSymbol);
			clientSymbols.add(createMethodClientSymbol(String.valueOf(idCounter++), className, m, methodChange));
		}
		return idCounter;
	}

	private int collectClassFields(
			String className,
			ApiManifest.ClassEntry clazz,
			Map<String, PairwiseDiff.ChangeEntry> changeMap,
			List<ClientSymbol> clientSymbols,
			int startId
	) {
		int idCounter = startId;
		for (Map.Entry<String, ApiManifest.FieldEntry> fieldEntry : clazz.fields().entrySet()) {
			ApiManifest.FieldEntry f = fieldEntry.getValue();
			if (f.isSynthetic() || (!f.accessFlags().contains(ACCESS_PUBLIC) && !f.accessFlags().contains(ACCESS_PROTECTED))) {
				continue;
			}

			String fullSymbol = className + "#" + f.name() + ":" + f.descriptor();
			PairwiseDiff.ChangeEntry fieldChange = changeMap.get(fullSymbol);
			clientSymbols.add(createFieldClientSymbol(String.valueOf(idCounter++), className, f, fieldChange));
		}
		return idCounter;
	}

	private ClientSymbol createMethodClientSymbol(
			String id,
			String className,
			ApiManifest.MethodEntry m,
			PairwiseDiff.ChangeEntry methodChange
	) {
		String mStatus = STATUS_STABLE;
		String mStatusText = TEXT_STABLE_RANGE;
		String mStatusClass = CSS_STABLE;

		if (methodChange != null) {
			if (methodChange.category() == PairwiseDiff.ChangeCategory.ADDED) {
				mStatus = STATUS_INTRODUCED;
				mStatusText = TEXT_INTRODUCED_26_3;
				mStatusClass = CSS_ADDED;
			} else if (methodChange.category() == PairwiseDiff.ChangeCategory.DEPRECATED) {
				mStatus = STATUS_DEPRECATED;
				mStatusText = "Deprecated in 26.3";
				mStatusClass = CSS_DEPRECATED;
			}
		}

		return new ClientSymbol(
				id,
				"METHOD",
				className + "#" + m.name(),
				m.descriptor(),
				mStatus,
				mStatusText,
				mStatusClass,
				determineScope(className),
				null,
				null,
				null,
				null,
				null
		);
	}

	private ClientSymbol createFieldClientSymbol(
			String id,
			String className,
			ApiManifest.FieldEntry f,
			PairwiseDiff.ChangeEntry fieldChange
	) {
		String fStatus = STATUS_STABLE;
		String fStatusText = TEXT_STABLE_RANGE;
		String fStatusClass = CSS_STABLE;

		if (fieldChange != null) {
			if (fieldChange.category() == PairwiseDiff.ChangeCategory.ADDED) {
				fStatus = STATUS_INTRODUCED;
				fStatusText = TEXT_INTRODUCED_26_3;
				fStatusClass = CSS_ADDED;
			} else if (fieldChange.category() == PairwiseDiff.ChangeCategory.DEPRECATED) {
				fStatus = STATUS_DEPRECATED;
				fStatusText = "Deprecated in 26.3";
				fStatusClass = CSS_DEPRECATED;
			}
		}

		return new ClientSymbol(
				id,
				"FIELD",
				className + "#" + f.name(),
				f.descriptor(),
				fStatus,
				fStatusText,
				fStatusClass,
				determineScope(className),
				null,
				null,
				null,
				null,
				null
		);
	}

	private int collectRemovedSymbols(PairwiseDiff diff, List<ClientSymbol> clientSymbols, int startId) {
		int idCounter = startId;
		for (PairwiseDiff.ChangeEntry change : diff.changes()) {
			if (change.category() == PairwiseDiff.ChangeCategory.REMOVED) {
				String symbol = change.symbol();
				String migration = change.migrationHint() != null ? change.migrationHint().replacementSymbol() : null;
				clientSymbols.add(new ClientSymbol(
						String.valueOf(idCounter++),
						change.type().name(),
						symbol,
						change.details() != null ? change.details().descriptor() : "()V",
						STATUS_REMOVED,
						"Removed upstream in 26.3 (Binary Incompatible)",
						"status-removed",
						determineScope(symbol),
						null, null, null, null,
						migration
				));
			}
		}
		return idCounter;
	}

	private void collectFoliaExtensions(
			File foliaJar,
			ApiManifest primaryManifest,
			List<ClientSymbol> clientSymbols,
			int startId
	) throws IOException {
		BytecodeManifestScanner.ScanConfiguration foliaConfig = createScanConfig("folia", foliaJar.getName(), "extension", true, "Folia threaded regions extension");
		ApiManifest foliaManifest = BytecodeManifestScanner.scanJar(foliaJar, foliaConfig);

		int idCounter = startId;
		for (Map.Entry<String, ApiManifest.ClassEntry> entry : foliaManifest.classes().entrySet()) {
			String className = entry.getKey();
			// Only index Folia-exclusive extension symbols (e.g. io.papermc.paper.threadedregions.*)
			if (className.startsWith("io.papermc.paper.threadedregions") && !primaryManifest.classes().containsKey(className)) {
				ApiManifest.ClassEntry clazz = entry.getValue();
				clientSymbols.add(new ClientSymbol(
						String.valueOf(idCounter++),
						clazz.kind(),
						className,
						"L" + className.replace('.', '/') + ";",
						STATUS_EXTENSION,
						TEXT_FOLIA_EXTENSION,
						CSS_EXTENSION,
						SCOPE_FOLIA_EXTENSION,
						null, null, null, null, null
				));

				for (Map.Entry<String, ApiManifest.MethodEntry> methodEntry : clazz.methods().entrySet()) {
					ApiManifest.MethodEntry m = methodEntry.getValue();
					if (m.isBridge() || m.isSynthetic() || (!m.accessFlags().contains(ACCESS_PUBLIC) && !m.accessFlags().contains(ACCESS_PROTECTED))) {
						continue;
					}
					clientSymbols.add(new ClientSymbol(
							String.valueOf(idCounter++),
							"METHOD",
							className + "#" + m.name(),
							m.descriptor(),
							STATUS_EXTENSION,
							TEXT_FOLIA_EXTENSION,
							CSS_EXTENSION,
							SCOPE_FOLIA_EXTENSION,
							null, null, null, null, null
					));
				}
			}
		}
	}

	private void writeExplorerDatasets(List<ClientSymbol> clientSymbols) throws IOException {
		File publicDataDir = new File(rootDir, "site/docs/public/data");
		File pagesDir = new File(publicDataDir, "pages");
		pagesDir.mkdirs();

		// Clean up existing page-*.json chunks to avoid stale phantom pages
		File[] existingPages = pagesDir.listFiles((_, name) -> name.startsWith("page-") && name.endsWith(".json"));
		if (existingPages != null) {
			for (File p : existingPages) {
				java.nio.file.Files.deleteIfExists(p.toPath());
			}
		}

		int totalSymbols = clientSymbols.size();
		int totalPages = (int) Math.ceil((double) totalSymbols / PAGE_SIZE);

		int countAdded = 0;
		int countRemoved = 0;
		int countDeprecated = 0;
		int countExtension = 0;
		int countStable = 0;

		for (ClientSymbol s : clientSymbols) {
			switch (s.status()) {
				case STATUS_INTRODUCED -> countAdded++;
				case STATUS_REMOVED -> countRemoved++;
				case STATUS_DEPRECATED -> countDeprecated++;
				case STATUS_EXTENSION -> countExtension++;
				case STATUS_STABLE -> countStable++;
				default -> { /* ignore unmapped */ }
			}
		}

		// 1. Write metadata index
		ExplorerMetadata meta = new ExplorerMetadata(
				totalSymbols,
				PAGE_SIZE,
				totalPages,
				countAdded,
				countRemoved,
				countDeprecated,
				countExtension,
				countStable
		);

		File metaFile = new File(publicDataDir, "explorer-meta.json");
		try (FileWriter writer = new FileWriter(metaFile, StandardCharsets.UTF_8)) {
			GSON.toJson(meta, writer);
		}

		// 2. Write paginated chunks: page-1.json, page-2.json, ...
		for (int page = 1; page <= totalPages; page++) {
			int fromIndex = (page - 1) * PAGE_SIZE;
			int toIndex = Math.min(fromIndex + PAGE_SIZE, totalSymbols);
			List<ClientSymbol> pageItems = clientSymbols.subList(fromIndex, toIndex);

			File pageFile = new File(pagesDir, "page-" + page + ".json");
			try (FileWriter writer = new FileWriter(pageFile, StandardCharsets.UTF_8)) {
				GSON.toJson(pageItems, writer);
			}
		}

		// 3. Write lightweight search index via stream
		List<SearchIndexItem> searchIndex = clientSymbols.stream()
				.map(s -> new SearchIndexItem(
						s.id(),
						s.name(),
						s.descriptor(),
						s.kind(),
						s.status(),
						s.scope()
				))
				.toList();

		File searchFile = new File(publicDataDir, "search-index.json");
		try (FileWriter writer = new FileWriter(searchFile, StandardCharsets.UTF_8)) {
			GSON.toJson(searchIndex, writer);
		}

		// Keep fallback full symbols file
		File fullFile = new File(publicDataDir, "explorer-symbols.json");
		try (FileWriter writer = new FileWriter(fullFile, StandardCharsets.UTF_8)) {
			GSON.toJson(clientSymbols, writer);
		}

		LOGGER.log(Level.INFO, "Exported {0} symbols across {1} chunked pages + lightweight search index to {2}",
				new Object[]{totalSymbols, totalPages, publicDataDir.getAbsolutePath()});
	}

	private static String determineScope(String name) {
		if (name.startsWith("io.papermc.paper.threadedregions")) {
			return SCOPE_FOLIA_EXTENSION;
		}
		if (name.startsWith("io.papermc.paper.datacomponent")) {
			return "Canonical Paper Data Component";
		}
		if (name.startsWith("io.papermc.paper")) {
			return "Paper API (io.papermc.paper)";
		}
		if (name.startsWith("com.destroystokyo.paper")) {
			return "Paper API (com.destroystokyo.paper)";
		}
		return "Canonical Bukkit / Paper (org.bukkit)";
	}

	private static String extractVersion(String jarName) {
		String name = jarName.replace(".jar", "");
		for (String prefix : List.of(PREFIX_REMAPPED_PAPER, "remapped-folia-", PREFIX_PAPER_API, "folia-api-")) {
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

		// Filter by target version if present in filename
		List<File> matchingVersion = found.stream()
				.filter(f -> f.getName().contains("-" + targetVersion + "-") || f.getName().contains("-" + targetVersion + "."))
				.toList();

		List<File> candidates = matchingVersion.isEmpty() ? found : matchingVersion;
		if (candidates.isEmpty()) {
			return null;
		}

		// Sort by integer build number extracted from filename
		return candidates.stream().max(Comparator.comparingInt(DocSiteDataGenerator::extractBuildNumber)).orElse(null);
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
		if (files == null) return;
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

	public record ExplorerMetadata(
			int totalSymbols,
			int pageSize,
			int totalPages,
			int countAdded,
			int countRemoved,
			int countDeprecated,
			int countExtension,
			int countStable
	) {}

	public record SearchIndexItem(
			String id,
			String name,
			String descriptor,
			String kind,
			String status,
			String scope
	) {}

	public record ClientSymbol(
			String id,
			String kind,
			String name,
			String descriptor,
			String status,
			String statusText,
			String statusClass,
			String scope,
			String testLink,
			String testMethod,
			String testOutcome,
			String testScopeNote,
			String migrationHint
	) {}
}
