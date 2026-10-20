# MockMC Requests for Comments (RFCs)

MockMC RFCs establish formal architectural contracts, data specifications, and future product roadmaps for the testing framework and developer platform.

---

## Active Proposed RFCs (The Road to 1.0.0)

- **[RFC: Native Language Server Protocol (LSP) Engine & IDE Extensions](RFC_LSP_IDE_EXTENSION.md)**
    - **Status**: Proposed / Active Working Draft
    - **Scope**: Standalone LSP daemon, real-time cross-version binary compatibility diagnostics in IDEs (IntelliJ IDEA & VS Code), and automated migration quick-fixes.

- **[RFC: Model Context Protocol (MCP) Server for AI Pair Programming](RFC_MCP_SERVER.md)**
    - **Status**: Proposed / Active Working Draft
    - **Scope**: Model Context Protocol (MCP) server exposing MockMC's bytecode manifest, descriptor diff engine, and test scaffolding tools to AI coding agents (Claude, Gemini, Cursor, Antigravity).

---

## Established & Adopted Specifications

- **[RFC: Backwards Version Expansion to 1.21 & 1.20.6](RFC_VERSION_EXPANSION_1_21.md)**
    - **Status**: Adopted & Implemented
    - **Summary**: Definitive boundary strategy establishing the 1.20.6–26.3 modern component era, Java 25 compatibility, standalone unbundling, and component fallback architecture.

- **[RFC: Canonical API Manifest Specification & Provenance Contract](RFC_MANIFEST_SCHEMA.md)**
    - **Status**: Adopted & Implemented
    - **Summary**: Bit-for-bit deterministic manifest serialization, class disposition accounting (`totalClassesDiscovered == analyzed + excluded + failed`), and decoupled overlay architecture (`mockmc-overlay-v1.json`).

- **[RFC: Automated Developer Reference & API Explorer](RFC_DEVELOPER_WIKI.md)**
    - **Status**: Adopted & Implemented
    - **Summary**: Turning MockMC's bytecode discovery into an interactive, offline client-side compatibility explorer with sub-millisecond symbol search and range diff aggregation.
