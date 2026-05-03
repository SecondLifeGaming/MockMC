<p align="center">
    <!-- Badges -->
    <a href="https://github.com/SecondLifeGaming/MockMC/actions/">
        <img alt="Build Status" src="https://github.com/SecondLifeGaming/MockMC/actions/workflows/publish.yml/badge.svg" />
    </a>
    <a href="https://central.sonatype.com/artifact/io.github.secondlifegaming/mockmc-v26.1">
        <img alt="Maven Central" src="https://img.shields.io/maven-central/v/io.github.secondlifegaming/mockmc-v26.1?color=1bcc94&logo=apache-maven" />
    </a>
    <a href="https://javadoc.io/doc/io.github.secondlifegaming/mockmc-v26.1">
        <img alt="Javadocs" src="https://javadoc.io/badge2/io.github.secondlifegaming/mockmc-v26.1/javadoc.svg" />
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
    <a href="https://codecov.io/gh/SecondLifeGaming/MockMC" >
        <img alt="Coverage" src="https://codecov.io/gh/SecondLifeGaming/MockMC/branch/master/graph/badge.svg?token=AFJ9H8R51P"/>
    </a>
    <!-- Logo -->
    <hr />
        <img alt="MockMC logo" title="MockMC" src="logo.png"/>
    <hr />
</p>

MockMC is a testing framework for Minecraft server software, forked from the excellent [MockBukkit](https://github.com/MockBukkit/MockBukkit) project. It provides complete mock implementations of server environments, allowing you to run unit tests for your plugins with speed and precision.

## 🛠 Why the Fork?

MockBukkit set the standard for Bukkit-based unit testing. However, as server APIs like Paper and Folia have expanded into thousands of methods, maintaining manual stubs became tedious.

While the original MockBukkit utilized a `metaminer` to help generate stubs, it lacked type safety, often skipped inheritance chains, and required significant manual overhead to keep the mock surface in sync. We created MockMC to solve these core architectural challenges.

### The JavaPoet Evolution
We have completely overhauled the metaminer engine using **JavaPoet**. This shift moves MockMC to an **"Engine First"** strategy:

* **Type-Safe Inheritance**: Unlike previous miners that generated basic stubs, our JavaPoet-based engine correctly maps inheritance and generic types directly from the API JARs.
* **Universal JAR Scraping**: The engine scrapes directly from provided JARs, allowing for instant, automated support for **Paper, Folia, Velocity, BungeeCord, and Waterfall**.
* **Zero-Lag Updates**: Every method—even those added in a brand-new Paper update—is immediately available with safe, type-specific defaults (Empty collections, Optionals, etc.).
* **Focus on Logic**: Because the engine handles the thousands of interface methods automatically, our manual implementation efforts are focused strictly on **simulating complex logic**.

## Usage

MockMC is available via Maven Central.

> [!TIP]
> **Latest Version:**
> [![Maven Central](https://img.shields.io/maven-central/v/io.github.secondlifegaming/mockmc-v26.1?color=1bcc94&logo=apache-maven)](<[https://central.sonatype.com/artifact/io.github.secondlifegaming/mockmc-v26.1](https://central.sonatype.com/artifact/io.github.secondlifegaming/mockmc-v26.1)>)

<details>
<summary><h3>Adding MockMC via Gradle</h3></summary>

```gradle
repositories {
    mavenCentral()
    maven { url 'https://repo.papermc.io/repository/maven-public/' }
}

// Helper to extract the Paper version from the MockMC JAR
def getMockMCPaperVersion() {
    def mockmcJar = configurations.testImplementation.resolve().find { it.name.contains('mockmc-') }
    if (mockmcJar) {
        def jarFile = new java.util.jar.JarFile(mockmcJar)
        def paperVersion = jarFile.manifest.mainAttributes.getValue('Paper-Version')
        jarFile.close()
        return paperVersion ?: '1.21-R0.1-SNAPSHOT'
    }
    return '1.21-R0.1-SNAPSHOT'
}

dependencies {
    testImplementation 'io.github.secondlifegaming:mockmc-v26.1:dev-2a95b9a32'
    testImplementation "io.papermc.paper:paper-api:${getMockMCPaperVersion()}"
}
```

</details>

<details>
<summary><h3>Adding MockMC via Maven</h3></summary>

Add the Paper repository and the MockMC dependency to your `pom.xml`.

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
        <artifactId>mockmc-v26.1</artifactId>
        <version>dev-2a95b9a32</version>
        <scope>test</scope>
    </dependency>

    <dependency>
        <groupId>io.papermc.paper</groupId>
        <artifactId>paper-api</artifactId>
        <version>1.21-R0.1-SNAPSHOT</version>
        <scope>test</scope>
    </dependency>
</dependencies>
```

</details>

### Quick Start

Initialize the mock environment in your test setup:

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

Unlike traditional mocking frameworks that throw `UnimplementedOperationException` when encountering an unmapped method, MockMC utilizes a **Total Coverage** model.

Because our engine generates the entire API surface using JavaPoet, every single method is guaranteed to exist. If a specific behavioral implementation hasn't been manually coded yet, the engine provides **Safe Defaults**:

* **Collections/Arrays**: Returns empty, non-null instances.
* **Optionals**: Returns `Optional.empty()`.
* **Objects**: Returns `null` or a basic mock stub where appropriate.
* **Primitives**: Returns standard defaults (e.g., `false`, `0`).

This ensures your test suites never crash due to upstream API changes, allowing you to focus on testing your plugin's logic rather than fighting the mock environment.

---
