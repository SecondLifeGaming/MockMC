package org.mockmc.mockmc.inventory;

import org.bukkit.block.ChiseledBookshelf;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.ChiseledBookshelfInventory;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * Mock implementation of a {@link ChiseledBookshelfInventory}.
 *
 * @see InventoryMock
 */
public class ChiseledBookshelfInventoryMock extends InventoryMock
		implements
			ChiseledBookshelfInventory,
			org.mockmc.mockmc.generated.server.org.bukkit.inventory.ChiseledBookshelfInventoryBaseMock
{

	public ChiseledBookshelfInventoryMock(@Nullable ChiseledBookshelf holder)
	{
		super(holder, InventoryType.CHISELED_BOOKSHELF);
	}

	protected ChiseledBookshelfInventoryMock(ChiseledBookshelfInventoryMock inventory)
	{
		super(inventory);
	}

	@Override
	@Nullable
	public ChiseledBookshelf getHolder()
	{
		return (ChiseledBookshelf) super.getHolder();
	}

	@Override
	@NotNull
	public ChiseledBookshelfInventoryMock getSnapshot()
	{
		return new ChiseledBookshelfInventoryMock(this);
	}
}
