package org.mockmc.mockmc.matcher.entity.human;

import org.bukkit.event.inventory.InventoryType;
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

import static org.mockmc.mockmc.matcher.entity.human.HumanEntityInventoryViewTypeMatcher.hasInventoryViewType;

@ExtendWith(MockMCExtension.class)
class HumanEntityInventoryViewTypeMatcherTest extends AbstractMatcherTest
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
	void inventoryViewType_matches()
	{
		human.openInventory(inventoryMock);
		assertMatches(hasInventoryViewType(InventoryType.BARREL), human);
	}

	@Test
	void inventoryViewType_missMatch()
	{
		human.openInventory(inventoryMock);
		assertDoesNotMatch(hasInventoryViewType(InventoryType.LOOM), human);
	}

	@Test
	void inventoryViewType_noneSelected()
	{
		assertDoesNotMatch(hasInventoryViewType(InventoryType.CREATIVE), human);
	}

	@Test
	void isNullSafe()
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
		return hasInventoryViewType(InventoryType.CRAFTING);
	}

}
