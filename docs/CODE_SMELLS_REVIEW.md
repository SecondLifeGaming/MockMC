# SonarCloud Code Smell Review & Action Plan

This document tracks, reviews, and categorizes all SonarCloud code smells identified for **MockMC** (`SecondLifeGaming_MockMC`), distinguishing between issues requiring codebase fixes/modifications versus those that represent intentional architectural choices or upstream Bukkit/Paper contract realities and should be marked safe or suppressed/configured in Sonar.

---

## 📊 Summary by Category

| Category | Description | Count / Scope | Recommended Action |
| :--- | :--- | :--- | :--- |
| **Category 1: Fix / Modify in Codebase** | Real bugs, outdated test assertions, unnecessary type casting, dead commented code, missing constructors, shadowed variables, and formatting/modernization cleanup. | 45+ distinct issue sites | Implement direct code refactoring, cleanup, and modernize syntax. |
| **Category 2: Upstream Mirror / Mock Architecture (Mark Safe or Ignore)** | Deep class hierarchy inheritance (Minecraft entity mocks mirror complex inheritance trees), multi-param block type constructors, and hiding `deserialize` methods (Bukkit `ConfigurationSerializable`). | 70+ entity/state mocks & serialization methods | Mark as **False Positive / Won't Fix** in SonarCloud or configure rule exclusions (`java:S110`, `java:S107`, `java:S2176`). |
| **Category 3: Multi-Version Deprecated API Mirrors (Rule Suppression & Version Tagging)** | Deprecated Bukkit/Paper API methods and historical endpoints mirrored by MockMC triggering `Add missing @deprecated Javadoc tag` and `Do not forget to remove this deprecated code someday`. | 80+ occurrences | **Multi-Version Immutability**: Deprecated methods can **never be removed** if they exist in older supported target JARs. Disable Sonar rule `java:S1133` globally and ensure retained APIs have `@deprecated` tags and `@mockmc.version <version>`. |
| **Category 4: Upstream / Project TODO Markers** | CWE/TODO flags in test plugins or stubbed future behaviors. | 9 occurrences | Review and convert to structured backlog tasks or mark as safe tracking notes. |

---

## 🛠️ Category 1: Code Smells to Fix or Modify in Codebase

These issues represent genuine code health improvements, hygiene fixes, or test maintainability upgrades that can be directly applied to the codebase.

### 1.1 Dead & Commented-Out Code (`unused+`)
*Commented-out code blocks add clutter and reduce readability.*

| File | Line | Sonar Issue | Fix Action |
| :--- | :--- | :--- | :--- |
| `src/.../paper/datacomponent/item/WrittenBookContentMock.java` | L113, L124, L143 | This block of commented-out lines of code should be removed | Remove dead commented code blocks or implement if valid. |
| `src/.../org/mockmc/mockmc/block/state/SkullStateMock.java` | L139, L143 | This block of commented-out lines of code should be removed | Clean up obsolete commented code. |
| `src/.../java/org/mockmc/mockmc/entity/MobMock.java` | L84 | This block of commented-out lines of code should be removed | Clean up dead code. |
| `src/.../java/org/mockmc/mockmc/plugin/PluginManagerMock.java` | L1001 | This block of commented-out lines of code should be removed | Remove commented block. |

---

### 1.2 Shadowed Variables & Confusing Field Names (`certpitfall...`)
*Local variables or loop counters hiding class fields cause subtle bugs and confusion.*

