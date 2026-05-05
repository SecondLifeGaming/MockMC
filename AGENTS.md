# 🤖 Agent System Instructions: MockMC "Engine First" Repository

## 🎯 Core Project Goal

**"Engine First" Mission**: Achieve **100% automated API mirrors** using the `metaminer` engine.

- **Boilerplate Automation**: All interface methods are automatically generated as "Smart Stubs".
- **Zero Manual Stubs**: Manual mock classes must focus strictly on **behavior**, not API compliance.

---

## 🚀 Bootstrap Protocol

1.  **Check `jars/`**: Ensure target API JARs (Paper, Folia, Velocity) are present.
2.  **Run Compilation**: Execute `./gradlew compileJava` to establish a baseline.

---

## 🗺️ Knowledge Index

| Context Trigger                | Document to Read                                | Description                                |
| :----------------------------- | :---------------------------------------------- | :----------------------------------------- |
| **New Implementation**         | [`docs/WALKTHROUGH.md`](docs/WALKTHROUGH.md)    | 1.21 modernization and stub logic.         |
| **Logic/Registration**         | [`docs/ARCHITECTURE.md`](docs/ARCHITECTURE.md)  | Metaminer engine internals.                |
| **Task Priority**              | [`TODO.md`](TODO.md)                            | Current phases (Phase 6 Functional Depth). |
| **Missing/Incorrect API**      | `metaminer/.../BaseMockGenerator.java`          | Source of truth for generation.            |

---

## ⚖️ Rules of Engagement

### 1. The "Generator-Only" Injunction

> [!CAUTION]
> **NEVER** modify files in `org.mockmc.mockmc.generated.*` directly.
> **IF** an API method is missing:
> 1.  Modify `BaseMockGenerator.java`.
> 2.  Run `./gradlew :metaminer:runGenerator`.

### 2. Dead Code Elimination
- **Delete** any manual method only containing `throw new UnimplementedOperationException();`.
- **CRITICAL**: Redundant stubs are considered blockers for PR approval.

### 3. Javadoc & Versioning
- Use `@mockmc.version <version>` for all manual behavior implementations.

### 4. Mandatory Tooling Sequence
Before committing:
1.  `./gradlew :metaminer:runGenerator` (if engine/logic changed).
2.  `./gradlew spotlessApply`.
3.  `./gradlew compileJava` (100% API Verification).
