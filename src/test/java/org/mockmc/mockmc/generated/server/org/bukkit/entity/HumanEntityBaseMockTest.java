// Auto-generated mechanical sanity test for HumanEntityBaseMock
package org.mockmc.mockmc.generated.server.org.bukkit.entity;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.mockmc.mockmc.generated.GeneratedTestBase;

@SuppressWarnings(
{"deprecation", "java:S1874"})
class HumanEntityBaseMockTest extends GeneratedTestBase
{
	@Test
	void testSafeDefaults()
	{
		HumanEntityBaseMock mock = new Stub();
		assertNotNull(mock);
		assertSafeDefault(mock.getName());
		assertSafeDefault(mock.getLastDeathLocation());
		assertSafeDefault(mock.getInventory());
		assertSafeDefault(mock.getEquipment());
		assertSafeDefault(mock.getItemInHand());
		assertSafeDefault(mock.getEnderChest());
		assertSafeDefault(mock.getOpenInventory());
		assertSafeDefault(mock.getMainHand());
		assertSafeDefault(mock.getPotentialRespawnLocation());
		assertSafeDefault(mock.getItemOnCursor());
		assertSafeDefault(mock.getPotentialBedLocation());
		assertSafeDefault(mock.getFishHook());
		assertSafeDefault(mock.getBedLocation());
		assertSafeDefault(mock.getGameMode());
		assertSafeDefault(mock.releaseLeftShoulderEntity());
		assertSafeDefault(mock.releaseRightShoulderEntity());
		assertSafeDefault(mock.getDiscoveredRecipes());
		assertSafeDefault(mock.getShoulderEntityLeft());
		assertSafeDefault(mock.getShoulderEntityRight());
	}

	private static class Stub implements HumanEntityBaseMock
	{
	}
}
