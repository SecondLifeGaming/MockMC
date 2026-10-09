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

MockMC is an automated, type-safe testing framework for Minecraft server software, forked from the excellent [MockBukkit](https://github.com/MockBukkit/MockBukkit) project.

While MockBukkit focuses on handcrafted in-game simulation, MockMC is designed for **bleeding-edge version adaptability and 100% compile-safe multi-platform testing**.

---

## ⚖️ MockBukkit vs. MockMC: Which Should You Use?

| Feature                    | MockBukkit                                                                            | MockMC                                                                                  |
| :------------------------- | :------------------------------------------------------------------------------------ | :-------------------------------------------------------------------------------------- |
| **Primary Goal**           | Deep in-game behavioral simulation (physics, complex event lifecycles, recipe logic). | Rapid adaptation to new/experimental APIs with 100% type-safe compilation.              |
| **Method Implementation**  | Handcrafted behavior for each method.                                                 | Automated **Smart Stubs** generated directly from server bytecode via JavaPoet.         |
| **New Release Turnaround** | Requires manual coding of new API methods before tests can compile.                   | **Day 1 readiness**: Regenerates the full API surface automatically from upstream JARs. |
| **Platforms Supported**    | Bukkit / Paper.                                                                       | **Paper, Folia** (regional schedulers), **Velocity**, and **Waterfall**.                |
| **Unimplemented Methods**  | Often throws `UnimplementedOperationException`.                                       | Safe defaults (`false`, `0`, `Optional.empty()`, empty collections).                    |

> [!TIP]
> **Rule of Thumb:**
>
> - If you need deep simulation of vanilla Minecraft mechanics on a stable release, use **MockBukkit**.
> - If you write your own test assertions and need your tests to compile and pass cleanly against **latest Paper/Folia snapshots or multi-platform proxies without runtime stub crashes**, use **MockMC**.

---

## ⚙️ How It Works: "Engine First"

MockMC decouples API compilation from manual implementation:

1. **Autonomous Bytecode Remapping**: The [`metaminer`](/metaminer) engine inspects target server JARs (`Paper`, `Folia`, `Velocity`) and remaps them without external build plugins.
2. **Automated API Mirrors**: JavaPoet generates `*BaseMock` interfaces with non-crashing Smart Stubs for every interface method.
3. **Layered Behavior**: Manual implementations focus strictly on complex logic (e.g., custom matchers, state tracking); the generator handles the remaining 95% of boilerplate.

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
    maven { url = uri("https://repo.papermc.io/repository/maven-public/") }
}

// Helper to safely extract the Paper API version bundled in the MockMC manifest
fun getMockMCPaperVersion(): String {
    return try {
        val mockmcJar = configurations["testRuntimeClasspath"].incoming.files
            .find { it.name.contains("mockmc-") }
        if (mockmcJar != null) {
            val jarFile = java.util.jar.JarFile(mockmcJar)
            val paperVersion = jarFile.manifest.mainAttributes.getValue("Paper-Version")
            jarFile.close()
            paperVersion ?: "26.2"
        } else "26.2"
    } catch (e: Exception) { "26.2" }
}

dependencies {
    testImplementation("io.github.secondlifegaming:mockmc-v26.2:0.0.5")
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
        <url>https://repo.papermc.io/repository/maven-public/</url>
    </repository>
</repositories>

<dependencies>
    <dependency>
        <groupId>io.github.secondlifegaming</groupId>
        <artifactId>mockmc-v26.2</artifactId>
        <version>0.0.5</version><!-- mockmc-version -->
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

## 🛡️ Smart Stub Defaults

When an API method does not yet have an explicit behavioral simulation, generated mocks automatically return safe defaults instead of throwing `UnimplementedOperationException`:

| Return Type                             | Default Value                                       |
| :-------------------------------------- | :-------------------------------------------------- |
| **Primitives (`boolean`, `int`, etc.)** | `false`, `0`, etc.                                  |
| **Optionals**                           | `Optional.empty()`                                  |
| **Collections / Lists / Sets**          | Empty, immutable collection                         |
| **Adventure Components**                | `Component.empty()`                                 |
| **Objects / Interfaces**                | `null` (or isolated default mock where appropriate) |