| File | Line | Sonar Issue | Fix Action |
| :--- | :--- | :--- | :--- |
| `src/.../block/data/BlockDataMockRegistry.java` | L135 | Rename `blockData` which hides field at L86 | Rename parameter/local variable to `data` or `targetData`. |
| `src/.../inventory/InventoryMock.java` | L524 | Rename `items` which hides field at L41 | Rename parameter/local to `targetItems` or `contents`. |
| `src/.../plugin/PluginManagerMock.java` | L115, L400 | Rename `parentTemporaryDirectory` hiding field at L94 | Rename local parameter to `tempDir` or `parentDir`. |
| `src/.../scoreboard/TeamMock.java` | L513 | Rename `color` which hides field at L52 | Rename local variable to `targetColor` or `chatColor`. |
| `src/.../structure/StructureMock.java` | L210 | Rename `size` which hides field at L33 | Rename local variable to `dimSize` or `structureSize`. |
| `src/.../block/BlockMockTest.java` | L184, L193 | Rename `location` hiding field at L47 | Rename test local variable to `targetLocation`. |
| `src/.../block/state/BeaconStateMockTest.java` | L345 | Rename `block` hiding field at L42 | Rename local variable to `targetBlock`. |
| `src/.../block/state/CreatureSpawnerStateMockTest.java` | L154, L188 | Rename `spawner` hiding field at L36 | Rename local variable to `spawnerState`. |
| `src/.../block/state/ShulkerBoxStateMockTest.java` | L97 | Rename `block` hiding field at L34 | Rename local variable to `testBlock`. |
| `src/.../entity/AbstractHorseMockTest.java` | L94 | Rename `owner` hiding field at L25 | Rename local variable to `horseOwner`. |
| `src/.../entity/AllayMockTest.java` | L209 | Rename `allayMock` hiding field at L40 | Rename local variable to `allay`. |
| `src/.../entity/EntityMockTest.java` | L385, L507, L527, L540, L554, L563, L573, L582, L592, L762 | Rename `world` hiding field at L78 | Rename local variables to `targetWorld` or `otherWorld`. |
| `src/.../entity/EntityMockTest.java` | L1207 | Rename `entity` hiding field at L80 | Rename local variable to `testEntity`. |
| `src/.../inventory/EntityEquipmentMockTest.java` | L174 | Rename `equipment` hiding field at L32 | Rename local variable to `testEquipment`. |
| `src/.../inventory/PlayerInventoryMockTest.java` | L47 | Rename `inventory` hiding field at L29 | Rename local variable to `playerInv`. |
| `src/.../inventory/meta/ItemMetaMockTest.java` | L1721 | Rename `meta` hiding field in class | Rename local variable to `itemMeta`. |
| `src/.../inventory/meta/ShieldMetaMockTest.java` | L202 | Rename `meta` hiding field at L38 | Rename local variable to `shieldMeta`. |
| `src/.../inventory/meta/TropicalFishBucketMetaMockTest.java` | L46 | Rename `meta` hiding field at L20 | Rename local variable to `fishMeta`. |
| `src/.../plugin/PluginManagerMockTest.java` | L113, L121, L227, L255, L266, L276, L295, L307, L324 | Rename `plugin` hiding field at L56 | Rename local variable to `targetPlugin` or `samplePlugin`. |
| `src/.../world/CoordinateTest.java` | L89, L98 | Rename `coordinate` hiding field at L15 | Rename local variable to `coord`. |

---

### 1.3 Missing Utility Constructors & Access Modifiers (`design+`, `convention+`)
*Utility classes with static methods only should have a private constructor.*

| File | Line | Sonar Issue | Fix Action |
| :--- | :--- | :--- | :--- |
| `metaminer/.../MethodDataScanner.java` | L18 | Add a private constructor to hide implicit public one | Add `private MethodDataScanner() {}`. |
| `metaminer/.../ItemMetaClassFinder.java` | L12 | Add a private constructor to hide implicit public one | Add `private ItemMetaClassFinder() {}`. |
| `src/.../inventory/CraftItemStack.java` | L6 | Add a private constructor to hide implicit public one | Add `private CraftItemStack() {}`. |
| `src/.../util/OldEnumMock.java` | L17 | Change visibility of constructor to `protected` | Change abstract/base enum mock constructor from `public` to `protected`. |
| `src/.../command/brigadier/bukkit/BukkitBrigadierForwardingMapMock.java` | L20 | Make this member `protected` | Adjust access modifier to `protected`. |
| `src/.../enchantments/EnchantmentMockTest.java` | L27 | Reorder modifiers to comply with JLS | Reorder modifiers (e.g. `public static final`). |

---

### 1.4 Unused Method Parameters & Generic Types (`certunused+`, `unused+`)

