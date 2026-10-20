# Compatibility Diff Viewer

Inspect binary and structural breaking changes computed by `VersionDiffEngine` across supported Minecraft server releases.

::: tip Descriptor-Level ABI Analysis
The compatibility reports and explorer views are generated directly from upstream server bytecode descriptors and modifiers. While comprehensive for runtime JVM linkage (`NoSuchMethodError`, `NoSuchFieldError`, modifier restrictions), source-level compatibility (such as complex generic boundary adjustments or compiler overload selection) is progressively validated alongside verified JUnit tests.
:::

## Overview: Full Modern Component Era (`1.20.6` → `26.3`)

Across the modern component architecture span (Paper `1.20.6-151` to `26.3-206`), `VersionDiffEngine` tracks over **4,000 API changes**:

| Impact Category | Detected Count | Description |
| :--- | :--- | :--- |
| **Binary Incompatible** | 2,036 | Dropped classes, altered JVM descriptors, or interface methods without defaults. |
| **Source Incompatible** | 0 | Ambiguous compile-time overloads or newly added checked exceptions. |
| **Compatible Additions** | 1,936 | New classes, added default interface methods, or new class methods. |
| **Deprecations** | 6 | Existing APIs marked with `@Deprecated` for scheduled removal. |
| **Total API Changes** | **4,013** | Evaluated public and protected API surface. |

---

## Key Architectural Transitions

### 1. Dropped Legacy Text & Messaging Methods
Upstream Paper progressively eliminated legacy String-based title, chat, and action bar APIs in favor of Kyori Adventure components:
- `org.bukkit.entity.Player#sendTitle(Ljava/lang/String;Ljava/lang/String;)V`
  - *Superseded by*: `org.bukkit.entity.Player#sendTitle(Lnet/kyori/adventure/title/Title;)V`
  - *MockMC Handling*: Retained as `@Deprecated` historical fallback stub.

### 2. Modern Data Component Architecture
With Minecraft 1.20.5/1.20.6 introducing structured item data components (`ItemComponentTypes`), MockMC mirrors the complete modern item component system:
- `io.papermc.paper.datacomponent.item.PaperMobVisibility`
- `io.papermc.paper.datacomponent.item.PaperSignText`
- `io.papermc.paper.datacomponent.item.PaperVillagerFood`

### 3. Dynamic Pairwise Queries
To query arbitrary version pairs (such as `1.21.11` to `26.2` or `26.1.2` to `26.3`), visit the [Interactive API Explorer](/) where diffs are aggregated on the fly.
