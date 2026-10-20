# Generator & API Coverage Specification

This document details how the MockMC `metaminer` engine measures, accounts for, and audits API surfaces in [`COVERAGE.md`](../COVERAGE.md).

---

## What the 100% Metric Actually Measures

The headline metric in `COVERAGE.md`:

```markdown
## Accounted-For API Surface: 100.00%
```

> [!IMPORTANT]
> **API Signature Coverage ≠ Behavioral Test Coverage**
>
> **100% Accounted-For API Surface** means that **every public non-static interface method discovered across the pinned server bytecode JARs has a defined, compile-safe implementation in MockMC**—either via an automated Smart Stub, a manual behavioral override, or a justified exclusion.
>
> It does **not** mean that all 12,000+ methods simulate complex vanilla game mechanics (such as world generation, entity pathfinding, or block physics).

---

## Accounting Categories

When `CoverageReportGenerator` analyzes the pinned JARs, it classifies each method into one of three categories:

| Category                               | Description                                                                                                                                                                            | Implementation Source                        |
| -------------------------------------- | -------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------- | -------------------------------------------- |
| **Category A: Automated BaseMocks**    | Generated `*BaseMock` interface default methods returning safe, non-crashing defaults (`false`, `0`, `Optional.empty()`, empty immutable collections, `Component.empty()`, or `null`). | `metaminer/generator/BaseMockGenerator.java` |
| **Category B: Manual Implementations** | Explicit behavioral mocks providing stateful simulation, event dispatching, player inventories, command sender logic, or entity metadata.                                              | `src/main/java/org/mockmc/mockmc/*Mock.java` |
| **Category C: Justified Exclusions**   | API classes intentionally excluded from generation (see below).                                                                                                                        | `org.mockmc.metaminer.util.ClassExclusions`  |
| **Unaccounted Methods**                | Methods present in the target JAR bytecode that have neither an automated stub, a manual override, nor an exclusion entry. **Must always be 0.**                                       | Regression / Blocker                         |

---

## Justified Exclusions

Classes are excluded from generation only under strictly justified circumstances:

1. **Internal Plugin Loading & Bootstrapping**: Classes in `io.papermc.paper.plugin.provider.*` or `io.papermc.paper.plugin.storage.*` represent the physical JAR loader lifecycle, not the mock plugin API surface.
2. **Registry Data Builders & Fillers**: Interfaces in `io.papermc.paper.registry.data.*` or `io.papermc.paper.registry.set.*` represent internal schema builders handled statically by mock registry datasets.
3. **Legacy Obsolete Interfaces**: Concrete implementations like `co.aikar.timings.Timing` where empty no-op handlers are supplied directly by the runtime rather than mock generation.

All exclusions are maintained centrally in [`ClassExclusions.java`](../metaminer/src/main/java/org/mockmc/metaminer/util/ClassExclusions.java).

---

## Source Artifacts Scanned

Coverage reports are produced against the explicit set of pinned JARs selected by `jars/manifests/versions.json` (spanning the 1.20.6–26.3 range):

- `jars/paper-26.3-206.jar`
- `jars/folia-26.3-3.jar`
- `jars/velocity-4.3.0-44.jar`
- `jars/waterfall-1.21-615.jar`
- Historical baseline jars (`paper-1.20.6-151`, `paper-1.21.11-132`, `paper-26.1.2-74`, `paper-26.2-134`)
- Remapped and unbundled libraries in `jars/cache/libraries/`

---

## Reproducing the Coverage Report

To regenerate `COVERAGE.md` from the pinned JARs:

```bash
# 1. Download and remap target JARs (if not already cached)
./gradlew downloadJars

# 2. Run the generator pipeline (which includes CoverageReportGenerator)
./gradlew :metaminer:runGenerator

# 3. Verify format and git diff
./gradlew spotlessApply
git diff COVERAGE.md
```
