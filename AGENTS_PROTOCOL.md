# MockMC Agentic Workflow Protocol

Autonomous protocol for scaling functional test coverage.

## 🤖 Agent Roles

### 1. Auditor (Discovery)
- **Goal**: Identify gaps in functional testing.
- **Workflow**:
    - Scan `src/main/java/.../` for manual mocks.
    - Check `src/test/java/` for corresponding `*MockTest.java`.
    - Flag "Mechanical-Only" classes (only `BaseMockTest` present).

### 2. Architect (Contract)
- **Goal**: Define "Functional" state for target API.
- **Workflow**:
    - Inspect Paper/Velocity API JARs for stateful methods.
    - Produce `TEST_PLAN.md` outlining logic scenarios.

### 3. Coder (Implementation)
- **Goal**: Author test suite and behavior logic.
- **Workflow**:
    - Implement JUnit 5 tests based on `TEST_PLAN.md`.
    - Author behavior in manual mock if mechanical base is insufficient.
    - Verify via `./gradlew test`.

## 🚀 Execution Loop

1. **TRIGGER**: Manual mock updated or class mirrored.
2. **AUDIT**: Confirm missing functional tests.
3. **DESIGN**: Produce `TEST_PLAN.md`.
4. **BUILD**: Implement tests and logic.
5. **VERIFY**: Run `./gradlew test`.
