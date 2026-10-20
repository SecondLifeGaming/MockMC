<template>
	<div class="range-browser">
		<div class="controls-panel">
			<div class="control-group">
				<label for="platform-select">Platform</label>
				<select id="platform-select" v-model="selectedPlatform" @change="onPlatformChange">
					<option value="paper">Paper (Canonical)</option>
					<option value="folia">Folia (Threaded Regions)</option>
					<option value="velocity">Velocity</option>
					<option value="waterfall">Waterfall</option>
				</select>
			</div>

			<div class="control-group">
				<label for="min-version-select">Baseline Version</label>
				<select id="min-version-select" v-model="minVersion" @change="onRangeChange">
					<option v-for="v in platformVersions" :key="'min-' + v" :value="v">
						{{ v }} {{ v === defaultMin ? "(Baseline)" : "" }}
					</option>
				</select>
			</div>

			<div class="control-group">
				<label for="max-version-select">Target Version</label>
				<select id="max-version-select" v-model="maxVersion" @change="onRangeChange">
					<option v-for="v in platformVersions" :key="'max-' + v" :value="v">
						{{ v }} {{ v === defaultMax ? "(Target)" : "" }}
					</option>
				</select>
			</div>

			<div class="control-group">
				<label for="category-select">Category</label>
				<select id="category-select" v-model="selectedCategory" @change="onFilterChange">
					<option value="ALL">All Categories</option>
					<option value="CLASS">Classes</option>
					<option value="INTERFACE">Interfaces</option>
					<option value="ENUM">Enums</option>
					<option value="RECORD">Records</option>
					<option value="ANNOTATION">Annotations</option>
					<option value="METHOD">Methods</option>
					<option value="FIELD">Fields</option>
				</select>
			</div>

			<div class="control-group">
				<label for="status-select">Filter Status</label>
				<select id="status-select" v-model="selectedStatus" @change="onFilterChange">
					<option value="CHANGED">Changed in Range</option>
					<option value="ALL">All Symbols (Full API Surface)</option>
					<option value="INTRODUCED">Introduced in Range (New)</option>
					<option value="REMOVED">Binary Breaking / Removed</option>
					<option value="DEPRECATED">Deprecated in Range</option>
					<option value="EXTENSION">Platform Extension (Folia / Scoped)</option>
					<option value="STABLE">Stable / Unchanged Only</option>
				</select>
			</div>

			<div class="control-group search-box">
				<label for="search-input">Search Symbol</label>
				<input
					id="search-input"
					type="text"
					v-model="searchQuery"
					@input="onSearchInput"
					placeholder="Instant search across 15,000+ API symbols (e.g. Player, sendTitle, ItemStack)..."
				/>
			</div>
		</div>

		<div v-if="selectedPlatform === 'velocity' || selectedPlatform === 'waterfall'" class="unsupported-banner">
			<strong>Platform Notice:</strong> Displaying API surface symbols for <strong>{{ selectedPlatform.toUpperCase() }}</strong>. Full cross-version binary diff tracking across older proxy releases is currently undergoing automated bytecode indexing.
		</div>

		<div class="summary-bar">
			<span class="badge badge-info">
				Showing {{ displayedTotal.toLocaleString() }}
				{{ isSearchingOrFiltering ? "Matching" : "of " + totalSymbolsCount.toLocaleString() }} Symbols
			</span>
			<span class="badge badge-added">{{ displayedCountAdded.toLocaleString() }} Added</span>
			<span class="badge badge-removed">{{ displayedCountRemoved.toLocaleString() }} Breaking</span>
			<span class="badge badge-deprecated">{{ displayedCountDeprecated.toLocaleString() }} Deprecated</span>
			<span class="badge badge-extension">{{ displayedCountExtension.toLocaleString() }} Extension</span>
			<span class="badge badge-stable">{{ displayedCountStable.toLocaleString() }} Stable</span>
		</div>

		<!-- Active Loading Indicator for Chunk Fetching -->
		<div v-if="loadingPage" class="chunk-loading">
			<div class="spinner-small"></div>
			<span>Loading page {{ currentPage }}...</span>
		</div>

		<!-- Error Banner if Data Fails to Load -->
		<div v-if="errorMessage" class="error-banner">
			<strong>Error loading explorer data:</strong> {{ errorMessage }}
			<button class="retry-btn" @click="retryLoad">Retry</button>
		</div>

		<div class="symbol-list">
			<div v-for="symbol in currentSymbols" :key="symbol.id" class="symbol-card">
				<div class="symbol-header">
					<span class="symbol-kind">{{ symbol.kind }}</span>
					<span class="symbol-name">{{ symbol.name }}</span>
					<span :class="['status-badge', symbol.statusClass || getStatusClass(symbol.status)]">
						{{ symbol.statusText || getStatusText(symbol.status) }}
					</span>
				</div>

				<div class="symbol-meta">
					<code>{{ symbol.descriptor }}</code>
				</div>

				<div class="symbol-details">
					<div class="scope-info"><strong>Platform Scope:</strong> {{ symbol.scope }}</div>
					<div v-if="symbol.testLink" class="test-info">
						<strong>Verified Test:</strong>
						<a :href="symbol.testLink" target="_blank" rel="noopener">
							{{ symbol.testMethod }} ({{ symbol.testOutcome }})
						</a>
						<span class="scope-note">{{ symbol.testScopeNote }}</span>
					</div>
					<div v-if="symbol.migrationHint" class="migration-hint">
						<strong>Migration:</strong> Superseded by <code>{{ symbol.migrationHint }}</code>
					</div>
				</div>
			</div>

			<!-- Empty State -->
			<div v-if="!loadingPage && currentSymbols.length === 0" class="empty-state">
				<template v-if="minVersion === maxVersion && selectedStatus === 'CHANGED'">
					<p>Baseline and Target versions are both set to <strong>{{ minVersion }}</strong>. By definition, there are 0 API changes when comparing identical releases.</p>
					<button class="reset-btn" @click="switchToFullApiSurface">View Full {{ selectedPlatform.toUpperCase() }} {{ minVersion }} API Surface</button>
				</template>
				<template v-else>
					<p>No API symbols match your current filters or search query.</p>
					<button class="reset-btn" @click="resetFilters">Reset Filters</button>
				</template>
			</div>
		</div>

		<div v-if="displayedPages > 1" class="pagination">
			<button :disabled="currentPage === 1" @click="changePage(currentPage - 1)">Previous</button>
			<span>Page {{ currentPage }} of {{ displayedPages.toLocaleString() }}</span>
			<button :disabled="currentPage === displayedPages" @click="changePage(currentPage + 1)">Next</button>
		</div>
	</div>
