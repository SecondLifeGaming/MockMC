package org.mockbukkit.mockbukkit.entity;

import org.bukkit.Sound;
import org.bukkit.entity.Golem;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.mockbukkit.mockbukkit.ServerMock;
import java.util.UUID;

/**
 * Mock implementation of a {@link Golem}.
 *
 * @see CreatureMock
 */
public class GolemMock extends CreatureMock
		implements
			Golem,
			org.mockbukkit.mockbukkit.generated.org.bukkit.entity.GolemBaseMock
{

	/**
	 * Constructs a new {@link GolemMock} on the provided {@link ServerMock} with a
	 * specified {@link UUID}.
	 *
	 * @param server
	 *            The server to create the entity on.
	 * @param uuid
	 *            The UUID of the entity.
	 */
	protected GolemMock(@NotNull ServerMock server, @NotNull UUID uuid)
	{
		super(server, uuid);
	}

	@Override
	@Nullable
	public Sound getAmbientSound()
	{
		return null;
	}

	@Override
	@Nullable
	public Sound getHurtSound()
	{
		return null;
	}

	@Override
	@Nullable
	public Sound getDeathSound()
	{
		return null;
	}
}
