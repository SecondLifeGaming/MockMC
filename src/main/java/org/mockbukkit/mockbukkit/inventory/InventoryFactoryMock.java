package org.mockbukkit.mockbukkit.inventory;

import com.google.common.base.Preconditions;
import net.kyori.adventure.text.Component;
import org.bukkit.entity.HumanEntity;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.InventoryHolder;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.mockbukkit.mockbukkit.exception.UnimplementedOperationException;

/**
 * Factory class to create various {@link InventoryMock} instances.
 */
public class InventoryFactoryMock
{

	/**
	 * Creates a new {@link InventoryMock} based on the provided parameters.
	 *
	 * @param owner
	 *            The owner of the inventory.
	 * @param type
	 *            The type of inventory to create.
	 * @param title
	 *            The title of the inventory.
	 * @param size
	 *            The size of the inventory.
	 * @return The created inventory.
	 * @throws IllegalArgumentException
	 *             If the inventory type is not creatable or the owner is invalid
	 *             for the type.
	 */
	@NotNull
	public InventoryMock createInventoryInternal(@Nullable InventoryHolder owner, @NotNull InventoryType type,
			@Nullable Component title, int size)
	{
		Preconditions.checkArgument(type.isCreatable(), "Inventory Type '" + type + "' is not creatable!");
		InventoryMock inventory = switch (type)
		{
			case CHEST :
				yield new ChestInventoryMock(owner, size > 0 ? size : type.getDefaultSize());
			case DISPENSER :
				yield new DispenserInventoryMock(owner);
			case DROPPER :
				yield new DropperInventoryMock(owner);
			case PLAYER :
				if (owner instanceof HumanEntity he)
				{
					yield new PlayerInventoryMock(he);
				} else
				{
					throw new IllegalArgumentException("Cannot create a Player Inventory for: " + owner);
				}
			case ENDER_CHEST :
				yield new EnderChestInventoryMock(owner);
			case HOPPER :
				yield new HopperInventoryMock(owner);
			case SHULKER_BOX :
				yield new ShulkerBoxInventoryMock(owner);
			case BARREL :
				yield new BarrelInventoryMock(owner);
			case LECTERN :
				yield new LecternInventoryMock(owner);
			case GRINDSTONE :
				yield new GrindstoneInventoryMock(owner);
			case STONECUTTER :
				yield new StonecutterInventoryMock(owner);
			case CARTOGRAPHY :
				yield new CartographyInventoryMock(owner);
			case SMOKER, FURNACE, BLAST_FURNACE :
				yield new FurnaceInventoryMock(owner);
			case LOOM :
				yield new LoomInventoryMock(owner);
			case ANVIL :
				yield new AnvilInventoryMock(owner);
			case SMITHING :
				yield new SmithingInventoryMock(owner);
			case BEACON :
				yield new BeaconInventoryMock(owner);
			case WORKBENCH :
				yield new WorkbenchInventoryMock(owner);
			case ENCHANTING :
				yield new EnchantingInventoryMock(owner);
			case BREWING :
				yield new BrewerInventoryMock(owner);
			case CRAFTER :
				yield new CrafterInventoryMock(owner);
			default :
				throw new UnimplementedOperationException("Inventory type not yet supported");
		};
		if (title != null)
		{
			inventory.setCustomTitle(title);
		}
		return inventory;
	}

}
