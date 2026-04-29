package org.mockmc.mockmc.matcher.entity.player;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.hamcrest.Matcher;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockmc.mockmc.MockMCExtension;
import org.mockmc.mockmc.MockMCInject;
import org.mockmc.mockmc.ServerMock;
import org.mockmc.mockmc.entity.PlayerMock;
import org.mockmc.testutils.matcher.AbstractMatcherTest;

import static org.mockmc.mockmc.matcher.entity.player.PlayerConsumeItemMatcher.hasConsumed;

@SuppressWarnings(
{"deprecation", "removal", "unchecked"})
@ExtendWith(MockMCExtension.class)
class PlayerConsumeItemMatcherTest extends AbstractMatcherTest
{

	@MockMCInject
	private ServerMock serverMock;
	@MockMCInject
	private PlayerMock player;
	private ItemStack item;

	@BeforeEach
	void setUp()
	{
		this.item = new ItemStack(Material.POTATO);
	}

	@Test
	void consumed_matches()
	{
		player.simulateConsumeItem(item);
		assertMatches(hasConsumed(item), player);
	}

	@Test
	void consumed_differentItem()
	{
		player.simulateConsumeItem(new ItemStack(Material.APPLE));
		assertDoesNotMatch(hasConsumed(item), player);
	}

	@Test
	void consumed_noItem()
	{
		assertDoesNotMatch(hasConsumed(item), player);
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
		return hasConsumed(item);
	}

}
