# Getting Started with MockMC

MockMC is an automated, high-fidelity mock framework for Minecraft plugins supporting modern Paper, Folia, Velocity, and Waterfall.

## Adding MockMC to Your Project

You can add MockMC to your test suite via **Direct JAR (Zero Auth)**, **Local Clone (`mavenLocal`)**, or **GitHub Packages**:

### Option 1: Direct Release JAR (Recommended / Zero Auth)

Download the pre-built JAR from [GitHub Releases](https://github.com/SecondLifeGaming/MockMC/releases) and place it in your project's `libs/` directory:

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

Build and publish MockMC directly to your local Maven cache:

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

To resolve directly from GitHub Packages in CI or local development, configure your credentials using Gradle providers (from environment variables or `~/.gradle/gradle.properties`):

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

---

## Core Concepts

1. **`ServerMock`**: The entrypoint simulating a running Minecraft server.
2. **`MockBukkit.mock()` / `MockMC.mock()`**: Bootstraps the mock server environment.
3. **`MockBukkit.load(MyPlugin.class)`**: Instantiates, enables, and registers events for your plugin using simulated classloaders.
4. **`MockBukkit.unmock()`**: Tears down mock singletons and clears scheduled tasks between tests.
