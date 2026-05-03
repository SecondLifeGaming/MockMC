package org.mockmc.mockmc.entity;

import org.bukkit.entity.Endermite;
import org.bukkit.entity.EntityType;
import org.jetbrains.annotations.NotNull;
import org.mockmc.mockmc.ServerMock;
import java.util.UUID;

@SuppressWarnings(
{"deprecation", "removal", "unchecked"})
public class EndermiteMock extends MonsterMock
		implements
			Endermite,
			org.mockmc.mockmc.generated.server.org.bukkit.entity.EndermiteBaseMock
{

	private int life = 0;

	/**
	 * Constructs a new {@link EndermiteMock} on the provided {@link ServerMock}
	 * with a specified {@link UUID}.
	 *
	 * @param server
	 *            The server to create the entity on.
	 * @param uuid
	 *            The UUID of the entity.
	 */
	public EndermiteMock(@NotNull ServerMock server, @NotNull UUID uuid)
	{
		super(server, uuid);
	}

	/**
	 * @deprecated No function anymore.
	 */
	@Override
	@Deprecated(since = "1.17")
	public boolean isPlayerSpawned()
	{
		return false;
	}

	/**
	 * @deprecated No function anymore.
	 */
	@Override
	@Deprecated(since = "1.17")
	public void setPlayerSpawned(boolean playerSpawned)
	{
		// Nop
	}

	@Override
	public void setLifetimeTicks(int ticks)
	{
		this.life = ticks;
	}

	@Override
	public int getLifetimeTicks()
	{
		return this.life;
	}

	@Override
	@NotNull
	public EntityType getType()
	{
		return EntityType.ENDERMITE;
	}
}
