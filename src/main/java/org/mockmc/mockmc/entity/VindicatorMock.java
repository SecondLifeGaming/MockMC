package org.mockmc.mockmc.entity;

import org.bukkit.Sound;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Vindicator;
import org.jetbrains.annotations.NotNull;
import org.mockmc.mockmc.ServerMock;
import java.util.UUID;

/**
 * Mock implementation of a {@link Vindicator}.
 *
 * @see IllagerMock
 */
public class VindicatorMock extends IllagerMock
		implements
			Vindicator,
			org.mockmc.mockmc.generated.org.bukkit.entity.VindicatorBaseMock
{

	private boolean johnny = false;

	/**
	 * Constructs a new {@link VindicatorMock} on the provided {@link ServerMock}
	 * with a specified {@link UUID}.
	 *
	 * @param server
	 *            The server to create the entity on.
	 * @param uuid
	 *            The UUID of the entity.
	 */
	public VindicatorMock(@NotNull ServerMock server, @NotNull UUID uuid)
	{
		super(server, uuid);
	}

	@Override
	public boolean isJohnny()
	{
		return this.johnny;
	}

	@Override
	public void setJohnny(boolean johnny)
	{
		this.johnny = johnny;
	}

	@Override
	@NotNull
	public Sound getCelebrationSound()
	{
		return Sound.ENTITY_VINDICATOR_CELEBRATE;
	}

	@Override
	@NotNull
	public EntityType getType()
	{
		return EntityType.VINDICATOR;
	}
}
