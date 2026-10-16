# RFC: Automated Minecraft Plugin Developer Reference & Version-Aware API Explorer

- **Status**: Proposed / Accepted for Planning
- **Author**: MockMC Core Architecture Team
- **Target Phase**: Phase 7 (Post Multi-Version Stabilization)
- **Related Documents**:
    - [`docs/ARCHITECTURE.md`](ARCHITECTURE.md)
    - [`docs/MULTI_VERSION_ARCHITECTURE.md`](MULTI_VERSION_ARCHITECTURE.md)
    - [`docs/GENERATOR_AND_COVERAGE.md`](GENERATOR_AND_COVERAGE.md)
    - [`docs/VERSION_SUPPORT.md`](VERSION_SUPPORT.md)
    - [`COMPAT.md`](../COMPAT.md)
    - [`COVERAGE.md`](../COVERAGE.md)
    - [`TODO.md`](../TODO.md)

---

## 1. Executive Summary & Vision

### 1.1 The Opportunity

Minecraft plugin developers operate in a fractured ecosystem where server APIs (Bukkit, Spigot, Paper, Folia, Velocity, Waterfall) evolve rapidly across minor and major releases. Developers routinely confront silent runtime breakage (`NoSuchMethodError`, `IncompatibleClassChangeError`), parameter and return-type mutations, subtle signature deprecations, and platform-exclusive namespaces.

Existing documentation sources (Javadoc, unofficial wikis, platform blogs) are **static, single-version snapshots**. They cannot answer essential real-world questions:

- _“Does this method exist on both Paper 26.2 and 26.3?”_
- _“What exact binary or source signature changes occurred between these two releases?”_
- _“Is this API available under Folia, or does it belong exclusively to Paper?”_
- _“Is this method missing because upstream removed it, because MockMC excludes it, or because the artifact was not discovered?”_
- _“Which MockMC mock implements this method, is it a safe default stub or a stateful behavioral simulation, and does a passing test verify it?”_

### 1.2 The "Engine First" Realization

MockMC already possesses what standard documentation projects lack: an autonomous **bytecode discovery and compatibility engine (`metaminer`)**. Through `JarCracker`, `StandaloneRemapper`, ASM bytecode inspection, and `jars/manifests/versions.json`, MockMC systematically inventories server and proxy JARs, tracks thousands of types and methods, enforces zero-downgrade platform hierarchies, generates smart stubs, and audits coverage.

Rather than building a disconnected, manually maintained wiki, MockMC will treat the **Developer Reference & API Explorer** as a direct projection of its API discovery infrastructure.

```
       +-----------------------------------------------------------+
       | Upstream Bytecode JARs (Paper, Folia, Velocity, Waterfall) |
       | Checked against SHA-256 manifests                          |
       +-----------------------------------------------------------+
                                     |
                                     v
       +-----------------------------------------------------------+
       |             MetaMiner Bytecode Scanner (ASM 9.8)          |
       |  Captures Provenance, Completeness & Unanalyzed Entries   |
       +-----------------------------------------------------------+
                                     |
                                     v
       +-----------------------------------------------------------+
       |   Canonical API Manifests (`api-manifest-<platform>-<ver>`) |
       +-----------------------------------------------------------+
                      |                             |
                      v                             v
       +-----------------------------+ +---------------------------+
       | Deterministic Pairwise Diff | | BaseMock & Test Generator |
       | Binary/Source Compatibility | | Smart Stubs + Test Suites |
       +-----------------------------+ +---------------------------+
          |                       |                 |
          v                       v                 v
   +--------------+      +------------------+ +--------------------+
   |  COMPAT.md   |      | Static Search &  | | Structural Stubs & |
   |  Markdown    |      | JSON Datasets    | | Verified Behavioral|
   |  Diff Reports|      | (Derived Ranges) | | Test Coverage Links|
   +--------------+      +------------------+ +--------------------+
                                  |
                                  v
                         +------------------+
                         |  MockMC Wiki &   |
                         |   API Explorer   |
                         |  (GitHub Pages)  |
                         +------------------+
```

This transforms MockMC into a tripartite product:

1. **The Modern Testing Framework**: Autonomous, high-fidelity mock server and proxy environment.
2. **The API Compatibility & Change Engine**: Machine-verifiable version diffs and fallback management.
3. **The Definitive Plugin Developer Reference**: Interactive, version-range-aware documentation grounded in verified bytecode.

---

## 2. Core Architectural Principles & Refinements

1. **Bytecode as Single Source of Truth**:
   Documentation is generated directly from unbundled, remapped upstream bytecode JARs. No hand-typed signatures or unverified javadoc claims.
2. **Explicit Provenance & Scan Completeness**:
   Every manifest records source artifact SHA-256, scanner version, remapping configuration, and scan status. Crucially, any classes or packages that could not be analyzed (linkage failure, malformed bytecode) are explicitly logged in the manifest. A manifest with unanalyzed entries cannot be silently treated as complete.
3. **API Identity vs. API Compatibility**:
   Method identity is determined strictly by its JVM descriptor `(declaringClass, methodName, methodDescriptor)`. The engine isolates bridge methods, generic signatures, visibility modifiers, and annotations. When a signature changes, it is classified accurately as a removal of the old descriptor and an addition of a new descriptor (with optional migration linkage), rather than loosely labeling it a "modified method". Changes are explicitly categorized as **Binary Incompatible**, **Source Incompatible**, or **Compatible Addition**.
4. **Derived Range Views over Pairwise Evidence**:
   To avoid a combinatorial $O(N^2)$ storage explosion, the repository stores canonical per-version manifests and pairwise diffs between adjacent releases. Version-range queries ($v_{\text{min}} \to v_{\text{max}}$) are **derived views** computed on demand by folding sequential pairwise changes or comparing boundary manifests.
5. **Rigorous Separation of Structural and Behavioral Claims**:
   The reference clearly separates three orthogonal dimensions:
    - _Structural Conformance_: API exists in bytecode; MockMC compiles a safe `*BaseMock` default stub.
    - _Behavioral Override_: Stateful logic manually implemented in `*Mock.java`.
    - _Behavioral Test Verification_: Linked to specific JUnit test classes, methods, and verified passing outcomes in CI. A passing unit test is documented as verifying specific assertions, never conflated with 100% vanilla engine equivalence.
6. **Unified Data Model, Dual Projections**:
   Markdown reports (`COMPAT.md`, `COMPAT-paper-26.2-to-26.3.md`) and the static web UI read from the **same canonical JSON metadata**. The wiki does not scrape Markdown reports; reports and web views project from identical underlying data.
7. **Strict Publication & Artifact-Trust Boundary**:
   GitHub Actions publishes documentation only from verified, trusted main-branch builds. PRs from forks run read-only validation without deploy tokens. External bytecode content (javadocs, method names) is strictly sanitized and HTML-escaped before presentation, backed by a strict Content Security Policy (CSP).

---

## 3. Data Model & Schema Specification

All metadata emitted by `metaminer` follows a formalized, versioned JSON schema.

> [!NOTE]
> All sample counts, method signatures, and class references in this section are illustrative schemas demonstrating structure, not verified assertions of upstream API state.

### 3.1 Platform Manifest Schema (`api-manifest-<platform>-<version>.json`)