| File | Line | Sonar Issue | Fix Action |
| :--- | :--- | :--- | :--- |
| `src/.../inventory/EntityEquipmentMock.java` | L99, L105 | Remove unused method parameter `silent` | If required by Bukkit API interface mirror, suppress with `@SuppressWarnings("unused")`; otherwise remove if internal helper. |
| `src/.../entity/LivingEntityMockTest.java` | L347 | Remove unused method parameter `override` | Remove parameter from helper method. |
| `src/.../entity/MockRangedEntity.java` | L12 | `T` is not used in the interface | Remove unused generic type parameter `<T>` from interface if not bound to Bukkit/Paper contract. |

---

### 1.5 Precision & Type Cast Improvements (`certconfusing+`, `clumsyredundant+`)

| File | Line | Sonar Issue | Fix Action |
| :--- | :--- | :--- | :--- |
| `src/.../entity/HumanEntityMock.java` | L541 | Explicitly cast `int` to `float` to document precision loss | Add explicit cast `(float) ...`. |
| `src/.../entity/InteractionMockTest.java` | L36, L37, L51, L52 | Explicitly cast `int` to `float` to document precision loss | Add `(float)` cast or float literal (e.g., `1.0f`). |
| `src/.../entity/PlayerMockTest.java` | L880, L889 | Explicitly cast `int` to `float` to document precision loss | Add `(float)` cast or float literals. |
| `src/.../entity/LootableMinecart.java` | L132, L139, L142 | Remove unnecessary cast to `ServerMock` / `BukkitSchedulerMock` | Delete redundant casts. |
| `src/.../command/brigadier/PaperCommandsMock.java` | L116 | Replace `new HashSet<>(num)` with `HashSet.newHashSet(num)` | Modernize with Java 19+ factory `HashSet.newHashSet(...)`. |
| `src/.../meta/ColorableArmorMetaMock.java` | L54 | Move conditional expression inside operation | Simplify ternaries inside method argument. |
| `src/.../inventory/SerializableMeta.java` | L211 | Merge this `if` statement with enclosing one | Combine nested `if` statements with `&&`. |

---

### 1.6 Modern Java 21/22 Patterns & Idioms (`java22+`, `java14+`, `conventionjava8+`)
*Adopt modern language patterns where applicable.*

| File | Line | Sonar Issue | Fix Action |
| :--- | :--- | :--- | :--- |
| `metaminer/.../MethodDataScanner.java` | L49, L69 | Replace "ignored"/"e" with unnamed pattern `_` | Use `catch (Exception _)` or `(var _)`. |
| `metaminer/.../MethodDataScannerTest.java` | L189 | Replace "e" with unnamed pattern `_` | Use `_`. |
| `src/main/java/org/mockmc/mockmc/BuildParameters.java` | L25 | Replace "e" with unnamed pattern `_` | Use `catch (Exception _)`. |
| `src/.../entity/DisplayMock.java` | L88 | Replace "e" with unnamed pattern `_` | Use `_`. |
| `src/.../entity/data/EntityData.java` | L105 | Replace "e" with unnamed pattern `_` | Use `_`. |
| `src/.../inventory/SerializableMeta.java` | L134, L156 | Replace "e" with unnamed pattern `_` | Use `_`. |
| `src/.../map/MapViewMock.java` | L148 | Replace "ignored" with unnamed pattern `_` | Use `_`. |
| `src/.../plugin/MockMCPluginClassLoaderGroup.java` | L24 | Replace "ignored" with unnamed pattern `_` | Use `_`. |
| `src/.../plugin/PluginManagerMock.java` | L590, L628, L966 | Replace "ignored"/"e" with unnamed pattern `_` | Use `_`. |
| `src/.../tags/TagsMock.java` | L131 | Replace "e" with unnamed pattern `_` | Use `_`. |
| `src/.../command/brigadier/BrigadierUtils.java` | L121 | Use stream instead of this loop | Convert loop to stream pipeline. |
| `src/.../profile/PlayerProfileMock.java` | L261 | Use stream instead of this loop | Convert loop to stream pipeline. |
| `src/.../block/state/BannerStateMock.java` | L203–L248 | Merge previous cases into comma-separated labels | Use modern switch expressions with comma-separated labels: `case A, B, C -> ...`. |
| `src/.../inventory/InventoryFactoryMock.java` | L86 | Merge previous cases using comma-separated labels | Use comma-separated `case A, B:`. |

