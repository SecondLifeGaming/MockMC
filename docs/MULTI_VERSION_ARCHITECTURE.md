# Multi-Version Architecture & Pinned Compatibility Contract

This document serves as the authoritative architectural contract governing multi-version and multi-platform API support in MockMC.

---

## Architectural Principles

MockMC achieves automated, type-safe API generation across backend and proxy ecosystems using an **"Engine First"** methodology. Unlike traditional mock frameworks that manually write stubs for every interface, MockMC uses the `metaminer` engine to extract API surfaces directly from target server bytecode and emit `*BaseMock` smart stubs.

To prevent cross-version pollution and maintain strict compilation guarantees, MockMC follows five core rules:

1. **Explicit Artifact Pinning**: MockMC never resolves arbitrary "latest" floating snapshots at generation or build time. All target platform artifacts are explicitly pinned by build number and SHA-256 hash.
2. **Canonical Surface Hierarchy**: The primary Paper target defines the canonical Bukkit and Paper API surface (`org.bukkit.*`, `io.papermc.paper.*`). Platform extensions and historical fallbacks are strictly subordinate.
3. **Namespace-Restricted Extensions**: Platform extensions (Folia, Velocity, Waterfall) provide platform-exclusive APIs only. They never downgrade or overwrite common Bukkit/Paper interfaces.
4. **Differential Historical Fallback**: When methods are removed upstream between versions, legacy signatures are generated with explicit `@Deprecated` and `@mockmc.version` metadata, isolated from the canonical tier.
5. **Separation of API Conformance and Behavioral Simulation**: 100% accounted-for compilation does not imply 100% behavioral fidelity. Smart Stubs provide non-crashing safe defaults; manual mocks provide behavioral state.

---

## Pinned Platform Artifacts (The Current Baseline)

MockMC pins the target platform builds in `jars/manifests/versions.json`:

| Platform      | Pinned Artifact                  | Release Line     | Scope / Authority                                                                          |
| ------------- | -------------------------------- | ---------------- | ------------------------------------------------------------------------------------------ |
| **Paper**     | `paper-26.3-206.jar`             | 26.3 (Primary)   | **Canonical source of truth** for Bukkit and Paper (`org.bukkit.*`, `io.papermc.paper.*`). |
| **Folia**     | `folia-26.3-3.jar`               | 26.3 (Extension) | **Platform-exclusive scope only**: `io.papermc.paper.threadedregions.*`.                   |
| **Velocity**  | `velocity-4.3.0-44.jar`          | 4.3.0            | Proxy API: `com.velocitypowered.api.*`.                                                    |
| **Waterfall** | `waterfall-1.21-615.jar`         | 1.21             | Proxy API: `net.md_5.bungee.*`.                                                            |

### Platform Tiering & Historical Multi-Version Range (1.20.6 – 26.3)

MockMC tracks the full modern component era spanning **1.20.6 through 26.3**.

- **Canonical Authority**: Paper 26.3 defines the primary compile surface.
- **Architectural Isolation**: Platform extensions like Folia supply regional threading namespaces (`io.papermc.paper.threadedregions.*`), while Velocity and Waterfall provide proxy APIs in their own respective packages.
- **Zero Downgrade Invariant**: MetaMiner enforces that platform extension passes and historical scans can never modify or downgrade classes emitted by canonical Paper.
- **Pairwise & Range Diffs**: Bytecode manifests across all pinned versions in `versions.json` feed the diff engine to identify binary and source breaks.

---

## Tiered Scanning Pipeline (`metaminer`)

The generator scans and processes bytecode in three strictly ordered passes:

```
+-----------------------------------------------------------------------+
|  Pass 1: Canonical Paper Pass (paper-26.3-206)                         |
|  - Generates org.bukkit.* and io.papermc.paper.*                      |
|  - Canonical authority for all shared server classes                   |
+-----------------------------------------------------------------------+
                                  |
                                  v
+-----------------------------------------------------------------------+
|  Pass 2: Platform Extension Pass (folia-26.3, velocity, waterfall)     |
|  - Folia: Restricted strictly to io.papermc.paper.threadedregions.*   |
|  - Velocity: Generates proxy/com/velocitypowered/api/**               |
|  - Waterfall: Generates proxy/net/md_5/bungee/**                      |
|  - Conflict rule: Any class existing in Pass 1 is skipped             |
+-----------------------------------------------------------------------+
                                  |
                                  v
+-----------------------------------------------------------------------+
|  Pass 3: Differential Historical Fallback Pass                         |
|  - Scans older baseline JARs for removed methods                       |
|  - Generates non-conflicting deprecated smart stubs:                  |
|    @Deprecated(forRemoval = true, since = "...")                      |
|    @mockmc.version <version>                                          |
+-----------------------------------------------------------------------+
```

---

## Classpath Resolution & Classloader Isolation

In `build.gradle.kts`, dependencies are ordered intentionally to mirror the tiered architecture:

1. `compileOnly("io.papermc.paper:paper-api:26.3...")` and `remapped-paper-*.jar` appear **first** on the compile and test classpaths.
2. Unbundled dependency libraries (`jars/cache/libraries`) are stripped of duplicate `paper-api` and `folia-api` JARs.
3. Fallback APIs (`jars/cache/libraries/dev/folia/folia-api`) are placed **after** Paper API. Any duplicate class is resolved from Paper 26.3; only Folia-exclusive classes resolve from Folia.
4. Conflicting service provider definitions (e.g., Adventure BossBar SPI in Velocity) are programmatically stripped upon download via `JarServiceStripper`.

### Multi-Backend Test Tasks

Testing is partitioned across backend targets:

- `./gradlew testPaper`: Runs unit tests against the Paper 26.3 runtime classpath.
- `./gradlew testFolia`: Prepends the Folia API classpath to verify regional scheduler stubs.
- `./gradlew testVelocity`: Filters strictly to `proxy/com/velocitypowered/**`.
- `./gradlew testWaterfall`: Filters strictly to `proxy/net/md_5/bungee/**`.
- `./gradlew testAllBackends`: Aggregates all four runs for complete verification.

---

## How to Introduce or Bump a Target Version

When adding a new version or updating an existing target:

1. **Update Pinned Declarations**:
    - In `jars/manifests/versions.json`, update the platform entry (`version`, `build`, `url`, and `primary` for Paper). This manifest is the single source of truth: `downloadJars` and the `test<Backend>` tasks derive artifact names from it, and the sha256 embedded in each fill-data URL is verified on download. Do not edit URLs in `build.gradle.kts`.
    - In `gradle.properties`, update `paper.api.version` and `paper.api.full-version`.
2. **Execute JAR Unbundling & Remapping**:
    ```bash
    ./gradlew downloadJars
    ```
3. **Purge Stale Generated Stubs**:
    ```bash
    rm -rf src/main/java/org/mockmc/mockmc/generated/* src/test/java/org/mockmc/mockmc/generated/*
    ```
4. **Run Code Generation**:
    ```bash
    ./gradlew :metaminer:runGenerator
    ```
5. **Format & Verify**:
    ```bash
    ./gradlew spotlessApply
    ./gradlew compileJava compileTestJava
    ./gradlew testAllBackends
    ```
6. **Update Documentation**:
    - Refresh `COVERAGE.md`.
    - Update `docs/VERSION_SUPPORT.md` with the new status tier.
