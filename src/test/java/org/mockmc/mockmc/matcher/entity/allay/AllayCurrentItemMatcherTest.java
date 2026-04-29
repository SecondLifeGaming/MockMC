package org.mockmc.mockmc.matcher.entity.allay;

import org.bukkit.Material;
import org.hamcrest.Matcher;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockmc.mockmc.MockMCExtension;
import org.mockmc.mockmc.MockMCInject;
import org.mockmc.mockmc.entity.AllayMock;
import org.mockmc.testutils.matcher.AbstractMatcherTest;

import static org.mockmc.mockmc.matcher.entity.allay.AllayCurrentItemMatcher.hasCurrentItem;

@ExtendWith(MockMCExtension.class)
class AllayCurrentItemMatcherTest extends AbstractMatcherTest
{

	@MockMCInject
	private AllayMock allay;

	@BeforeEach
	void setUp()
	{
		allay.simulatePlayerInteract(Material.STONE);
	}

	@Override
	protected Matcher<?> createMatcher()
	{
		return hasCurrentItem(Material.STONE);
	}

	@Test
	void testMatches()
	{
		assertMatches(hasCurrentItem(Material.STONE), allay);
	}

	@Test
	void testDoesNotMatch()
	{
		assertDoesNotMatch(hasCurrentItem(Material.AIR), allay);
	}

	@Test
	void testDoesNotMatchNull()
	{
		assertNullSafe(createMatcher());
	}

	@Test
	void unknownTypeSafe()
	{
		testCopesWithUnknownTypes();
	}

}
