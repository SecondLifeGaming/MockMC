package org.mockmc.mockmc.matcher.entity.skeleton;

import org.hamcrest.Matcher;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockmc.mockmc.MockMCExtension;
import org.mockmc.mockmc.MockMCInject;
import org.mockmc.mockmc.ServerMock;
import org.mockmc.mockmc.entity.PlayerMock;
import org.mockmc.mockmc.entity.SkeletonMock;
import org.mockmc.testutils.matcher.AbstractMatcherTest;

import static org.mockmc.mockmc.matcher.entity.ranged.RangedEntityAttackMatcher.hasAttacked;

@ExtendWith(MockMCExtension.class)
class RangedEntityAttackMatcherTest extends AbstractMatcherTest
{

	private static final float CHARGE = 0.5f;

	@MockMCInject
	private ServerMock serverMock;
	@MockMCInject
	private PlayerMock target;
	@MockMCInject
	private SkeletonMock skeleton;

	@Test
	void attacked()
	{
		skeleton.rangedAttack(target, CHARGE);
		assertMatches(hasAttacked(target, CHARGE), skeleton);
	}

	@Test
	void notAttacked()
	{
		assertDoesNotMatch(hasAttacked(target, CHARGE), skeleton);
	}

	@Test
	void attacked_wrongCharge()
	{
		skeleton.rangedAttack(target, CHARGE);
		assertDoesNotMatch(hasAttacked(target, 0.4f), skeleton);
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
		return hasAttacked(target, CHARGE);
	}

}