```json
{
    "$schema": "https://mockmc.org/schemas/api-manifest-v1.json",
    "provenance": {
        "platform": "paper",
        "version": "26.3",
        "build": 178,
        "sourceJarSha256": "5dd0c8fb20a96eb7c98f3c4b1c890e99ef6db56fd689a2cf06975a3471fda757",
        "scannerVersion": "metaminer-2.1.0-sha.a8f93e1",
        "remappingConfig": {
            "mappingSource": "mojang+spigot-tsrg",
            "mappingSha256": "e3b0c44298fc1c149afbf4c8996fb92427ae41e4649b934ca495991b7852b855",
            "asmVersion": "ASM9_8"
        },
        "scannedAt": "2026-10-14T00:00:00Z",
        "tier": "canonical"
    },
    "scanCompleteness": {
        "status": "SUCCESS",
        "scannedPackageRoots": ["org.bukkit", "io.papermc.paper", "com.destroystokyo.paper", "co.aikar.timings"],
        "totalClassesAnalyzed": 2154,
        "totalMethodsAnalyzed": 18420,
        "unanalyzedEntries": []
    },
    "classes": {
        "org.bukkit.entity.Player": {
            "kind": "INTERFACE",
            "accessFlags": ["PUBLIC", "ABSTRACT", "INTERFACE"],
            "signature": "Ljava/lang/Object;Lorg/bukkit/entity/HumanEntity;Lorg/bukkit/conversations/Conversable;",
            "superClass": "java.lang.Object",
            "interfaces": ["org.bukkit.entity.HumanEntity", "org.bukkit.conversations.Conversable"],
            "annotations": [],
            "methods": {
                "sendTitle(Lnet/kyori/adventure/title/Title;)V": {
                    "name": "sendTitle",
                    "descriptor": "(Lnet/kyori/adventure/title/Title;)V",
                    "signature": null,
                    "returnType": "void",
                    "parameters": [{ "index": 0, "name": "title", "type": "net.kyori.adventure.title.Title" }],
                    "exceptions": [],
                    "accessFlags": ["PUBLIC", "ABSTRACT"],
                    "isBridge": false,
                    "isSynthetic": false,
                    "annotations": [{ "type": "org.jetbrains.annotations.NotNull" }],
                    "mockmc": {
                        "status": "BEHAVIORAL_OVERRIDE",
                        "mockClass": "org.mockmc.mockmc.entity.PlayerMock",
                        "verification": {
                            "hasTest": true,
                            "testClass": "org.mockmc.mockmc.entity.PlayerMockTest",
                            "testMethod": "testSendTitleAdventure",
                            "latestOutcome": "PASSED",
                            "scopeNote": "Verifies title packet simulation and title state assertions."
                        }
                    }
                }
            }
        }
    }
}
```

### 3.2 Pairwise Diff Schema (`diff-<platform>-<fromVer>-to-<toVer>.json`)

The diff engine treats JVM descriptors as the atomic unit of method identity. When an API transitions from a primitive/string parameter to an object parameter, it is recorded as the removal/deprecation of the legacy descriptor and the addition of the new descriptor, linked via a `migrationHint`.

```json
{
    "platform": "paper",
    "fromVersion": "26.2",
    "toVersion": "26.3",
    "summary": {
        "binaryIncompatibleChanges": 103,
        "sourceIncompatibleChanges": 14,
        "compatibleAdditions": 96,
        "deprecations": 8
    },
    "changes": [
        {
            "symbol": "org.bukkit.entity.Player#sendTitle(Ljava/lang/String;Ljava/lang/String;)V",
            "type": "METHOD",
            "category": "REMOVED",
            "compatibilityImpact": "BINARY_INCOMPATIBLE",
            "details": {
                "descriptor": "(Ljava/lang/String;Ljava/lang/String;)V",
                "reason": "Removed upstream in 26.3"
            },
            "migrationHint": {
                "relation": "SUPERSEDED_BY",
                "replacementSymbol": "org.bukkit.entity.Player#sendTitle(Lnet/kyori/adventure/title/Title;)V"
            },
            "mockmcHandling": "Maintained as @Deprecated historical fallback stub in MockMC."
        },
        {
            "symbol": "org.bukkit.entity.Player#sendTitle(Lnet/kyori/adventure/title/Title;)V",
            "type": "METHOD",
            "category": "ADDED",
            "compatibilityImpact": "COMPATIBLE_ADDITION",
            "details": {
                "descriptor": "(Lnet/kyori/adventure/title/Title;)V",
                "accessFlags": ["PUBLIC", "ABSTRACT"]
            },
            "mockmcHandling": "Implemented in PlayerMock with stateful simulation."
        }
    ]
}
```

### 3.3 Derived Range Query Model

To answer queries across wide spans (e.g., Paper 26.0 through 26.3):

1. **Dynamic Aggregation**: The range view reads chronological pairwise diffs:
   $$\Delta(v_0 \to v_n) = \Delta(v_0 \to v_1) \oplus \Delta(v_1 \to v_2) \oplus \dots \oplus \Delta(v_{n-1} \to v_n)$$
