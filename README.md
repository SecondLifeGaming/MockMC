<p align="center">
    <a href="https://secondlifegaming.github.io/MockMC/"><img alt="Documentation & API Explorer" src="https://img.shields.io/badge/docs%20%26%20explorer-GitHub%20Pages-blue?style=flat&logo=github" /></a>
    <a href="https://github.com/SecondLifeGaming/MockMC/releases"><img alt="GitHub Release" src="https://img.shields.io/github/v/release/SecondLifeGaming/MockMC?color=1bcc94&logo=github&label=GitHub%20Packages" /></a>
    <br />
    <a href="https://github.com/SecondLifeGaming/MockMC/actions/workflows/build.yml"><img alt="Build Status" src="https://github.com/SecondLifeGaming/MockMC/actions/workflows/build.yml/badge.svg" /></a>
    <a href="https://sonarcloud.io/project/issues?resolved=false&types=CODE_SMELL&id=SecondLifeGaming_MockMC"><img alt="Code Smells" src="https://sonarcloud.io/api/project_badges/measure?project=SecondLifeGaming_MockMC&metric=code_smells" /></a>
    <a href="https://sonarcloud.io/component_measures?id=SecondLifeGaming_MockMC&metric=sqale_rating&view=list"><img alt="Maintainability" src="https://sonarcloud.io/api/project_badges/measure?project=SecondLifeGaming_MockMC&metric=sqale_rating" /></a>
    <a href="https://sonarcloud.io/project/issues?resolved=false&types=BUG&id=SecondLifeGaming_MockMC"><img alt="Bugs" src="https://sonarcloud.io/api/project_badges/measure?project=SecondLifeGaming_MockMC&metric=bugs" /></a>
    <br />
    <hr />
    <img alt="MockMC logo" title="MockMC" src="logo.png" />
    <hr />
</p>

**MockMC** is an automated multi-platform testing framework and API compatibility engine for the modern Minecraft ecosystem. It covers the full server and proxy stack **Paper**, **Folia** (regional multithreading), **Velocity**, and **Waterfall** spanning **Minecraft 1.20.6 through 26.3**.

Powered by an autonomous bytecode analysis engine (`metaminer`), MockMC generates 100% compile-safe method mirrors directly from upstream server JARs, eliminating boilerplate stub maintenance so tests never crash on unmocked interface methods.

---

## Key Highlights

- **Full Platform Stack**: First-class support for **Paper**, **Folia** (with regional scheduler execution), **Velocity**, and **Waterfall** in a single test harness.
- **Modern Minecraft Support (1.20.6 → 26.3)**: Built directly on the modern Data Component and Mojang-mapped architecture.
- **Engine-First (Zero Manual Stubs)**: `metaminer` inspects upstream bytecode and generates non-crashing Smart Stubs with safe defaults (`false`, `0`, `Optional.empty()`, empty collections). Manual mocks focus purely on behavioral state progression.
- **Descriptor-Level ABI Diff Engine**: Computes exact bytecode and source breakages across server versions, tracking binary breakages (`NoSuchMethodError`) before your plugin hits production.
- **Interactive Version-Range Explorer**: Web-based offline search & diff explorer over 16,000+ API symbols across Paper and Folia releases.

---

## Quick Start

You can add MockMC to your test suite via **Direct JAR (Zero Auth)**, **Local Clone (`mavenLocal`)**, or **GitHub Packages**:

### Option 1: Direct Release JAR (Recommended / Zero Auth)

