<p align="center">
    <!-- Badges -->
    <a href="https://github.com/SecondLifeGaming/MockMC/actions/workflows/build.yml">
        <img alt="Build Status" src="https://github.com/SecondLifeGaming/MockMC/actions/workflows/build.yml/badge.svg" />
    </a>
    <a href="https://github.com/SecondLifeGaming/MockMC/releases">
        <img alt="GitHub Release" src="https://img.shields.io/github/v/release/SecondLifeGaming/MockMC?color=1bcc94&logo=github&label=GitHub%20Packages" />
    </a>
    <a href="https://sonarcloud.io/project/issues?resolved=false&types=CODE_SMELL&id=SecondLifeGaming_MockMC">
        <img alt="Code Smells" src="https://sonarcloud.io/api/project_badges/measure?project=SecondLifeGaming_MockMC&metric=code_smells">
    </a>
    <a href="https://sonarcloud.io/component_measures?id=SecondLifeGaming_MockMC&metric=sqale_rating&view=list">
        <img alt="Maintainability" src="https://sonarcloud.io/api/project_badges/measure?project=SecondLifeGaming_MockMC&metric=sqale_rating">
    </a>
    <a href="https://sonarcloud.io/project/issues?resolved=false&types=BUG&id=SecondLifeGaming_MockMC">
        <img alt="Bugs" src="https://sonarcloud.io/api/project_badges/measure?project=SecondLifeGaming_MockMC&metric=bugs">
    </a>
    <!-- Logo -->
    <hr />
        <img alt="MockMC logo" title="MockMC" src="logo.png"/>
    <hr />
</p>

MockMC is a testing framework for Minecraft server software, forked from the excellent [MockBukkit](https://github.com/MockBukkit/MockBukkit) project. It provides complete mock implementations of server environments, allowing you to run unit tests for your plugins with speed and precision.

## 🛠 Why the Fork?

MockBukkit set the standard for Bukkit-based unit testing. However, as server APIs like Paper and Folia have expanded into thousands of methods, maintaining manual stubs became tedious.

While the original MockBukkit utilized [metaminer](https://github.com/MockBukkit/MockBukkit/tree/minecraft/v26/metaminer) to help generate stubs, it required manual overhead to keep the mock surface in sync. We created MockMC to solve these core architectural challenges.

### The Overhaul

We have completely overhauled the [metaminer](/metaminer) engine using **JavaPoet**. This shift moves MockMC to an **"Engine First"** strategy:

- **Automated Source Generation**: Uses **JavaPoet** for automated source generation directly from the server schemas.
- **Type-Safe Inheritance**: The generation engine parses and mirrors deep interface inheritance hierarchies and complex generic types straight out of the upstream source distributions.
- **Universal Scraping Surface**: The tool reads structures cleanly across ecosystems, enabling instant, automated runtime stub generations for **Paper, Folia, Velocity, BungeeCord, and Waterfall**.
- **Zero-Lag Updates**: Every method, even deep experimental tracking components added in a brand-new target server revision, is immediately generated with safe, type-specific fallback rules.
- **Focus on Logic**: Because our engine programmatically spins up the thousands of interface methods automatically, our human implementation efforts are focused strictly on **simulating complex physics and block states**.

## Usage

MockMC is published to Maven Central for frictionless public resolution. Visual tracking releases are also mirrored directly to GitHub Packages.

> [!TIP]
> **Latest Version Status:**
> [![GitHub Packages](https://img.shields.io/github/v/release/SecondLifeGaming/MockMC?color=1bcc94&logo=github&label=GitHub%20Packages)](https://github.com/SecondLifeGaming/MockMC/releases)

<details>
<summary><h3>Adding MockMC via Gradle (Kotlin DSL)</h3></summary>

Add the Paper public repository and drop MockMC into your dependencies block. No access tokens or credential configurations are required.

```kotlin
repositories {
    mavenCentral()
    maven { url = uri("[https://repo.papermc.io/repository/maven-public/](https://repo.papermc.io/repository/maven-public/)") }
}

// Helper block to safely extract matching Paper API version bounds from the MockMC manifest
def getMockMCPaperVersion() {
    try {
        def mockmcJar = configurations.testRuntimeClasspath.incoming.files.find { it.name.contains("mockmc-") }
        if (mockmcJar) {
            def jarFile = new java.util.jar.JarFile(mockmcJar)
            def paperVersion = jarFile.manifest.mainAttributes.getValue("Paper-Version")
            jarFile.close()
            return paperVersion ?: "26.2"
        }
    } catch (Exception ignored) { /* Fallback to standard tracking default */ }
    return "26.2"
}

dependencies {
    testImplementation("io.github.secondlifegaming:mockmc-v26.2:0.0.4")
    testImplementation("io.papermc.paper:paper-api:${getMockMCPaperVersion()}")
}

```

</details>

<details>
<summary><h3>Adding MockMC via Maven</h3></summary>

Add the Paper repository mapping and the library dependencies to your plugin’s `pom.xml`:

```xml
<repositories>
    <repository>
        <id>papermc</id>
        <url>[https://repo.papermc.io/repository/maven-public/](https://repo.papermc.io/repository/maven-public/)</url>
    </repository>
</repositories>

<dependencies>
    <dependency>
        <groupId>io.github.secondlifegaming</groupId>
        <artifactId>mockmc-v26.2</artifactId>
        <version>0.0.4</version>
        <scope>test</scope>
    </dependency>

    <dependency>
        <groupId>io.papermc.paper</groupId>
        <artifactId>paper-api</artifactId>
        <version>26.2</version>
        <scope>test</scope>
    </dependency>
</dependencies>

```

</details>

### Quick Start

Initialize the mock server pipeline inside your unit test setup:

```java
private ServerMock server;
private MyPlugin plugin;

@BeforeEach
public void setUp() {
    server = MockMC.mock();
    plugin = MockMC.load(MyPlugin.class);
}

@AfterEach
public void tearDown() {
    MockMC.unmock();
}

```

---

## 🛡️ Total API Coverage

Rather than throwing `UnimplementedOperationException` when encountering an unmapped method block, MockMC utilizes a **Total Coverage** layer.

Because our automated code engine maps the entire API surface using JavaPoet source patterns, every single method is guaranteed to link cleanly at runtime. If a specific behavior has not been explicitly overwritten with complex logic yet, the engine provides **Safe Defaults**:

- **Collections/Arrays**: Returns clean, empty, non-null instances.
- **Optionals**: Returns standard `Optional.empty()`.
- **Objects**: Returns `null` or an isolated mock sub-stub where safe.
- **Primitives**: Returns typical zeroed boundaries (e.g., `false`, `0`).

This setup ensures your active automated testing pipelines never encounter compilation breakage or unexpected failures due to minor structural additions in the upstream API.

---
