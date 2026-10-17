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

	private static final String PLATFORM_PAPER = "paper";
	private static final String PLATFORM_FOLIA = "folia";
	private static final String KEY_PLATFORMS = "platforms";
	private static final String PREFIX_REMAPPED_FOLIA = "remapped-folia-";
	private static final String PREFIX_FOLIA_API = "folia-api-";
	private static final String PREFIX_INTRODUCED_IN = "Introduced in ";
	private static final String PREFIX_STABLE_THROUGHOUT = "Stable throughout ";

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

	private List<String> resolvePlatformVersions(String platform) {
		File manifestFile = new File(rootDir, "jars/manifests/versions.json");
		List<String> versions = new ArrayList<>();
		if (manifestFile.exists()) {
			try (java.io.FileReader fr = new java.io.FileReader(manifestFile, StandardCharsets.UTF_8)) {
				com.google.gson.JsonObject root = com.google.gson.JsonParser.parseReader(fr).getAsJsonObject();
				if (root.has(KEY_PLATFORMS) && root.getAsJsonObject(KEY_PLATFORMS).has(platform)) {
					com.google.gson.JsonArray verArr = root.getAsJsonObject(KEY_PLATFORMS).getAsJsonObject(platform).getAsJsonArray("versions");
					for (com.google.gson.JsonElement el : verArr) {
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

	private Map<String, ApiManifest> scanPlatformManifests(
			File cacheDir,
			String platform,
			List<String> versions,
			String primaryVersion,
			String[] prefixes
	) throws IOException {
		Map<String, ApiManifest> manifestCache = new HashMap<>();
		for (String v : versions) {
			File jar = findBestJar(cacheDir, v, prefixes);
			if (jar != null) {
				String tier;
				if (platform.equals(PLATFORM_FOLIA)) {
					tier = "extension";
				} else if (v.equals(primaryVersion)) {
					tier = "canonical";
				} else {
					tier = "baseline";
				}
				BytecodeManifestScanner.ScanConfiguration cfg = createScanConfig(platform, jar.getName(), tier, false, null);
				ApiManifest manifest = BytecodeManifestScanner.scanJar(jar, cfg);
				manifestCache.put(v, manifest);
			}
		}
		return manifestCache;
	}

	@Override
	public void generateData() throws IOException {
		File cacheDir = new File(rootDir, "jars/cache");
		List<String> paperVersions = resolvePlatformVersions(PLATFORM_PAPER);
		List<String> foliaVersions = resolvePlatformVersions(PLATFORM_FOLIA);
		TargetVersions defaultVersions = resolveTargetVersions();

		// 1. Scan and cache Paper and Folia manifests
		Map<String, ApiManifest> paperManifestCache = scanPlatformManifests(
				cacheDir, PLATFORM_PAPER, paperVersions, defaultVersions.primary(),
				new String[]{PREFIX_REMAPPED_PAPER, PREFIX_PAPER_API});

		Map<String, ApiManifest> foliaManifestCache = scanPlatformManifests(
				cacheDir, PLATFORM_FOLIA, foliaVersions, defaultVersions.primary(),
				new String[]{PREFIX_REMAPPED_FOLIA, PREFIX_FOLIA_API});

		String primaryVersion = defaultVersions.primary();
		ApiManifest primaryPaperManifest = paperManifestCache.get(primaryVersion);
		if (primaryPaperManifest == null) {
			LOGGER.log(Level.WARNING, "Skipping DocSiteDataGenerator: Required primary Paper JAR for version {0} not found in {1}",
					new Object[]{primaryVersion, cacheDir.getAbsolutePath()});
			return;
		}

		// 2. Precompute lifecycle maps for Paper and Folia
		PlatformLifecycle paperLifecycle = computeLifecycle(paperVersions, paperManifestCache);
		PlatformLifecycle foliaLifecycle = computeLifecycle(foliaVersions, foliaManifestCache);

		File defaultFoliaJar = findBestJar(cacheDir, defaultVersions.baseline(), PREFIX_REMAPPED_FOLIA, PREFIX_FOLIA_API);

		// 3. Generate Paper & Folia pairwise diffs
		generatePairwiseDiffs(PLATFORM_PAPER, paperVersions, paperManifestCache, defaultFoliaJar, paperLifecycle, defaultVersions);
		generatePairwiseDiffs(PLATFORM_FOLIA, foliaVersions, foliaManifestCache, null, foliaLifecycle, defaultVersions);
	}

	private void generatePairwiseDiffs(
			String platform,
			List<String> versions,
			Map<String, ApiManifest> manifests,
			File foliaJar,
			PlatformLifecycle lifecycle,
			TargetVersions defaultVersions
	) throws IOException {
		for (int t = 0; t < versions.size(); t++) {
			String targetVer = versions.get(t);
			ApiManifest targetManifest = manifests.get(targetVer);
			if (targetManifest == null) continue;

			for (int b = t + 1; b < versions.size(); b++) {
				String baselineVer = versions.get(b);
				ApiManifest baselineManifest = manifests.get(baselineVer);
				if (baselineManifest == null) continue;

				List<ClientSymbol> symbols = buildSymbolsForPair(targetManifest, baselineManifest, targetVer, baselineVer, foliaJar, lifecycle);

				File diffDir = new File(rootDir, String.join(File.separator, "site", "docs", "public", "data", "diffs", platform, baselineVer + "-" + targetVer));
				writeExplorerDatasetsTo(symbols, diffDir);

				if (platform.equals(PLATFORM_PAPER) && baselineVer.equals(defaultVersions.baseline()) && targetVer.equals(defaultVersions.primary())) {
					File publicDataDir = new File(rootDir, String.join(File.separator, "site", "docs", "public", "data"));
					writeExplorerDatasetsTo(symbols, publicDataDir);
				}
			}
		}
	}

	private record PlatformLifecycle(
			Map<String, String> addedIn,
			Map<String, String> removedIn,
			Map<String, String> deprecatedIn
	) {}

	private PlatformLifecycle computeLifecycle(List<String> versionsDescending, Map<String, ApiManifest> manifests) {
		Map<String, String> addedIn = new HashMap<>();
		Map<String, String> removedIn = new HashMap<>();
		Map<String, String> deprecatedIn = new HashMap<>();

		// Process chronological steps from earliest to latest
		List<String> chronological = versionsDescending.reversed();

		for (int i = 0; i < chronological.size() - 1; i++) {
			String fromVer = chronological.get(i);
			String toVer = chronological.get(i + 1);

			ApiManifest fromManifest = manifests.get(fromVer);
			ApiManifest toManifest = manifests.get(toVer);
			if (fromManifest == null || toManifest == null) continue;

			PairwiseDiff stepDiff = VersionDiffEngine.computeDiff(fromManifest, toManifest);
			for (PairwiseDiff.ChangeEntry change : stepDiff.changes()) {
				String sym = change.symbol();
				if (change.category() == PairwiseDiff.ChangeCategory.ADDED) {
					addedIn.putIfAbsent(sym, toVer);
				} else if (change.category() == PairwiseDiff.ChangeCategory.REMOVED) {
					removedIn.putIfAbsent(sym, toVer);
				} else if (change.category() == PairwiseDiff.ChangeCategory.DEPRECATED) {
					deprecatedIn.putIfAbsent(sym, toVer);
				}
			}
		}

		return new PlatformLifecycle(addedIn, removedIn, deprecatedIn);
	}

	private List<ClientSymbol> buildSymbolsForPair(
			ApiManifest targetManifest,
			ApiManifest baselineManifest,
			String targetVer,
			String baselineVer,
			File foliaJar,
			PlatformLifecycle lifecycle
	) throws IOException {
		PairwiseDiff diff = VersionDiffEngine.computeDiff(baselineManifest, targetManifest);
		Map<String, PairwiseDiff.ChangeEntry> changeMap = new HashMap<>();
		for (PairwiseDiff.ChangeEntry entry : diff.changes()) {
			changeMap.put(entry.symbol(), entry);
		}

		List<ClientSymbol> clientSymbols = new ArrayList<>();
		DiffContext ctx = new DiffContext(targetVer, baselineVer, lifecycle);
		int idCounter = collectPrimarySymbols(targetManifest, changeMap, clientSymbols, 1, ctx);

		idCounter = collectRemovedSymbols(diff, clientSymbols, idCounter, targetVer, lifecycle);

		if (foliaJar != null) {
			collectFoliaExtensions(foliaJar, targetManifest, clientSymbols, idCounter);
		}

		clientSymbols.sort(Comparator.comparing(ClientSymbol::name));

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
		return reindexedSymbols;
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

	private record DiffContext(
			String targetVer,
			String baselineVer,
			PlatformLifecycle lifecycle
	) {}

	private int collectPrimarySymbols(
			ApiManifest primaryManifest,
			Map<String, PairwiseDiff.ChangeEntry> changeMap,
			List<ClientSymbol> clientSymbols,
			int startId,
			DiffContext ctx
	) {
		int idCounter = startId;
		for (Map.Entry<String, ApiManifest.ClassEntry> classEntry : primaryManifest.classes().entrySet()) {
			String className = classEntry.getKey();
			ApiManifest.ClassEntry clazz = classEntry.getValue();

			// Only expose public and protected classes in public developer reference
			if (!clazz.accessFlags().contains(ACCESS_PUBLIC) && !clazz.accessFlags().contains(ACCESS_PROTECTED)) {
				continue;
			}

			clientSymbols.add(createClassClientSymbol(String.valueOf(idCounter++), className, clazz, changeMap.get(className), ctx));
			idCounter = collectClassMethods(className, clazz, changeMap, clientSymbols, idCounter, ctx);
			idCounter = collectClassFields(className, clazz, changeMap, clientSymbols, idCounter, ctx);
		}
		return idCounter;
	}

	private ClientSymbol createClassClientSymbol(
			String id,
			String className,
			ApiManifest.ClassEntry clazz,
			PairwiseDiff.ChangeEntry classChange,
			DiffContext ctx
	) {
		boolean isAdded = classChange != null && classChange.category() == PairwiseDiff.ChangeCategory.ADDED;
		String birthVer = ctx.lifecycle().addedIn().getOrDefault(className, ctx.targetVer());
		String classStatus = isAdded ? STATUS_INTRODUCED : STATUS_STABLE;
		String classStatusText = isAdded ? PREFIX_INTRODUCED_IN + birthVer : PREFIX_STABLE_THROUGHOUT + ctx.baselineVer() + " - " + ctx.targetVer();
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
			int startId,
			DiffContext ctx
	) {
		int idCounter = startId;
		for (Map.Entry<String, ApiManifest.MethodEntry> methodEntry : clazz.methods().entrySet()) {
			ApiManifest.MethodEntry m = methodEntry.getValue();
			if (m.isBridge() || m.isSynthetic() || (!m.accessFlags().contains(ACCESS_PUBLIC) && !m.accessFlags().contains(ACCESS_PROTECTED))) {
				continue;
			}

			String fullSymbol = className + "#" + methodEntry.getKey();
			PairwiseDiff.ChangeEntry methodChange = changeMap.get(fullSymbol);
			clientSymbols.add(createMethodClientSymbol(String.valueOf(idCounter++), className, m, methodChange, ctx));
		}
		return idCounter;
	}

	private int collectClassFields(
			String className,
			ApiManifest.ClassEntry clazz,
			Map<String, PairwiseDiff.ChangeEntry> changeMap,
			List<ClientSymbol> clientSymbols,
			int startId,
			DiffContext ctx
	) {
		int idCounter = startId;
		for (Map.Entry<String, ApiManifest.FieldEntry> fieldEntry : clazz.fields().entrySet()) {
			ApiManifest.FieldEntry f = fieldEntry.getValue();
			if (f.isSynthetic() || (!f.accessFlags().contains(ACCESS_PUBLIC) && !f.accessFlags().contains(ACCESS_PROTECTED))) {
				continue;
			}

			String fullSymbol = className + "#" + f.name() + ":" + f.descriptor();
			PairwiseDiff.ChangeEntry fieldChange = changeMap.get(fullSymbol);
			clientSymbols.add(createFieldClientSymbol(String.valueOf(idCounter++), className, f, fieldChange, ctx));
		}
		return idCounter;
	}

	private ClientSymbol createMethodClientSymbol(
			String id,
			String className,
			ApiManifest.MethodEntry m,
			PairwiseDiff.ChangeEntry methodChange,
			DiffContext ctx
	) {
		String fullSymbol = className + "#" + m.name();
		String mStatus = STATUS_STABLE;
		String mStatusText = PREFIX_STABLE_THROUGHOUT + ctx.baselineVer() + " - " + ctx.targetVer();
		String mStatusClass = CSS_STABLE;

		if (methodChange != null) {
			if (methodChange.category() == PairwiseDiff.ChangeCategory.ADDED) {
				String birthVer = ctx.lifecycle().addedIn().getOrDefault(fullSymbol, ctx.targetVer());
				mStatus = STATUS_INTRODUCED;
				mStatusText = PREFIX_INTRODUCED_IN + birthVer;
				mStatusClass = CSS_ADDED;
			} else if (methodChange.category() == PairwiseDiff.ChangeCategory.DEPRECATED) {
				String depVer = ctx.lifecycle().deprecatedIn().getOrDefault(fullSymbol, ctx.targetVer());
				mStatus = STATUS_DEPRECATED;
				mStatusText = "Deprecated in " + depVer;
				mStatusClass = CSS_DEPRECATED;
			}
		}

		return new ClientSymbol(
				id,
				"METHOD",
				fullSymbol,
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
			PairwiseDiff.ChangeEntry fieldChange,
			DiffContext ctx
	) {
		String fullSymbol = className + "#" + f.name();
		String fStatus = STATUS_STABLE;
		String fStatusText = PREFIX_STABLE_THROUGHOUT + ctx.baselineVer() + " - " + ctx.targetVer();
		String fStatusClass = CSS_STABLE;

		if (fieldChange != null) {
			String fullFieldSig = fullSymbol + ":" + f.descriptor();
			if (fieldChange.category() == PairwiseDiff.ChangeCategory.ADDED) {
				String birthVer = ctx.lifecycle().addedIn().getOrDefault(fullFieldSig, ctx.lifecycle().addedIn().getOrDefault(fullSymbol, ctx.targetVer()));
				fStatus = STATUS_INTRODUCED;
				fStatusText = PREFIX_INTRODUCED_IN + birthVer;
				fStatusClass = CSS_ADDED;
			} else if (fieldChange.category() == PairwiseDiff.ChangeCategory.DEPRECATED) {
				String depVer = ctx.lifecycle().deprecatedIn().getOrDefault(fullFieldSig, ctx.lifecycle().deprecatedIn().getOrDefault(fullSymbol, ctx.targetVer()));
				fStatus = STATUS_DEPRECATED;
				fStatusText = "Deprecated in " + depVer;
				fStatusClass = CSS_DEPRECATED;
			}
		}

		return new ClientSymbol(
				id,
				"FIELD",
				fullSymbol,
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

	private int collectRemovedSymbols(PairwiseDiff diff, List<ClientSymbol> clientSymbols, int startId, String targetVer, PlatformLifecycle lifecycle) {
		int idCounter = startId;
		for (PairwiseDiff.ChangeEntry change : diff.changes()) {
			if (change.category() == PairwiseDiff.ChangeCategory.REMOVED) {
				String symbol = change.symbol();
				String deathVer = lifecycle.removedIn().getOrDefault(symbol, targetVer);
				String migration = change.migrationHint() != null ? change.migrationHint().replacementSymbol() : null;
				clientSymbols.add(new ClientSymbol(
						String.valueOf(idCounter++),
						change.type().name(),
						symbol,
						change.details() != null ? change.details().descriptor() : "()V",
						STATUS_REMOVED,
						"Removed upstream in " + deathVer + " (Binary Incompatible)",
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
		BytecodeManifestScanner.ScanConfiguration foliaConfig = createScanConfig(PLATFORM_FOLIA, foliaJar.getName(), "extension", true, "Folia threaded regions extension");
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

	private void writeExplorerDatasetsTo(List<ClientSymbol> clientSymbols, File targetDir) throws IOException {
		File pagesDir = new File(targetDir, "pages");
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

		File metaFile = new File(targetDir, "explorer-meta.json");
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
						s.statusText(),
						s.scope()
				))
				.toList();

		File searchFile = new File(targetDir, "search-index.json");
		try (FileWriter writer = new FileWriter(searchFile, StandardCharsets.UTF_8)) {
			GSON.toJson(searchIndex, writer);
		}

		// Keep fallback full symbols file
		File fullFile = new File(targetDir, "explorer-symbols.json");
		try (FileWriter writer = new FileWriter(fullFile, StandardCharsets.UTF_8)) {
			GSON.toJson(clientSymbols, writer);
		}

		LOGGER.log(Level.INFO, "Exported {0} symbols across {1} chunked pages + lightweight search index to {2}",
				new Object[]{totalSymbols, totalPages, targetDir.getAbsolutePath()});
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
		for (String prefix : List.of(PREFIX_REMAPPED_PAPER, PREFIX_REMAPPED_FOLIA, PREFIX_PAPER_API, PREFIX_FOLIA_API)) {
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
			String statusText,
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
