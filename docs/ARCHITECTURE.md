# Architecture

## Metaminer Engine

The `metaminer` sub-project is the autonomous code generation engine for MockMC. It runs as a Bukkit plugin inside a headless Paper server (`StandaloneRunner`) and orchestrates a pipeline of `DataGenerator` instances.

### Core Components

- **`BaseMockGenerator.java`** — The primary JavaPoet engine. Loads remapped backend and proxy JARs, parses their public interface hierarchies (including generic type parameters and deep inheritance chains), and emits `*BaseMock` interfaces with "Smart Stub" default implementations.
- **`BaseTestGenerator.java`** — Generates a `BaseTest.java` per mock class for mechanical sanity coverage across all 2,000+ generated types.
- **`CoverageReportGenerator.java`** — Produces `COVERAGE.md` summarising which manual mocks have behavioral overrides vs. pure generated stubs.
- **`MetaMiner.java`** — The Bukkit plugin entry point. Wires together all `DataGenerator` instances and drives the full generation run via `./gradlew :metaminer:runGenerator`.
- **`StandaloneRunner.java`** — Launches the generation pipeline outside of a live server context for CI use.

### Data Generators

| Generator | Purpose |
|---|---|
| `MaterialDataGenerator` | Material registry data |
| `TagDataGenerator` | Block/item/entity tag sets |
| `RecipeDataGenerator` | Crafting recipe snapshots |
| `KeyedDataGenerator` / `RegistryKeyClassDataGenerator` | Keyed registry class scaffolding |
| `BlockDataGenerator` / `BlockStateGenerator` | Block state test data |
| `ItemStackMetaDataGenerator` / `ItemStackTestDataGenerator` | Item meta and serialization test data |
| `TranslationDataGenerator` | Translation key mappings |
| `WorldConfigurationGenerator` | World config defaults |
| `PotionDataGenerator` | Potion effect data |

---

## Autonomous JAR Pipeline (Phase 5+)

MockMC no longer relies on external build plugins (Paperweight) to prepare server JARs. The entire unbundling and remapping process is self-contained inside the `metaminer/util/` package.

### `JarCracker.java`
Automates unbundling of Paper/Folia "bundler" JARs:
1. Launches the bundler JAR in an isolated temporary directory.
2. Extracts the patched server JAR and its full `libraries/` dependency tree.
3. Repacks the output with normalized ZIP headers for compatibility with `SpecialSource`.

### `StandaloneRemapper.java`
Remaps obfuscated server JARs to Mojang names using an isolated JVM subprocess:
- **Isolation**: Runs `SpecialSource` in a child process to avoid classpath conflicts.
- **ASM 9.8 prioritization**: The child process classpath is ordered so ASM 9.8 takes precedence, enabling Java 25 bytecode (Major Version 69) support.

### `MappingProvider.java`
Automatically downloads and converts official Mojang ProGuard mappings to TSRG format (via `ProGuardToTsrg.java`) for use with `StandaloneRemapper`.

**Bootstrap flow:**
```
jars/*.jar  →  JarCracker  →  jars/cache/remapped-*.jar
                                        ↓
                              BaseMockGenerator
                                        ↓
             src/main/java/org/mockmc/mockmc/generated/**
```

---

## Generated Mocks

Generated output lives under `src/main/java/org/mockmc/mockmc/generated/`, split into two subpackages:

- **`generated/server/`** — Paper, Folia, Spigot/Bukkit API surfaces.
- **`generated/proxy/`** — Velocity (`com.velocitypowered.api`) and BungeeCord/Waterfall (`net.md_5.bungee`) surfaces.

Each generated `*BaseMock` interface provides Smart Stub defaults:

| Return type | Default |
|---|---|
| `void` | No-op |
| `Component` | `Component.empty()` |
| `Collection` / `List` / `Set` | Empty immutable instance |
| `Optional<T>` | `Optional.empty()` |
| `boolean` | `false` |
| Numeric primitives | `0` |
| Objects / interfaces | `null` |

> [!CAUTION]
> Never edit files under `generated/` directly. If a method is wrong or missing, fix `BaseMockGenerator.java` and re-run `./gradlew :metaminer:runGenerator`.

---

## Visibility Hardening

`BaseMockGenerator` enforces strict public-only reflection. Non-public interface methods (e.g., internal server methods like `PaperLeashable.unleash()`) are skipped during generation, preventing "ghost" methods from leaking into the public API surface and causing compilation mismatches.

---

## Folia Schedulers & Threading

Folia's `RegionScheduler`, `GlobalRegionScheduler`, and `AsyncScheduler` (`io.papermc.paper.threadedregions.scheduler`) are mocked to run tasks **synchronously** in the test environment, preserving API compatibility while eliminating actual threading overhead and region-lock constraints.

---

## Proxy/Backend JAR Requirements

The `jars/` directory must contain the correct snapshot JARs before running `:runGenerator`. These are downloaded automatically by the `downloadJars` Gradle task defined in `build.gradle.kts`:

| Platform | Package scanned |
|---|---|
| Paper | `org.bukkit`, `io.papermc.paper` |
| Folia | `io.papermc.paper.threadedregions` |
| Velocity | `com.velocitypowered.api` |
| BungeeCord/Waterfall | `net.md_5.bungee` |
