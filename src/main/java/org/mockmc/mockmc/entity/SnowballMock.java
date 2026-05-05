package org.mockmc.mockmc.entity;

import com.google.common.base.Preconditions;
import org.bukkit.Material;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Snowball;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;
import org.mockmc.mockmc.ServerMock;
import org.mockmc.mockmc.inventory.ItemStackMock;
import java.util.UUID;

public class SnowballMock extends ThrowableProjectileMock
		implements
			Snowball,
			org.mockmc.mockmc.generated.server.org.bukkit.entity.SnowballBaseMock
{

	private ItemStack item = new ItemStackMock(Material.SNOWBALL);

	/**
	 * Constructs a new {@link SnowballMock} on the provided {@link ServerMock} with
	 * a specified {@link UUID}.
	 *
	 * @param server
	 *            The server to create the entity on.
	 * @param uuid
	 *            The UUID of the entity.
	 */
	public SnowballMock(@NotNull ServerMock server, @NotNull UUID uuid)
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
		Preconditions.checkArgument(item != null, "Item cannot be null");
		ItemStack localCopy = new ItemStackMock(item);
		localCopy.setAmount(1);
		this.item = localCopy;
	}

	@Override
	@NotNull
	public EntityType getType()
	{
		return EntityType.SNOWBALL;
	}
}
