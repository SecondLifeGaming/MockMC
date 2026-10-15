# Writing Your First MockMC Test

MockMC provides JUnit 5 integration to create robust unit tests for commands, event listeners, and player interactions.

## Basic Plugin Test Example

```java
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockmc.mockmc.MockBukkit;
import org.mockmc.mockmc.ServerMock;
import org.mockmc.mockmc.entity.PlayerMock;

import static org.junit.jupiter.api.Assertions.assertEquals;

class MyPluginTest {

    private ServerMock server;
    private MyPlugin plugin;

    @BeforeEach
    void setUp() {
        server = MockBukkit.mock();
        plugin = MockBukkit.load(MyPlugin.class);
    }

    @AfterEach
    void tearDown() {
        MockBukkit.unmock();
    }

    @Test
    void testFlyCommand() {
        PlayerMock player = server.addPlayer();

        // Simulate player executing "/fly"
        player.performCommand("fly");

        // Verify flying mode
        assertEquals(true, player.isFlying());
        player.assertSaid("Flight enabled!");
    }
}
```

## Using JUnit 5 Extension (`@ExtendWith(MockMCExtension.class)`)

MockMC also supports lifecycle injection to avoid manual setup/teardown boilerplate:

```java
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockmc.mockmc.MockMCExtension;
import org.mockmc.mockmc.MockMCInject;
import org.mockmc.mockmc.ServerMock;
import org.mockmc.mockmc.entity.PlayerMock;

@ExtendWith(MockMCExtension.class)
class ModernPluginTest {

    @MockMCInject
    private ServerMock server;

    @Test
    void testPlayerJoin() {
        PlayerMock player = server.addPlayer("Alex");
        player.assertSaid("Welcome to the server, Alex!");
    }
}
```
