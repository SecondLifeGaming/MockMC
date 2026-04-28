package org.mockbukkit.mockbukkit.entity;

import com.destroystokyo.paper.entity.RangedEntity;
import org.bukkit.entity.LivingEntity;
import org.mockbukkit.mockbukkit.exception.UnimplementedOperationException;

/**
 * Mock implementation of a {@link RangedEntity}.
 *
 * @see MobMock
 */
@SuppressWarnings(
{"deprecation", "removal", "unchecked"})
public interface MockRangedEntity<T extends MobMock>
		extends
			RangedEntity,
			org.mockbukkit.mockbukkit.generated.com.destroystokyo.paper.entity.RangedEntityBaseMock
{

	/**
	 * Whether this ranged entity has attacked another entity with specified charge
	 *
	 * @param target
	 *            The entity to have been attacked
	 * @param charge
	 *            The charge during the attack
	 * @return True if the ranged entity has attacked another entity with specified
	 *         charge
	 */
	default boolean hasAttackedWithCharge(LivingEntity target, float charge)
	{
		throw new UnimplementedOperationException();
	}

	/**
	 * Whether this ranged entity has attacked another entity while aggressive
	 *
	 * @param target
	 *            The entity to have been attacked
	 * @return True if the ranged entity has attacked another entity while
	 *         aggressive
	 */
	default boolean hasAttackedWhileAggressive(LivingEntity target)
	{
		throw new UnimplementedOperationException();
	}

}
