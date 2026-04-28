# Architecture Baseline

## Metaminer Engine
The `metaminer` module is the core engine for generating API stubs and data for MockBukkit.
- `BaseMockGenerator.java`: The JavaPoet engine that loads the backend and proxy JARs, parses their interfaces, and generates `*BaseMock` classes.
- `MetaMiner.java`: The Bukkit plugin entry point that orchestrates `DataGenerator` instances, including `BaseMockGenerator`, `MaterialDataGenerator`, `TagDataGenerator`, and others.

## Generated Mocks
The generated mocks are placed under `src/main/java/org/mockbukkit/mockbukkit/generated/`. 
They implement the various upstream interfaces (Paper, Folia, Velocity, Waterfall, Spigot) and provide default implementations for methods to avoid manual stubbing.

## Cross-Platform Registration Logic
Cross-platform APIs are intercepted and registered through `MetaMiner` which uses Java Reflection and ClassLoaders to map types and provide smart-stub behaviors such as:
- `Void`: No-op.
- `net.kyori.adventure.text.Component`: Returns `Component.empty()`.
- Collections: Return `Collections.emptyList()` or `emptySet()`.

## Folia Schedulers & Threading
Folia introduces a unique threading model with its `RegionScheduler`, `GlobalRegionScheduler`, and `AsyncScheduler` under `io.papermc.paper.threadedregions.scheduler`. MockBukkit maps these threading abstractions so tests can seamlessly execute tasks without actual multi-threading overhead:
- Calls to `getServer().getRegionScheduler()` or `getServer().getGlobalRegionScheduler()` are intercepted and routed to mocked implementations.
- Tasks are typically executed synchronously in the test environment to maintain determinism, bypassing the strict region-locking constraints of Folia while preserving API compatibility.

## Proxy/Backend Nuances
The `metaminer` specifically targets multiple proxy and backend environments to guarantee 100% API coverage.
- **Velocity**: `com.velocitypowered.api` is scanned to generate proxy-specific stubs.
- **BungeeCord/Waterfall**: `net.md_5.bungee` surfaces are mapped for legacy proxy support.
- The `jars/` directory must contain the accurate snapshot jars for these platforms before running `:runGenerator`.

## Generator Mechanics
The `BaseMockGenerator` dynamically builds interface hierarchies using Java Reflection:
- **Deduplication**: Redundant interfaces are explicitly pruned. If an interface extends another and both provide conflicting definitions, the engine resolves to the minimal viable provider.
- **Suppressions (Quirks)**: Legacy or problematic API noise is suppressed via `quirks.json` to prevent modern build cycles from throwing deprecation warnings.
