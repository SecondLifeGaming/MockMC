package org.mockmc.mockmc.entity;

import org.bukkit.entity.CaveSpider;
import org.bukkit.entity.EntityType;
import org.jetbrains.annotations.NotNull;
import org.mockmc.mockmc.ServerMock;
import java.util.UUID;

/**
 * Mock implementation of a {@link CaveSpider}.
 *
 * @see SpiderMock
 */
public class CaveSpiderMock extends SpiderMock
		implements
			CaveSpider,
			org.mockmc.mockmc.generated.server.org.bukkit.entity.CaveSpiderBaseMock
{

	/**
	 * Constructs a new {@link CaveSpiderMock} on the provided {@link ServerMock}
	 * with a specified {@link UUID}.
	 *
	 * @param server
	 *            The server to create the entity on.
	 * @param uuid
	 *            The UUID of the entity.
	 */
	public CaveSpiderMock(@NotNull ServerMock server, @NotNull UUID uuid)
	{
		super(server, uuid);
	}

	@Override
	@NotNull
	public EntityType getType()
	{
		return EntityType.CAVE_SPIDER;
	}
}
