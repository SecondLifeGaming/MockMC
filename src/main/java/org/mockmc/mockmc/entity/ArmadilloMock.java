package org.mockmc.mockmc.entity;

import org.bukkit.entity.Armadillo;
import org.bukkit.entity.EntityType;
import org.jetbrains.annotations.NotNull;
import org.mockmc.mockmc.ServerMock;
import java.util.UUID;

/**
 * Mock implementation of an {@link Armadillo}.
 *
 * @see AnimalsMock
 * @mockmc.version 1.21-1.0.0
 */
public class ArmadilloMock extends AnimalsMock
		implements
			Armadillo,
			org.mockmc.mockmc.generated.server.org.bukkit.entity.ArmadilloBaseMock
{

	private State state = State.IDLE;

	/**
	 * Constructs a new Armadillo on the provided {@link ServerMock} with a
	 * specified {@link UUID}.
	 *
	 * @param server
	 *            The server to create the entity on.
	 * @param uuid
	 *            The UUID of the entity.
	 */
	public ArmadilloMock(@NotNull ServerMock server, @NotNull UUID uuid)
	{
		super(server, uuid);
	}

	@Override
	public void rollUp()
	{
		this.state = State.SCARED;
	}

	@Override
	public void rollOut()
	{
		this.state = State.IDLE;
	}

	@Override
	@NotNull
	public State getState()
	{
		return this.state;
	}

	public void setState(@NotNull State state)
	{
		this.state = state;
	}

	@Override
	@NotNull
	public EntityType getType()
	{
		return EntityType.ARMADILLO;
	}

}
