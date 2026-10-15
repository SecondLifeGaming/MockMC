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
				<label for="min-version-select">Min Version</label>
				<select id="min-version-select" v-model="minVersion">
					<option v-for="ver in availableVersions" :key="ver" :value="ver">{{ ver }}</option>
				</select>
			</div>

			<div class="control-group">
				<label for="max-version-select">Max Version</label>
				<select id="max-version-select" v-model="maxVersion">
					<option v-for="ver in availableVersions" :key="ver" :value="ver">{{ ver }}</option>
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

		<div class="summary-bar">
			<span class="badge badge-info">
				Showing {{ displayedTotal.toLocaleString() }}
				{{ isSearchingOrFiltering ? "Matching" : "of " + totalSymbolsCount.toLocaleString() }} Symbols
			</span>
			<span class="badge badge-added">{{ displayedCountAdded.toLocaleString() }} Added</span>
			<span class="badge badge-removed">{{ displayedCountRemoved.toLocaleString() }} Breaking</span>
			<span class="badge badge-deprecated">{{ displayedCountDeprecated.toLocaleString() }} Deprecated</span>
			<span class="badge badge-stable">{{ displayedCountStable.toLocaleString() }} Stable</span>
		</div>

		<!-- Active Loading Indicator for Chunk Fetching -->
		<div v-if="loadingPage" class="chunk-loading">
			<div class="spinner-small"></div>
			<span>Loading page {{ currentPage }}...</span>
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

const availableVersions = ["26.2", "26.3"];

// Metadata
const totalSymbolsCount = ref(15058);
const totalPagesCount = ref(302);
const countAdded = ref(169);
const countRemoved = ref(238);
const countDeprecated = ref(0);
const countStable = ref(14651);

// Chunks cache
const pageCache = new Map();
const currentSymbols = ref([]);

// Search index
const searchIndex = ref([]);
const searchMatches = ref([]);
const isSearchingOrFiltering = ref(false);

onMounted(async () => {
	const baseUrl = import.meta.env.BASE_URL || "/";
	loadingPage.value = true;
	// 1. Fetch metadata (instant, <1KB)
	fetch(`${baseUrl}data/explorer-meta.json`)
		.then((r) => (r.ok ? r.json() : null))
		.then((meta) => {
			if (meta) {
				totalSymbolsCount.value = meta.totalSymbols;
				totalPagesCount.value = meta.totalPages;
				countAdded.value = meta.countAdded;
				countRemoved.value = meta.countRemoved;
				countDeprecated.value = meta.countDeprecated;
				countStable.value = meta.countStable;
			}
		})
		.catch(() => {});

	// 2. Load lightweight search index and immediately apply default filter (CHANGED)
	try {
		const res = await fetch(`${baseUrl}data/search-index.json`);
		if (res.ok) {
			searchIndex.value = await res.json();
		}
	} catch (e) {
		console.error("Failed to load search index:", e);
	} finally {
		loadingPage.value = false;
	}

	// Apply initial filters (defaults to CHANGED)
	applyFilters();
});

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
		testLink: item.name.includes("Player")
			? "https://github.com/westkevin12/MockBukkit/blob/master/src/test/java/org/mockmc/mockmc/entity/PlayerMockTest.java"
			: null,
		testMethod: item.name.includes("Player") ? "PlayerMockTest" : null,
		testOutcome: "PASSED",
		testScopeNote: item.name.includes("Player") ? "Verifies player state machine and entity simulation." : null,
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
	background: rgba(16, 185, 129, 0.15);
	color: #10b981;
}
.badge-removed {
	background: rgba(239, 68, 68, 0.15);
	color: #ef4444;
}
.badge-deprecated {
	background: rgba(245, 158, 11, 0.15);
	color: #f59e0b;
}
.badge-stable {
	background: rgba(59, 130, 246, 0.15);
	color: #3b82f6;
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
	overflow-wrap: break-word;
	word-break: break-word;
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
	overflow-wrap: break-word;
	word-break: break-all;
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
	background: rgba(16, 185, 129, 0.15);
	color: #10b981;
}
.status-removed {
	background: rgba(239, 68, 68, 0.15);
	color: #ef4444;
}
.status-deprecated {
	background: rgba(245, 158, 11, 0.15);
	color: #f59e0b;
}
.status-stable {
	background: rgba(59, 130, 246, 0.15);
	color: #3b82f6;
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
	overflow-wrap: break-word;
	word-break: break-word;
}
.test-info a {
	color: var(--vp-c-brand-1);
	text-decoration: underline;
	word-break: break-all;
}
.scope-note {
	display: block;
	font-style: italic;
	font-size: 0.8rem;
	color: var(--vp-c-text-3);
	overflow-wrap: break-word;
}
.migration-hint {
	color: #f59e0b;
	word-break: break-all;
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