2. **Net Status Evaluation**:
    - Present at $v_0$ and $v_n$ with matching descriptor $\implies$ **Stable throughout range**.
    - Added at $v_i$ ($i > 0$) and present at $v_n$ $\implies$ **Introduced in range** (at $v_i$).
    - Present at $v_0$ and removed at $v_j$ ($j \le n$) $\implies$ **Removed in range** (at $v_j$, binary breaking).
    - Added at $v_i$ and removed at $v_j$ ($i < j \le n$) $\implies$ **Transient / Short-lived API**.
3. **No Storage Bloat**: Only $N$ manifests and $N-1$ adjacent diffs are persisted in the build artifact repository, completely avoiding $O(N^2)$ storage growth.

---

## 4. Feature Specifications

### 4.1 Feature 1: The Interactive API Explorer & Version-Range Browser

The cornerstone web interface, running purely client-side on GitHub Pages.

#### UI Controls:

- **Platform Selector**: Tabs (`Paper`, `Folia`, `Velocity`, `Waterfall`).
- **Version Range Bounds**: Min version ($v_{\text{min}}$) and Max version ($v_{\text{max}}$).
- **Symbol Query Filter**: Sub-millisecond client-side fuzzy search across classes, methods, return types, and packages.
- **Compatibility Filter**:
    - _All Symbols_
    - _Fully Compatible Throughout Range_
    - _Binary Breaking in Range_
    - _Introduced in Range_
    - _Deprecated in Range_

#### Symbol Information Display:

1. **Version Timeline**: Visual horizontal badge bar indicating availability across discovered builds (e.g., `[ 26.2: YES ] -> [ 26.3: YES ]`).
2. **Exact JVM Descriptor & Type Details**: Clean representation of return types, parameters, generic bounds, and exceptions, with synthetic/bridge methods filtered out by default.
3. **Platform Scope**: Distinguishes canonical Bukkit/Paper API from platform-exclusive namespaces (e.g. Folia `io.papermc.paper.threadedregions.*`).
4. **MockMC Implementation & Test Status**:
    - Badge: `Automated Smart Stub` vs. `Behavioral Override` vs. `Justified Exclusion`.
    - Test Link: Direct clickable link to the verified test case in the MockMC repository.
    - Outcome & Scope: Notes test outcome and scope boundaries (e.g., "Verifies state machine; does not execute vanilla pathfinding").

### 4.2 Feature 2: Cross-Version Compatibility Changelog

A dedicated upgrade assistant for developers migrating plugins between versions:

- Select arbitrary base version and target version (e.g. `Paper 26.2` to `Paper 26.3`).
- Instant breakdown into distinct change categories:
    - **Binary Incompatible**: Dropped classes/methods, descriptor alterations, abstract methods added to interfaces.
    - **Source Incompatible**: Ambiguous overloads, checked exceptions added.
    - **Compatible Additions**: New default methods, new overload additions.
    - **Deprecations**: Newly flagged `@Deprecated` or `@ScheduledForRemoval`.
- **Migration Links**: Shows when an old descriptor was superseded by a modern alternative (e.g. legacy text methods replaced by Adventure components).
- **Export Options**: View in UI, export as Markdown, or download raw JSON diff.

### 4.3 Feature 3: Generated Test Explorer & Testing Lab

Bridge the gap between API reference and test writing:

1. **Learn (Testing Recipes)**:
    - Setting up MockMC with JUnit 5 and Gradle/Maven.
    - Mocking Player connections, permissions, and command execution.
    - Simulating block break/place, inventory transactions, and custom events.
    - Multi-version testing strategies: running the same test suite against Paper and Folia.
2. **Explore (Test Surface Explorer)**:
    - Search for an API method and see which unit tests directly exercise its behavior.
    - Distinguish structural stubs from verified logic (e.g. `Player#getInventory()` has stateful behavior; `Player#getPostEffects()` returns empty default).
3. **Contribute (Behavioral Mock Contributions)**:
    - Streamlined checklist for submitting behavioral implementations.
    - Clear guidelines: mock only state and behavioral contracts, leaving API boilerplate to `BaseMockGenerator`.

---

## 5. Security, Artifact Trust & Publication Boundary

