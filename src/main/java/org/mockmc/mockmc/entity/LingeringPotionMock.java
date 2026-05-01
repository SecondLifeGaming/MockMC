package org.mockmc.mockmc.entity;

import org.bukkit.entity.EntityType;
import org.bukkit.entity.LingeringPotion;
import org.jetbrains.annotations.NotNull;
import org.mockmc.mockmc.ServerMock;
import java.util.UUID;

/**
 * Mock implementation of a {@link LingeringPotion}.
 *
 * @see ThrownPotionMock
 */
public class LingeringPotionMock extends ThrownPotionMock
		implements
			LingeringPotion,
			org.mockmc.mockmc.generated.server.org.bukkit.entity.LingeringPotionBaseMock
{

	/**
	 * Constructs a new {@link LingeringPotion} on the provided {@link ServerMock}
	 * with a specified {@link UUID}.
	 *
	 * @param server
	 *            The server to create the entity on.
	 * @param uuid
	 *            The UUID of the entity.
	 */
	public LingeringPotionMock(@NotNull ServerMock server, @NotNull UUID uuid)
	{
		super(server, uuid);
	}

	@Override
	@NotNull
	public EntityType getType()
	{
		return EntityType.LINGERING_POTION;
	}
}
