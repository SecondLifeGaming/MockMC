# Version Support Matrix & Compatibility Lifecycle

This document defines the official support matrix, version tiers, and compatibility guarantees for MockMC.

---

## 🧭 Support Tiers

MockMC classifies upstream server versions into four distinct lifecycle categories:

| Status Tier | Definition | Build Guarantee |
|---|---|---|
| **Tier 1: Canonical** | The primary upstream release defining the canonical API surface. All common Bukkit and Paper classes mirror this version. | 100% compile safety, multi-backend automated testing, active behavioral improvements. |
| **Tier 2: Extension** | Platform-exclusive releases (Folia, proxies) pinned to their latest stable build. | 100% compile safety for platform-exclusive namespaces; zero downgrade to canonical classes. |
| **Tier 3: Fallback** | Older releases whose deprecated or removed signatures are maintained via `@Deprecated` smart stubs. | Compile safety for legacy call sites; no behavioral expansion. |
| **Tier 4: Experimental / Planned** | Candidate versions under architectural validation or awaiting upstream release. | Subject to change as upstream APIs stabilize. |

---

## 📊 Current Target Matrix

| Platform | Pinned Version / Build | Status Tier | Canonical Surface | Tested Via |
|---|---|---|---|---|
| **Paper** | `26.3` (Build 157) | **Tier 1: Canonical** | `org.bukkit.*`, `io.papermc.paper.*` | `./gradlew testPaper` |
| **Folia** | `26.2` (Build 7) | **Tier 2: Extension** | `io.papermc.paper.threadedregions.*` | `./gradlew testFolia` |
| **Velocity** | `4.2.1-SNAPSHOT` (Build 36) | **Tier 2: Extension** | `com.velocitypowered.api.*` | `./gradlew testVelocity` |
| **Waterfall** | `1.21` (Build 615) | **Tier 2: Extension** | `net.md_5.bungee.*` | `./gradlew testWaterfall` |
| **Paper 26.2** | Legacy fallback | **Tier 3: Fallback** | Select deprecated methods | Automated BaseMocks |
| **Folia 26.3** | Awaiting upstream build | **Tier 4: Planned** | Scheduled for Tier 1 parity once published | — |

---

## ⚠️ Important Distinction: Pinned Fallback vs. Native Support

> [!IMPORTANT]
> **A pinned fallback is not the same as native version parity.**
>
> When MockMC includes `folia-26.2-7.jar` alongside `paper-26.3-157.jar`, MockMC **does not** claim native Folia 26.3 support. Instead:
> 1. Folia-specific threading and regional scheduling APIs (`io.papermc.paper.threadedregions.*`) are supplied by the 26.2 build so plugins compiling against Folia APIs can run tests.
> 2. All general Bukkit and Paper APIs are supplied by Paper 26.3.
> 3. Once upstream Folia publishes a 26.3 build, Folia will be bumped to 26.3 without structural disruption.

---

## 🛠️ Artifact Coordinates & Consumer Configuration

### Current Stable Artifact (Paper 26.3 Line)

```kotlin
// build.gradle.kts
repositories {
    mavenCentral()
    maven("https://repo.papermc.io/repository/maven-public/")
}

dependencies {
    // Current primary release line
    testImplementation("io.github.secondlifegaming:mockmc:0.0.5") // Or versioned line: mockmc-v26.2
    testImplementation("io.papermc.paper:paper-api:26.3-R0.1-SNAPSHOT")
}
```

### Upstream Version Bump Schedule

When a new major Minecraft/Paper release occurs:
1. Paper is upgraded first as the canonical baseline.
2. Proxy APIs (Velocity, Waterfall) are verified for backward-compatible interface contracts.
3. Extension platforms (Folia) follow as soon as upstream provides a bootable JAR.