</template>

<script setup>
import { ref, shallowRef, computed, onMounted } from "vue";

const defaultMin = "1.20.6";
const defaultMax = "26.3";

const selectedPlatform = ref("paper");
const minVersion = ref(defaultMin);
const maxVersion = ref(defaultMax);
const selectedCategory = ref("ALL");
const selectedStatus = ref("CHANGED");
const searchQuery = ref("");
const currentPage = ref(1);
const loadingPage = ref(false);
const errorMessage = ref(null);

const platformVersionsMap = {
	paper: ["26.3", "26.2", "26.1.2", "1.21.11", "1.20.6"],
	folia: ["26.3", "26.2", "26.1.2", "1.21.11", "1.20.6"],
	velocity: ["4.3.0", "4.2.1"],
	waterfall: ["1.21"],
};

const platformVersions = computed(() => {
	return platformVersionsMap[selectedPlatform.value] || ["26.3", "26.2"];
});

const activeDataPath = computed(() => {
	const baseUrl = import.meta.env.BASE_URL || "/";
	const vers = platformVersions.value;
	// platformVersions is descending: ['26.3', '26.2', '26.1.2', '1.21.11', '1.20.6']
	// In the precomputed data folders, directory names are always older-newer: e.g. 26.1.2-26.2, 1.20.6-26.3
	const v1 = minVersion.value;
	const v2 = maxVersion.value;
	const idx1 = vers.indexOf(v1);
	const idx2 = vers.indexOf(v2);

	let vLower = v1;
	let vUpper = v2;
	if (idx1 !== -1 && idx2 !== -1) {
		// Higher index in platformVersions means older version
		if (idx1 < idx2) {
			vLower = v2;
			vUpper = v1;
		} else {
			vLower = v1;
			vUpper = v2;
		}
	}
	return `${baseUrl}data/diffs/${selectedPlatform.value}/${vLower}-${vUpper}/`;
});

function onPlatformChange() {
	const vers = platformVersions.value;
	if (vers.length > 0) {
		maxVersion.value = vers[0];
		minVersion.value = vers[vers.length - 1];
	}
	syncRangeMetaInstant();
	loadData();
}

function onRangeChange() {
	syncRangeMetaInstant();
	loadData();
}

// In-memory response cache to ensure instantaneous 0ms round-trips when switching filters/versions
const fetchCache = new Map();

async function cachedFetchJson(url, signal) {
	if (fetchCache.has(url)) {
		return fetchCache.get(url);
	}
	const promise = (async () => {
		const res = await fetch(url, { signal });
		if (!res.ok) {
			throw new Error(`HTTP error ${res.status} fetching ${url}`);
		}
		return await res.json();
	})();
	fetchCache.set(url, promise);
	try {
		return await promise;
	} catch (e) {
		fetchCache.delete(url);
		throw e;
	}
}

