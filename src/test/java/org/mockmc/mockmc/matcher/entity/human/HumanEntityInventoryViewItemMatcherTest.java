package org.mockmc.mockmc.matcher.entity.human;

import org.bukkit.Material;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.ItemStack;
import org.hamcrest.Matcher;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockmc.mockmc.MockMCExtension;
import org.mockmc.mockmc.MockMCInject;
import org.mockmc.mockmc.ServerMock;
import org.mockmc.mockmc.entity.HumanEntityMock;
import org.mockmc.mockmc.inventory.InventoryMock;
import org.mockmc.testutils.matcher.AbstractMatcherTest;

import static org.mockmc.mockmc.matcher.entity.human.HumanEntityInventoryViewItemMatcher.hasItemInInventoryView;

@ExtendWith(MockMCExtension.class)
class HumanEntityInventoryViewItemMatcherTest extends AbstractMatcherTest
{

	@MockMCInject
	private ServerMock serverMock;
	@MockMCInject
	private HumanEntityMock human;
	private InventoryMock inventoryMock;

	@BeforeEach
	void setUp()
	{
		this.inventoryMock = serverMock.createInventory(human, InventoryType.BARREL);
	}

	@Test
	void itemInInventory_matches()
	{
		human.openInventory(inventoryMock);
		inventoryMock.addItem(new ItemStack(Material.DIAMOND));
		assertMatches(hasItemInInventoryView(Material.DIAMOND), human);
	}

	@Test
	void itemInInventory_noMatch()
	{
		human.openInventory(inventoryMock);
		inventoryMock.addItem(new ItemStack(Material.DIRT));
		assertDoesNotMatch(hasItemInInventoryView(Material.DIAMOND_BLOCK), human);
	}

	@Test
	void itemInInventory_noView()
	{
		assertDoesNotMatch(hasItemInInventoryView(Material.BEACON), human);
	}

	@Test
	void nullSafe()
	{
		testIsNullSafe();
	}

	@Test
	void unknownTypeSafe()
	{
		testCopesWithUnknownTypes();
	}

	@Override
	protected Matcher<?> createMatcher()
	{
		return hasItemInInventoryView(Material.APPLE);
	}

}
