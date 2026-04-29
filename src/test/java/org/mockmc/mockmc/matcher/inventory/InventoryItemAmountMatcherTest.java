package org.mockmc.mockmc.matcher.inventory;

import org.bukkit.Material;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.inventory.ItemStack;
import org.hamcrest.Matcher;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockmc.mockmc.MockMCExtension;
import org.mockmc.mockmc.MockMCInject;
import org.mockmc.mockmc.ServerMock;
import org.mockmc.mockmc.inventory.InventoryMock;
import org.mockmc.testutils.matcher.AbstractMatcherTest;

import static org.mockmc.mockmc.matcher.inventory.InventoryItemAmountMatcher.containsAtLeast;

@ExtendWith(MockMCExtension.class)
class InventoryItemAmountMatcherTest extends AbstractMatcherTest
{

	@MockMCInject
	private ServerMock serverMock;
	@MockMCInject
	private InventoryHolder inventoryHolder;
	private InventoryMock inventory;
	private ItemStack item;

	@BeforeEach
	void setUp()
	{
		this.inventory = new InventoryMock(inventoryHolder, InventoryType.ANVIL);
		this.item = new ItemStack(Material.DIRT);
	}

	@Test
	void containsAtLeast_matches()
	{
		inventory.addItem(item);
		assertMatches(containsAtLeast(item, 1), inventory);
	}

	@Test
	void containsAtLeast_wrongItem()
	{
		inventory.addItem(new ItemStack(Material.APPLE));
		assertDoesNotMatch(containsAtLeast(item, 1), inventory);
	}

	@Test
	void containsAtLeast_emptyInventory()
	{
		assertDoesNotMatch(containsAtLeast(item, 1), inventory);
	}

	@Test
	void containsAtLeast_matchesSpecifiedAmount()
	{
		inventory.addItem(new ItemStack(Material.DIRT, 2));
		inventory.addItem(new ItemStack(Material.DIRT, 3));
		assertMatches(containsAtLeast(item, 5), inventory);
	}

	@Test
	void nullSafe()
	{
		testIsNullSafe();
	}

	@Override
	protected Matcher<?> createMatcher()
	{
		return containsAtLeast(item, 1);
	}

}
