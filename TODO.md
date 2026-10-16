# MockMC Roadmap: The "Engine First" Mission

This document outlines the strategic phases for achieving 100% automated API mirroring and high-fidelity logic simulation.

## 🚀 Phase 6: Multi-Version Expansion & Functional Depth (CURRENT)

_Focus: Validating the multi-version architecture (Paper 26.3 + Folia 26.2 proof of concept) and expanding behavioral mock depth._

### 1. Architectural Validation (26.2 - 26.3 Multi-Version Contract)

- [x] **Canonical Tier Establishment**: Paper 26.3 established as the primary source of truth for Bukkit/Paper API surface.
- [x] **Platform Scope Restriction**: Folia 26.2 restricted strictly to `io.papermc.paper.threadedregions.*`, preventing downgrade of canonical Paper classes.
- [x] **Autonomous Unbundling & Remapping**: Decoupled from build-time Paperweight; `JarCracker` and `StandaloneRemapper` support Java 25 bytecode.
- [x] **Multi-Backend Test Verification**: Distinct test tasks (`testPaper`, `testFolia`, `testVelocity`, `testWaterfall`, `testAllBackends`) in Gradle.
- [x] **Architectural Documentation**: Published `MULTI_VERSION_ARCHITECTURE.md`, `VERSION_SUPPORT.md`, and `GENERATOR_AND_COVERAGE.md`.

### 2. Behavioral Depth Workstream (Current Sprint)

_Note on API versioning: While the compile API surface targets Paper 26.3, core in-game mechanics and mock state machines originally authored under 1.21.x are progressively audited and upgraded to modern Paper semantics._

- [/] **Targeted Logic Implementation**:
    - [ ] `PaperLeashable`: Implement stateful leash tracking (now that visibility issues are resolved).
    - [ ] `GameRule`: Extend dynamic game rule registration support.
    - [ ] `PluginManager`: Enhance event firing for Paper-specific events.

### 3. Planned Multi-Version Expansion & Automation (Future Milestones)

- [ ] **Automated Historical Fallback Scanning**: Expand MetaMiner to automatically scan older baseline JARs for removed methods and generate `@Deprecated(forRemoval = true, since = "...")` stubs tagged with `@mockmc.version`.
- [ ] **CI Coverage Auditing**: Integrate `COVERAGE.md` verification into CI to fail builds if unaccounted methods > 0.
- [x] **Maven Central Publishing**: CI/CD pipeline configured (`feat/maven-central`); GPG signing via in-memory key, `secrets: inherit` propagation to reusable workflow. Pending successful end-to-end run.

## 🌐 Phase 7: Automated Developer Reference & API Explorer (PLANNED RFC)

_Focus: Turning MockMC's bytecode discovery and diff infrastructure into an automated, version-aware Minecraft plugin developer reference and interactive API explorer. See [`docs/RFC_DEVELOPER_WIKI.md`](docs/RFC_DEVELOPER_WIKI.md)._

### 7.1: Canonical Manifest Schema, Provenance & Scanner Completeness

- [x] **Canonical Manifest Specification & Provenance Contract**: Formalized and hardened [`docs/RFC_MANIFEST_SCHEMA.md`](docs/RFC_MANIFEST_SCHEMA.md) with deterministic serialization rules, per-entry disposition inventory, member-level completeness, and decoupled overlay architecture.
- [x] **Bytecode API Manifest Model**: Implemented strongly typed immutable records (`ApiManifest.java`, `MockmcOverlay.java`).
- [x] **Deterministic Canonical Serializer**: Built `ManifestCanonicalSerializer.java` with UTF-8, LF line endings, 2-space indentation, lexicographical key sorting, and canonical SHA-256 digest computation.
- [x] **Bytecode Scanner with Exhaustive Accounting**: Implemented `BytecodeManifestScanner.java` in `:metaminer` enforcing the accounting triplet (`totalClassesDiscovered == analyzed + excluded + failed`), member-level completeness invariant, and scoped fallback provenance.
- [x] **Manifest Integrity & Repeatability Test Suite**: Built `ManifestIntegrityTest.java` verifying bit-for-bit repeatability, line-ending invariants, and ASM parsing completeness.

### 7.2: Deterministic Pairwise Diff Engine & Compatibility Classifications

- [x] **Descriptor-Level Pairwise Diff Engine**: Implemented `VersionDiffEngine` comparing arbitrary platform versions and tracking JVM descriptors as atomic identity.
- [x] **Binary vs. Source Incompatibility**: Distinguishes binary breaks (`NoSuchMethodError`, removed descriptors, added non-default interface methods), source breaks, and compatible additions.
- [x] **Bridge & Synthetic Method Filtering**: Filters compiler bridge and synthetic methods from public API change summaries.
- [x] **Migration Linkage & Deprecation Tracking**: Tracks `@Deprecated` introduction and correlates removed/added overloads.
- [x] **Machine-Readable Diff Data Model**: Implemented `PairwiseDiff.java` immutable schema representation with unit test coverage (`VersionDiffEngineTest.java`).

### 7.3: Generated COMPAT.md & Per-Platform/Version Reports

