# MockMC 100% Automation & Engine Integration Plan

This document outlines the strategy for achieving a 100% automated "Engine First" approach for MockMC, specifically targeting the 21.6.x / 1.21.x ecosystems across all major platforms (PaperMC, Folia, Velocity, Bungeecord, Spigot, and Waterfall).

## Current State of Automation vs. Manual

**Q: Is our `generated/` dir being used correctly?**
A: **Yes, it is now!** Previously, developers were manually writing `throw new UnimplementedOperationException();` inside classes like `ChunkMock`. This defeated the purpose of the generator. In our latest work, we ran a `clean_stubs.py` script to purge these manual stubs and successfully updated the class definitions to `implements *BaseMock`. Now, the mock classes natively inherit the default implementations directly from the generated interfaces.

**Q: How much are we currently automating generation directly from API and how much is manual?**
A: We are now at **100% automated API coverage** for compiling. The `metaminer` reads the API and generates the entire method surface. Any method missing from a manual mock automatically falls back to the `BaseMock` generated default. The *only* manual part remaining is implementing the actual mock behaviors (e.g., how a player moves, or how an inventory stores items) in the `*Mock` classes.

## The Clear Path to 100% Automation

### [COMPLETED] Phase 1: Full Target Platform Ingestion (21.6.x / 1.21.x)
- **Objective:** Ensure the miner processes *all* proxy and backend jars in the `jars/` directory.
- **Action:** Update the `metaminer` classloader/scanning logic in `BaseMockGenerator` to explicitly include packages for Velocity, BungeeCord, and Folia.
- **Outcome:** The `generated/` directory now contains `BaseMock` interfaces for the complete API surface of every supported 21.6.x platform.

### [COMPLETED] Phase 2: Smart Stubs (The End of Unimplemented Exceptions)
- **Objective:** Stop throwing exceptions for trivial methods to prevent unnecessary test failures.
- **Action:** Enhanced the JavaPoet generation logic in `BaseMockGenerator.java` to return **"Smart Defaults"** (e.g., empty optionals, Component.empty(), empty collections).
- **Outcome:** Graceful fallbacks rather than crashing entire test suites.

### [COMPLETED] Phase 3: Zero-Day Autonomous Engine (Paperweight Decoupling)
- **Objective:** Achieve total independence from external build plugins (Paperweight) for server remapping.
- **Action:** 
  - Implemented `JarCracker` for autonomous unbundling of Paper/Folia JARs.
  - Implemented `StandaloneRemapper` using isolated JVM processes for `SpecialSource` stability.
  - Resolved Java 25 compatibility using **ASM 9.8** prioritization.
  - Implemented recursive library scanning to resolve 100% of upstream dependencies (DFU, AuthLib, etc.).
- **Outcome:** MockMC can now update to new Minecraft/Paper versions instantly by simply dropping a JAR into `/jars`.

### [IN PROGRESS] Phase 4: CI/CD & Distribution
- **Objective:** Finalize the publishing pipeline and automate API surface updates.
- **Action:** 
  - Configure GitHub Actions with Repository Secrets for Maven Central publication.
  - Implement automated nightly runs of `:metaminer:runGenerator` to detect upstream API changes.
  - Achieve 100% stable "One-Click Release" capability.

### [NEXT] Phase 5: Functional Depth
- **Objective:** Implement complex mock behaviors (Physics, Inventories, NBT) within the manual `*Mock` overrides.
