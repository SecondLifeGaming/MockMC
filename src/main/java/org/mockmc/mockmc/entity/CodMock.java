package org.mockmc.mockmc.entity;

import org.bukkit.Material;
import org.bukkit.entity.Cod;
import org.bukkit.entity.EntityType;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;
import org.mockmc.mockmc.ServerMock;
import org.mockmc.mockmc.inventory.ItemStackMock;
import java.util.UUID;

/**
 * Mock implementation of a {@link Cod}.
 *
 * @see SchoolableFishMock
 */
public class CodMock extends SchoolableFishMock
		implements
			Cod,
			org.mockmc.mockmc.generated.server.org.bukkit.entity.CodBaseMock
{

	/**
	 * Constructs a new {@link CodMock} on the provided {@link ServerMock} with a
	 * specified {@link UUID}.
	 *
	 * @param server
	 *            The server to create the entity on.
	 * @param uuid
	 *            The UUID of the entity.
	 */
	public CodMock(@NotNull ServerMock server, @NotNull UUID uuid)
	{
		super(server, uuid);
	}

	@Override
	@NotNull
	public ItemStack getBaseBucketItem()
	{
		return new ItemStackMock(Material.COD_BUCKET);
	}

	@Override
	@NotNull
	public EntityType getType()
	{
		return EntityType.COD;
	}
}
