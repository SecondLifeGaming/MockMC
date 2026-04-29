package org.mockmc.mockmc.inventory;

import org.bukkit.entity.HumanEntity;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * A basic inventory view.
 *
 * @see InventoryViewMock
 */
@SuppressWarnings(
{"deprecation", "removal", "unchecked"})
public class SimpleInventoryViewMock extends InventoryViewMock
{

	/**
	 * Constructs a new {@link SimpleInventoryViewMock} with the provided
	 * parameters, and "Inventory" as the name.
	 *
	 * @param player
	 *            The player this view is for.
	 * @param top
	 *            The top inventory.
	 * @param bottom
	 *            The bottom inventory.
	 * @param type
	 *            The type of the inventory.
	 */
	public SimpleInventoryViewMock(HumanEntity player, Inventory top, Inventory bottom, InventoryType type)
	{
		super(player, top, bottom, type);
	}

	/**
	 * Creates a very simple mock {@code IntventoryView} with as player, top, and
	 * bottom {@code null}, and as type {@code InventoryType.CHEST}.
	 */
	public SimpleInventoryViewMock()
	{
		this(null, null, null, InventoryType.CHEST);
	}

	@Override
	public void setCursor(@Nullable ItemStack item)
	{
		this.getPlayer().setItemOnCursor(item);
	}

	@Override
	@NotNull
	public ItemStack getCursor()
	{
		return this.getPlayer().getItemOnCursor();
	}
}
