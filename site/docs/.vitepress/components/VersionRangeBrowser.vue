<template>
	<div class="range-browser">
		<div class="controls-panel">
			<div class="control-group">
				<label for="platform-select">Platform</label>
				<select id="platform-select" v-model="selectedPlatform">
					<option value="paper">Paper (Canonical)</option>
					<option value="folia">Folia (Threaded Regions)</option>
					<option value="velocity">Velocity</option>
					<option value="waterfall">Waterfall</option>
				</select>
			</div>

			<div class="control-group">
				<label for="min-version-select">Baseline Version</label>
				<select id="min-version-select" v-model="minVersion" disabled title="Currently validated baseline from versions.json">
					<option value="26.2">26.2 (Validated Baseline)</option>
				</select>
			</div>

			<div class="control-group">
				<label for="max-version-select">Target Version</label>
				<select id="max-version-select" v-model="maxVersion" disabled title="Currently validated canonical target from versions.json">
					<option value="26.3">26.3 (Canonical Target)</option>
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

		<div v-if="selectedPlatform !== 'paper'" class="unsupported-banner">
			<strong>Platform Notice:</strong> The active manifest and range-diff validation currently indexes canonical <strong>Paper 26.2 → 26.3</strong> with Folia 26.2 regional extensions. Standalone multi-version pairwise diffing for {{ selectedPlatform.toUpperCase() }} is under active indexing.
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
				<p>No API symbols match your current filters or search query.</p>
				<button class="reset-btn" @click="resetFilters">Reset Filters</button>
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
import { ref, computed, onMounted } from "vue";

const selectedPlatform = ref("paper");
const minVersion = ref("26.2");
const maxVersion = ref("26.3");
const selectedCategory = ref("ALL");
const selectedStatus = ref("CHANGED");
const searchQuery = ref("");
const currentPage = ref(1);
const loadingPage = ref(false);
const errorMessage = ref(null);

const availableVersions = ["26.2", "26.3"];

// Metadata initialized from canonical explorer-meta.json (16,180 symbols)
const totalSymbolsCount = ref(16180);
const totalPagesCount = ref(324);
const countAdded = ref(166);
const countRemoved = ref(163);
const countDeprecated = ref(0);
const countExtension = ref(334);
const countStable = ref(15517);

// Chunks cache
const pageCache = new Map();
const currentSymbols = ref([]);

// Search index
const searchIndex = ref([]);
const searchMatches = ref([]);
const isSearchingOrFiltering = ref(false);

async function initData() {
	const baseUrl = import.meta.env.BASE_URL || "/";
	loadingPage.value = true;
	errorMessage.value = null;

	try {
		// 1. Fetch metadata
		const metaRes = await fetch(`${baseUrl}data/explorer-meta.json`);
		if (!metaRes.ok) throw new Error(`HTTP ${metaRes.status} loading explorer-meta.json`);
		const meta = await metaRes.json();
		if (!meta || typeof meta.totalSymbols !== "number") throw new Error("Invalid explorer-meta schema");

		totalSymbolsCount.value = meta.totalSymbols;
		totalPagesCount.value = meta.totalPages;
		countAdded.value = meta.countAdded;
		countRemoved.value = meta.countRemoved;
		countDeprecated.value = meta.countDeprecated;
		countExtension.value = meta.countExtension || 0;
		countStable.value = meta.countStable;

		// 2. Fetch search index
		const searchRes = await fetch(`${baseUrl}data/search-index.json`);
		if (!searchRes.ok) throw new Error(`HTTP ${searchRes.status} loading search-index.json`);
		const searchData = await searchRes.json();
		if (!Array.isArray(searchData)) throw new Error("Invalid search-index schema");
		searchIndex.value = searchData;

		applyFilters();
	} catch (e) {
		console.error("Failed to load explorer data:", e);
		errorMessage.value = e.message || "Failed to load API metadata files";
	} finally {
		loadingPage.value = false;
	}
}

onMounted(() => {
	initData();
});

function retryLoad() {
	pageCache.clear();
	initData();
}

function resetFilters() {
	selectedCategory.value = "ALL";
	selectedStatus.value = "CHANGED";
	searchQuery.value = "";
	applyFilters();
}

