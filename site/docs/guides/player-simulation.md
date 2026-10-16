# Simulating Players, Inventories & Events

MockMC provides state machines for in-game mechanics, allowing tests to verify complex gameplay logic without booting a full server.

## Player Simulation

Create virtual players, simulate chat, execute commands, and inspect sent messages:

```java
PlayerMock player = server.addPlayer("Steve");

// Check initial game mode
assertEquals(GameMode.SURVIVAL, player.getGameMode());

// Send message to player
player.sendMessage("Welcome!");

// Assert exact message received
player.assertSaid("Welcome!");
```

## Inventory Transactions & Content Matching

MockMC simulates container transactions, item stack counts, and custom item meta:

```java
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

PlayerMock player = server.addPlayer();
ItemStack diamondSword = new ItemStack(Material.DIAMOND_SWORD);
ItemMeta meta = diamondSword.getItemMeta();
meta.setDisplayName("Excalibur");
diamondSword.setItemMeta(meta);

player.getInventory().addItem(diamondSword);

assertTrue(player.getInventory().contains(Material.DIAMOND_SWORD));
```

## Event Firing & Listener Testing

Simulate Bukkit and Paper events through the `PluginManager`:

```java
import org.bukkit.event.player.PlayerJoinEvent;

PlayerJoinEvent event = new PlayerJoinEvent(player, Component.text("Steve joined"));
server.getPluginManager().callEvent(event);
```