// Precomputed diff metadata table for instant 0ms badge stats without waiting for network fetches
const DIFF_METADATA_TABLE = {
	paper: {
		"1.20.6-1.20.6": { totalSymbols: 14304, totalPages: 287, countAdded: 0, countRemoved: 0, countDeprecated: 0, countExtension: 0, countStable: 14304 },
		"1.21.11-1.21.11": { totalSymbols: 14198, totalPages: 284, countAdded: 0, countRemoved: 0, countDeprecated: 0, countExtension: 0, countStable: 14198 },
		"26.1.2-26.1.2": { totalSymbols: 14256, totalPages: 286, countAdded: 0, countRemoved: 0, countDeprecated: 0, countExtension: 0, countStable: 14256 },
		"26.2-26.2": { totalSymbols: 14612, totalPages: 293, countAdded: 0, countRemoved: 0, countDeprecated: 0, countExtension: 0, countStable: 14612 },
		"26.3-26.3": { totalSymbols: 14716, totalPages: 295, countAdded: 0, countRemoved: 0, countDeprecated: 0, countExtension: 0, countStable: 14716 },
		"1.20.6-1.21.11": { totalSymbols: 15767, totalPages: 316, countAdded: 1463, countRemoved: 1569, countDeprecated: 3, countExtension: 350, countStable: 12382 },
		"1.20.6-26.1.2": { totalSymbols: 15870, totalPages: 318, countAdded: 1519, countRemoved: 1614, countDeprecated: 3, countExtension: 350, countStable: 12384 },
		"1.20.6-26.2": { totalSymbols: 16311, totalPages: 327, countAdded: 1657, countRemoved: 1699, countDeprecated: 2, countExtension: 350, countStable: 12603 },
		"1.20.6-26.3": { totalSymbols: 16523, totalPages: 331, countAdded: 1774, countRemoved: 1807, countDeprecated: 2, countExtension: 350, countStable: 12590 },
		"1.21.11-26.1.2": { totalSymbols: 14370, totalPages: 288, countAdded: 139, countRemoved: 114, countDeprecated: 1, countExtension: 350, countStable: 13766 },
		"1.21.11-26.2": { totalSymbols: 14847, totalPages: 297, countAdded: 272, countRemoved: 235, countDeprecated: 1, countExtension: 350, countStable: 13989 },
		"1.21.11-26.3": { totalSymbols: 15091, totalPages: 302, countAdded: 433, countRemoved: 375, countDeprecated: 1, countExtension: 350, countStable: 13932 },
		"26.1.2-26.2": { totalSymbols: 14737, totalPages: 295, countAdded: 135, countRemoved: 125, countDeprecated: 0, countExtension: 350, countStable: 14127 },
		"26.1.2-26.3": { totalSymbols: 14997, totalPages: 300, countAdded: 308, countRemoved: 281, countDeprecated: 0, countExtension: 350, countStable: 14058 },
		"26.2-26.3": { totalSymbols: 14880, totalPages: 298, countAdded: 178, countRemoved: 164, countDeprecated: 0, countExtension: 350, countStable: 14188 },
	},
	folia: {
		"1.20.6-1.20.6": { totalSymbols: 14455, totalPages: 290, countAdded: 0, countRemoved: 0, countDeprecated: 0, countExtension: 0, countStable: 14455 },
		"1.21.11-1.21.11": { totalSymbols: 14209, totalPages: 285, countAdded: 0, countRemoved: 0, countDeprecated: 0, countExtension: 0, countStable: 14209 },
		"26.1.2-26.1.2": { totalSymbols: 14269, totalPages: 286, countAdded: 0, countRemoved: 0, countDeprecated: 0, countExtension: 0, countStable: 14269 },
		"26.2-26.2": { totalSymbols: 14655, totalPages: 294, countAdded: 0, countRemoved: 0, countDeprecated: 0, countExtension: 0, countStable: 14655 },
		"26.3-26.3": { totalSymbols: 14746, totalPages: 295, countAdded: 0, countRemoved: 0, countDeprecated: 0, countExtension: 0, countStable: 14746 },
		"1.20.6-1.21.11": { totalSymbols: 15992, totalPages: 320, countAdded: 1537, countRemoved: 1783, countDeprecated: 5, countExtension: 0, countStable: 12667 },
		"1.20.6-26.1.2": { totalSymbols: 16104, totalPages: 323, countAdded: 1598, countRemoved: 1835, countDeprecated: 5, countExtension: 0, countStable: 12666 },
		"1.20.6-26.2": { totalSymbols: 16576, totalPages: 332, countAdded: 1743, countRemoved: 1921, countDeprecated: 4, countExtension: 0, countStable: 12908 },
		"1.20.6-26.3": { totalSymbols: 16773, totalPages: 336, countAdded: 1846, countRemoved: 2027, countDeprecated: 4, countExtension: 0, countStable: 12896 },
		"1.21.11-26.1.2": { totalSymbols: 14388, totalPages: 288, countAdded: 146, countRemoved: 119, countDeprecated: 1, countExtension: 0, countStable: 14122 },
		"1.21.11-26.2": { totalSymbols: 14913, totalPages: 299, countAdded: 297, countRemoved: 258, countDeprecated: 1, countExtension: 0, countStable: 14357 },
		"1.21.11-26.3": { totalSymbols: 15142, totalPages: 303, countAdded: 445, countRemoved: 396, countDeprecated: 1, countExtension: 0, countStable: 14300 },
		"26.1.2-26.2": { totalSymbols: 14799, totalPages: 296, countAdded: 154, countRemoved: 144, countDeprecated: 0, countExtension: 0, countStable: 14501 },
		"26.1.2-26.3": { totalSymbols: 15044, totalPages: 301, countAdded: 314, countRemoved: 298, countDeprecated: 0, countExtension: 0, countStable: 14432 },
		"26.2-26.3": { totalSymbols: 14911, totalPages: 299, countAdded: 166, countRemoved: 165, countDeprecated: 0, countExtension: 0, countStable: 14580 },
	},
	velocity: {
		"4.2.1-4.2.1": { totalSymbols: 180, totalPages: 4, countAdded: 0, countRemoved: 0, countDeprecated: 0, countExtension: 0, countStable: 180 },
		"4.3.0-4.3.0": { totalSymbols: 180, totalPages: 4, countAdded: 0, countRemoved: 0, countDeprecated: 0, countExtension: 0, countStable: 180 },
		"4.2.1-4.3.0": { totalSymbols: 180, totalPages: 4, countAdded: 0, countRemoved: 0, countDeprecated: 0, countExtension: 0, countStable: 180 },
	},
	waterfall: {
		"1.21-1.21": { totalSymbols: 174, totalPages: 4, countAdded: 0, countRemoved: 0, countDeprecated: 0, countExtension: 0, countStable: 174 },
	},
};