---

### 1.7 Test Smells & Flaky Patterns (`junittests+`, `bad-practicetests+`)
*Replace sleeps and anti-patterns in tests to avoid flakiness and poor assertion design.*

| File | Line | Sonar Issue | Fix Action |
| :--- | :--- | :--- | :--- |
| `src/.../entity/LootableMinecartMockTest.java` | L57 | Remove `TimeUnit.sleep()` | Replace sleep with scheduler mock time advance (`server.getScheduler().performOneTick()`). |
| `src/.../scheduler/BukkitSchedulerMockTest.java` | L276, L291, L326, L409, L431 | Remove `Thread.sleep()` | Use MockBukkit tick stepping / scheduler stepping instead of real thread sleep. |
| `src/.../block/state/DecoratedPotStateMockTest.java` | L120 | Swap 2 arguments: expected vs actual | Fix `assertEquals(actual, expected)` to `assertEquals(expected, actual)`. |
| `src/.../command/ConsoleCommandSenderMockTest.java` | L184, L191 | Implementation identical to other test | Ensure tests assert distinct conditions or parameterize. |
| `src/.../entity/EntityMockTest.java` | L465, L472 | Implementation identical to lines 405/412 | Ensure tests test different permission/attachment cases. |
| `src/.../inventory/ItemFactoryMockTest.java` | L59 | Reduce number of assertions from 30 to < 25 | Split assertion block into smaller focused test cases or `assertAll`. |
| `src/.../inventory/MenuTypeMockTest.java` | L42 | Replace 3 tests with a single `@ParameterizedTest` | Refactor into JUnit 5 `@ParameterizedTest` with `@EnumSource` or `@ValueSource`. |
| `src/test/java/org/mockmc/mockmc/MockMCTest.java` | L324, L340 | Use `assertDoesNotThrow()` instead of try/catch/fail | Replace try-catch-fail with `assertDoesNotThrow(...)`. |
| `src/.../entity/PlayerMockTest.java` | L778 | Use `assertDoesNotThrow()` instead of try/catch/fail | Replace with `assertDoesNotThrow(...)`. |
| `src/.../entity/ThrownPotionMockTest.java` | L111 | Use `assertThrows()` instead of try/catch/fail | Replace with `assertThrows(Exception.class, () -> ...)`. |
| `src/.../inventory/meta/ItemMetaMockTest.java` | L863 | Use `assertThrows()` instead of try/catch/fail | Replace with `assertThrows(...)`. |
| `src/.../ServerMockTest.java` | L2228, L2255, L2281, L2308, L2334, L2360, L2459, L2482 | Lambda in `assertThrows` has multiple throwing invocations | Extract setup calls out of the `assertThrows` lambda so only the tested call is inside. |
| `src/.../inventory/meta/ShieldMetaMockTest.java` | L149 | Lambda in `assertThrows` has multiple throwing invocations | Extract setup call out of the lambda. |
| `src/.../registry/RegistryMockTest.java` | L53, L90 | Lambda in `assertThrows` has multiple throwing invocations | Extract setup call out of the lambda. |
| `src/.../inventory/ShulkerBoxInventoryMockTest.java` | L18 | Remove redundant visibility modifiers (`public`) | Remove `public` from JUnit 5 test classes/methods. |

---

### 1.8 Cognitive Complexity & Loop Control (`brain-overload+`)

| File | Line | Sonar Issue | Fix Action |
| :--- | :--- | :--- | :--- |
| `src/.../world/damagesource/CombatTrackerMock.java` | L310 | Reduce Cognitive Complexity from 20 to 15 | Decompose complex calculation / combat event handling into private helper methods. |
| `metaminer/.../MethodDataScanner.java` | L29 | Reduce break/continue statements in loop to at most 1 | Refactor filtering loop with stream or helper predicate. |
| `src/.../block/state/BeehiveStateMock.java` | L123 | Reduce break/continue statements in loop to at most 1 | Simplify loop conditions. |
| `src/.../map/MapCanvasMock.java` | L141, L158 | Reduce break/continue; do not assign to loop counter inside loop | Refactor into `while` loop or step function rather than mutating `for` index inside body. |

