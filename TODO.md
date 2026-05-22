# MockMC Roadmap: The "Engine First" Mission

This document outlines the strategic phases for achieving 100% automated API mirroring and high-fidelity logic simulation.

## 🚀 Phase 6: Functional Mock Depth & Scale (CURRENT)
*Focus: Scaling manual behavior implementations for the 1.21.x API surface.*

- [/] **Targeted Logic Implementation**:
    - [ ] `PaperLeashable`: Implement stateful leash tracking (now that visibility issues are resolved).
    - [ ] `GameRule`: Extend dynamic game rule registration support.
    - [ ] `PluginManager`: Enhance event firing for Paper-specific events.
- [ ] **Automated Coverage Auditing**: Integrate `COVERAGE.md` into CI to prevent regression in functional depth.

## ✅ Phase 5: Autonomous Modernization (COMPLETE)
*Focus: Full decoupling from legacy build tools and Java 25 readiness.*

- [x] **Paperweight Decoupling**: Removed all reliance on `io.papermc.paperweight` and Gradle-based remapping.
- [x] **Autonomous Remapping Engine**:
    - [x] Implemented `StandaloneRemapper` with ASM 9.8 prioritization for Java 25 support.
    - [x] Integrated `JarCracker` for zero-dependency Paper/Folia unbundling.
    - [x] Automatic Mojang mapping resolution via `MappingProvider`.
- [x] **Visibility Hardening**: Refactored `BaseMockGenerator` to skip non-public interface methods (preventing ghost method leaks like `unleash()`).
- [x] **100% API Mirroring**: Verified clean compilation against Paper 26.1.2 Build 53.

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
- [x] **Platform Package Separation**:
    - [x] Refactored `generated/` into `server` (Bukkit/Paper) and `proxy` (Velocity/Bungee) subpackages.
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