// Metadata initialized from canonical explorer-meta.json (16,523 symbols for 1.20.6 -> 26.3)
const totalSymbolsCount = ref(16523);
const totalPagesCount = ref(331);
const countAdded = ref(1774);
const countRemoved = ref(1807);
const countDeprecated = ref(2);
const countExtension = ref(350);
const countStable = ref(12590);

function syncRangeMetaInstant() {
	const platform = selectedPlatform.value;
	const table = DIFF_METADATA_TABLE[platform];
	if (!table) return;

	const vers = platformVersions.value;
	const v1 = minVersion.value;
	const v2 = maxVersion.value;
	const idx1 = vers.indexOf(v1);
	const idx2 = vers.indexOf(v2);

	let vLower = v1;
	let vUpper = v2;
	if (idx1 !== -1 && idx2 !== -1) {
		if (idx1 < idx2) {
			vLower = v2;
			vUpper = v1;
		} else {
			vLower = v1;
			vUpper = v2;
		}
	}

	const metaKey = `${vLower}-${vUpper}`;
	const meta = table[metaKey];
	if (meta) {
		totalSymbolsCount.value = meta.totalSymbols;
		totalPagesCount.value = meta.totalPages;
		countAdded.value = meta.countAdded;
		countRemoved.value = meta.countRemoved;
		countDeprecated.value = meta.countDeprecated;
		countExtension.value = meta.countExtension;
		countStable.value = meta.countStable;
	}
}

const currentSymbols = ref([]);

// Search index (shallowRef prevents deep proxy wrapping of 15,000+ objects)
const searchIndex = shallowRef([]);
const searchMatches = shallowRef([]);
const isSearchingOrFiltering = ref(false);
const isFullIndexLoaded = ref(false);

function getSingleVersionDiffConfig(v, platform) {
	const base = `${import.meta.env.BASE_URL || "/"}data/diffs/${platform}/`;
	if (platform === "velocity") {
		return { dir: `${base}4.2.1-4.3.0/`, filter: () => true };
	}
	if (platform === "waterfall") {
		return { dir: `${base}1.21-1.21/`, filter: () => true };
	}
	switch (v) {
		case "26.2":
			return { dir: `${base}26.1.2-26.2/`, filter: (s) => s.status !== "REMOVED" };
		case "26.1.2":
			return { dir: `${base}1.21.11-26.1.2/`, filter: (s) => s.status !== "REMOVED" };
		case "1.21.11":
			return { dir: `${base}1.20.6-1.21.11/`, filter: (s) => s.status !== "REMOVED" };
		case "1.20.6":
			return { dir: `${base}1.20.6-1.21.11/`, filter: (s) => s.status !== "INTRODUCED" };
		default:
			return { dir: `${base}26.2-26.3/`, filter: (s) => s.status !== "REMOVED" };
	}
}

