package org.mockmc.mockmc.entity;

import com.google.common.base.Preconditions;
import org.bukkit.Material;
import org.bukkit.entity.EnderPearl;
import org.bukkit.entity.EntityType;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;
import org.mockmc.mockmc.ServerMock;
import org.mockmc.mockmc.inventory.ItemStackMock;
import java.util.UUID;

public class EnderPearlMock extends ThrowableProjectileMock
		implements
			EnderPearl,
			org.mockmc.mockmc.generated.server.org.bukkit.entity.EnderPearlBaseMock
{

	@NotNull
	private ItemStack item = new ItemStackMock(Material.ENDER_PEARL);

	/**
	 * Constructs a new {@link EnderPearlMock} on the provided {@link ServerMock}
	 * with a specified {@link UUID}.
	 *
	 * @param server
	 *            The server to create the entity on.
	 * @param uuid
	 *            The UUID of the entity.
	 */
	public EnderPearlMock(@NotNull ServerMock server, @NotNull UUID uuid)
	{
		super(server, uuid);
	}

	@Override
	@NotNull
	public ItemStack getItem()
	{
		return new ItemStackMock(this.item);
	}

	@Override
	public void setItem(@NotNull ItemStack item)
	{
		Preconditions.checkNotNull(item, "Item cannot be null");
		ItemStack localCopy = new ItemStackMock(item);
		localCopy.setAmount(1);
		this.item = localCopy;
	}

	@Override
	@NotNull
	public EntityType getType()
	{
		return EntityType.ENDER_PEARL;
	}
}
