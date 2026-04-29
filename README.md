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
        <img alt="Coverage" src="https://codecov.io/gh/SecondLifeGaming/MockMC/graph/badge.svg?token=uk4UiHzmkx"/>
    </a>
    <!-- Logo -->
    <hr />
        <img alt="MockMC logo" title="MockMC" src="logo.png"/>
    <hr />
</p>

MockMC is a framework that makes the unit testing of Bukkit plugins a whole lot easier.
It aims to provide complete mock implementations that can be controlled from a unit test.

## 🚀 The "Engine First" Mission

MockMC has evolved from manual stubbing to an **"Engine First"** architecture.

Historically, mocking frameworks relied on developers manually adding stubs and throwing `UnimplementedOperationException` for new API methods. This led to a massive maintenance burden every time Paper or Spigot updated their API surface.

To resolve this, we leverage the **`metaminer`** engine:

- **100% API Mirroring**: Using JavaPoet, `metaminer` reflects 100% of the API JARs (Paper, Folia, Velocity, etc.) to generate "Smart Stubs".
- **Zero Manual Boilerplate**: All interface methods are automatically generated with safe defaults (Empty collections, Optionals, default primitives, etc.).
- **Behavior-Focused Development**: Developers no longer spend time on API compliance. Manual mock classes focus strictly on **behavior**, not on satisfying interface contracts.

Whether you are testing for **Paper**, **Folia**, **Velocity**, or **Waterfall**, MockMC's engine ensures you have the full API surface ready for your tests without the `UnimplementedOperationException` headache.

## :page_facing_up: Table of contents

