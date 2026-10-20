# Architecture & Requests for Comments (RFCs)

The formal specifications governing MockMC's bytecode manifest engine, diff algorithms, and ecosystem roadmap are documented in the project RFCs:

## Adopted Architectural Specifications

- **[RFC: Canonical API Manifest Specification & Provenance Contract](https://github.com/westkevin12/MockBukkit/blob/master/docs/RFC_MANIFEST_SCHEMA.md)**
  - Bit-for-bit deterministic manifest serialization rules, member-level bytecode completeness, and decoupled overlay architecture (`mockmc-overlay-v1.json`).
- **[RFC: Backwards Version Expansion to 1.21 & 1.20.6](https://github.com/westkevin12/MockBukkit/blob/master/docs/RFC_VERSION_EXPANSION_1_21.md)**
  - Definitive boundary strategy targeting Minecraft 1.21.x and 1.20.6 (modern component era) with zero workstation JDK downgrades.
- **[RFC: Automated Minecraft Plugin Developer Reference & API Explorer](https://github.com/westkevin12/MockBukkit/blob/master/docs/RFC_DEVELOPER_WIKI.md)**
  - Dynamic pairwise and range diff aggregation algorithms folding bytecode diffs without storing $O(N^2)$ manifest files.

---

## Active Proposed RFCs (The Road to 1.0.0)

- **[RFC: Native Language Server Protocol (LSP) Engine & IDE Extensions](https://github.com/westkevin12/MockBukkit/blob/master/docs/RFC_LSP_IDE_EXTENSION.md)**
  - Real-time cross-version binary compatibility diagnostics in IntelliJ IDEA and VS Code, hover timelines, and Folia regional thread gutter markers.
- **[RFC: Model Context Protocol (MCP) Server for AI Pair Programming](https://github.com/westkevin12/MockBukkit/blob/master/docs/RFC_MCP_SERVER.md)**
  - Containerized MCP server (`ghcr.io/secondlifegaming/mockmc-mcp`) providing AI agents with real-time API compatibility lookups, deprecation migrations, and test scaffolding.

---

## Summary of Core Guarantees

- **Bit-for-Bit Determinism**: Strict UTF-8, LF line endings, 2-space indentation, lexicographical key sorting, and canonical SHA-256 byte digest computation.
- **Accounting Triplet**: Every class in the upstream scan scope satisfies:
  $$\text{discoveredEntries} = \text{totalClassesAnalyzed} + \text{totalClassesExcluded} + \text{totalClassesFailed}$$
- **Member-Level Completeness**: 100% of declared bytecode methods and fields are enumerated for analyzed classes.
- **Decoupled Facts**: Manifests capture pure upstream facts, while overlays track mock implementation status and unit tests.
