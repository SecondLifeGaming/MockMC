# Getting Started with MockMC

MockMC is an automated, high-fidelity mock framework for Minecraft plugins supporting modern Paper, Folia, Velocity, and Waterfall.

## Adding MockMC to Your Project

### Gradle (Kotlin DSL)

```kotlin
repositories {
    mavenCentral()
    maven("https://repo.papermc.io/repository/maven-public/")
}

dependencies {
    testImplementation("io.github.secondlifegaming:mockmc:0.0.6")
    testImplementation("org.junit.jupiter:junit-jupiter:6.0.3")
}

tasks.test {
    useJUnitPlatform()
}
```

### Maven (`pom.xml`)

```xml
<dependency>
    <groupId>io.github.secondlifegaming</groupId>
    <artifactId>mockmc</artifactId>
    <version>0.0.6</version>
    <scope>test</scope>
</dependency>
```

---

## Core Concepts

1. **`ServerMock`**: The entrypoint simulating a running Minecraft server.
2. **`MockBukkit.mock()` / `MockMC.mock()`**: Bootstraps the mock server environment.
3. **`MockBukkit.load(MyPlugin.class)`**: Instantiates, enables, and registers events for your plugin using simulated classloaders.
4. **`MockBukkit.unmock()`**: Tears down mock singletons and clears scheduled tasks between tests.