async function loadSingleVersionData() {
	errorMessage.value = null;

	// When minVersion === maxVersion and viewing "CHANGED" (the default),
	// there are by definition 0 changed symbols between identical versions.
	if (selectedStatus.value === "CHANGED" && !searchQuery.value.trim() && selectedCategory.value === "ALL") {
		searchIndex.value = [];
		searchMatches.value = [];
		currentSymbols.value = [];
		isSearchingOrFiltering.value = true;
		loadingPage.value = false;
		return;
	}

	loadingPage.value = true;
	const { dir: sampleDir, filter: filterCondition } = getSingleVersionDiffConfig(
		minVersion.value,
		selectedPlatform.value,
	);

	try {
		const searchData = await cachedFetchJson(`${sampleDir}search-index.json`);
		searchIndex.value = searchData.filter(filterCondition).map((s) => ({
			...s,
			status: "STABLE",
			statusText: `Stable in ${selectedPlatform.value.toUpperCase()} ${minVersion.value}`,
			statusClass: "status-stable",
		}));
		isFullIndexLoaded.value = true;

		totalSymbolsCount.value = searchIndex.value.length;
		totalPagesCount.value = Math.ceil(searchIndex.value.length / 50) || 1;
		countAdded.value = 0;
		countRemoved.value = 0;
		countDeprecated.value = 0;
		countExtension.value = 0;
		countStable.value = searchIndex.value.length;

		applyFilters();
	} catch (e) {
		console.warn("Failed to load symbols for version:", e);
		searchIndex.value = [];
		currentSymbols.value = [];
		errorMessage.value = e.message || "Failed to load API symbols";
	} finally {
		loadingPage.value = false;
	}
}

async function loadMeta(dir) {
	// If metadata was already provided by instant table, do not overwrite it asynchronously
	const table = DIFF_METADATA_TABLE[selectedPlatform.value];
	if (table) return;

	try {
		const meta = await cachedFetchJson(`${dir}explorer-meta.json`);
		applyMeta(meta);
	} catch (e) {
		console.warn("Failed to fetch explorer-meta.json:", e);
	}
}

async function loadSymbolsByFilter(dir) {
	isFullIndexLoaded.value = false;
	const isDefaultChangedView = selectedStatus.value === "CHANGED" && selectedCategory.value === "ALL" && !searchQuery.value.trim();

	if (isDefaultChangedView) {
		try {
			const changedData = await cachedFetchJson(`${dir}changed-symbols.json`);
			searchIndex.value = changedData;
			applyFilters();
			loadSearchIndexBackground(dir);
			return;
		} catch (e) {
			console.warn("Changed symbols dataset not found, falling back to full index:", e);
		}
	}

	try {
		const searchData = await cachedFetchJson(`${dir}search-index.json`);
		searchIndex.value = searchData;
		isFullIndexLoaded.value = true;
		applyFilters();
	} catch (e) {
		console.warn("Failed to load search-index:", e);
		searchIndex.value = [];
		currentSymbols.value = [];
		errorMessage.value = `Diff data for ${selectedPlatform.value} ${minVersion.value}-${maxVersion.value} is not precomputed.`;
	}
}

async function loadData() {
	if (minVersion.value === maxVersion.value) {
		await loadSingleVersionData();
		return;
	}

	const dir = activeDataPath.value;
	loadingPage.value = true;
	errorMessage.value = null;

	try {
		await loadMeta(dir);
		await loadSymbolsByFilter(dir);
	} catch (e) {
		console.error("Failed to load initial page data:", e);
		errorMessage.value = e.message || "Failed to load API symbols";
	} finally {
		loadingPage.value = false;
	}
}

let searchIndexAbortController = null;

async function loadSearchIndexBackground(dir) {
	if (searchIndexAbortController) {
		searchIndexAbortController.abort();
	}
	searchIndexAbortController = new AbortController();
	const signal = searchIndexAbortController.signal;

	try {
		const searchData = await cachedFetchJson(`${dir}search-index.json`, signal);
		if (Array.isArray(searchData)) {
			searchIndex.value = searchData;
			isFullIndexLoaded.value = true;
			if (searchQuery.value.trim() || selectedCategory.value !== "ALL" || selectedStatus.value !== "CHANGED") {
				applyFilters();
			}
		}
	} catch (e) {
		if (e.name !== "AbortError") {
			console.warn("Background search index fetch warning:", e);
		}
	}
}

function applyMeta(meta) {
	if (!meta || typeof meta.totalSymbols !== "number") return;
	totalSymbolsCount.value = meta.totalSymbols;
	totalPagesCount.value = meta.totalPages;
	countAdded.value = meta.countAdded;
	countRemoved.value = meta.countRemoved;
	countDeprecated.value = meta.countDeprecated;
	countExtension.value = meta.countExtension || 0;
	countStable.value = meta.countStable;
}

