# Manifest Specification & Provenance Contract

The formal specification governing bytecode manifest generation, source hash verification, and accounting completeness is documented in the project RFCs:

- **Specification RFC**: [RFC: Canonical API Manifest Specification & Provenance Contract](https://github.com/westkevin12/MockBukkit/blob/master/docs/RFC_MANIFEST_SCHEMA.md)
- **Developer Reference RFC**: [RFC: Automated Minecraft Plugin Developer Reference & Version-Aware API Explorer](https://github.com/westkevin12/MockBukkit/blob/master/docs/RFC_DEVELOPER_WIKI.md)

## Summary of Guarantees

- **Bit-for-Bit Determinism**: Strict UTF-8, LF line endings, 2-space indentation, lexicographical key sorting, and canonical SHA-256 byte digest computation.
- **Accounting Triplet**: Every class in the upstream scan scope satisfies:
  $$\text{discoveredEntries} = \text{totalClassesAnalyzed} + \text{totalClassesExcluded} + \text{totalClassesFailed}$$
- **Member-Level Completeness**: 100% of declared bytecode methods and fields are enumerated for analyzed classes.
- **Decoupled Facts**: Manifests capture pure upstream facts, while `mockmc-overlay-v1.json` tracks mock implementation status and unit tests.
