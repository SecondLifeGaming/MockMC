package org.mockmc.mockmc.inventory;

import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.CartographyInventory;
import org.bukkit.inventory.InventoryHolder;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * Mock implementation of a {@link CartographyInventory}.
 *
 * @see InventoryMock
 */
public class CartographyInventoryMock extends InventoryMock
		implements
			CartographyInventory,
			org.mockmc.mockmc.generated.org.bukkit.inventory.CartographyInventoryBaseMock
{

	/**
	 * Constructs a new {@link CartographyInventoryMock} for the given holder.
	 *
	 * @param holder
	 *            The holder of the inventory.
	 */
	public CartographyInventoryMock(@Nullable InventoryHolder holder)
	{
		super(holder, InventoryType.CARTOGRAPHY);
	}

	protected CartographyInventoryMock(@NotNull CartographyInventoryMock inventory)
	{
		super(inventory);
	}

	@Override
	@NotNull
	public InventoryMock getSnapshot()
	{
		return new CartographyInventoryMock(this);
	}
}
