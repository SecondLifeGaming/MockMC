package org.mockmc.mockmc.entity;

import org.bukkit.entity.EntityType;
import org.bukkit.entity.SplashPotion;
import org.jetbrains.annotations.NotNull;
import org.mockmc.mockmc.ServerMock;
import java.util.UUID;

/**
 * Mock implementation of a {@link SplashPotion}.
 *
 * @see ThrownPotionMock
 */
public class SplashPotionMock extends ThrownPotionMock
		implements
			SplashPotion,
			org.mockmc.mockmc.generated.server.org.bukkit.entity.SplashPotionBaseMock
{

	/**
	 * Constructs a new {@link SplashPotion} on the provided {@link ServerMock} with
	 * a specified {@link UUID}.
	 *
	 * @param server
	 *            The server to create the entity on.
	 * @param uuid
	 *            The UUID of the entity.
	 */
	public SplashPotionMock(@NotNull ServerMock server, @NotNull UUID uuid)
	{
		super(server, uuid);
	}

	@Override
	@NotNull
	public EntityType getType()
	{
		return EntityType.SPLASH_POTION;
	}
}
