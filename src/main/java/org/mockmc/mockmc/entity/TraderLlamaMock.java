package org.mockmc.mockmc.entity;

import org.bukkit.entity.EntityType;
import org.bukkit.entity.TraderLlama;
import org.jetbrains.annotations.NotNull;
import org.mockmc.mockmc.ServerMock;
import java.util.UUID;

/**
 * Mock implementation of a {@link TraderLlama}.
 *
 * @see LlamaMock
 */
public class TraderLlamaMock extends LlamaMock
		implements
			TraderLlama,
			org.mockmc.mockmc.generated.org.bukkit.entity.TraderLlamaBaseMock
{

	/**
	 * Constructs a new {@link TraderLlama} on the provided {@link ServerMock} with
	 * a specified {@link UUID}.
	 *
	 * @param server
	 *            The server to create the entity on.
	 * @param uuid
	 *            The UUID of the entity.
	 */
	public TraderLlamaMock(@NotNull ServerMock server, @NotNull UUID uuid)
	{
		super(server, uuid);
	}

	@Override
	@NotNull
	public EntityType getType()
	{
		return EntityType.TRADER_LLAMA;
	}
}
