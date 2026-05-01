package org.mockmc.mockmc.entity;

import org.bukkit.entity.EntityType;
import org.bukkit.entity.Nautilus;
import org.jspecify.annotations.NullMarked;
import org.mockmc.mockmc.ServerMock;
import java.util.UUID;

/**
 * Mock implementation of an {@link Nautilus}.
 *
 * @see AbstractNautilusMock
 */
@NullMarked
public class NautilusMock extends AbstractNautilusMock
		implements
			Nautilus,
			org.mockmc.mockmc.generated.server.org.bukkit.entity.NautilusBaseMock
{

	/**
	 * Constructs a new {@link Nautilus} on the provided {@link ServerMock} with a
	 * specified {@link UUID}.
	 *
	 * @param server
	 *            The server to create the entity on.
	 * @param uuid
	 *            The UUID of the entity.
	 */
	public NautilusMock(ServerMock server, UUID uuid)
	{
		super(server, uuid);
	}

	@Override
	public EntityType getType()
	{
		return EntityType.NAUTILUS;
	}
}
