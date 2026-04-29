package org.mockmc.mockmc.entity;

import org.bukkit.entity.EntityType;
import org.bukkit.entity.GlowItemFrame;
import org.jetbrains.annotations.NotNull;
import org.mockmc.mockmc.ServerMock;
import java.util.UUID;

/**
 * Mock implementation of an {@link GlowItemFrame}.
 *
 * @see ItemFrameMock
 */
public class GlowItemFrameMock extends ItemFrameMock
		implements
			GlowItemFrame,
			org.mockmc.mockmc.generated.org.bukkit.entity.GlowItemFrameBaseMock
{

	/**
	 * Constructs a new {@link GlowItemFrame} on the provided {@link ServerMock}
	 * with a specified {@link UUID}.
	 *
	 * @param server
	 *            The server to create the entity on.
	 * @param uuid
	 *            The UUID of the entity.
	 */
	public GlowItemFrameMock(@NotNull ServerMock server, @NotNull UUID uuid)
	{
		super(server, uuid);
	}

	@Override
	@NotNull
	public EntityType getType()
	{
		return EntityType.GLOW_ITEM_FRAME;
	}
}