1. [Usage](#mag-usage)
    - [Adding MockMC via gradle](#adding-mockmc-via-gradle)
    - [Adding MockMC via Maven](#adding-mockmc-via-maven)
    - [Using MockMC](#using-mockmc)
2. [Features](#sparkles-features)
    - [Mock Plugins](#mock-plugins)
    - [Mock Players](#mock-players)
    - [Mock Worlds](#mock-worlds)
3. [Troubleshooting (My tests are being skipped)](#question-my-tests-are-being-skipped-unimplementedoperationexception)
4. [Discord server](#headphones-discord-server)
5. [Examples (See MockMC in action)](#tada-examples-see-mockmc-in-action)

## :mag: Usage

MockMC can easily be included in your project using either Maven or gradle.

> [!TIP]
> Currently, the newest version available is
>
> [![ALTERNATE-TEXT](https://img.shields.io/maven-central/v/io.github.secondlifegaming/mockmc-v26.1?color=1bcc94&logo=apache-maven)](https://central.sonatype.com/artifact/io.github.secondlifegaming/mockmc-v26.1)

> Note: The Breaking Changes intended for 3.0 were already made in 2.145.1. Due to an Error it didn't get properly
> tagged

<details>
<summary><h3>Adding MockMC via Gradle</h3></summary>

MockMC can easily be included in Gradle using the Maven Central and PaperMC repositories.
Make sure to update the version as necessary.

First add this new variable, this will allow you to pull the right paper api version from the mockmc dependency:

```gradle
def getMockMCPaperVersion() {
    def mockmcJar = configurations.testImplementation
        .resolve()
        .find { it.name.startsWith('mockmc-') }

    if (mockmcJar) {
        def jarFile = new java.util.jar.JarFile(mockmcJar)
        def manifest = jarFile.manifest
        def paperVersion = manifest.mainAttributes.getValue('Paper-Version')
        jarFile.close()
        return paperVersion ?: '1.21-R0.1-SNAPSHOT'
    }
    return '1.21-R0.1-SNAPSHOT'
}

ext.paperVersion = getMockMCPaperVersion()
```

Now add the dependencies:

```gradle
repositories {
    mavenCentral()
    maven { url 'https://repo.papermc.io/repository/maven-public/' }
}

dependencies {
    testImplementation 'io.github.secondlifegaming:mockmc-v26.1:${paperVersion}'
}
```

If you prefer to always have the latest Git version or need a specific commit/branch, you can always
use [JitPack](https://jitpack.io/#SecondLifeGaming/MockMC) as your maven repository:

```gradle
repositories {
    maven { url 'https://jitpack.io' }
    maven { url 'https://repo.papermc.io/repository/maven-public/' }
}

dependencies {
    testImplementation 'com.github.westkevin12:MockMC:v26.1-SNAPSHOT'
    testImplementation 'io.papermc.paper:paper-api:${paperVersion}'
}
```

Note: use `v1.13-SNAPSHOT` to test a Bukkit 1.13 plugin or any other version if
the [branch](https://github.com/westkevin12/MockMC/branches) exists.
These branches will not be receiving patches actively, but any issues will be resolved and any pull requests on them
will be accepted.
This is because back-porting every single patch on every branch is incredibly time-consuming and slows down the
development of MockMC.

</details>

<details>
<summary><h3>Adding MockMC via Maven</h3></summary>

MockMC can easily be included in Maven using the default Maven Central and PaperMC repositories.

> Note: Make sure to update the version as necessary and put this dependency above your dependency that provides bukkit.

Also here you can extract the manifest version this way:

```xml

<plugin>
    <groupId>org.codehaus.gmaven</groupId>
    <artifactId>groovy-maven-plugin</artifactId>
    <version>2.1.1</version>
    <executions>
        <execution>
            <phase>initialize</phase>
            <goals>
                <goal>execute</goal>
            </goals>
            <configuration>
                <source>
                    // Extract Paper version from MockMC JAR
                    def mockmcJar = project.artifacts.find {
                    it.artifactId.startsWith('mockmc-')
                    }?.file

                    if (mockmcJar) {
                    def jar = new java.util.jar.JarFile(mockmcJar)
                    def manifest = jar.manifest
                    def paperVersion = manifest.mainAttributes.getValue('Paper-Version')
                    jar.close()

                    if (paperVersion) {
                    project.properties['paper.version.from.mockmc'] = paperVersion
                    }
                    }
                </source>
            </configuration>
        </execution>
    </executions>
</plugin>
```

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
    <version>[version]</version>
    <scope>test</scope>
  </dependency>
  <dependency>
      <groupId>io.papermc.paper</groupId>
      <artifactId>paper-api</artifactId>
      <version>${paper.version.from.mockmc}</version>
      <scope>test</scope>
  </dependency>
</dependencies>
```

The `test` scope is important here since you are likely to only be using MockMC during the `test` stage of your
Maven lifecycle and not in your final product.

If you prefer to always have the latest Git version or need a specific commit/branch, you can always
use [JitPack](https://jitpack.io/#SecondLifeGaming/MockMC) as your maven repository:

```xml
<repositories>
    <repository>
        <id>jitpack.io</id>
        <url>https://jitpack.io</url>
    </repository>
    <repository>
        <id>papermc</id>
        <url>https://repo.papermc.io/repository/maven-public/</url>
    </repository>
</repositories>

<dependencies>
  <dependency>
    <groupId>com.github.SecondLifeGaming</groupId>
    <artifactId>MockMC</artifactId>
    <version>v26.1-SNAPSHOT</version>
    <scope>test</scope>
  </dependency>
  <dependency>
      <groupId>io.papermc.paper</groupId>
      <artifactId>paper-api</artifactId>
      <version>${paper.version.from.mockmc}</version>
      <scope>test</scope>
  </dependency>
</dependencies>
```

Note: use `v26.1-SNAPSHOT` to test against Paper API 26.1, or any other version if
the [branch](https://github.com/westkevin12/MockMC/branches) exists.
These branches will not be receiving patches actively, but any issues will be resolved and any pull requests on them
will be accepted.
This is because back-porting every single patch on every branch is incredibly time-consuming and slows down the
development of MockMC.

</details>

### Using MockMC

A plugin can be loaded in this initialiser block.

```java
private ServerMock server;
private MyPlugin plugin;

@BeforeEach
public void setUp()
{
    server = MockMC.mock();
    plugin = MockMC.load(MyPlugin.class);
}

@AfterEach
public void tearDown()
{
    MockMC.unmock();
}
```

## :sparkles: Features

### Mock Plugins

MockMC contains several functions that make the unit testing of Bukkit plugins a lot easier.

It is possible to create a mock plugin.
This is useful when the plugin you are testing may be looking at other loaded plugins.
The following piece of code creates a placeholder plugin that extends JavaPlugin.

```java
PluginMock plugin = MockMC.createMockPlugin();
```

### Mock Players

MockMC makes it easy to create several mock players to use in unit testing.
By running `server.setPlayers(int numberOfPlayers)` one can set the number of online players.
From then on it's possible to get a certain player using `server.getPlayer(int i)`.

An even easier way to create a player on the fly is by simply using

```java
PlayerMock player = server.addPlayer();
```

A mock player also supports several simulated actions, such as damaging a block or even
breaking it. This will fire all the required events and will remove the block if the
events weren't cancelled.

```java
Block block = ...;
player.simulateBlockBreak(block);
```

### Mock Worlds

Another feature is the easy creation of mock worlds.
One can make a superflat world using one simple command:

```java
World world = new WorldMock(Material material, int heightUntilAir)
```

Using `Material.DIRT` and 3 as heightUntilAir will create a superflat world with a height of a 128.
At y=0 everything will be `Material.BEDROCK`, and from 1 until 3 (inclusive) will be `Material.DIRT`
and everything else will be `Material.AIR`.
Each block is created the moment it is first accessed, so if only one block is only ever touched only one
block will ever be created in-memory.

## :question: My tests are being skipped!? (UnimplementedOperationException)

Sometimes your code may use a method that is not yet implemented in MockMC.
When this happens MockMC will, instead of returning placeholder values, throw
an `UnimplementedOperationException`.
These exception extends `AssumptionException` and will cause the test to be skipped.

These exceptions should just be ignored, though pull requests that add functionality to MockMC are always welcome!
If you don't want to add the required methods yourself you can also request the method on the issues page.

## :headphones: Discord Server

You can also find us on discord by the way!
If you need any help with MockMC or have a question regarding this project, feel free to join and connect with other
members of the community.

<p align="center">
  <a href="https://discord.gg/s4cWYgsFaV">
    <img src="https://discordapp.com/api/guilds/792754410576019477/widget.png?style=banner3" alt="Discord Invite"/>
  </a>
</p>

## :tada: Examples (See MockMC in action)

Several projects have utilized MockMC for their needs already.
If you want to see some projects that are using MockMC right now, feel free to take a peak:

- [Slimefun/Slimefun4](https://github.com/Slimefun/Slimefun4/tree/master/src/test/java/io/github/thebusybiscuit/slimefun4)
  (1700+ Unit Tests)
- [Insprill/custom-join-messages](https://github.com/Insprill/custom-join-messages/tree/master/src/test/kotlin/net/insprill/cjm)
  (170+ Unit Tests)
- [carelesshippo/SpectatorModeRewrite](https://github.com/carelesshippo/SpectatorModeRewrite/tree/dev/src/test/java/me/ohowe12/spectatormode)
  (80+ Unit Tests)
- [lluiscamino/MultiverseHardcore](https://github.com/lluiscamino/MultiverseHardcore/tree/master/src/test/java/me/lluiscamino/multiversehardcore)
  (75+ Unit Tests)
- [axelrindle/PocketKnife](https://github.com/axelrindle/PocketKnife/tree/main/api/src/test/kotlin)
  (50+ Unit Tests)
- [ez-plugins/EzEconomy](https://github.com/ez-plugins/EzEconomy/tree/main/ezeconomy-bukkit/src/test/java/com/skyblockexp/ezeconomy)
  (50+ Unit Tests)
- _and more! (If you want to see your plugin here, open up an issue and we'll consider adding it)_

You can also have a look at our documentation where we outline various examples and tricks on how to use MockMC
already:
https://docs.mockmc.org

## :gift_heart: Sponsors

Thanks to JetBrains, the creators of IntelliJ IDEA, for providing us with licenses as part of
their [Open Source program](https://www.jetbrains.com/opensource/).
[![JetBrains](https://resources.jetbrains.com/storage/products/company/brand/logos/jb_beam.svg)](https://www.jetbrains.com/opensource/)