Because documentation is automatically generated from server bytecode and published publicly, strict security boundaries are established:

```
+-------------------------------------------------------------------------+
| UNTRUSTED ZONE (Pull Requests & Fork Runs)                              |
| - Runs ./gradlew :metaminer:runGenerator & validation tasks             |
| - Verifies JAR checksums against pinned hashes                           |
| - Verifies schema validity and scan completeness                        |
| - NO GitHub Pages deployment credentials                                |
| - NO write access to repository                                         |
+-------------------------------------------------------------------------+
                                    |
                                    v (PR Merged to Protected Branch)
+-------------------------------------------------------------------------+
| TRUSTED ZONE (Protected Main Branch & Release Tags Only)                |
| - Validates SHA-256 of all input JARs                                   |
| - Executes MetaMiner Manifest & Diff Generators                         |
| - HTML Sanitization & Link Integrity Check                              |
| - Injects Content Security Policy (CSP)                                 |
| - Deploys static bundle to GitHub Pages via secure OIDC token           |
+-------------------------------------------------------------------------+
```

### Security Measures:

1. **Input Verification**: No floating or unverified JARs are scanned. Every artifact downloaded via `DownloadJarsTask` is strictly checked against the SHA-256 hash in `jars/manifests/versions.json`.
2. **Bytecode Content Sanitization**: Method names, parameter names, javadocs, and class names scraped from JARs are rigorously HTML-escaped and sanitized before embedding in web pages or search indices, preventing HTML injection or XSS.
3. **Strict Content Security Policy**: The static documentation site enforces CSP headers:
   `default-src 'self'; script-src 'self'; style-src 'self' 'unsafe-inline'; img-src 'self' data:; connect-src 'self';`
4. **Isolated Fork Execution**: PR workflows never have access to GitHub Pages deployment keys or write permissions. Deployment occurs strictly on authenticated CI runs on `main` or release tags.
5. **Pre-Publication Integrity Gates**:
    - `0` unanalyzed classes or linkage errors without explicit justification.
    - `0` broken internal links or invalid cross-references.
    - Complete schema conformance validated with JSON Schema validator.

---

## 6. Hosting & Site Architecture

### 6.1 Static Site Deployment via GitHub Pages

- **Generator**: Modern, fast static documentation generator (VitePress or Starlight/Astro) supporting markdown guides and custom client-side reactive components.
- **Client-Side Search**: Pre-built static search index (Pagefind or MiniSearch) bundled during the CI build. Enables zero-server, sub-millisecond full-text queries over 20,000+ API symbols.
- **Data Hydration**: Manifests and diffs are emitted as compact JSON chunks in `site/public/data/`, loaded lazily by the client-side explorer when a platform/version is selected.

### 6.2 Repository Layout

```
MockBukkit/
├── docs/                                  # Source markdown & architecture
│   ├── index.md                           # Documentation site home
│   ├── getting-started/                   # Installation, setup, quickstart
│   ├── guides/                            # Testing recipes (Paper, Folia, Proxies)
│   ├── api/                               # Overview & manual architecture pages
│   ├── compatibility/                     # High-level compatibility contracts
│   ├── testing/                           # Test Explorer guides & conventions
│   ├── RFC_DEVELOPER_WIKI.md              # Authoritative RFC
│   └── rfc.md                             # RFC index
├── metaminer/                             # Engine & code generators
│   └── src/main/java/org/mockmc/metaminer/
│       ├── manifest/                      # API Manifest models & scanner
│       │   ├── ApiManifest.java
│       │   ├── ClassMetadata.java
│       │   ├── MethodMetadata.java
│       │   ├── ScanCompleteness.java
│       │   └── ManifestScanner.java
│       ├── diff/                          # Version diff engine
│       │   ├── VersionDiffEngine.java
│       │   ├── CompatibilityCategory.java
│       │   └── DiffReport.java
│       ├── docgen/                        # Web & markdown doc generators
│       │   ├── DocSiteDataGenerator.java
│       │   └── MultiVersionCompatReporter.java
│       ├── CoverageReportGenerator.java
│       └── CompatReportGenerator.java
├── site/                                  # Static doc site app (VitePress/Astro)
│   ├── package.json
│   ├── components/                        # VersionRangeBrowser, DiffViewer
│   └── public/data/                       # Pre-generated JSON manifests & index
└── .github/workflows/
    └── docs.yml                           # GitHub Pages CI build & deployment
```

