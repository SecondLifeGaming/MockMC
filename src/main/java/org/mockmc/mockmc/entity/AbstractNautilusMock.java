package org.mockmc.mockmc.entity;

import org.bukkit.entity.AbstractNautilus;
import org.bukkit.entity.AnimalTamer;
import org.jetbrains.annotations.Nullable;
import org.jspecify.annotations.NullMarked;
import org.mockmc.mockmc.ServerMock;
import java.util.UUID;

@NullMarked
public class AbstractNautilusMock extends AnimalsMock
		implements
			AbstractNautilus,
			org.mockmc.mockmc.generated.org.bukkit.entity.AbstractNautilusBaseMock
{

	private boolean isTamed = false;

	@Nullable
	private AnimalTamer owner;

	/**
	 * Constructs a new {@link AbstractNautilus} on the provided {@link ServerMock}
	 * with a specified {@link UUID}.
	 *
	 * @param server
	 *            The server to create the entity on.
	 * @param uuid
	 *            The UUID of the entity.
	 */
	public AbstractNautilusMock(ServerMock server, UUID uuid)
	{
		super(server, uuid);
	}

	@Override
	public boolean isTamed()
	{
		return this.isTamed;
	}

	@Override
	public void setTamed(boolean tame)
	{
		this.isTamed = tame;
	}

	@Override
	@Nullable
	public UUID getOwnerUniqueId()
	{
		return this.owner != null ? this.owner.getUniqueId() : null;
	}

	@Override
	@Nullable
	public AnimalTamer getOwner()
	{
		return this.owner;
	}

	@Override
	public void setOwner(@Nullable AnimalTamer tamer)
	{
		this.owner = tamer;
	}
}