---

## 🛡️ Category 2: MockMC Architecture Realities (Mark Safe / Suppress in Sonar)

These issues are triggered by Sonar rules that are fundamentally incompatible with Minecraft API mirroring and Mock Bukkit architecture. Fixing them in code would violate Bukkit contracts or break compatibility.

### 2.1 Deep Inheritance Tree (`java:S110` - "This class has X parents which is greater than 5 authorized")
**Why this occurs:**
Minecraft entity hierarchies are notoriously deep (e.g., `Object` -> `EntityMock` -> `CommandSenderMock` -> `LivingEntityMock` -> `MobMock` -> `CreatureMock` -> `AgeableMock` -> `AbstractHorseMock` -> `ChestedHorseMock` -> `DonkeyMock` / `MuleMock`).
MockMC **must** mirror Bukkit/Paper's exact class and interface inheritance so `instanceof` checks work in user plugin tests.

**Recommendation:**
- **Action:** Add Sonar property exclusion or suppress `java:S110` on mock entity packages.
- **Sonar Configuration:**
  ```properties
  # In build.gradle.kts sonar block:
  property("sonar.issue.ignore.multicriteria", "e1")
  property("sonar.issue.ignore.multicriteria.e1.ruleKey", "java:S110")
  property("sonar.issue.ignore.multicriteria.e1.resourceKey", "**/entity/**/*,**/block/state/**/*")
  ```
