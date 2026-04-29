package org.mockmc.mockmc.entity;

import org.bukkit.entity.Player;
import org.bukkit.entity.Skeleton;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockmc.mockmc.MockMCExtension;
import org.mockmc.mockmc.MockMCInject;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockmc.mockmc.matcher.entity.ranged.RangedEntityAttackMatcher.hasAttacked;
import static org.mockmc.mockmc.matcher.entity.ranged.RangedEntityAttackMatcher.hasNotAttacked;

@SuppressWarnings(
{"deprecation", "removal", "unchecked"})
@ExtendWith(MockMCExtension.class)
class AbstractSkeletonMockTest
{

	@MockMCInject
	private AbstractSkeletonMock skeleton;

	@Test
	void testSetSkeletonTypeThrows()
	{
		assertThrows(UnsupportedOperationException.class, () -> skeleton.setSkeletonType(Skeleton.SkeletonType.NORMAL));
	}

	@Test
	void testShouldBurnInDayDefault()
	{
		assertTrue(skeleton.shouldBurnInDay());
	}

	@Test
	void testSetShouldBurnInDay()
	{
		skeleton.setShouldBurnInDay(false);
		assertFalse(skeleton.shouldBurnInDay());
	}

	@Test
	void testIsChargingAttackDefault()
	{
		assertFalse(skeleton.isChargingAttack());
	}

	@Test
	void testSetChargingAttack()
	{
		skeleton.setChargingAttack(true);
		assertTrue(skeleton.isChargingAttack());
	}

	@Test
	void testRangedAttack(@MockMCInject Player player)
	{
		skeleton.rangedAttack(player, 0.5f);
		assertThat(skeleton, hasAttacked(player, 0.5f));
	}

	@Test
	void testRangedAttackThrowsWithNullEntity()
	{
		assertThrows(NullPointerException.class, () -> skeleton.rangedAttack(null, 0.5f));
	}

	@Test
	void testRangedAttackThrowsWithInvalidCharge(@MockMCInject Player player)
	{
		assertThrows(IllegalArgumentException.class, () -> skeleton.rangedAttack(player, -0.5f));
		assertThrows(IllegalArgumentException.class, () -> skeleton.rangedAttack(player, 1.5f));
	}

	@Test
	void testAssertAttackedThrowsWithNoAttack(@MockMCInject Player player)
	{
		assertThat(skeleton, hasNotAttacked(player, 0.5f));
	}

	@Test
	void testAssertAttackThrowsWithInvalidCharge(@MockMCInject Player player)
	{
		skeleton.rangedAttack(player, 0.5f);

		assertThrows(IllegalArgumentException.class, () -> skeleton.hasAttackedWithCharge(player, -0.5f));
		assertThrows(IllegalArgumentException.class, () -> skeleton.hasAttackedWithCharge(player, 1.5f));
	}

	@Test
	void testAssertAttackThrowsWithWrongCharge(@MockMCInject Player player)
	{
		skeleton.rangedAttack(player, 0.5f);

		assertThat(skeleton, hasNotAttacked(player, 0.6f));
	}

	@Test
	void testAssertAgressiveAttack(@MockMCInject Player player)
	{
		skeleton.setChargingAttack(true);
		skeleton.rangedAttack(player, 0.5f);
		assertThat(skeleton, hasAttacked(player, 0.5f, true));
	}

	@Test
	void testAssertAgressiveAttackThrowsWhenNotAgressive(@MockMCInject Player player)
	{
		skeleton.rangedAttack(player, 0.5f);
		assertThat(skeleton, hasNotAttacked(player, 0.5f, true));
	}

}
