# MockMC Agentic Workflow Protocol

This document defines the autonomous protocol for scaling functional test coverage using AI agents.

## 🤖 Agent Roles

### 1. The Auditor (Verification Specialist)
- **Goal**: Identify gaps in functional testing.
- **Workflow**:
    - Scans `src/main/java/org/mockmc/mockmc/` for manual mocks.
    - Cross-references with `src/test/java/` to see if a corresponding `*MockTest.java` exists.
    - Reports classes with "Mechanical-Only" coverage (i.e., only `BaseMockTest` exists).

### 2. The Architect (Contract Analyst)
- **Goal**: Define what "Functional" means for a specific API.
- **Workflow**:
    - Reads the Paper/Velocity API JARs or online documentation for a target class.
    - Identifies "Stateful" methods (setters, getters, logic-heavy methods).
    - Writes a `TEST_PLAN.md` for the class, outlining specific scenarios (e.g., "Setting health should trigger HealthChangeEvent").

### 3. The Coder (Implementation Specialist)
- **Goal**: Author the functional test suite.
- **Workflow**:
    - Reads the `TEST_PLAN.md` provided by the Architect.
    - Author a JUnit 5 test class using `MockMCExtension`.
    - Implements logic in the manual mock if the mechanical base is insufficient.
    - Ensures all tests pass and follow the "Safe Defaults" principles.

## 🚀 Autonomous Execution Loop

1. **TRIGGER**: A new class is mirrored or a manual mock is updated.
2. **AUDIT**: Auditor confirms if functional tests are missing.
3. **DESIGN**: Architect produces a `TEST_PLAN.md`.
4. **BUILD**: Coder implements the tests and logic.
5. **VERIFY**: Run `./gradlew test` and update the `COVERAGE.md`.

## 🛠️ Tooling Requirements

- **Class Discovery**: `grep`, `find`, and `ls`.
- **API Inspection**: `javap` or `BaseMockGenerator` analysis.
- **Test Execution**: `./gradlew test`.

---
*This protocol is part of the "Engine First" mission to achieve 100% functional fidelity.*
