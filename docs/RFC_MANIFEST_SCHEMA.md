# RFC: Canonical API Manifest Specification & Provenance Contract (Phase 7.1)

- **Status**: Finalized Specification
- **Target Component**: `metaminer` (Scanner & Generator)
- **Primary Schema References**:
  - Upstream API Manifest: `https://mockmc.org/schemas/api-manifest-v1.json`
  - MockMC Overlay Metadata: `https://mockmc.org/schemas/mockmc-overlay-v1.json`
- **Companion RFC**: [RFC: Automated Minecraft Plugin Developer Reference & Version-Aware API Explorer](RFC_DEVELOPER_WIKI.md)

---

## 1. Executive Summary

This specification establishes the authoritative, finalized contract for **Phase 7.1 (Canonical Manifest Schema, Provenance & Scanner Completeness)**.

To power the version-aware API explorer and the deterministic pairwise compatibility engine (`VersionDiffEngine` in Phase 7.2), `metaminer` must produce byte-accurate, reproducible JSON manifests (`api-manifest-<platform>-<version>.json`) directly from upstream unbundled platform JARs.

This document resolves the final four contract gaps:
1. **Per-Entry Auditable Disposition Inventory**: Every discovered class is assigned an immutable, mutually exclusive disposition (`ANALYZED`, `JUSTIFIED_EXCLUSION`, or `FAILED`).
2. **Member-Level Parse Invariant**: All declared methods and fields are fully enumerated for every analyzed class. Partial parsing constitutes class failure.
3. **Structural Decoupling of Upstream Facts vs. MockMC Overlay**: `api-manifest-*.json` contains **only** upstream bytecode facts. MockMC implementation status and test linkage reside in a distinct, independently versioned `mockmc-overlay-*.json`.
4. **Exact Canonical Byte Serialization**: Defines UTF-8, strict `\n` line endings, single trailing newline, deterministic string escaping, and SHA-256 digest computation over canonical manifest bytes.
5. **Exact Symbol Fallback Provenance**: Scoped fallback tagging is applied only to symbols actually merged from fallback sources, preserving exact origin coordinates.

---

## 2. Canonical Serialization & Digest Specification

To achieve bit-for-bit reproducibility across JVMs, operating systems, and CI runners:

1. **Character Encoding**: Strict UTF-8 without Byte Order Mark (BOM).
2. **Line Terminators**: Strict UNIX LF (`\n`, `0x0A`). No CRLF (`0x0D 0x0A`).
3. **Trailing Newline**: Exactly one terminal `\n` at the end of the file.
4. **Whitespace & Indentation**: Exactly 2 standard ASCII spaces (`0x20 0x20`) per indentation level.
5. **Key Ordering**: All JSON object keys are sorted lexicographically by UTF-8 byte value at all nesting depths.
6. **Collection Ordering**: All array/set values are sorted deterministically:
   - Modifiers/Flags: Alphabetical order (`["ABSTRACT", "INTERFACE", "PUBLIC"]`).
   - Interfaces: Alphabetical by fully-qualified binary name.
   - Annotations: Alphabetical by annotation descriptor/type.
   - Parameters: Ascending by parameter index (`0, 1, 2...`).
7. **Canonical Manifest Digest**:
   $$\text{manifestSha256} = \text{SHA-256}(\text{exact canonical manifest bytes including terminal LF})$$
   Execution envelopes (`*.run.json`) are stored alongside the manifest and are excluded from this content digest.

---

## 3. Upstream API Manifest Schema (`api-manifest-v1.json`)

The manifest captures **pure upstream bytecode facts**. It has zero dependencies on MockMC implementation code or test execution.

### 3.1 Top-Level Contract

```json
{
  "$schema": "https://mockmc.org/schemas/api-manifest-v1.json",
  "schemaVersion": "1.0.0",
  "provenance": { ... },
  "scanAccounting": { ... },
  "classes": { ... }
}
```

---

### 3.2 Section: `provenance`

Cryptographic traceability back to the upstream binary and scanner commit.

```json
{
  "provenance": {
    "platform": "paper",
    "version": "26.3",
    "build": 178,
    "tier": "canonical",
    "sourceJar": {
      "fileName": "paper-26.3-178.jar",
      "sha256": "5dd0c8fb20a96eb7c98f3c4b1c890e99ef6db56fd689a2cf06975a3471fda757",
      "byteLength": 74312896
    },
    "scanner": {
      "version": "metaminer-2.2.0",
      "gitCommit": "a8f93e1b0c44298fc1c149afbf4c8996fb92427a",
      "dirty": false
    },
    "remapping": {
      "source": "mojang+spigot-tsrg",
      "mappingSha256": "e3b0c44298fc1c149afbf4c8996fb92427ae41e4649b934ca495991b7852b855",
      "asmVersion": "ASM9_8"
    }
  }
}
```