- **Affected Classes (All Safe / Won't Fix):**
  - All ~50 entity mocks (`DonkeyMock` (10), `MuleMock` (10), `LlamaMock` (10), `TraderLlamaMock` (11), `EvokerMock` (10), `IllusionerMock` (10), `CamelHuskMock` (10), `CamelMock` (9), `CatMock` (9), `HorseMock` (9), `PillagerMock` (9), `WolfMock` (9), `VindicatorMock` (9), `ZombieNautilusMock` (9), `ZombieHorseMock` (9), `SkeletonHorseMock` (9), `ChestedHorseMock` (9), `NautilusMock` (9), `CowMock` (9), `ArmadilloMock` (8), `AxolotlMock` (8), `BoggedMock` (8), `CaveSpiderMock` (8), `ChickenMock` (8), `CodMock` (8), `DrownedMock` (8), `ElderGuardianMock` (8), `EnderDragonMock` (8), `FoxMock` (8), `FrogMock` (8), `GlowSquidMock` (8), `GoatMock` (8), `HappyGhastMock` (8), `HoglinMock` (8), `HuskMock` (8), `IllagerMock` (8), `OcelotMock` (8), `ParchedMock` (8), `ParrotMock` (8), `PigMock` (8), `PigZombieMock` (8), `PiglinBruteMock` (8), `PiglinMock` (8), `PolarBearMock` (8), `RabbitMock` (8), `RavagerMock` (8), `SalmonMock` (8), `SheepMock` (8), `SkeletonMock` (8), `StrayMock` (8), `StriderMock` (8), `TameableAnimalMock` (8), `TropicalFishMock` (8), `TurtleMock` (8), `VillagerMock` (8), `WitchMock` (8), `WitherMock` (8), `WitherSkeletonMock` (8), `ZombieVillagerMock` (8), `AbstractCowMock` (8), `AbstractHorseMock` (8), `AbstractNautilusMock` (8), `AbstractBossMock` (7), `AbstractSkeletonMock` (7), `AbstractVillagerMock` (7), `BlazeMock` (7), `BreezeMock` (7), `BreezeWindChargeMock` (7), `CopperGolemMock` (7), `CreakingMock` (7), `CreeperMock` (7), `DolphinMock` (7), `EndermanMock` (7), `EndermiteMock` (7), `GiantMock` (7), `IronGolemMock` (7), `LargeFireballMock` (7), `LingeringPotionMock` (7), `PiglinAbstractMock` (7), `PufferFishMock` (7), `RaiderMock` (7), `SchoolableFishMock` (7), `ShulkerMock` (7), `SilverfishMock` (7), `SmallFireballMock` (7), `SpiderMock` (7), `SplashPotionMock` (7), `TadpoleMock` (7), `VexMock` (7), `WardenMock` (7), `WindChargeMock` (7), `ZoglinMock` (7), `ZombieMock` (7), and Boat variants).
  - Block states (`BlastFurnaceStateMock` (6), `FurnaceStateMock` (6), `SmokerStateMock` (6)).

---

### 2.2 Static Method Hiding in ItemMeta (`java:S2176` - "Rename this method; it hides 'deserialize' in ItemMetaMock")
**Why this occurs:**
Bukkit `ConfigurationSerializable` contract specifies a static `deserialize(Map<String, Object>)` method on each serializable class. Subclasses of `ItemMetaMock` (`ArmorMetaMock`, `BannerMetaMock`, `BundleMetaMock`, `FireworkMetaMock`, `PotionMetaMock`, etc.) each define their own static `deserialize` method so Bukkit's `ConfigurationSerialization` can deserialize them by reflection.

**Recommendation:**
- **Action:** Mark as **False Positive / Won't Fix** or suppress `java:S2176` on `org.mockmc.mockmc.inventory.meta.*`.
- **Reason:** Bukkit serialization reflection requires static `deserialize` with this exact signature on each meta class. Renaming would break Bukkit config deserialization.

---

### 2.3 Constructor Parameter Count (`java:S107` - "Constructor has 14 parameters, which is greater than 7 authorized")
**Why this occurs:**
`BlockTypeMock` constructor (L58) accepts all the Minecraft block properties (hardness, blast resistance, sound groups, light emission, loot tables, etc.).

**Recommendation:**
- **Action:** Mark as **Won't Fix** or refactor into a Builder if beneficial for internal construction.
- If internal, a `BlockTypeMock.Builder` is clean, but suppressing `java:S107` on registry mock data objects is also standard.

---

### 2.4 Java 25 Super/This Call Pre-Validation (`java25suspicious+`)
*Sonar warning: "Move this validation logic before the super() or this() call."*
- `ShelfStateMock.java:30`
- `ItemMock.java:43`
- `ChestInventoryMock.java:30, 31`
- `ScheduledTask.java:53, 74`

**Recommendation:**
- In older Java versions, statements before `super(...)` were prohibited (JLS 8.8.7.1). Java 22+ (JEP 447 / JEP 482) permits statements before `super()`.
- If the project target bytecode and toolchain support statements before `super()`, these can be reorganized cleanly; otherwise mark safe / as intended until language level migration is finalized.

---

### 2.5 Overriding `equals` in Block State/Data Mocks (`suspicious+`)
*Sonar warning: "Override the equals method in this class."*
- `BrewingStandDataMock.java:15`
- `LeavesDataMock.java:11`
- `BeehiveStateMock.java:20`
- `CampfireStateMock.java:20`
- `ChiseledBookshelfStateMock.java:15`
- `CommandBlockStateMock.java:17`
- `CrafterStateMock.java:16`
- `CreatureSpawnerStateMock.java:18`
- `EnchantingTableStateMock.java:16`
- `EndGatewayStateMock.java:18`
- `EnderChestStateMock.java:13`
- `JukeboxStateMock.java:16`
- `SculkSensorStateMock.java:14`
- `SculkShriekerStateMock.java:13`
- `ShelfStateMock.java:17`
- `SkullStateMock.java:26`

**Recommendation:**
- Check if their parent (`BlockStateMock` / `BlockDataMock`) implements `equals` based on block position, world, or properties.
- In Minecraft/Bukkit, two `BlockState` instances at different positions or different states are compared. If the subclass introduces unique fields (e.g., items stored inside `CrafterStateMock` or honey level in `BeehiveStateMock`), overriding `equals` and `hashCode` ensures accurate test assertions.
- **Action:** Implement `equals` and `hashCode` where state holds unique fields; if identity or parent equality is deliberate, annotate or mark safe.

---

## 📜 Category 3: Deprecated Upstream API Mirrors & Multi-Version Persistence (Rule Suppression & Version Tagging)

MockMC is a multi-version API mocking platform supporting an entire matrix of platform tiers and historical Minecraft/Paper/Folia versions. 

> [!IMPORTANT]
> **Multi-Version Immutability Principle**: As a multi-version mocking platform, **we can never remove deprecated methods if they exist in older supported target JARs or upstream versions**. 
> Removing a deprecated method would immediately break consumers running test suites targeted against earlier API versions where that method was either actively used or deprecated but present. Even methods removed in modern Paper must be retained with `@Deprecated(forRemoval = true, since = "...")` and `@mockmc.version <version>` fallbacks (per [`AGENTS.md`](file:///home/west/github.com/westkevin12/MockBukkit/AGENTS.md) and [`docs/MULTI_VERSION_ARCHITECTURE.md`](file:///home/west/github.com/westkevin12/MockBukkit/docs/MULTI_VERSION_ARCHITECTURE.md)).

### 3.1 "Do not forget to remove this deprecated code someday" (`java:S1133`)
Sonar warns on all `@Deprecated` methods with: *"Do not forget to remove this deprecated code someday"*.
- **Why this rule is fundamentally invalid for MockMC**: In a standard standalone application, deprecation implies eventual deletion. In MockMC, **deprecated methods are permanent API fixtures** required to maintain binary and source compatibility across the multi-version testing matrix.
- **Action**: **Disable / Globally suppress rule `java:S1133`** in SonarCloud and the Gradle sonar analysis configuration. Every one of the 60+ occurrences is an architectural false positive.

---

### 3.2 "Add the missing @deprecated Javadoc tag" (`bad-practiceobsolete+`)
Sonar requires every `@Deprecated` annotation to have an accompanying `@deprecated <reason>` Javadoc tag explaining why it's deprecated and what to use instead.

**Recommendation:**
- For methods deprecated upstream by Bukkit/Paper, ensure the `@deprecated` Javadoc tag points to the upstream deprecation reason or modern alternative, complemented by `@mockmc.version <version>`.
- For methods removed in modern Paper but retained in MockMC for older versions, tag with `@Deprecated(forRemoval = true, since = "...")` and `@mockmc.version <version>` to explicitly document their historical compatibility window.


---

## 📝 Category 4: Project TODOs & Placeholders

| File | Line | Issue | Recommendation |
| :--- | :--- | :--- | :--- |
| `src/.../paper/datacomponent/item/WrittenBookContentMock.java` | L113 | Complete task associated to TODO | Implement written book content data component stubs or track in `TODO.md`. |
| `src/.../block/BlockTypeMock.java` | L169 | Complete task associated to TODO | Implement block type properties. |
| `src/.../block/state/CampfireStateMock.java` | L175 | Complete task associated to TODO | Campfire cooking time / item state. |
| `src/.../entity/MobMock.java` | L83 | Complete task associated to TODO | Mob AI / target goals. |
| `src/.../entity/PaintingMock.java` | L76 | Complete task associated to TODO | Painting variant / art type mocking. |
| `src/.../entity/VillagerMock.java` | L186, L260 | Complete task associated to TODO | Villager restock / trading levels. |
| `src/.../entity/WanderingTraderMock.java` | L102 | Complete task associated to TODO | Wandering trader despawn delays. |
| `src/.../plugin/MockMCURLClassLoader.java` | L61 | Complete task associated to TODO | Plugin class loader isolation. |
| `src/.../plugin/PluginManagerMock.java` | L1000 | Complete task associated to TODO | Plugin dependency graph loading. |
| `src/.../entity/HumanEntityMockTest.java` | L104 | Complete task associated to TODO | Test case expansion. |

---

## 🎯 Implementation & Configuration Roadmap

1. **SonarCloud / Gradle Configuration (`build.gradle.kts`)**:
   - Add ignore rules for `java:S110` (inheritance depth) and `java:S1133` (removal of deprecated code) which are non-applicable to mock mirrors.
   - Ignore `java:S2176` (method hiding) on `org.mockmc.mockmc.inventory.meta.*` for `deserialize`.
2. **Batch Code Cleanup (Category 1)**:
   - Clean up commented-out code blocks.
   - Fix shadowed variable names in tests and production code.
   - Add private constructors to static utility classes.
   - Modernize test assertions (`assertThrows`, `assertDoesNotThrow`, remove sleeps).
3. **Deprecation Standard (Category 3)**:
   - Ensure all `@Deprecated` methods have `@deprecated` Javadoc tags.
