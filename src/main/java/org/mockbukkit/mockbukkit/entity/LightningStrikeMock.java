package org.mockbukkit.mockbukkit.entity;

import com.google.common.base.Preconditions;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LightningStrike;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.mockbukkit.mockbukkit.ServerMock;
import java.util.UUID;

/**
 * Mock implementation of a {@link LightningStrike}.
 *
 * @see EntityMock
 */
@SuppressWarnings(
{"removal"})
public class LightningStrikeMock extends EntityMock
		implements
			org.mockbukkit.mockbukkit.generated.org.bukkit.entity.LightningStrikeBaseMock
{

	private boolean isEffect = false;

	private int numberOfFlashes = 3;

	private int ticksLived = 0;

	@Nullable
	private Player causingPlayer = null;

	/**
	 * Constructs a new {@link LightningStrikeMock} on the provided
	 * {@link ServerMock} with a specified {@link UUID}.
	 *
	 * @param server
	 *            The server to create the entity on.
	 * @param uuid
	 *            The UUID of the entity.
	 */
	public LightningStrikeMock(@NotNull ServerMock server, @NotNull UUID uuid)
	{
		super(server, uuid);
	}

	/**
	 * @mockbukkit.version 1.21-1.0.0
	 */
	@Override
	public boolean isEffect()
	{
		return this.isEffect;
	}

	/**
	 * Set whether the strike is an effect that does no damage.
	 *
	 * @param effect
	 *            {@code true} if the strike is an effect, otherwise {@code false}
	 */
	public void setEffect(boolean effect)
	{
		this.isEffect = effect;
	}

	/**
	 * @deprecated Use modern API instead.
	 */
	@Override
	@Deprecated(forRemoval = true)
	public int getFlashes()
	{
		return this.getFlashCount();
	}

	/**
	 * @deprecated Use modern API instead.
	 */
	@Override
	@Deprecated(forRemoval = true)
	public void setFlashes(int flashes)
	{
		this.setFlashCount(flashes);
	}

	/**
	 * @mockbukkit.version 1.21-1.0.0
	 */
	@Override
	public int getLifeTicks()
	{
		return this.ticksLived;
	}

	@Override
	public void setLifeTicks(int ticks)
	{
		this.ticksLived = ticks;
	}

	@Override
	@Nullable
	public Player getCausingPlayer()
	{
		return this.causingPlayer;
	}

	/**
	 * @mockbukkit.version 1.21-1.0.0
	 */
	@Override
	public void setCausingPlayer(@Nullable Player player)
	{
		this.causingPlayer = player;
	}

	@Override
	public int getFlashCount()
	{
		return this.numberOfFlashes;
	}

	@Override
	public void setFlashCount(int flashes)
	{
		Preconditions.checkArgument(flashes >= 1, "flashes must be >= 1");
		this.numberOfFlashes = flashes;
	}

	@Override
	@Nullable
	public Entity getCausingEntity()
	{
		return this.getCausingPlayer();
	}

	@Override
	@NotNull
	public EntityType getType()
	{
		return EntityType.LIGHTNING_BOLT;
	}

	/**
	 * @deprecated Use modern API instead.
	 */
	@Override
	@Deprecated(forRemoval = true)
	@NotNull
	public LightningStrike.Spigot spigot()
	{
		return org.mockbukkit.mockbukkit.generated.org.bukkit.entity.LightningStrikeBaseMock.super.spigot();
	}
}
