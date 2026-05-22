# Walkthrough: Modernization & Smart Stub Logic

## Mission: "Engine First"

The "Engine First" mission is the core philosophy of MockMC. It ensures that the framework achieves **100% automated API mirrors** of the underlying server software (Paper, Folia, Velocity, etc.) without the manual maintenance burden of traditional mocking frameworks.

---

## 🏗️ The Metaminer Engine

The backbone of MockMC is the `metaminer` sub-project. It uses reflection and JavaPoet to parse the official API JARs and generate a comprehensive set of "Base Mock" interfaces.

### 🚀 Zero-Day Autonomous Generation (Phase 5+)

Starting with the Phase 5 modernization, MockMC no longer relies on external Gradle plugins (like Paperweight) to prepare server JARs. Instead, it features a fully autonomous, build-tool-independent pipeline.

#### 1. JarCracker: Autonomous Unbundling
Paper and Folia distribute "bundler" JARs that extract themselves at runtime. `JarCracker` automates this by:
- Launching the bundler in an isolated temporary directory.
- Extracting the patched server JAR and its entire dependency tree (`libraries/`).
- **Repacking**: Normalizing the JAR's ZIP headers to ensure compatibility with standard remapping tools.

#### 2. StandaloneRemapper: Isolated Remapping
To support modern Minecraft features (Java 25 bytecode), the engine uses a dual-JVM strategy:
- **Isolation**: Remapping via `SpecialSource` is executed in a separate JVM process.
- **Prioritization**: The remapper's classpath is reconstructed to prioritize **ASM 9.8**, ensuring it can process Java 25 classes (Major Version 69).
- **Mojang Mappings**: Automatically downloads and converts official Mojang mappings via `MappingProvider`.

#### 3. Visibility Hardening
The generator now enforces strict accessibility rules. It automatically skips non-public interface methods, preventing internal server "ghost" methods (like `unleash()` in `PaperLeashable`) from leaking into the public API surface and causing compilation mismatches.

---

## 🛠️ The Behavior Layer

Manual mock classes (e.g., `ServerMock`, `PlayerMock`) are strictly **Behavior Mocks**.

### Creating or Modifying a Mock:

1. **Inherit from Generated Base**: Ensure your manual mock implements the generated `*BaseMock` interface.
2. **Override Strictly for Logic**: Only implement methods that require actual state tracking (e.g., `setHealth`).
3. **Delete Redundant Stubs**: If a method only contains `throw new UnimplementedOperationException();`, **delete it**. The generated BaseMock already handles this.
4. **Version Tagging**: Every manual override must include the `@mockmc.version` Javadoc tag.
    ```java
    /**
     * Custom behavior for health tracking.
     * @mockmc.version 1.21-1.0.0
     */
    ```

---

## 🌊 Phase 6: Functional Depth & Scale

As of 1.21.x, MockMC focus has shifted to high-fidelity logic simulation for complex entity types.

### 1. High-Fidelity Entities
- **Warden**: Sound vibration tracking and sonic boom simulation.
- **Bee**: Pollination tracking and nectar states.
- **Trial Spawner**: Cooldown and reward distribution logic.

### 2. Advanced Testing Matchers
- **`InventoryContentMatcher`**: Deep-compare inventories with support for **1.20.5+ Data Components**.
- **`EntityStateMatcher`**: Verify AI goals and custom entity states.

---

## ⚖️ Protocols for Agents (AI/LLM)

Agents must strictly follow the **"Generator-Only" Injunction**:

- **NEVER** edit files in the `generated/` package manually.
- **ALWAYS** modify `BaseMockGenerator.java` if an API method is missing.
- **ALWAYS** run `./gradlew :metaminer:runGenerator` after engine logic changes.
- **ALWAYS** run `./gradlew compileJava` to verify 100% API compliance.
