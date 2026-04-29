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

### [COMPLETED] Phase 3: Test Suite Modernization & Branding
- **Objective:** Fix remaining test compilation errors and unify the project under the MockMC brand.
- **Outcome:** 
  - All tests compile and pass against the 1.21.x API.
  - Namespace transitioned to `org.mockmc.mockmc`.
  - Group ID transitioned to `io.github.secondlifegaming` for Maven Central.

### [IN PROGRESS] Phase 4: CI/CD & Distribution
- **Objective:** Finalize the publishing pipeline and automate API surface updates.
- **Action:** 
  - Configure GitHub Actions with Repository Secrets for Maven Central publication.
  - Implement automated nightly runs of `:metaminer:runGenerator` to detect upstream API changes.
  - Achieve 100% stable "One-Click Release" capability.