onMounted(() => {
	loadData();
});

function retryLoad() {
	loadData();
}

function resetFilters() {
	selectedCategory.value = "ALL";
	selectedStatus.value = "CHANGED";
	searchQuery.value = "";
	applyFilters();
}

function switchToFullApiSurface() {
	selectedStatus.value = "ALL";
	onFilterChange();
}

function paginateCurrentList() {
	const pageSize = 50;
	const list = isSearchingOrFiltering.value ? searchMatches.value : searchIndex.value;
	const start = (currentPage.value - 1) * pageSize;
	const pageItems = list.slice(start, start + pageSize);

	currentSymbols.value = pageItems.map((item) => ({
		id: item.id,
		kind: item.kind,
		name: item.name,
		descriptor: item.descriptor || "",
		status: item.status,
		statusText: item.statusText || getStatusText(item.status),
		statusClass: getStatusClass(item.status),
		scope: item.scope || "",
		testLink: null,
		testMethod: null,
		testOutcome: null,
		testScopeNote: null,
		migrationHint: null,
	}));
}

function changePage(page) {
	currentPage.value = page;
	paginateCurrentList();
	window.scrollTo({ top: 300, behavior: "smooth" });
}

let searchDebounceTimer = null;

function onSearchInput() {
	if (searchDebounceTimer) clearTimeout(searchDebounceTimer);
	searchDebounceTimer = setTimeout(() => {
		onFilterChange();
	}, 150);
}

async function onFilterChange() {
	if (searchDebounceTimer) clearTimeout(searchDebounceTimer);

	// If the user requests ALL or STABLE symbols, or a search query, but we only have changed-symbols loaded:
	const needsFullIndex = selectedStatus.value === "ALL" || selectedStatus.value === "STABLE" || searchQuery.value.trim().length > 0;
	if (needsFullIndex && !isFullIndexLoaded.value && minVersion.value !== maxVersion.value) {
		loadingPage.value = true;
		try {
			const fullData = await cachedFetchJson(`${activeDataPath.value}search-index.json`);
			if (Array.isArray(fullData)) {
				searchIndex.value = fullData;
				isFullIndexLoaded.value = true;
			}
		} catch (e) {
			console.warn("Failed to dynamically hydrate full search index:", e);
		} finally {
			loadingPage.value = false;
		}
	} else if (minVersion.value === maxVersion.value && searchIndex.value.length === 0) {
		await loadSingleVersionData();
		return;
	}

	applyFilters();
}

function matchesSymbol(s, category, status, query) {
	if (category !== "ALL" && s.kind !== category) return false;
	if (status === "CHANGED") {
		if (s.status === "STABLE") return false;
	} else if (status !== "ALL" && s.status !== status) {
		return false;
	}
	if (!query) return true;
	return (
		(s.name && s.name.toLowerCase().includes(query)) ||
		(s.descriptor && s.descriptor.toLowerCase().includes(query)) ||
		(s.scope && s.scope.toLowerCase().includes(query))
	);
}

function applyFilters() {
	const query = searchQuery.value.trim().toLowerCase();
	const category = selectedCategory.value;
	const status = selectedStatus.value;

	if (!query && category === "ALL" && status === "ALL" && minVersion.value !== maxVersion.value) {
		isSearchingOrFiltering.value = false;
		searchMatches.value = [];
		currentPage.value = 1;
		paginateCurrentList();
		return;
	}

	isSearchingOrFiltering.value = true;
	currentPage.value = 1;

	let added = 0;
	let removed = 0;
	let deprecated = 0;
	let extension = 0;
	let stable = 0;
	const matches = [];
	const index = searchIndex.value;
	const total = index.length;

	for (let i = 0; i < total; i++) {
		const s = index[i];
		if (!matchesSymbol(s, category, status, query)) continue;
		matches.push(s);
		const st = s.status;
		if (st === "INTRODUCED") added++;
		else if (st === "REMOVED") removed++;
		else if (st === "DEPRECATED") deprecated++;
		else if (st === "EXTENSION") extension++;
		else if (st === "STABLE") stable++;
	}

	searchMatches.value = matches;
	filteredCountAdded.value = added;
	filteredCountRemoved.value = removed;
	filteredCountDeprecated.value = deprecated;
	filteredCountExtension.value = extension;
	filteredCountStable.value = stable;

	paginateCurrentList();
}

const filteredCountAdded = ref(0);
const filteredCountRemoved = ref(0);
const filteredCountDeprecated = ref(0);
const filteredCountExtension = ref(0);
const filteredCountStable = ref(0);

const displayedTotal = computed(() => {
	return isSearchingOrFiltering.value ? searchMatches.value.length : totalSymbolsCount.value;
});

