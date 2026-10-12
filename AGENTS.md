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

| Context Trigger           | Document to Read                               | Description                                          |
| :------------------------ | :--------------------------------------------- | :--------------------------------------------------- |
| **New Implementation**    | [`docs/ARCHITECTURE.md`](docs/ARCHITECTURE.md) | Engine pipeline, smart stub logic, JAR requirements. |
| **Multi-Version Pipeline**| [`docs/MULTI_VERSION_ARCHITECTURE.md`](docs/MULTI_VERSION_ARCHITECTURE.md) | Canonical Paper, artifact pinning, extension bounds. |
| **Version Matrix**        | [`docs/VERSION_SUPPORT.md`](docs/VERSION_SUPPORT.md) | Supported platform tiers and consumer coordinates.  |
| **Coverage & Auditing**   | [`docs/GENERATOR_AND_COVERAGE.md`](docs/GENERATOR_AND_COVERAGE.md) | Structural accounting metrics and exclusions.       |
| **Logic/Registration**    | [`docs/ARCHITECTURE.md`](docs/ARCHITECTURE.md) | Metaminer internals and generated mock layout.       |
| **Task Priority**         | [`TODO.md`](TODO.md)                           | Current phases (Phase 6 Functional Depth).           |
| **Missing/Incorrect API** | `metaminer/.../BaseMockGenerator.java`         | Source of truth for generation.                      |
| **Multi-Agent Workflow**  | [`AGENTS_PROTOCOL.md`](AGENTS_PROTOCOL.md)     | Auditor / Architect / Coder role definitions.        |

---

## ⚖️ Rules of Engagement

### 1. The "Generator-Only" Injunction

> [!CAUTION]
> **NEVER** modify files in `org.mockmc.mockmc.generated.*` directly.
> **IF** an API method is missing:
>
> 1.  Modify `BaseMockGenerator.java`.
> 2.  Run `./gradlew :metaminer:runGenerator`.

### 2. Dead Code Elimination

- **Delete** any manual method only containing `throw new UnimplementedOperationException();`.
- **CRITICAL**: Redundant stubs are considered blockers for PR approval.

### 3. Javadoc & Versioning

- Use `@mockmc.version <version>` for all manual behavior implementations.

### 4. Multi-Version & Platform Tiering

- **Primary Source of Truth**: The latest Paper release defines the canonical Bukkit and Paper API surface.
- **Platform Extensions**: Folia, Velocity, and Waterfall supply platform-exclusive namespaces (e.g. `io.papermc.paper.threadedregions.*`). Never allow older Folia versions to override or downgrade modern Paper interfaces.
- **Deprecated Fallbacks**: Methods removed between versions must be tagged with `@Deprecated(forRemoval = true, since = "...")` and `@mockmc.version <version>`.

### 5. Mandatory Tooling Sequence

Before committing:

1.  `./gradlew :metaminer:runGenerator` (if engine/logic changed).
2.  `./gradlew spotlessApply`.
3.  `./gradlew compileJava` (100% API Verification).
