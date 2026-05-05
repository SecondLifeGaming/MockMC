package org.mockmc.mockmc.entity;

import com.google.common.base.Preconditions;
import org.bukkit.Material;
import org.bukkit.entity.Egg;
import org.bukkit.entity.EntityType;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;
import org.mockmc.mockmc.ServerMock;
import org.mockmc.mockmc.inventory.ItemStackMock;
import java.util.UUID;

/**
 * Mock implementation of an {@link Egg}.
 *
 * @see ThrowableProjectileMock
 */
public class EggMock extends ThrowableProjectileMock
		implements
			Egg,
			org.mockmc.mockmc.generated.server.org.bukkit.entity.EggBaseMock
{

	@NotNull
	private ItemStack item = new ItemStackMock(Material.EGG);

	/**
	 * Constructs a new {@link EggMock} on the provided {@link ServerMock} with a
	 * specified {@link UUID}.
	 *
	 * @param server
	 *            The server to create the entity on.
	 * @param uuid
	 *            The UUID of the entity.
	 */
	public EggMock(@NotNull ServerMock server, @NotNull UUID uuid)
	{
		super(server, uuid);
	}

	@Override
	@NotNull
	public ItemStack getItem()
	{
		return this.item;
	}

	@Override
	public void setItem(@NotNull ItemStack item)
	{
		Preconditions.checkNotNull(item, "Item cannot be null");
		this.item = item;
	}

	@Override
	@NotNull
	public EntityType getType()
	{
		return EntityType.EGG;
	}
}
