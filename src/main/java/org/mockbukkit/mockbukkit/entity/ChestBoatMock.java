package org.mockbukkit.mockbukkit.entity;

import org.bukkit.Material;
import org.bukkit.entity.ChestBoat;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.inventory.Inventory;
import org.jetbrains.annotations.NotNull;
import org.mockbukkit.mockbukkit.ServerMock;
import org.mockbukkit.mockbukkit.inventory.ChestInventoryMock;
import java.util.UUID;

@SuppressWarnings(
{"deprecation", "removal", "unchecked"})
public class ChestBoatMock extends BoatMock
		implements
			ChestBoat,
			org.mockbukkit.mockbukkit.generated.org.bukkit.entity.ChestBoatBaseMock
{

	private final Inventory inventory;

	/**
	 * Constructs a new {@link ChestBoatMock} on the provided {@link ServerMock}
	 * with a specified {@link UUID}.
	 *
	 * @param server
	 *            The server to create the entity on.
	 * @param uuid
	 *            The UUID of the entity.
	 */
	public ChestBoatMock(@NotNull ServerMock server, @NotNull UUID uuid)
	{
		super(server, uuid);
		this.inventory = new ChestInventoryMock(this, 3 * 9);
	}

	@Override
	@NotNull
	public Inventory getInventory()
	{
		return this.inventory;
	}

	@Override
	@NotNull
	public Material getBoatMaterial()
	{
		return switch (getBoatType())
		{
			case OAK -> Material.OAK_CHEST_BOAT;
			case SPRUCE -> Material.SPRUCE_CHEST_BOAT;
			case BIRCH -> Material.BIRCH_CHEST_BOAT;
			case JUNGLE -> Material.JUNGLE_CHEST_BOAT;
			case ACACIA -> Material.ACACIA_CHEST_BOAT;
			case CHERRY -> Material.CHERRY_CHEST_BOAT;
			case DARK_OAK -> Material.DARK_OAK_CHEST_BOAT;
			case MANGROVE -> Material.MANGROVE_CHEST_BOAT;
			case BAMBOO -> Material.BAMBOO_CHEST_RAFT;
		};
	}

	@Override
	@NotNull
	public Entity getEntity()
	{
		return this;
	}

	@Override
	@NotNull
	public EntityType getType()
	{
		return EntityType.OAK_CHEST_BOAT;
	}
}
