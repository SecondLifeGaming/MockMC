package org.mockmc.mockmc.matcher.inventory.meta;

import org.hamcrest.Matcher;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockmc.mockmc.MockMCExtension;
import org.mockmc.mockmc.MockMCInject;
import org.mockmc.mockmc.inventory.meta.ItemMetaMock;
import org.mockmc.testutils.matcher.AbstractMatcherTest;

import java.util.List;

import static org.mockmc.mockmc.matcher.inventory.meta.ItemMetaAnyLoreMatcher.hasAnyLore;

@ExtendWith(MockMCExtension.class)
class ItemMetaAnyLoreMatcherTest extends AbstractMatcherTest
{

	@MockMCInject
	private ItemMetaMock itemMeta;

	@Test
	void hasAnyLore_matches()
	{
		itemMeta.setLore(List.of("Hello", "world!"));
		assertMatches(hasAnyLore(), itemMeta);
	}

	@Test
	void hasAnyLore_noLore()
	{
		assertDoesNotMatch(hasAnyLore(), itemMeta);
	}

	@Test
	void nullSafe()
	{
		testIsNullSafe();
	}

	@Override
	protected Matcher<?> createMatcher()
	{
		return hasAnyLore();
	}

}