- **Dirty Worktree Gate**: For local builds, `scanner.dirty` may be `true`. For publication to documentation sites and release artifacts, `scanner.dirty == false` is mandatory.

---

### 3.3 Section: `scanAccounting` (Exhaustive Disposition Inventory)

To ensure mathematical proof of completeness, `scanAccounting` pairs aggregate counters with a complete, auditable disposition inventory:

$$\text{discoveredEntries} = \text{totalClassesAnalyzed} + \text{totalClassesExcluded} + \text{totalClassesFailed}$$

```json
{
  "scanAccounting": {
    "status": "COMPLETE",
    "scannedPackageRoots": [
      "org.bukkit",
      "io.papermc.paper",
      "com.destroystokyo.paper",
      "co.aikar.timings"
    ],
    "scopedPackagePrefixes": [],
    "totalClassesDiscovered": 2196,
    "totalClassesAnalyzed": 2154,
    "totalClassesExcluded": 42,
    "totalClassesFailed": 0,
    "totalMethodsAnalyzed": 18420,
    "totalFieldsAnalyzed": 1240,
    "justifiedExclusionRules": [
      {
        "pattern": "io.papermc.paper.internal.**",
        "category": "INTERNAL_IMPLEMENTATION",
        "justification": "Internal Paper implementation details not part of the public plugin API contract."
      },
      {
        "pattern": "org.bukkit.craftbukkit.**",
        "category": "NMS_INTERNAL",
        "justification": "CraftBukkit server internals excluded from public API surface."
      }
    ],
    "dispositionInventory": {
      "com.destroystokyo.paper.Title": {
        "disposition": "ANALYZED",
        "methodsCount": 12,
        "fieldsCount": 0
      },
      "io.papermc.paper.internal.ToolUtils": {
        "disposition": "JUSTIFIED_EXCLUSION",
        "matchedRule": "io.papermc.paper.internal.**",
        "category": "INTERNAL_IMPLEMENTATION"
      }
    },
    "failedEntries": []
  }
}
```

#### Member-Level Completeness Invariant:
1. Every class with disposition `ANALYZED` must have **100% of its declared bytecode methods and fields enumerated**.
2. If ASM throws a linkage, format, or missing dependency error during class or member parsing, the entire class is marked with disposition `FAILED`, logged in `failedEntries`, and the manifest status becomes `"INCOMPLETE"`. Partial member population is strictly prohibited.
3. `status == "COMPLETE"` requires `totalClassesFailed == 0` and `failedEntries.length == 0`.

---

### 3.4 Section: `classes`, `methods`, and `fields`

Classes and members represent pure upstream declarations.

```json
{
  "classes": {
    "org.bukkit.entity.Player": {
      "kind": "INTERFACE",
      "accessFlags": ["ABSTRACT", "INTERFACE", "PUBLIC"],
      "signature": "Ljava/lang/Object;Lorg/bukkit/entity/HumanEntity;Lorg/bukkit/conversations/Conversable;",
      "superClass": "java.lang.Object",
      "interfaces": [
        "org.bukkit.conversations.Conversable",
        "org.bukkit.entity.HumanEntity"
      ],
      "annotations": [],
      "methods": {
        "sendTitle(Lnet/kyori/adventure/title/Title;)V": {
          "name": "sendTitle",
          "descriptor": "(Lnet/kyori/adventure/title/Title;)V",
          "signature": null,
          "returnType": "void",
          "parameters": [
            {
              "index": 0,
              "name": "title",
              "type": "net.kyori.adventure.title.Title"
            }
          ],
          "exceptions": [],
          "accessFlags": ["ABSTRACT", "PUBLIC"],
          "isBridge": false,
          "isSynthetic": false,
          "isDefault": false,
          "annotations": [
            {
              "type": "org.jetbrains.annotations.NotNull",
              "values": {}
            }
          ],
          "symbolProvenance": {
            "originPlatform": "paper",
            "originVersion": "26.3",
            "targetPlatform": "paper",
            "targetVersion": "26.3",
            "isPinnedFallback": false
          }
        }
      },
      "fields": {
        "MAX_HEALTH": {
          "name": "MAX_HEALTH",
          "descriptor": "D",
          "signature": null,
          "type": "double",
          "accessFlags": ["FINAL", "PUBLIC", "STATIC"],
          "isSynthetic": false,
          "constantValue": 20.0,
          "annotations": []
        }
      }
    }
  }
}
```

