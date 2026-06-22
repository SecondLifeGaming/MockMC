# MockMC / MockBukkit Paper 26.2 Build #25 Upgrade Handoff

This document details the changes and fixes implemented during the upgrade of MockMC/MockBukkit to Paper 26.2 Build #25.

## Summary of Changes

1. **Paper 26.2 Build #25 Upgrade**:
   - Bumped the Paper version in `build.gradle.kts`, `gradle.properties`, and `metaminer/build.gradle.kts`.
   - Cleared stale caches, regenerated API stubs using `runGenerator`, and restored `en-us.json` translation files.

2. **Core Fixes & API Mirror Alignment**:
   - **Coordinate Key Decoding**: Implemented coordinate decoding within [WorldMock.java](file:///home/west/github.com/westkevin12/MockBukkit/src/main/java/org/mockmc/mockmc/world/WorldMock.java) to resolve block location failures.
   - **Tag Registry Count**: Updated assertions in [TagRegistryTest.java](file:///home/west/github.com/westkevin12/MockBukkit/src/test/java/org/mockmc/mockmc/tags/TagRegistryTest.java) to align with Paper 26.2's 34 damage type tags.
   - **Player Whitelist Mutability**: Fixed `PlayerMock.isWhitelisted()` and `PlayerListMock` whitelist copying logic. It now returns the actual backing set rather than an immutable copy, allowing test setups to mutably change whitelist states.
   - **Recipe Preconditions**: Resolved recipe query failures in [RecipeManager.java](file:///home/west/github.com/westkevin12/MockBukkit/src/main/java/org/mockmc/mockmc/inventory/RecipeManager.java) by aligning preconditions messages.
   - **PotentSulfurStateMock BlockState**: Fully implemented block state mappings and registered it in `BlockStateMockFactory`.
   - **UnsafeValues & ItemStack Serialization**: Enhanced item serialization logic in [ItemStackMock.java](file:///home/west/github.com/westkevin12/MockBukkit/src/main/java/org/mockmc/mockmc/inventory/ItemStackMock.java) and [UnsafeValuesMock.java](file:///home/west/github.com/westkevin12/MockBukkit/src/main/java/org/mockmc/mockmc/util/UnsafeValuesMock.java) to output both `v` and `DataVersion` keys, enabling successful Yaml deserialization in `ItemStackMirrorTest` and full component mapping serialization.
   - **Entity Overrides**: Added `shear()` overload in [SnowmanMock.java](file:///home/west/github.com/westkevin12/MockBukkit/src/main/java/org/mockmc/mockmc/entity/SnowmanMock.java) to properly handle sheep-like shears, and added `setElder()` override in [GuardianMock.java](file:///home/west/github.com/westkevin12/MockBukkit/src/main/java/org/mockmc/mockmc/entity/GuardianMock.java) to throw `UnsupportedOperationException` as expected by tests.
   - **Block Mocking**: Resolved `BlockMock.java` location validation and implemented the new `breakNaturally(ItemStack, boolean)` overload to delegate correctly.

3. **Metaminer Generation Fixes**:
   - **Mockito Non-Reentrancy in TestRegistryAccess**: Completely rewrote [TestRegistryAccess.java](file:///home/west/github.com/westkevin12/MockBukkit/metaminer/src/test/java/org/mockmc/metaminer/TestRegistryAccess.java) to prevent `Mockito.when()` calls nested inside invocation handlers/answers. The stubs now evaluate dynamically using custom Answers, preventing internal `NoSuchElementException` and `NullPointerException` (when mocking `GameRule` and `PotionEffectType` properties).
   - All 2,200 `metaminer` unit tests now pass.

## Verification

- **Metaminer Tests**: 100% Passing (`2,200 tests completed, 0 failed`).
- **Compilation**: Clean compile succeeded for both `main` and `test` source trees (`BUILD SUCCESSFUL`).
- **Warnings & Diagnostics**: All outstanding warnings in `current_problems` (including nullability constraints, override deprecations, cognitive complexity, and Javadoc deficiencies) have been resolved.
- **MockBukkit Test Suite**: **100% PASSING** (`20,960 tests completed, 0 failed, 21 skipped`).

## What Remains

1. **Git Commit & Pull Request**: Commit the warning-resolution refactors and prepare the upgrade pull request for review.

---
*MockMC Upgrade Team*
