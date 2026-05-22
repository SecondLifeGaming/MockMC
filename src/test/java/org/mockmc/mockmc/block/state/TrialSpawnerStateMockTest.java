package org.mockmc.mockmc.block.state;

import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.loot.LootContext;
import org.bukkit.loot.LootTable;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockmc.mockmc.MockMCExtension;
import org.mockmc.mockmc.MockMCInject;

import java.util.Collection;
import java.util.Collections;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

@ExtendWith(MockMCExtension.class)
class TrialSpawnerStateMockTest
{

	private static class LootTableMock implements LootTable
	{

		@Override
		public @NotNull Collection<ItemStack> populateLoot(@Nullable Random random, @NotNull LootContext context)
		{
			return Collections.emptyList();
		}

		@Override
		public void fillInventory(@NotNull Inventory inventory, @Nullable Random random, @NotNull LootContext context)
		{
			// No-op for mock implementation
		}

		@Override
		public @NotNull NamespacedKey getKey()
		{
			return NamespacedKey.minecraft("mock");
		}
	}

	@MockMCInject
	private TrialSpawnerStateMock trialSpawner;

	@BeforeEach
	void setUp()
	{
		trialSpawner = new TrialSpawnerStateMock(Material.TRIAL_SPAWNER);
	}

	@Test
	void testGetCooldownEndDefault()
	{
		assertEquals(0L, trialSpawner.getCooldownEnd());
	}

	@Test
	void testSetCooldownEnd()
	{
		trialSpawner.setCooldownEnd(100L);
		assertEquals(100L, trialSpawner.getCooldownEnd());
	}

	@Test
	void testGetCooldownLengthDefault()
	{
		assertEquals(3600, trialSpawner.getCooldownLength());
	}

	@Test
	void testSetCooldownLength()
	{
		trialSpawner.setCooldownLength(7200);
		assertEquals(7200, trialSpawner.getCooldownLength());
	}

	@Test
	void testIsOminousDefault()
	{
		assertFalse(trialSpawner.isOminous());
	}

	@Test
	void testSetOminous()
	{
		trialSpawner.setOminous(true);
		assertTrue(trialSpawner.isOminous());
	}

	@Test
	void testGetNormalConfiguration()
	{
		assertNotNull(trialSpawner.getNormalConfiguration());
	}

	@Test
	void testGetOminousConfiguration()
	{
		assertNotNull(trialSpawner.getOminousConfiguration());
	}

	@Test
	void testRewardsTable()
	{
		LootTable table = new LootTableMock();
		trialSpawner.getNormalConfiguration().addPossibleReward(table, 10);
		assertEquals(1, trialSpawner.getNormalConfiguration().getPossibleRewards().size());
		assertTrue(trialSpawner.getNormalConfiguration().getPossibleRewards().containsKey(table));
	}
}