- [x] **CompatReportGenerator Refactoring**: Upgraded `CompatReportGenerator` to consume `BytecodeManifestScanner` and `VersionDiffEngine`.
- [x] **Markdown Report Renderer**: Built `CompatMarkdownRenderer` to project structured `PairwiseDiff` into deterministic GitHub-flavored Markdown.
- [x] **Multi-Report Generation**: Emits unified `COMPAT.md` from the canonical JSON data model without ad-hoc string scraping.
- [x] **Deterministic Output Guarantee**: Sorted lexicographical grouping by class and symbol prevents git churn.

### 7.4: Static Documentation Site Shell, Guides & Search

- [x] **Static Doc Site Scaffolding**: Set up GitHub Pages documentation framework in `site/` (VitePress).
- [x] **Core Developer Guides**: Ported and authored core guides (Getting Started, First Test, Multi-Version Testing Recipes, Player Simulation, Architecture, Manifest Specification).
- [x] **Pre-Indexed Static Search**: Integrated client-side local search provider for sub-millisecond offline symbol queries.
- [x] **Site Build Verification**: Clean compilation (`npm run docs:build`) with client/server bundle generation and zero dead links.

### 7.5: Version-Range Explorer & Test Cross-Referencing

- [x] **Derived Range Aggregation**: Implemented `RangeDiffAggregator` to compute arbitrary range views dynamically by folding pairwise diffs without storing $O(N^2)$ manifests (`RangeDiffAggregatorTest.java`).
- [x] **Interactive Range Browser**: Built client-side reactive component (`VersionRangeBrowser.vue`) in `site/` with platform selector, min/max version filters, real-time query filtering, and status badges.
- [x] **Behavioral Test Cross-Referencing**: Linked verified JUnit tests with outcome indicators, explicit test scope caveats, and migration pointers.

### 7.6: CI Validation, Security Hardening & Automatic Publication

- [x] **Pre-Publication Integrity Gates**: CI verification enforcing `:metaminer:test` (disposition completeness, manifest digests, range diff aggregation) and VitePress dead-link validation.
- [x] **Artifact-Trust & Fork Isolation**: GitHub Actions workflow separates `validate-and-build` from `deploy`, restricting deployment credentials strictly to `refs/heads/master`.
- [x] **Content Sanitization & CSP**: Hardened Content Security Policy (`default-src 'self'`, sanitized script/style execution, and no-referrer policy).
- [x] **Automated GitHub Pages Deployment**: Created `.github/workflows/docs.yml` building and publishing the documentation and explorer on `master` pushes.

## ✅ Phase 5: Autonomous Modernization (COMPLETE)

_Focus: Full decoupling from legacy build tools and Java 25 readiness._

- [x] **Paperweight Decoupling**: Removed all reliance on `io.papermc.paperweight` and Gradle-based remapping.
- [x] **Autonomous Remapping Engine**:
    - [x] Implemented `StandaloneRemapper` with ASM 9.8 prioritization for Java 25 support.
    - [x] Integrated `JarCracker` for zero-dependency Paper/Folia unbundling.
    - [x] Automatic Mojang mapping resolution via `MappingProvider`.
- [x] **Visibility Hardening**: Refactored `BaseMockGenerator` to skip non-public interface methods (preventing ghost method leaks like `unleash()`).
- [x] **100% API Mirroring**: Verified clean compilation against Paper 26.2 Build 25.

---

## 🏛️ Completed Phases Archive

<details>
<summary>Phase 4: Functional Depth & UX</summary>

- [x] **"Middle Ground" Logic**:
    - [x] `Warden`: Sound vibration tracking and sonic boom simulation.
    - [x] `Bee`: Nectar state and hive interaction.
    - [x] `Trial Spawner`: Cooldown and reward distribution logic.
- [x] **Advanced Matchers**:
    - [x] `InventoryContentMatcher`: Deep comparison of item stacks including data components.
    - [x] `EntityBehaviorMatcher`: Verifying AI state and goal triggers.
- [x] **Regional Threading**: Enhance `PlayerSimulation` and `ServerMock` to support Folia's regional scheduler assertions.
  </details>

<details>
<summary>Phase 3: Architectural Evolution</summary>

- [x] **Base64/Binary State Serialization**:
    - [x] Implemented `NbtStateMock` for binary state handling.
    - [x] Integrated NBT support into `EntityMock` for 1-1 vanilla state cloning.
- [x] **Platform Package Separation**: - [x] Refactored `generated/` into `server` (Bukkit/Paper) and `proxy` (Velocity/Bungee) subpackages.
  </details>

<details>
<summary>Phase 2: Automated Testing Infrastructure</summary>

- [x] **Mechanical Sanity Tests**: Extended MetaMiner to generate `BaseTest.java` for all 2,000+ mocks.
- [x] **Agentic Workflow Protocol**: Defined in `AGENTS_PROTOCOL.md`.
- [x] **Coverage Dashboard**: Implemented `CoverageReportGenerator`.
  </details>

<details>
<summary>Phase 1: Generator Polish & "Smart" Metadata</summary>

- [x] **Tighten Deprecation Logic**: Signature-based quirk matching.
- [x] **Quirks Audit**: Populated `quirks.json`.
- [x] **Address Build Warnings**: Resolved legacy terminal warnings.
  </details>

---

## Technical Debt & Maintenance

- [x] **DCE (Dead Code Elimination)**: Audited manual mocks and removed redundant `UnimplementedOperationException`.
- [x] **Javadoc Standardization**: Applied `@mockmc.version` tag.
- [x] **Spotless Apply**: Formatting standardized.
