// Auto-generated mechanical sanity test for ServerBaseMock
package org.mockmc.mockmc.generated.server.org.bukkit;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.mockmc.mockmc.generated.GeneratedTestBase;

@SuppressWarnings(
{"deprecation", "removal", "java:S1874"})
class ServerBaseMockTest extends GeneratedTestBase
{
	@Test
	void testSafeDefaults()
	{
		ServerBaseMock mock = new Stub();
		assertNotNull(mock);
		assertSafeDefault(mock.getName());
		assertSafeDefault(mock.getUnsafe());
		assertSafeDefault(mock.getLogger());
		assertSafeDefault(mock.getVersion());
		assertSafeDefault(mock.recipeIterator());
		assertSafeDefault(mock.getWorlds());
		assertSafeDefault(mock.getBukkitVersion());
		assertSafeDefault(mock.getMinecraftVersion());
		assertSafeDefault(mock.getOnlinePlayers());
		assertSafeDefault(mock.getWorldType());
		assertSafeDefault(mock.getInitialEnabledPacks());
		assertSafeDefault(mock.getInitialDisabledPacks());
		assertSafeDefault(mock.getServerResourcePack());
		assertSafeDefault(mock.getServerTickManager());
		assertSafeDefault(mock.getPluginsFolder());
		assertSafeDefault(mock.getIp());
		assertSafeDefault(mock.getResourcePack());
		assertSafeDefault(mock.getResourcePackHash());
		assertSafeDefault(mock.getResourcePackPrompt());
		assertSafeDefault(mock.getWhitelistedPlayers());
	}

	@Test
	void testSafeDefaultsPart2()
	{
		ServerBaseMock mock = new Stub();
		assertNotNull(mock);
		assertSafeDefault(mock.getUpdateFolder());
		assertSafeDefault(mock.getUpdateFolderFile());
		assertSafeDefault(mock.getPluginManager());
		assertSafeDefault(mock.getScheduler());
		assertSafeDefault(mock.getServicesManager());
		assertSafeDefault(mock.createWorldBorder());
		assertSafeDefault(mock.getCommandAliases());
		assertSafeDefault(mock.getServerConfig());
		assertSafeDefault(mock.getIPBans());
		assertSafeDefault(mock.getBannedPlayers());
		assertSafeDefault(mock.getOperators());
		assertSafeDefault(mock.getDefaultGameMode());
		assertSafeDefault(mock.getConsoleSender());
		assertSafeDefault(mock.getWorldContainer());
		assertSafeDefault(mock.getOfflinePlayers());
		assertSafeDefault(mock.getMessenger());
		assertSafeDefault(mock.getHelpMap());
		assertSafeDefault(mock.createMerchant());
		assertSafeDefault(mock.motd());
		assertSafeDefault(mock.shutdownMessage());
	}

	@Test
	void testSafeDefaultsPart3()
	{
		ServerBaseMock mock = new Stub();
		assertNotNull(mock);
		assertSafeDefault(mock.getMotd());
		assertSafeDefault(mock.getServerLinks());
		assertSafeDefault(mock.getShutdownMessage());
		assertSafeDefault(mock.getWarningState());
		assertSafeDefault(mock.getItemFactory());
		assertSafeDefault(mock.getEntityFactory());
		assertSafeDefault(mock.getScoreboardManager());
		assertSafeDefault(mock.getServerIcon());
		assertSafeDefault(mock.getBossBars());
		assertSafeDefault(mock.getTPS());
		assertSafeDefault(mock.getTickTimes());
		assertSafeDefault(mock.advancementIterator());
		assertSafeDefault(mock.getStructureManager());
		assertSafeDefault(mock.getCommandMap());
		assertSafeDefault(mock.getPermissionMessage());
		assertSafeDefault(mock.permissionMessage());
		assertSafeDefault(mock.getMobGoals());
		assertSafeDefault(mock.getDatapackManager());
		assertSafeDefault(mock.getPotionBrewer());
		assertSafeDefault(mock.getRegionScheduler());
	}

	@Test
	void testSafeDefaultsPart4()
	{
		ServerBaseMock mock = new Stub();
		assertNotNull(mock);
		assertSafeDefault(mock.getAsyncScheduler());
		assertSafeDefault(mock.getGlobalRegionScheduler());
		assertSafeDefault(mock.spigot());
		assertSafeDefault(mock.getRespawnWorld());
		assertSafeDefault(mock.getLevelDirectory());
	}

	private static class Stub implements ServerBaseMock
	{
	}
}
