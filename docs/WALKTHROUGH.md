# Walkthrough: Modernization & Smart Stub Logic

## Mission: "Engine First"

The "Engine First" mission is the core philosophy of MockMC. It ensures that the framework achieves **100% automated API mirrors** of the underlying server software (Paper, Folia, Velocity, etc.) without the manual maintenance burden of traditional mocking frameworks.

---

## 🏗️ The Metaminer Engine

The backbone of MockMC is the `metaminer` sub-project. It uses reflection and JavaPoet to parse the official API JARs located in the `jars/` directory and generate a comprehensive set of "Base Mock" interfaces.

### 1. Automated Surface Generation

When you run `./gradlew :metaminer:runGenerator`, the engine:

1. Scans all interfaces in the target JAR.
2. Generates corresponding `*BaseMock` interfaces in `src/main/java/org/mockmc/mockmc/generated/`.
3. Implements **Smart Stub Logic** for every method, ensuring the code always compiles and returns safe default values.

### 2. Smart Stub Defaults:

- **Primitives**: Returns `0`, `false`, or `""`.
- **Collections**: Returns `Collections.emptyList()`, `Collections.emptySet()`, or `emptyMap()`.
- **Optionals**: Returns `Optional.empty()`.
- **Adventure Components**: Returns `Component.empty()`.
- **Special Types**: Returns default MockMC objects (e.g., `NamespacedKey.minecraft("mock")`, an empty `ItemStack`, or a 0,0,0 `Location`).

---

## 🛠️ The Behavior Layer

Manual mock classes (e.g., `ServerMock`, `PlayerMock`) are now strictly **Behavior Mocks**. They no longer need to implement hundreds of boilerplate methods just to satisfy the compiler.

### Creating or Modifying a Mock:

1. **Inherit from Generated Base**: Ensure your manual mock implements the generated `*BaseMock` interface.
    ```java
    public class PlayerMock extends LivingEntityMock implements PlayerBaseMock { ... }
    ```
2. **Override Strictly for Logic**: Only implement methods that require actual state tracking or logic (e.g., `setHealth`, `sendMessage`).
3. **Delete Redundant Stubs**: If you find a method that only contains `throw new UnimplementedOperationException();`, **delete it**. The generated BaseMock already handles this with a safe Smart Stub.
4. **Version Tagging**: Every manual override must include the `@mockmc.version` Javadoc tag to track when the behavior was implemented.
    ```java
    /**
     * Custom behavior for health tracking.
     * @mockmc.version 1.21-1.0.0
     */
    @Override
    public void setHealth(double health) { ... }
    ```

---

## 🌊 Phase 4: Functional Depth (1.21.1+)

As of 1.21.1, MockMC has moved from "Safe Defaults" to **"Correct Behavior"** for the most complex entity and block types.

### 1. High-Fidelity Entities
- **Warden**: Full anger management, disturbance history, and sonic boom mechanics.
- **Bee**: Pollination tracking (ticks/crops) and nectar states.
- **Trial Spawner**: Complete support for Normal/Ominous configurations, player tracking, and reward table integration.
- **Sniffer**: Digging behavior, explored location tracking, and state management.
- **Armadillo**: Rolling/Searing state transitions.
- **Wolf**: Support for all new 1.20.5 variants.

### 2. Advanced Testing Matchers
We have introduced fluent Hamcrest-style matchers to simplify complex assertions:
- **`InventoryContentMatcher`**: Deep-compare inventories with support for **1.20.5+ Data Components**.
- **`EntityStateMatcher`**: Verify AI goals, target selection, and custom entity states (e.g., Warden anger levels).

### 3. Stability & Performance
- **Registry Deadlock Resolution**: Refactored `RegistryAccessMock` to ensure safe, deadlock-free initialization of 1.21 tags and registries.
- **Data Preservation**: Fixed `ItemStackMock` to ensure Data Components are preserved during all cloning and inventory operations.

---

## 🌍 Organization & Distribution

- **Namespace**: `org.mockmc.mockmc` (Internal Java package)
- **Distribution Group**: `io.github.secondlifegaming` (Maven Group ID)
- **Primary Remote**: `https://github.com/SecondLifeGaming/MockMC`

---

## ⚖️ Protocols for Agents (AI/LLM)

Agents must strictly follow the **"Generator-Only" Injunction**:

- **NEVER** edit files in the `generated/` package manually.
- **ALWAYS** modify `BaseMockGenerator.java` if an API method is missing or a return type is incorrect.
- **ALWAYS** run `./gradlew compileJava` after any change to verify 100% API compliance.

> [!IMPORTANT]
> The presence of `UnimplementedOperationException` in a manual mock is considered a blocker for release. If the engine can provide a safe default, the manual mock should stay clean.
