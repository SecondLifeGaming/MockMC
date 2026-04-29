package org.mockmc.mockmc.matcher.entity.goat;

import org.bukkit.entity.LivingEntity;
import org.hamcrest.Matcher;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockmc.mockmc.MockMCExtension;
import org.mockmc.mockmc.MockMCInject;
import org.mockmc.mockmc.ServerMock;
import org.mockmc.mockmc.entity.GoatMock;
import org.mockmc.testutils.matcher.AbstractMatcherTest;

import static org.mockmc.mockmc.matcher.entity.goat.GoatEntityRammedMatcher.hasRammed;

@ExtendWith(MockMCExtension.class)
class GoatEntityRammedMatcherTest extends AbstractMatcherTest
{

	@MockMCInject
	private ServerMock serverMock;
	@MockMCInject
	private GoatMock goatMock;
	@MockMCInject
	private LivingEntity target;

	@Test
	void rammed()
	{
		goatMock.ram(target);
		assertMatches(hasRammed(target), goatMock);
	}

	@Test
	void notRammed()
	{
		assertDoesNotMatch(hasRammed(target), goatMock);
	}

	@Test
	void rammed_differentTarget()
	{
		LivingEntity differentTarget = serverMock.addPlayer();
		goatMock.ram(differentTarget);
		assertDoesNotMatch(hasRammed(target), goatMock);
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
		return hasRammed(target);
	}

}
