package org.mockbukkit.mockbukkit.entity;

import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Mob;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.mockbukkit.mockbukkit.ServerMock;
import java.util.UUID;
import java.util.concurrent.ThreadLocalRandom;
import org.mockbukkit.mockbukkit.generated.org.bukkit.entity.MobBaseMock;

/**
 * Mock implementation of a {@link Mob}.
 *
 * @see LivingEntityMock
 */
public abstract class MobMock extends LivingEntityMock implements Mob, MobBaseMock
{

	private boolean aware = true;

	private boolean leftHanded;

	private LivingEntity target;

	/**
	 * Constructs a new {@link MobMock} on the provided {@link ServerMock} with a
	 * specified {@link UUID}.
	 *
	 * @param server
	 *            The server to create the entity on.
	 * @param uuid
	 *            The UUID of the entity.
	 */
	protected MobMock(@NotNull ServerMock server, @NotNull UUID uuid)
	{
		super(server, uuid);
	}

	@Override
	public void setTarget(@Nullable LivingEntity target)
	{
		this.target = target;
	}

	@Nullable
	@Override
	public LivingEntity getTarget()
	{
		return this.target;
	}

	@Override
	public void setAware(boolean aware)
	{
		this.aware = aware;
	}

	@Override
	public boolean isAware()
	{
		return this.aware;
	}

	@Override
	public boolean isLeftHanded()
	{
		return this.leftHanded;
	}

	@Override
	public void setLeftHanded(boolean leftHanded)
	{
		this.leftHanded = leftHanded;
	}

	/**
	 * Called immediately after the entity is spawned.
	 */
	public void finalizeSpawn()
	{
		/*
		 * TODO: Unimplemented (#354)
		 * this.registerAttribute(Attribute.GENERIC_FOLLOW_RANGE);
		 * this.getAttribute(Attribute.GENERIC_FOLLOW_RANGE).addModifier(new
		 * AttributeModifier("Random spawn bonus",
		 * ThreadLocalRandom.current().nextGaussian() * 0.05D,
		 * AttributeModifier.Operation.MULTIPLY_SCALAR_1));
		 */
		this.setLeftHanded(ThreadLocalRandom.current().nextFloat() < 0.05F);
	}

}