const displayedPages = computed(() => {
	return isSearchingOrFiltering.value ? Math.ceil(searchMatches.value.length / 50) || 1 : totalPagesCount.value;
});

const displayedCountAdded = computed(() => {
	if (minVersion.value === maxVersion.value) return 0;
	return isSearchingOrFiltering.value ? filteredCountAdded.value : countAdded.value;
});

const displayedCountRemoved = computed(() => {
	if (minVersion.value === maxVersion.value) return 0;
	return isSearchingOrFiltering.value ? filteredCountRemoved.value : countRemoved.value;
});

const displayedCountDeprecated = computed(() => {
	if (minVersion.value === maxVersion.value) return 0;
	return isSearchingOrFiltering.value ? filteredCountDeprecated.value : countDeprecated.value;
});

const displayedCountExtension = computed(() => {
	if (minVersion.value === maxVersion.value) return 0;
	return isSearchingOrFiltering.value ? filteredCountExtension.value : countExtension.value;
});

const displayedCountStable = computed(() => {
	if (minVersion.value === maxVersion.value) {
		return isSearchingOrFiltering.value ? filteredCountStable.value : totalSymbolsCount.value;
	}
	return isSearchingOrFiltering.value ? filteredCountStable.value : countStable.value;
});

function getStatusClass(status) {
	switch (status) {
		case "INTRODUCED":
			return "status-added";
		case "REMOVED":
			return "status-removed";
		case "DEPRECATED":
			return "status-deprecated";
		case "EXTENSION":
			return "status-extension";
		default:
			return "status-stable";
	}
}

function getStatusText(status) {
	switch (status) {
		case "INTRODUCED":
			return `Introduced in ${maxVersion.value}`;
		case "REMOVED":
			return `Removed upstream in ${maxVersion.value} (Binary Incompatible)`;
		case "DEPRECATED":
			return `Deprecated in ${maxVersion.value}`;
		case "EXTENSION":
			return "Platform Extension (Threaded Regions / Proxy)";
		default:
			return `Stable throughout ${minVersion.value} - ${maxVersion.value}`;
	}
}
</script>

