package org.mockmc.mockmc.inventory;

import org.bukkit.block.Shelf;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.ShelfInventory;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * Mock implementation of a {@link ShelfInventory}.
 *
 * @see InventoryMock
 */
public class ShelfInventoryMock extends InventoryMock
		implements
			ShelfInventory,
			org.mockmc.mockmc.generated.org.bukkit.inventory.ShelfInventoryBaseMock
{

	public ShelfInventoryMock(@Nullable Shelf holder)
	{
		super(holder, InventoryType.SHELF);
	}

	protected ShelfInventoryMock(ShelfInventoryMock inventory)
	{
		super(inventory);
	}

	@Override
	@Nullable
	public Shelf getHolder()
	{
		return (Shelf) super.getHolder();
	}

	@Override
	@NotNull
	public ShelfInventoryMock getSnapshot()
	{
		return new ShelfInventoryMock(this);
	}
}
