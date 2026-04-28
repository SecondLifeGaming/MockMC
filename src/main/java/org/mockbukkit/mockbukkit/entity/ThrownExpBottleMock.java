package org.mockbukkit.mockbukkit.entity;

import com.google.common.base.Preconditions;
import org.bukkit.Material;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.ThrownExpBottle;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;
import org.mockbukkit.mockbukkit.ServerMock;
import org.mockbukkit.mockbukkit.inventory.ItemStackMock;
import java.util.UUID;

public class ThrownExpBottleMock extends ThrowableProjectileMock
		implements
			ThrownExpBottle,
			org.mockbukkit.mockbukkit.generated.org.bukkit.entity.ThrownExpBottleBaseMock
{

	@NotNull
	private ItemStack item = new ItemStackMock(Material.EXPERIENCE_BOTTLE);

	/**
	 * Constructs a new {@link ThrownExpBottleMock} on the provided
	 * {@link ServerMock} with a specified {@link UUID}.
	 *
	 * @param server
	 *            The server to create the entity on.
	 * @param uuid
	 *            The UUID of the entity.
	 */
	public ThrownExpBottleMock(@NotNull ServerMock server, @NotNull UUID uuid)
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
		return EntityType.EXPERIENCE_BOTTLE;
	}
}
