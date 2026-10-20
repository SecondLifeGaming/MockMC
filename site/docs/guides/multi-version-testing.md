# Multi-Version Testing Recipes (Paper & Folia)

Modern Minecraft servers differ significantly in their threading and concurrency models. While Paper uses global and chunk-based scheduling, Folia executes tasks across regionalized threads (`io.papermc.paper.threadedregions.*`).

## 1. Pinned Extension Scope Contract

MockMC targets canonical Paper as its primary source of truth, while providing Folia region scheduling via strictly scoped package extensions:

- `io.papermc.paper.threadedregions.RegionizedServer`
- `io.papermc.paper.threadedregions.scheduler.FoliaRegionScheduler`
- `io.papermc.paper.threadedregions.ThreadedRegionizer`

This isolation ensures Folia extension classes never overwrite or downgrade canonical Paper interfaces.

---

## 2. Testing Regional Schedulers

```java
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockmc.mockmc.MockMCExtension;
import org.mockmc.mockmc.MockMCInject;
import org.mockmc.mockmc.ServerMock;
import org.bukkit.Location;

import java.util.concurrent.atomic.AtomicBoolean;

import static org.junit.jupiter.api.Assertions.assertTrue;

@ExtendWith(MockMCExtension.class)
class FoliaCompatibilityTest {

    @MockMCInject
    private ServerMock server;

    @Test
    void testRegionTaskExecution() {
        Location location = new Location(server.addSimpleWorld("world"), 100, 64, 100);
        AtomicBoolean ran = new AtomicBoolean(false);

        // Schedule task at specific regional coordinates
        server.getRegionScheduler().run(plugin, location, _ -> ran.set(true));

        // Advance simulation ticks
        server.getScheduler().performTicks(1);

        assertTrue(ran.get(), "Regional task should have executed in target region");
    }
}
```

---

## 3. Running Multi-Backend Verification in Gradle

MockMC provides individual backend verification tasks:

```bash
# Verify against Paper
./gradlew testPaper

# Verify against Folia threaded regions
./gradlew testFolia

# Verify against proxy backends
./gradlew testVelocity testWaterfall

# Verify all platforms
./gradlew testAllBackends
```