async function loadPage(pageNumber) {
	if (pageCache.has(pageNumber)) {
		currentSymbols.value = pageCache.get(pageNumber);
		return;
	}

	loadingPage.value = true;
	try {
		const baseUrl = import.meta.env.BASE_URL || "/";
		const res = await fetch(`${baseUrl}data/pages/page-${pageNumber}.json`);
		if (res.ok) {
			const data = await res.json();
			pageCache.set(pageNumber, data);
			currentSymbols.value = data;
		}
	} catch (err) {
		console.error(`Error loading page ${pageNumber}:`, err);
	} finally {
		loadingPage.value = false;
	}
}

function changePage(page) {
	currentPage.value = page;
	if (isSearchingOrFiltering.value) {
		paginateSearchResults();
	} else {
		loadPage(page);
	}
	window.scrollTo({ top: 300, behavior: "smooth" });
}

function onSearchInput() {
	applyFilters();
}

function onFilterChange() {
	applyFilters();
}

function applyFilters() {
	const query = searchQuery.value.trim().toLowerCase();
	const category = selectedCategory.value;
	const status = selectedStatus.value;

	if (!query && category === "ALL" && status === "ALL") {
		isSearchingOrFiltering.value = false;
		searchMatches.value = [];
		currentPage.value = 1;
		loadPage(1);
		return;
	}

	isSearchingOrFiltering.value = true;
	currentPage.value = 1;

	searchMatches.value = searchIndex.value.filter((s) => {
		if (category !== "ALL" && s.kind !== category) return false;
		if (status === "CHANGED") {
			if (s.status === "STABLE") return false;
		} else if (status !== "ALL" && s.status !== status) {
			return false;
		}
		if (query) {
			return (
				s.name.toLowerCase().includes(query) ||
				s.descriptor.toLowerCase().includes(query) ||
				s.scope.toLowerCase().includes(query)
			);
		}
		return true;
	});

	paginateSearchResults();
}

function paginateSearchResults() {
	const pageSize = 50;
	const start = (currentPage.value - 1) * pageSize;
	const pageItems = searchMatches.value.slice(start, start + pageSize);

	currentSymbols.value = pageItems.map((item) => ({
		id: item.id,
		kind: item.kind,
		name: item.name,
		descriptor: item.descriptor,
		status: item.status,
		statusText: getStatusText(item.status),
		statusClass: getStatusClass(item.status),
		scope: item.scope,
		testLink: null,
		testMethod: null,
		testOutcome: null,
		testScopeNote: null,
		migrationHint: null,
	}));
}

const displayedTotal = computed(() => {
	return isSearchingOrFiltering.value ? searchMatches.value.length : totalSymbolsCount.value;
});

const displayedPages = computed(() => {
	return isSearchingOrFiltering.value ? Math.ceil(searchMatches.value.length / 50) || 1 : totalPagesCount.value;
});

const displayedCountAdded = computed(() => {
	if (isSearchingOrFiltering.value) {
		return searchMatches.value.filter((s) => s.status === "INTRODUCED").length;
	}
	return countAdded.value;
});

const displayedCountRemoved = computed(() => {
	if (isSearchingOrFiltering.value) {
		return searchMatches.value.filter((s) => s.status === "REMOVED").length;
	}
	return countRemoved.value;
});

const displayedCountDeprecated = computed(() => {
	if (isSearchingOrFiltering.value) {
		return searchMatches.value.filter((s) => s.status === "DEPRECATED").length;
	}
	return countDeprecated.value;
});

const displayedCountExtension = computed(() => {
	if (isSearchingOrFiltering.value) {
		return searchMatches.value.filter((s) => s.status === "EXTENSION").length;
	}
	return countExtension.value;
});

const displayedCountStable = computed(() => {
	if (isSearchingOrFiltering.value) {
		return searchMatches.value.filter((s) => s.status === "STABLE").length;
	}
	return countStable.value;
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
			return "Introduced in 26.3";
		case "REMOVED":
			return "Removed upstream in 26.3 (Binary Incompatible)";
		case "DEPRECATED":
			return "Deprecated in 26.3";
		case "EXTENSION":
			return "Platform Extension (Folia Threaded Regions)";
		default:
			return "Stable throughout 26.2 - 26.3";
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