---

## 7. Implementation Roadmap & Phased Prioritization

Execution begins after Phase 6 multi-version stabilization, structured into six independently useful milestones:

```
[ Phase 6 (Current) ]
├── Multi-Version Architecture Validation (Paper 26.3 + Folia 26.2)
└── Behavioral Depth Workstream (PaperLeashable, GameRule, PluginManager)
      │
      v
[ Phase 7: Automated Developer Reference & API Explorer ]
├── Milestone 7.1: Canonical Manifest Schema, Provenance & Scanner Completeness
├── Milestone 7.2: Deterministic Pairwise Diff Engine & Compatibility Classifications
├── Milestone 7.3: Generated COMPAT.md & Per-Platform/Version Reports
├── Milestone 7.4: Static Documentation Site Shell, Guides & Search
├── Milestone 7.5: Version-Range Explorer & Test Cross-Referencing
└── Milestone 7.6: CI Validation, Security Hardening & Automatic Publication
```

### Milestone Breakdown:

#### Milestone 7.1: Canonical Manifest Schema, Provenance & Scanner Completeness

- Formalize `ApiManifest` JSON schema with provenance, scanner version, remapping config, and completeness auditing.
- Implement `ManifestScanner` in `metaminer` using ASM 9.8.
- Scan pinned JARs and write canonical manifests to `jars/manifests/api/`.
- Ensure any unanalyzed classes or scan failures are explicitly captured and tested.

#### Milestone 7.2: Deterministic Pairwise Diff Engine & Compatibility Classifications

- Implement `VersionDiffEngine` to compare two `ApiManifest` instances.
- Track JVM descriptors accurately; classify changes into `BINARY_INCOMPATIBLE`, `SOURCE_INCOMPATIBLE`, `COMPATIBLE_ADDITION`, and `DEPRECATION`.
- Isolate bridge/synthetic methods from public API surfaces.
- Support `migrationHint` detection (e.g. String descriptor superseded by Adventure `Title`).
- Verify determinism with comprehensive unit tests against Paper 26.2 and 26.3 bytecode.

#### Milestone 7.3: Generated `COMPAT.md` & Per-Platform/Version Reports

- Refactor `CompatReportGenerator.java` to use `VersionDiffEngine`.
- Generate canonical repository `COMPAT.md` and per-version diff reports (e.g. `COMPAT-paper-26.2-to-26.3.md`) from the exact same JSON data model.
- Guarantee sorted, deterministic output to eliminate unnecessary git diff churn.

#### Milestone 7.4: Static Documentation Site Shell, Guides & Search

- Scaffold static documentation site in `site/` (VitePress / Starlight).
- Port and write core developer guides: Getting Started, First Test, Multi-Version Testing, Architecture.
- Implement pre-indexed static search pipeline (Pagefind).

#### Milestone 7.5: Version-Range Explorer & Test Cross-Referencing

- Implement client-side `VersionRangeBrowser` widget:
    - Dynamically computes range views by folding pairwise diffs.
    - Sub-second fuzzy search and filtering by binary compatibility status.
    - Inline signature diff inspection.
- Cross-reference MockMC implementation status and link verified JUnit tests with outcome indicators.

#### Milestone 7.6: CI Validation, Security Hardening & Automatic Publication

- Create `.github/workflows/docs.yml`:
    - Enforce PR validation: fails if manifests have unverified entries, broken links, or schema errors.
    - Enforce trusted main-branch deployment to GitHub Pages.
    - Apply HTML sanitization and CSP headers.

---

## 8. Summary of Strategic Value

By executing this RFC, MockMC extends its mission from a mock library to the **definitive, machine-verified knowledge backbone of the Minecraft server development world**.

- **For Plugin Developers**: An indispensable tool for understanding version compatibility and verifying APIs before deployment.
- **For the MockMC Project**: A natural, zero-maintenance documentation pipeline that improves automatically every time upstream publishes a build or MockMC adds an API target.
