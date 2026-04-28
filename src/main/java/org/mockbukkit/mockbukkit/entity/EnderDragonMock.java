package org.mockbukkit.mockbukkit.entity;

import org.bukkit.Location;
import org.bukkit.boss.DragonBattle;
import org.bukkit.entity.EnderDragon;
import org.bukkit.entity.EntityType;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.mockbukkit.mockbukkit.ServerMock;
import java.util.UUID;

public class EnderDragonMock extends AbstractBossMock
		implements
			EnderDragon,
			org.mockbukkit.mockbukkit.generated.org.bukkit.entity.EnderDragonBaseMock
{

	private Location podium = new Location(getWorld(), 0, 0, 0);

	private Phase phase = Phase.HOVER;

	private final DragonBattle battle;

	/**
	 * Constructs a new {@link MonsterMock} on the provided {@link ServerMock} with
	 * a specified {@link UUID}.
	 *
	 * @param server
	 *            The server to create the entity on.
	 * @param uuid
	 *            The UUID of the entity.
	 */
	public EnderDragonMock(@NotNull ServerMock server, @NotNull UUID uuid)
	{
		super(server, uuid, "Ender Dragon");
		battle = new DragonBattleMock(this);
		this.setMaxHealth(200.0D);
		this.setHealth(200.0D);
	}

	@NotNull
	@Override
	public Phase getPhase()
	{
		return phase;
	}

	@Override
	public void setPhase(@NotNull EnderDragon.Phase phase)
	{
		this.phase = phase;
	}

	@Override
	@Nullable
	public DragonBattle getDragonBattle()
	{
		return battle;
	}

	@Override
	@NotNull
	public Location getPodium()
	{
		return podium;
	}

	@Override
	public void setPodium(@Nullable Location location)
	{
		podium = location;
	}

	@Override
	@NotNull
	public EntityType getType()
	{
		return EntityType.ENDER_DRAGON;
	}
}
