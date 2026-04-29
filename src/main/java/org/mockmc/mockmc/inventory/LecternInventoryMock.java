package org.mockmc.mockmc.inventory;

import org.bukkit.block.Lectern;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.inventory.LecternInventory;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * Mock implementation of a {@link LecternInventory}.
 *
 * @see InventoryMock
 */
public class LecternInventoryMock extends InventoryMock
		implements
			LecternInventory,
			org.mockmc.mockmc.generated.org.bukkit.inventory.LecternInventoryBaseMock
{

	/**
	 * Constructs a new {@link LecternInventoryMock} for the given holder.
	 *
	 * @param holder
	 *            The holder of the inventory.
	 */
	public LecternInventoryMock(InventoryHolder holder)
	{
		super(holder, InventoryType.LECTERN);
	}

	protected LecternInventoryMock(@NotNull LecternInventoryMock inventory)
	{
		super(inventory);
	}

	@Override
	@NotNull
	public Inventory getSnapshot()
	{
		return new LecternInventoryMock(this);
	}

	@Override
	@Nullable
	public Lectern getHolder()
	{
		return (Lectern) super.getHolder();
	}
}
