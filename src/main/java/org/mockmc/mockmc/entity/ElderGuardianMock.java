package org.mockmc.mockmc.entity;

import org.bukkit.entity.ElderGuardian;
import org.bukkit.entity.EntityType;
import org.jetbrains.annotations.NotNull;
import org.mockmc.mockmc.ServerMock;
import java.util.UUID;

/**
 * Mock implementation of a {@link ElderGuardian}.
 *
 * @see GuardianMock
 */
public class ElderGuardianMock extends GuardianMock
		implements
			ElderGuardian,
			org.mockmc.mockmc.generated.server.org.bukkit.entity.ElderGuardianBaseMock
{

	/**
	 * Constructs a new {@link ElderGuardianMock} on the provided {@link ServerMock}
	 * with a specified {@link UUID}.
	 *
	 * @param server
	 *            The server to create the entity on.
	 * @param uuid
	 *            The UUID of the entity.
	 */
	public ElderGuardianMock(@NotNull ServerMock server, @NotNull UUID uuid)
	{
		super(server, uuid);
	}

	@Override
	@NotNull
	public EntityType getType()
	{
		return EntityType.ELDER_GUARDIAN;
	}

	@Override
	public boolean isElder()
	{
		return true;
	}
}
