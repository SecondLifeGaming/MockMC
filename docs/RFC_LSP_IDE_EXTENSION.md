# RFC: Native Language Server Protocol (LSP) Engine & IDE Extensions

## 1. Executive Summary

Minecraft plugin developers frequently face runtime binary incompatibilities (`NoSuchMethodError`, `ClassNotFoundException`, `IncompatibleClassChangeError`) when deploying plugins across server versions. While compiling against Paper 26.3 works at build time, calling an API introduced in 26.1 will fail in a production server running 1.20.6 or 1.21.1.

MockMC already maintains the canonical bytecode data model (`ApiManifest`), the descriptor-level pairwise diff engine (`VersionDiffEngine`), and full version matrices across 1.20.6–26.3.

This RFC proposes embedding this intelligence directly into developer IDEs via:

1. **MockMC Language Server Protocol (LSP)**: A lightweight, headless daemon providing real-time diagnostics, hover tooltips, and code action quick-fixes for any LSP-compatible editor.
2. **First-Party IntelliJ IDEA Plugin & VS Code Extension**: Native integrations providing target-version gutter markers, real-time method availability badges, and interactive compatibility checking.

---

## 2. Problem Statement & Motivation

Today, plugin developers discover cross-version API breakage through one of three painful mechanisms:

1. **Production Runtime Crashes**: A server owner runs the plugin on Paper 1.20.6, and a call to a 26.2 method explodes.
2. **Tedious Multi-Project Builds**: Authors maintain complex multi-module Gradle setups with remapped reflection wrappers.
3. **Manual Javadoc / Site Inspection**: Cross-referencing disparate changelogs or external wikis.

Neither javac nor IntelliJ can natively tell a developer: _"You are compiling with Paper 26.3, but your `plugin.yml` / configuration targets servers back to 1.20.6, and this method does not exist on 1.20.6."_

MockMC is uniquely equipped to provide this diagnostic because it already parses and indexes the exact bytecode descriptors for every release in that span.

---

## 3. Architecture & Design

```
+-------------------------------------------------------------+
|             IDEs (IntelliJ IDEA / VS Code / Cursor)         |
+-------------------------------------------------------------+
       |                                              ^
       | LSP JSON-RPC (or native PSI inspection)     | Diagnostics / Hover
       v                                              |
+-------------------------------------------------------------+
|                 MockMC Language Server (LSP)                |
|                                                             |
|  - Workspace Config Reader (reads targetRange, e.g. 1.20.6) |
|  - AST / Bytecode Symbol Resolver                           |
|  - Embedded VersionDiffEngine & ApiManifest Index           |
+-------------------------------------------------------------+
```

### 3.1 Configuration Protocol (`.mockmc.json` or `build.gradle.kts`)

A plugin project declares its intended deployment targets:

```json
{
    "targetRange": {
        "min": "1.20.6",
        "max": "26.3"
    },
    "platforms": ["paper", "folia"]
}
```

Or via Gradle plugin configuration:

```kotlin
mockmc {
    targetVersionRange("1.20.6", "26.3")
}
```

### 3.2 LSP Capabilities

#### A. Diagnostics (`textDocument/publishDiagnostics`)

When a developer writes:

```java
// Method introduced in 26.1; absent in 1.20.6
player.showTitle(title);
```

The LSP publishes an inline warning or error diagnostic:

> ⚠️ **MockMC Compatibility**: `Player#showTitle(Title)` was introduced in **26.1** and is absent in **1.20.6** (target: `1.20.6..26.3`). Calling this will throw `NoSuchMethodError` on older servers.

#### B. Hover Tooltips (`textDocument/hover`)

Hovering over any Bukkit, Paper, or Folia symbol displays its availability timeline:

```
org.bukkit.entity.Player#sendActionBar(Component)
------------------------------------------------
Lifecycle: STABLE across 1.20.6 - 26.3
Platform:  Canonical Bukkit / Paper
Verified:  PlayerSimulationTest#testActionBar (PASS)
```

#### C. Code Actions & Quick-Fixes (`textDocument/codeAction`)

When calling a deprecated or removed method:

- Automatic replacement with modern Adventure component equivalents.
- Conditional version guard generation:
    ```java
    if (ServerCompat.isAtLeast("26.1")) {
        player.showTitle(title);
    } else {
        // legacy fallback
    }
    ```

---

## 4. IDE Implementations

### 4.1 IntelliJ IDEA Plugin (`mockmc-intellij`)

- Implemented as an IntelliJ Inspection Provider using IntelliJ PSI for sub-millisecond, zero-overhead symbol resolution.
- Project Tool Window: "MockMC Version Compatibility" tab showing all binary breaking call sites across the codebase.
- Gutter icons indicating Folia regional thread constraints (e.g. warning if scheduling on global scheduler in Folia context).

### 4.2 Visual Studio Code Extension (`vscode-mockmc`)

- Bundles the MockMC LSP binary using Eclipse LSP4J.
- Integrates seamlessly with Red Hat Java Language Support (`vscode-java`).

---

## 5. Technical Considerations & Edge Case Mitigations

### 5.1 Classpath & Type Resolution in LSP vs. IntelliJ
- **IntelliJ PSI Integration**: IntelliJ natively maintains an AST and symbol index across all project dependencies and JDK binaries. The IntelliJ plugin directly queries PSI method call references (`PsiMethodCallExpression`) to retrieve fully qualified class names and parameter descriptors with zero ambiguity and sub-millisecond resolution.
- **VS Code & LSP Daemon**: Standard text-based LSP lacks classpath awareness. To accurately match method overloads without misidentifying signatures, the MockMC LSP coordinates with `vscode-java` (Eclipse JDT Language Server) via a lightweight bridge plugin or uses a fast AST parser (e.g. JavaParser/Tree-sitter) backed by Gradle/Maven dependency caches (`pom.xml` / `build.gradle.kts` classpath resolution).

### 5.2 Performance & Scale Invariants
- In large multi-module repositories containing hundreds of classes, `textDocument/publishDiagnostics` must not cause typing latency.
- Manifests and diff lookups are pre-indexed into an in-memory prefix trie and hash map (`O(1)` symbol and descriptor lookups).
- Diagnostic publishing is debounced (e.g. 200ms after last keystroke) and scoped strictly to active open buffers, deferring whole-project scans to explicit project builds or background tasks.

### 5.3 Folia Concurrency & Regional Thread Gutter Markers
- Regional threading violations represent the single most common cause of runtime exceptions in Folia environments.
- The inspection engine flags:
    1. Direct entity mutations or block placement calls inside global scheduler callbacks (`getGlobalRegionScheduler().run(...)`).
    2. Missing entity coordinate context when executing tasks on `RegionScheduler`.
    3. Gutter icons indicating when an enclosing code block is guaranteed regional or asynchronous.

---

## 6. Implementation Roadmap

1. **Phase 8.1.1 (Engine Decoupling)**: Package `metaminer` manifest index and `VersionDiffEngine` into a standalone, lightweight JAR (`mockmc-compat-engine.jar`) with zero external ASM dependencies.
2. **Phase 8.1.2 (LSP Daemon & Type Resolution)**: Build standard JSON-RPC LSP daemon with `textDocument/publishDiagnostics`, `textDocument/hover`, and Eclipse JDT.LS bridge.
3. **Phase 8.1.3 (VS Code Extension)**: Publish extension to Visual Studio Marketplace and Open VSX.
4. **Phase 8.2.1 (IntelliJ Plugin)**: Author native Kotlin/Java IntelliJ plugin leveraging PSI inspections, version compatibility tool window, and Folia gutter icons; publish to JetBrains Marketplace.
