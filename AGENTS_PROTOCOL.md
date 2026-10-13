# MockMC Agentic Workflow Protocol

Autonomous protocol for scaling functional test coverage and preserving multi-version integrity.

## 🤖 Agent Roles

### 1. Auditor (Discovery & Integrity)
- **Goal**: Identify gaps in functional testing and verify version boundaries.
- **Workflow**:
    - Scan `src/main/java/org/mockmc/mockmc/` for manual mocks.
    - Check `src/test/java/` for corresponding `*MockTest.java`.
    - Flag "Mechanical-Only" classes (where only `BaseMockTest` exists).
    - **Multi-Version Verification**: Ensure changes do not contaminate pinned artifact namespaces or allow extension JARs (Folia 26.2) to overwrite canonical Paper (26.3) interfaces.

### 2. Architect (Contract)
- **Goal**: Define "Functional" state for target API while preserving the version contract.
- **Workflow**:
    - Inspect canonical Paper 26.3 and platform API JARs for stateful methods.
    - Produce `TEST_PLAN.md` outlining state scenarios.
    - Verify whether new methods should be generated as Smart Stubs or implemented manually.

### 3. Coder (Implementation & Verification)
- **Goal**: Author test suites, behavior logic, and maintain generator integrity.
- **Workflow**:
    - Implement JUnit 5 tests based on `TEST_PLAN.md`.
    - Author behavior in manual mock classes if mechanical stub defaults are insufficient.
    - **NEVER** edit files under `org/mockmc/mockmc/generated/` directly.
    - Execute the mandatory multi-backend verification suite before marking changes complete:
      ```bash
      ./gradlew downloadJars
      rm -rf src/main/java/org/mockmc/mockmc/generated/* src/test/java/org/mockmc/mockmc/generated/*
      ./gradlew :metaminer:runGenerator   # if generator rules or target JARs changed
      ./gradlew spotlessApply
      ./gradlew compileJava compileTestJava
      ./gradlew testPaper
      ./gradlew testFolia
      ./gradlew testVelocity
      ./gradlew testWaterfall
      ```

## 🚀 Execution Loop

1. **TRIGGER**: Manual mock updated, class mirrored, or upstream version bumped.
2. **AUDIT**: Confirm missing functional tests and check version boundaries.
3. **DESIGN**: Produce `TEST_PLAN.md`.
4. **BUILD**: Implement tests and behavior in manual mocks.
5. **VERIFY**: Run `./gradlew compileJava` and `./gradlew testAllBackends`.
6. **EVIDENCE**: Document verified test results, spotless compliance, and zero unaccounted methods in `COVERAGE.md`.