Download the target release JAR (e.g. `mockmc-v26.3-0.1.1.jar`) directly from [GitHub Releases](https://github.com/SecondLifeGaming/MockMC/releases) and drop it into your project's `libs/` folder:

```kotlin
// build.gradle.kts
dependencies {
    testImplementation(files("libs/mockmc-v26.3-0.1.1.jar"))
    testImplementation("org.junit.jupiter:junit-jupiter:6.0.3")
}

tasks.test {
    useJUnitPlatform()
}
```

---

### Option 2: Clone or Fork (`mavenLocal`)

Build and publish MockMC directly to your local Maven repository in seconds:

```bash
git clone https://github.com/SecondLifeGaming/MockMC.git
cd MockMC
./gradlew publishToMavenLocal
```

Then in your plugin's `build.gradle.kts`:

```kotlin
repositories {
    mavenLocal()
    mavenCentral()
    maven("https://repo.papermc.io/repository/maven-public/")
}

dependencies {
    testImplementation("io.github.secondlifegaming:mockmc-v26.3:0.1.1")
    testImplementation("org.junit.jupiter:junit-jupiter:6.0.3")
}
```

---

### Option 3: GitHub Packages Repository

If resolving via GitHub Packages, supply your GitHub username and personal access token (with `read:packages` scope) via environment variables or `~/.gradle/gradle.properties`:

```kotlin
// build.gradle.kts
repositories {
    mavenCentral()
    maven("https://repo.papermc.io/repository/maven-public/")
    maven("https://maven.pkg.github.com/SecondLifeGaming/MockMC") {
        credentials {
            username = providers.environmentVariable("GITHUB_ACTOR").orElse(providers.gradleProperty("gpr.user")).orNull
            password = providers.environmentVariable("GITHUB_TOKEN").orElse(providers.gradleProperty("gpr.key")).orNull
        }
    }
}

dependencies {
    // Replace <version> with target release tag (e.g. 0.1.1)
    testImplementation("io.github.secondlifegaming:mockmc-v26.3:<version>")
    testImplementation("org.junit.jupiter:junit-jupiter:6.0.3")
}
```

### Maven (`pom.xml`)

```xml
<dependency>
    <groupId>io.github.secondlifegaming</groupId>
    <artifactId>mockmc-v26.3</artifactId>
    <version>${mockmc.version}</version>
    <scope>test</scope>
</dependency>
```

### Writing a Test

```java
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockmc.mockmc.MockMCExtension;
import org.mockmc.mockmc.MockMCInject;
import org.mockmc.mockmc.ServerMock;
import org.mockmc.mockmc.entity.PlayerMock;

import static org.junit.jupiter.api.Assertions.assertTrue;

@ExtendWith(MockMCExtension.class)
class MyPluginTest {

    @MockMCInject
    private ServerMock server;

    @Test
    void testCommandExecution() {
        PlayerMock player = server.addPlayer("Steve");
        player.performCommand("spawn");

        player.assertSaid("Teleported to spawn!");
        assertTrue(player.isOnline());
    }
}
```

---

## Supported Platforms & Version Matrix

MockMC maintains an explicitly pinned multi-tier contract across upstream server lines:

| Platform      | Supported Version Span | Primary Target Build | Role & Scope                                                                        |
| :------------ | :--------------------- | :------------------- | :---------------------------------------------------------------------------------- |
| **Paper**     | `1.20.6` – `26.3`      | `paper-26.3-206`     | **Canonical Authority**: Bukkit & Paper APIs (`org.bukkit.*`, `io.papermc.paper.*`) |
| **Folia**     | `1.20.6` – `26.3`      | `folia-26.3-3`       | **Platform Extension**: Regional scheduling (`io.papermc.paper.threadedregions.*`)  |
| **Velocity**  | `4.x`                  | `velocity-4.3.0-44`  | **Proxy Extension**: Velocity proxy API (`com.velocitypowered.api.*`)               |
| **Waterfall** | `1.21`                 | `waterfall-1.21-615` | **Proxy Extension**: BungeeCord API (`net.md_5.bungee.*`)                           |

- **Zero-Downgrade Invariant**: Extension platforms (Folia, Velocity) supply platform-specific namespaces without overriding or downgrading canonical Paper classes.
- **Historical Fallbacks**: Deprecated or removed signatures across the 1.20.6–26.3 range are maintained with `@Deprecated(forRemoval = true)` stubs.

---

## Documentation & Developer Reference

- **[Interactive API Explorer & Documentation](https://secondlifegaming.github.io/MockMC/)**: Query method descriptors, view lifecycle status, and compare breaking changes across 1.20.6–26.3.
- **[API Compatibility Report (COMPAT.md)](COMPAT.md)**: Machine-generated descriptor-level diff covering 4,000+ API changes.

---

## The Road to 1.0.0

MockMC is expanding beyond unit testing into a comprehensive API intelligence and developer toolchain:

1. **Native Language Server & IDE Plugins (IntelliJ & VS Code)**:
    - Live cross-version compatibility inspection directly in the editor.
    - Highlights Bukkit/Paper API calls that break or do not exist on older targets within your declared version range (e.g. `1.20.6..26.3`).
    - See [RFC: Native Language Server Protocol & IDE Extensions](docs/RFC_LSP_IDE_EXTENSION.md).

2. **MockMC MCP Server (Model Context Protocol)**:
    - Exposes MockMC's bytecode manifest, descriptor diff engine, and test mappings to AI pair programmers (Claude, Gemini, Antigravity, Cursor).
    - Allows agents to autonomously verify cross-version ABI safety, inspect deprecation replacements, and draft verified test cases.
    - See [RFC: Model Context Protocol (MCP) Server](docs/RFC_MCP_SERVER.md).

3. **Deepened Functional State Simulation**:
    - Continuous transition of peripheral stubs into stateful simulation machines (registries, custom item components, Folia scheduler ticking).

---

## Building & Contributing

Prerequisites: **JDK 25**

```bash
# Download and verify target platform JARs
./gradlew downloadJars

# Compile and run all backend test suites
./gradlew compileJava compileTestJava
./gradlew testAllBackends

# Re-run bytecode analysis & smart stub generator
./gradlew :metaminer:runGenerator
./gradlew spotlessApply
```

Please read [CONTRIBUTING.md](CONTRIBUTING.md) and [AGENTS_PROTOCOL.md](AGENTS_PROTOCOL.md) before submitting pull requests.

---

## License

MockMC is licensed under the [MIT License](LICENSE).
