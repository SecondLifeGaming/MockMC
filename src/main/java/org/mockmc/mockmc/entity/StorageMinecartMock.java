package org.mockmc.mockmc.entity;

import org.bukkit.Material;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.minecart.StorageMinecart;
import org.bukkit.inventory.Inventory;
import org.jetbrains.annotations.NotNull;
import org.mockmc.mockmc.ServerMock;
import java.util.UUID;

public class StorageMinecartMock extends LootableMinecart
		implements
			StorageMinecart,
			org.mockmc.mockmc.generated.server.org.bukkit.entity.minecart.StorageMinecartBaseMock
{

	private final Inventory inventory;

	/**
	 * Constructs a new {@link LootableMinecart} on the provided {@link ServerMock}
	 * with a specified {@link UUID}.
	 *
	 * @param server
	 *            The server to create the entity on.
	 * @param uuid
	 *            The UUID of the entity.
	 */
	public StorageMinecartMock(@NotNull ServerMock server, @NotNull UUID uuid)
	{
		super(server, uuid);
		inventory = server.createInventory(this, 3 * 9);
	}

	@Override
	@NotNull
	public Entity getEntity()
	{
		return this;
	}

	@Override
	@NotNull
	public Material getMinecartMaterial()
	{
		return Material.CHEST_MINECART;
	}

	@Override
	@NotNull
	public Inventory getInventory()
	{
		return this.inventory;
	}

	@Override
	@NotNull
	public EntityType getType()
	{
		return EntityType.CHEST_MINECART;
	}
}
