package org.mockmc.mockmc.entity;

import org.bukkit.entity.EntityType;
import org.bukkit.entity.PiglinBrute;
import org.jetbrains.annotations.NotNull;
import org.mockmc.mockmc.ServerMock;
import java.util.UUID;

/**
 * Mock implementation of a {@link PiglinBrute}.
 *
 * @see PiglinAbstractMock
 */
public class PiglinBruteMock extends PiglinAbstractMock
		implements
			PiglinBrute,
			org.mockmc.mockmc.generated.org.bukkit.entity.PiglinBruteBaseMock
{

	/**
	 * Constructs a new {@link PiglinBruteMock} on the provided {@link ServerMock}
	 * with a specified {@link UUID}.
	 *
	 * @param server
	 *            The server to create the entity on.
	 * @param uuid
	 *            The UUID of the entity.
	 */
	public PiglinBruteMock(@NotNull ServerMock server, @NotNull UUID uuid)
	{
		super(server, uuid);
	}

	@Override
	@NotNull
	public EntityType getType()
	{
		return EntityType.PIGLIN_BRUTE;
	}
}
