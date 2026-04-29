package org.mockmc.mockmc.entity;

import org.bukkit.entity.Ambient;
import org.jetbrains.annotations.NotNull;
import org.mockmc.mockmc.ServerMock;
import java.util.UUID;

/**
 * Mock implementation of an {@link Ambient}.
 *
 * @see MobMock
 */
public class AmbientMock extends MobMock
		implements
			Ambient,
			org.mockmc.mockmc.generated.org.bukkit.entity.AmbientBaseMock
{

	/**
	 * Constructs a new {@link AmbientMock} on the provided {@link ServerMock} with
	 * a specified {@link UUID}.
	 *
	 * @param server
	 *            The server to create the entity on.
	 * @param uuid
	 *            The UUID of the entity.
	 */
	public AmbientMock(@NotNull ServerMock server, @NotNull UUID uuid)
	{
		super(server, uuid);
	}

	@Override
	@NotNull
	public String toString()
	{
		return "AmbientMock";
	}
}
