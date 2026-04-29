# 🤖 Agent System Instructions: MockMC "Engine First" Repository

## 🎯 Core Project Goal

**"Engine First" Mission**: Achieve **100% automated API mirrors** using the `metaminer` engine.

- **Boilerplate Automation**: All interface methods are automatically generated as "Smart Stubs".
- **Zero Manual Stubs**: Manual mock classes must focus strictly on **behavior**, not API compliance.

---

## 🚀 Bootstrap Protocol (Read First)

Before modifying any code, an agent must execute this sequence:

1.  **Check `jars/`**: Verify the target API JARs (Paper, Folia, Velocity, etc.) are present.
2.  **Verify `generated/`**: Check `src/main/java/org/mockmc/mockmc/generated/` to see the current automated surface.
3.  **Run Compilation**: Execute `./gradlew compileJava` to establish a baseline and **log any pre-existing warnings**.

---

## 🗺️ Knowledge Index & Access Triggers

| Context Trigger                   | Document to Read                                                                                                  | Description                                       |
| :-------------------------------- | :---------------------------------------------------------------------------------------------------------------- | :------------------------------------------------ |
| **New Implementation Phase**      | [`docs/WALKTHROUGH.md`](docs/WALKTHROUGH.md)                                                                      | History of the 1.21 modernization and stub logic. |
| **Logic/Registration Changes**    | [`docs/ARCHITECTURE.md`](docs/ARCHITECTURE.md)                                                                    | Metaminer engine and reflection-based mapping.    |
| **Task Priority Verification**    | [`docs/TODO.md`](docs/TODO.md)                                                                                    | Current project phases (e.g., Phase 4 CI/CD).     |
| **Missing/Incorrect API**         | [`metaminer/.../BaseMockGenerator.java`](metaminer/src/main/java/org.mockmc/metaminer/BaseMockGenerator.java) | The source of truth for the generation engine.    |
| **After running `:runGenerator`** | `src/main/java/.../generated/` diff                                                                               | Review the diff to verify the new API surface.    |

---

## 🛠️ Automated Smart Stub Logic

The generator implements these defaults for any method not manually overridden:

- **Void**: No-op (empty body).
- **Adventure API**: Returns `Component.empty()`.
- **Collections**: Returns `Collections.emptyList()`, `emptySet()`, or `emptyMap()`.
- **Optionals**: Returns `Optional.empty()`.
- **Primitives**: Returns `0`, `0.0`, or `false`.
- **Special Types**: Returns `NamespacedKey.minecraft("mock")`, an empty `ItemStack`, or a `Location(0,0,0)`.

---

## ⚖️ Rules of Engagement

### 1. The "Generator-Only" Injunction

> [!CAUTION]
> **NEVER** modify files in `org.mockmc.mockmc.generated.*` directly.
> **IF** an API method is missing:
>
> 1.  Modify `BaseMockGenerator.java`. If modifying the generator, you **must** check `jars/` to ensure it is parsing the correct backend (e.g., Folia vs. Velocity).
> 2.  Run `./gradlew :metaminer:runGenerator`.

### 2. Dead Code Elimination

Proactively delete any manual method that only contains `throw new UnimplementedOperationException();`. These are redundant as they are handled natively by the generated `*BaseMock` classes.
**CRITICAL**: The presence of `UnimplementedOperationException` in a manual mock is considered a **blocker** for PR approval.

### 3. Javadoc & Versioning

All manual behavior implementations must use the custom `@mockmc.version` tag.

```java
/**
 * Custom behavior implementation.
 * @mockmc.version 1.21-1.0.0
 */
```

### 4. Mandatory Tooling Sequence

Before committing, agents **MUST** run:

1.  `./gradlew :metaminer:runGenerator` (If logic changed).
2.  **Address Warnings**: Resolve any IDE warnings or SonarQube linting issues introduced by your changes.
3.  `./gradlew spotlessApply` (Standard Formatting).
4.  `./gradlew compileJava` (100% API Verification).
