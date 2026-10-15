# Compatibility Diff Viewer

Inspect binary and source breaking changes computed by `VersionDiffEngine` between platform versions.

## Current Comparison: `Paper 26.2` \u2192 `Paper 26.3`

| Impact Category | Detected Count | Description |
| :--- | :--- | :--- |
| **Binary Incompatible** | 238 | Dropped classes, altered JVM descriptors, or interface methods without defaults. |
| **Source Incompatible** | 0 | Ambiguous compile-time overloads or newly added checked exceptions. |
| **Compatible Additions** | 169 | New classes, added default interface methods, or new class methods. |
| **Deprecations** | 0 | Existing APIs marked with `@Deprecated`. |

---

## Notable Highlights

### Dropped Legacy Text Methods
Upstream Paper 26.3 removed legacy String-based title and messaging APIs in favor of Kyori Adventure components:
- `org.bukkit.entity.Player#sendTitle(Ljava/lang/String;Ljava/lang/String;)V`
  - *Superseded by*: `org.bukkit.entity.Player#sendTitle(Lnet/kyori/adventure/title/Title;)V`
  - *MockMC Handling*: Retained as `@Deprecated` historical fallback stub.

### Added Component Data Models
- `io.papermc.paper.datacomponent.item.PaperMobVisibility`
- `io.papermc.paper.datacomponent.item.PaperSignText`
- `io.papermc.paper.datacomponent.item.PaperVillagerFood`
