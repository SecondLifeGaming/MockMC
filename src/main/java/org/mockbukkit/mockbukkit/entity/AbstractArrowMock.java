package org.mockbukkit.mockbukkit.entity;

import com.google.common.base.Preconditions;
import org.bukkit.Sound;
import org.bukkit.entity.AbstractArrow;
import org.jetbrains.annotations.NotNull;
import org.mockbukkit.mockbukkit.ServerMock;
import java.util.UUID;

@SuppressWarnings(
{"deprecation", "removal", "unchecked"})
public class AbstractArrowMock extends AbstractProjectileMock
		implements
			AbstractArrow,
			org.mockbukkit.mockbukkit.generated.org.bukkit.entity.AbstractArrowBaseMock
{

	private int knockbackStrength = 0;

	private double damage = -1;

	private int pierceLevel = 0;

	private boolean critical = false;

	private PickupStatus pickupStatus = PickupStatus.DISALLOWED;

	private boolean shotFromCrossbow = false;

	private int lifetime = 0;

	private Sound hitSound = Sound.ENTITY_ARROW_HIT;

	private boolean noPhysics = false;

	/**
	 * Constructs a new {@link AbstractArrowMock} on the provided {@link ServerMock}
	 * with a specified {@link UUID}.
	 *
	 * @param server
	 *            The server to create the entity on.
	 * @param uuid
	 *            The UUID of the entity.
	 */
	protected AbstractArrowMock(@NotNull ServerMock server, @NotNull UUID uuid)
	{
		super(server, uuid);
	}

	@Override
	public int getKnockbackStrength()
	{
		return this.knockbackStrength;
	}

	@Override
	public void setKnockbackStrength(int knockbackStrength)
	{
		Preconditions.checkArgument(knockbackStrength >= 0, "Knockback value (%s) cannot be negative",
				knockbackStrength);
		this.knockbackStrength = knockbackStrength;
	}

	@Override
	public double getDamage()
	{
		if (damage < 0)
		{
			return super.getEntityProperty("baseDamage").getAsDouble();
		}
		return this.damage;
	}

	@Override
	public void setDamage(double damage)
	{
		Preconditions.checkArgument(damage >= 0, "Damage value (%s) must be positive", damage);
		this.damage = damage;
	}

	@Override
	public int getPierceLevel()
	{
		return this.pierceLevel;
	}

	@Override
	public void setPierceLevel(int pierceLevel)
	{
		Preconditions.checkArgument(0 <= pierceLevel && pierceLevel <= Byte.MAX_VALUE,
				"Pierce level (%s) out of range, expected 0 < level < 127", pierceLevel);
		this.pierceLevel = pierceLevel;
	}

	@Override
	public boolean isCritical()
	{
		return this.critical;
	}

	@Override
	public void setCritical(boolean critical)
	{
		this.critical = critical;
	}

	@Override
	@NotNull
	public PickupStatus getPickupStatus()
	{
		return this.pickupStatus;
	}

	@Override
	public void setPickupStatus(@NotNull PickupStatus status)
	{
		Preconditions.checkArgument(status != null, "PickupStatus cannot be null");
		this.pickupStatus = status;
	}

	@Override
	public boolean isShotFromCrossbow()
	{
		return this.shotFromCrossbow;
	}

	@Override
	public void setShotFromCrossbow(boolean shotFromCrossbow)
	{
		this.shotFromCrossbow = shotFromCrossbow;
	}

	@Override
	public void setLifetimeTicks(int ticks)
	{
		this.lifetime = ticks;
	}

	@Override
	public int getLifetimeTicks()
	{
		return this.lifetime;
	}

	@Override
	@NotNull
	public Sound getHitSound()
	{
		return this.hitSound;
	}

	@Override
	public void setHitSound(@NotNull Sound sound)
	{
		Preconditions.checkArgument(sound != null, "Sound can not be null");
		this.hitSound = sound;
	}

	@Override
	public void setNoPhysics(boolean noPhysics)
	{
		this.noPhysics = noPhysics;
	}

	@Override
	public boolean hasNoPhysics()
	{
		return noPhysics;
	}
}
