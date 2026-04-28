package org.mockbukkit.mockbukkit.inventory;

import org.bukkit.block.DecoratedPot;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.DecoratedPotInventory;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class DecoratedPotInventoryMock extends InventoryMock
		implements
			DecoratedPotInventory,
			org.mockbukkit.mockbukkit.generated.org.bukkit.inventory.DecoratedPotInventoryBaseMock
{

	public DecoratedPotInventoryMock(@Nullable InventoryHolder holder)
	{
		super(holder, 1, InventoryType.DECORATED_POT);
	}

	protected DecoratedPotInventoryMock(@NotNull DecoratedPotInventoryMock inventory)
	{
		super(inventory);
	}

	@Override
	@NotNull
	public Inventory getSnapshot()
	{
		return new DecoratedPotInventoryMock(this);
	}

	@Override
	public void setItem(@Nullable ItemStack item)
	{
		setItem(0, item);
	}

	@Override
	@Nullable
	public ItemStack getItem()
	{
		return getItem(0);
	}

	@Override
	public DecoratedPot getHolder()
	{
		return (DecoratedPot) super.getHolder();
	}
}
