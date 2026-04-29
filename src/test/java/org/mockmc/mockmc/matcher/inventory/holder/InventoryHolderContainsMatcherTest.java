package org.mockmc.mockmc.matcher.inventory.holder;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.hamcrest.Matcher;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockmc.mockmc.MockMCExtension;
import org.mockmc.mockmc.MockMCInject;
import org.mockmc.mockmc.ServerMock;
import org.mockmc.mockmc.entity.AllayMock;
import org.mockmc.testutils.matcher.AbstractMatcherTest;

import static org.mockmc.mockmc.matcher.inventory.holder.InventoryHolderContainsMatcher.hasItemInInventory;

@ExtendWith(MockMCExtension.class)
class InventoryHolderContainsMatcherTest extends AbstractMatcherTest
{

	@MockMCInject
	private ServerMock server;
	@MockMCInject
	private AllayMock allay;
	private static final ItemStack stack = new ItemStack(Material.STONE);

	@BeforeEach
	void setUp()
	{
		allay.simulatePlayerInteract(Material.STONE);
		allay.simulateItemPickup(stack);
	}

	@Test
	void testMatches()
	{
		assertMatches(hasItemInInventory(stack), allay);
	}

	@Test
	void testDoesNotMatch()
	{
		assertDoesNotMatch(hasItemInInventory(new ItemStack(Material.DIAMOND)), allay);
	}

	@Test
	void testDoesNotMatchNull()
	{
		assertNullSafe(createMatcher());
	}

	@Override
	protected Matcher<?> createMatcher()
	{
		return hasItemInInventory(stack);
	}

}