#### Provenance Invariant for Scoped Fallbacks:
- Canonical Paper symbols have `isPinnedFallback: false`.
- Extension symbols merged from Folia 26.2 for `io.papermc.paper.threadedregions.*` receive:
  ```json
  "symbolProvenance": {
    "originPlatform": "folia",
    "originVersion": "26.2",
    "targetPlatform": "paper",
    "targetVersion": "26.3",
    "isPinnedFallback": true,
    "fallbackReason": "Folia 26.3 unreleased; threaded region API sourced from Folia 26.2."
  }
  ```
  Symbols not sourced from fallback are **never** tagged as fallbacks.

---

## 4. MockMC Behavioral Overlay Schema (`mockmc-overlay-v1.json`)

MockMC implementation and test verification data is completely decoupled from the upstream API manifest. It lives in a separate companion overlay file (`mockmc-overlay-<platform>-<version>.json`).

### 4.1 Overlay Schema

```json
{
  "$schema": "https://mockmc.org/schemas/mockmc-overlay-v1.json",
  "schemaVersion": "1.0.0",
  "provenance": {
    "platform": "paper",
    "targetVersion": "26.3",
    "manifestSha256": "3cb4053678da47fbe2b1a108a7324fa567e9f3b1456209b52a78430e38a2e128",
    "mockmcCommit": "a8f93e1b0c44298fc1c149afbf4c8996fb92427a",
    "dirty": false
  },
  "symbols": {
    "org.bukkit.entity.Player#sendTitle(Lnet/kyori/adventure/title/Title;)V": {
      "status": "BEHAVIORAL_OVERRIDE",
      "mockClass": "org.mockmc.mockmc.entity.PlayerMock",
      "isImplemented": true,
      "verification": {
        "hasTest": true,
        "testClass": "org.mockmc.mockmc.entity.PlayerMockTest",
        "testMethod": "testSendTitleAdventure",
        "outcome": "PASSED",
        "scopeNote": "Verifies title packet simulation and title state assertions."
      }
    },
    "org.bukkit.Server#getMotd()Ljava/lang/String;": {
      "status": "SMART_STUB",
      "mockClass": "org.mockmc.mockmc.generated.ServerBaseMock",
      "isImplemented": true,
      "verification": {
        "hasTest": false
      }
    }
  }
}
```

### 4.2 Overlay Benefits:
1. **Independent Caching**: Upstream API manifests never change unless the upstream JAR build changes.
2. **Fast CI Runs**: When a MockMC unit test or mock method is updated, only the overlay updates. The upstream manifest remains untouched.
3. **Pure Upstream API Diffs**: Phase 7.2's `VersionDiffEngine` diffs two upstream manifests without false diff noise from MockMC mock implementations.

---

## 5. Execution Envelope (`*.run.json`)

Run execution metadata (timestamps, JVM properties, scan elapsed time) is saved alongside the manifest in `api-manifest-<platform>-<ver>.run.json`.

```json
{
  "manifestFile": "api-manifest-paper-26.3.json",
  "manifestSha256": "3cb4053678da47fbe2b1a108a7324fa567e9f3b1456209b52a78430e38a2e128",
  "scannedAt": "2026-10-14T22:20:00Z",
  "durationMs": 4210,
  "executionEnvironment": {
    "javaVersion": "25",
    "osName": "Linux",
    "osArch": "amd64"
  }
}
```

---

## 6. Implementation Deliverables (Phase 7.1)

1. **`ManifestDataModel.java`**: Immutable Java records for `ApiManifest`, `ScanAccounting`, `ClassEntry`, `MethodEntry`, `FieldEntry`, `SymbolProvenance`, and `MockmcOverlay`.
2. **`BytecodeManifestScanner.java`**: ASM-based analyzer in `:metaminer` with strict disposition tracking and member-level completeness enforcement.
3. **`ManifestCanonicalSerializer.java`**: Deterministic JSON serializer enforcing UTF-8, LF line endings, 2-space indentation, lexicographical sorting, and SHA-256 calculation.
4. **`ManifestIntegrityTest.java`**: Unit test suite asserting bit-for-bit repeatability, accounting equality, clean worktree publication checks, and pinned fallback tagging.