<style scoped>
.range-browser {
	margin-top: 1.5rem;
	font-family: inherit;
}
.controls-panel {
	display: flex;
	flex-wrap: wrap;
	gap: 1rem;
	padding: 1.25rem;
	background: var(--vp-c-bg-soft);
	border: 1px solid var(--vp-c-divider);
	border-radius: 8px;
	margin-bottom: 1rem;
}
.unsupported-banner {
	margin-bottom: 1rem;
	padding: 0.75rem 1rem;
	background: rgba(245, 158, 11, 0.12);
	border-left: 4px solid #f59e0b;
	border-radius: 4px;
	font-size: 0.88rem;
	color: var(--vp-c-text-1);
}
.error-banner {
	margin-bottom: 1rem;
	padding: 0.75rem 1rem;
	background: var(--vp-c-red-soft, rgba(239, 68, 68, 0.14));
	border-left: 4px solid var(--vp-c-red-1, #dc2626);
	border-radius: 4px;
	font-size: 0.88rem;
	color: var(--vp-c-text-1);
	display: flex;
	align-items: center;
	justify-content: space-between;
}
.retry-btn,
.reset-btn {
	padding: 0.35rem 0.75rem;
	background: var(--vp-c-bg);
	border: 1px solid var(--vp-c-divider);
	border-radius: 4px;
	font-size: 0.8rem;
	font-weight: 600;
	cursor: pointer;
	color: var(--vp-c-text-1);
}
.retry-btn:hover,
.reset-btn:hover {
	border-color: var(--vp-c-brand-1);
	color: var(--vp-c-brand-1);
}
.empty-state {
	padding: 2.5rem 1rem;
	text-align: center;
	background: var(--vp-c-bg-soft);
	border: 1px dashed var(--vp-c-divider);
	border-radius: 8px;
	color: var(--vp-c-text-2);
}
.empty-state p {
	margin-bottom: 1rem;
	font-size: 0.95rem;
}
.control-group {
	display: flex;
	flex-direction: column;
	gap: 0.25rem;
}
.control-group label {
	font-size: 0.85rem;
	font-weight: 600;
	color: var(--vp-c-text-2);
}
.control-group select,
.control-group input {
	padding: 0.45rem 0.75rem;
	border-radius: 6px;
	border: 1px solid var(--vp-c-divider);
	background: var(--vp-c-bg);
	color: var(--vp-c-text-1);
}
.search-box {
	flex: 1 1 100%;
}
.chunk-loading {
	display: flex;
	align-items: center;
	gap: 0.5rem;
	padding: 0.5rem;
	font-size: 0.85rem;
	color: var(--vp-c-brand-1);
}
.spinner-small {
	width: 16px;
	height: 16px;
	border: 2px solid var(--vp-c-divider);
	border-top-color: var(--vp-c-brand-1);
	border-radius: 50%;
	animation: spin 0.8s linear infinite;
}
@keyframes spin {
	to {
		transform: rotate(360deg);
	}
}
.summary-bar {
	display: flex;
	gap: 0.5rem;
	margin-bottom: 1rem;
	flex-wrap: wrap;
}
.badge {
	padding: 0.25rem 0.6rem;
	border-radius: 4px;
	font-size: 0.8rem;
	font-weight: 600;
}
.badge-info {
	background: var(--vp-c-default-soft);
	color: var(--vp-c-text-1);
}
.badge-added {
	background: var(--vp-c-green-soft, rgba(16, 185, 129, 0.16));
	color: var(--vp-c-green-1, #059669);
}
.badge-removed {
	background: var(--vp-c-red-soft, rgba(239, 68, 68, 0.16));
	color: var(--vp-c-red-1, #dc2626);
}
.badge-deprecated {
	background: var(--vp-c-yellow-soft, rgba(245, 158, 11, 0.16));
	color: var(--vp-c-yellow-1, #d97706);
}
.badge-extension {
	background: var(--vp-c-purple-soft, rgba(168, 85, 247, 0.16));
	color: var(--vp-c-purple-1, #9333ea);
}
.badge-stable {
	background: var(--vp-c-blue-soft, rgba(59, 130, 246, 0.16));
	color: var(--vp-c-blue-1, #2563eb);
}

.symbol-list {
	display: flex;
	flex-direction: column;
	gap: 0.75rem;
	width: 100%;
	max-width: 100%;
}
.symbol-card {
	padding: 1rem;
	border: 1px solid var(--vp-c-divider);
	border-radius: 8px;
	background: var(--vp-c-bg);
	overflow-wrap: anywhere;
	max-width: 100%;
	box-sizing: border-box;
}
.symbol-header {
	display: flex;
	align-items: center;
	gap: 0.75rem;
	margin-bottom: 0.4rem;
	flex-wrap: wrap;
}
.symbol-kind {
	font-size: 0.75rem;
	font-weight: 700;
	text-transform: uppercase;
	color: var(--vp-c-text-2);
	background: var(--vp-c-bg-mute);
	padding: 0.15rem 0.4rem;
	border-radius: 4px;
}
.symbol-name {
	font-weight: 600;
	font-size: 0.95rem;
	overflow-wrap: anywhere;
	max-width: 100%;
}
.status-badge {
	font-size: 0.75rem;
	font-weight: 600;
	padding: 0.15rem 0.5rem;
	border-radius: 4px;
	margin-left: auto;
	white-space: nowrap;
}
.status-added {
	background: var(--vp-c-green-soft, rgba(16, 185, 129, 0.16));
	color: var(--vp-c-green-1, #059669);
}
.status-removed {
	background: var(--vp-c-red-soft, rgba(239, 68, 68, 0.16));
	color: var(--vp-c-red-1, #dc2626);
}
.status-deprecated {
	background: var(--vp-c-yellow-soft, rgba(245, 158, 11, 0.16));
	color: var(--vp-c-yellow-1, #d97706);
}
.status-stable {
	background: var(--vp-c-blue-soft, rgba(59, 130, 246, 0.16));
	color: var(--vp-c-blue-1, #2563eb);
}
.status-extension {
	background: var(--vp-c-purple-soft, rgba(168, 85, 247, 0.16));
	color: var(--vp-c-purple-1, #9333ea);
}

.symbol-meta {
	margin-bottom: 0.5rem;
	overflow-x: auto;
	max-width: 100%;
}
.symbol-meta code {
	font-size: 0.8rem;
	word-break: break-all;
	white-space: pre-wrap;
}
.symbol-details {
	font-size: 0.85rem;
	display: flex;
	flex-direction: column;
	gap: 0.25rem;
	color: var(--vp-c-text-2);
	overflow-wrap: anywhere;
}
.test-info a {
	color: var(--vp-c-brand-1);
	text-decoration: underline;
	overflow-wrap: anywhere;
}
.scope-note {
	display: block;
	font-style: italic;
	font-size: 0.8rem;
	color: var(--vp-c-text-3);
	overflow-wrap: anywhere;
}
.migration-hint {
	color: var(--vp-c-yellow-1, #d97706);
	overflow-wrap: anywhere;
}

.pagination {
	display: flex;
	justify-content: center;
	align-items: center;
	gap: 1rem;
	margin-top: 1.5rem;
}
.pagination button {
	padding: 0.4rem 0.8rem;
	border: 1px solid var(--vp-c-divider);
	background: var(--vp-c-bg-soft);
	border-radius: 4px;
	cursor: pointer;
}
.pagination button:disabled {
	opacity: 0.5;
	cursor: not-allowed;
}
</style>
