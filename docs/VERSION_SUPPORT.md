# Version Support Matrix & Compatibility Lifecycle

This document defines the official support matrix, version tiers, and compatibility guarantees for MockMC.

---

## Support Tiers

MockMC classifies upstream server versions into four distinct lifecycle categories:

| Status Tier | Definition | Build Guarantee |
| :--- | :--- | :--- |
| **Tier 1: Canonical** | The primary upstream release defining the canonical API surface. All common Bukkit and Paper classes mirror this version. | 100% compile safety, multi-backend automated testing, active behavioral improvements. |
| **Tier 2: Extension** | Platform-exclusive releases (Folia, proxies) pinned to their latest stable build. | 100% compile safety for platform-exclusive namespaces; zero downgrade to canonical classes. |
| **Tier 3: Fallback** | Historical releases whose deprecated or removed signatures are maintained via `@Deprecated` smart stubs. | Compile safety for legacy call sites across 1.20.6–26.3. |
| **Tier 4: Experimental / Planned** | Candidate versions under architectural validation or awaiting upstream release. | Subject to change as upstream APIs stabilize. |

---

## Supported Version Matrix (1.20.6 – 26.3)

MockMC provides full API mirroring and compatibility intelligence across **Minecraft 1.20.6 through 26.3**:

| Platform | Pinned Version / Build | Status Tier | Canonical Surface / Scope | Tested Via |
| :--- | :--- | :--- | :--- | :--- |
| **Paper** | `26.3` (Build 206) | **Tier 1: Canonical** | `org.bukkit.*`, `io.papermc.paper.*` | `./gradlew testPaper` |
| **Folia** | `26.3` (Build 3) | **Tier 2: Extension** | `io.papermc.paper.threadedregions.*` | `./gradlew testFolia` |
| **Velocity** | `4.3.0` (Build 44) | **Tier 2: Extension** | `com.velocitypowered.api.*` | `./gradlew testVelocity` |
| **Waterfall** | `1.21` (Build 615) | **Tier 2: Extension** | `net.md_5.bungee.*` | `./gradlew testWaterfall` |
| **Paper Historical** | `1.20.6`, `1.21.11`, `26.1.2`, `26.2` | **Tier 3: Fallback** | Historical diffing, ABI breaking change tracking, `@Deprecated` fallbacks | Automated BaseMocks & Diff Engine |
| **Folia Historical** | `1.20.6`, `1.21.11`, `26.1.2`, `26.2` | **Tier 3: Fallback** | Historical regional scheduler tracking | Scoped BaseMocks |

---

## Validation Scope & Provenance

MockMC validates the entire modern component era spanning **1.20.6 through 26.3**. 

`jars/manifests/versions.json` is the authoritative manifest declaring all pinned builds with SHA-256 hashes. Precomputed pairwise diffs and range diffs for every intermediate version step are generated directly from upstream bytecode and indexed in the [Interactive API Explorer](https://secondlifegaming.github.io/MockMC/explorer/).

---

## Extension Isolation Contract

> [!IMPORTANT]
> **Zero Downgrade Invariant**
>
> 1. General Bukkit and Paper APIs are exclusively governed by the canonical Paper build (`paper-26.3-206.jar`).
> 2. Folia-specific threading and regional scheduling APIs (`io.papermc.paper.threadedregions.*`) are supplied by the Folia extension build.
> 3. MetaMiner strictly enforces that platform extension passes cannot overwrite, modify, or downgrade any method or class emitted by the canonical tier.
> 4. Proxy platforms (`com.velocitypowered.api.*` and `net.md_5.bungee.*`) live in distinct package namespaces and are fully decoupled from server backends.

---

## Consumer Configuration

### Option 1: Direct Release JAR (Zero Auth)

Drop the release JAR into your `libs/` folder:

```kotlin
// build.gradle.kts
dependencies {
    testImplementation(files("libs/mockmc-v26.3-0.1.1.jar"))
    testImplementation("org.junit.jupiter:junit-jupiter:6.0.3")
}
```

### Option 2: Clone or Fork (`mavenLocal`)

```bash
git clone https://github.com/SecondLifeGaming/MockMC.git
cd MockMC
./gradlew publishToMavenLocal
```

```kotlin
// build.gradle.kts
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

### Option 3: GitHub Packages Repository

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
    // Current release line (e.g. 0.1.1)
    testImplementation("io.github.secondlifegaming:mockmc-v26.3:<version>")
    testImplementation("org.junit.jupiter:junit-jupiter:6.0.3")
}
```

### Upstream Version Bump Schedule

When a new major Minecraft/Paper release occurs:
1. Paper is updated first as the canonical baseline in `jars/manifests/versions.json`.
2. MetaMiner regenerates the base stubs via `./gradlew :metaminer:runGenerator`.
3. Proxy and extension platforms (Velocity, Folia) are updated once stable builds are published upstream.
