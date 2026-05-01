# MockMC Roadmap: The "Engine First" Mission

This document outlines the strategic phases for achieving 100% automated API mirroring and high-fidelity logic simulation.

## Phase 1: Generator Polish & "Smart" Metadata [COMPLETE]
*Focus: Improving the developer experience and reducing legacy noise.*

- [x] **Tighten Deprecation Logic**: Updated `BaseMockGenerator.java` to support signature-based quirk matching and modern Javadoc injection.
- [x] **Quirks Audit**: Populated `quirks.json` with modern guidance for `Art`, `CommandResult`, `LightningStrike`, and `Enchantment`.
- [x] **Refine Suppressions**: Updated generator to apply `@SuppressWarnings` surgically at the class level.
- [x] **Address Build Warnings**: Resolved legacy terminal warnings in `ArtMock`, `OldKeyedEnumMock`, and `EnchantmentMock`.

## Phase 2: Automated Testing Infrastructure [COMPLETE]
*Focus: Mechanical verification and scaling coverage.*

- [x] **Mechanical Sanity Tests**: Extended MetaMiner to generate `BaseTest.java` for all 2,000+ generated mocks.
    - [x] Verify registry registration.
    - [x] Verify safe defaults (empty collections, non-null optionals).
- [x] **Agentic Workflow Protocol**: Defined in `AGENTS_PROTOCOL.md` (Auditor, Architect, Coder roles).
- [x] **Coverage Dashboard**: Implemented `CoverageReportGenerator` reporting **100% API Mirroring** (10,015 methods).

## Phase 3: Architectural Evolution [COMPLETE]
*Focus: Future-proofing and deep-state cloning.*

- [x] **Base64/Binary State Serialization**:
    - [x] Implemented `NbtStateMock` for binary state handling.
    - [x] Integrated NBT support into `EntityMock` for 1-1 vanilla state cloning.
- [x] **Platform Package Separation**:
    - [x] Refactored `generated/` into `server` (Bukkit/Paper) and `proxy` (Velocity/Bungee) subpackages.
    - [x] Eliminated IDE auto-complete pollution across platforms.
- [x] **Consolidated Registry Access**: Refactored `RegistryAccessMock` to strictly follow modern Paper 1.21 patterns with dynamic discovery.

## Phase 4: Functional Depth & UX [IN PROGRESS]
*Focus: Manual implementation of complex logic.*

- [ ] **"Middle Ground" Logic**:
    - [ ] `Warden`: Sound vibration tracking and sonic boom simulation.
    - [ ] `Bee`: Nectar state and hive interaction.
    - [ ] `Trial Spawner`: Cooldown and reward distribution logic.
- [ ] **Advanced Matchers**:
    - [ ] `InventoryContentMatcher`: Deep comparison of item stacks including data components.
    - [ ] `EntityBehaviorMatcher`: Verifying AI state and goal triggers.
- [ ] **Regional Threading**: Enhance `PlayerSimulation` and `ServerMock` to support Folia's regional scheduler assertions.

---

## Technical Debt & Maintenance
- [x] **DCE (Dead Code Elimination)**: Audited manual mocks and removed redundant `UnimplementedOperationException`.
- [x] **Javadoc Standardization**: Applied `@mockmc.version` tag across all touched manual mocks.
- [x] **Spotless Apply**: Formatting standardized across the entire source tree.
