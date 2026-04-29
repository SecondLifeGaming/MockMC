package org.mockmc.mockmc.entity;

import com.google.common.base.Preconditions;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.EvokerFangs;
import org.bukkit.entity.LivingEntity;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.mockmc.mockmc.ServerMock;
import java.util.UUID;

/**
 * Mock implementation of an {@link EvokerFangs}.
 *
 * @see EntityMock
 */
public class EvokerFangsMock extends EntityMock
		implements
			EvokerFangs,
			org.mockmc.mockmc.generated.org.bukkit.entity.EvokerFangsBaseMock
{

	private LivingEntity livingEntity;

	private int attackDelay;

	/**
	 * Constructs a new {@link EvokerFangsMock} on the provided {@link ServerMock}
	 * with a specified {@link UUID}.
	 *
	 * @param server
	 *            The server to create the entity on.
	 * @param uuid
	 *            The UUID of the entity.
	 */
	public EvokerFangsMock(@NotNull ServerMock server, @NotNull UUID uuid)
	{
		super(server, uuid);
	}

	@Override
	@Nullable
	public LivingEntity getOwner()
	{
		return this.livingEntity;
	}

	@Override
	public void setOwner(@Nullable LivingEntity owner)
	{
		this.livingEntity = owner;
	}

	@Override
	public int getAttackDelay()
	{
		return this.attackDelay;
	}

	@Override
	public void setAttackDelay(int delay)
	{
		Preconditions.checkArgument(delay >= 0, "Delay must be positive");
		this.attackDelay = delay;
	}

	@Override
	@NotNull
	public EntityType getType()
	{
		return EntityType.EVOKER_FANGS;
	}
}
