package org.mockmc.mockmc.inventory;

import com.google.common.base.Preconditions;
import net.kyori.adventure.text.Component;
import org.bukkit.entity.HumanEntity;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.InventoryHolder;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.mockmc.mockmc.exception.UnimplementedOperationException;

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
		InventoryMock inventory;
		switch (type)
		{
			case CHEST :
				inventory = new ChestInventoryMock(owner, size > 0 ? size : type.getDefaultSize());
				break;
			case DISPENSER :
				inventory = new DispenserInventoryMock(owner);
				break;
			case DROPPER :
				inventory = new DropperInventoryMock(owner);
				break;
			case PLAYER :
				if (owner instanceof HumanEntity he)
				{
					inventory = new PlayerInventoryMock(he);
				} else
				{
					throw new IllegalArgumentException("Cannot create a Player Inventory for: " + owner);
				}
				break;
			case ENDER_CHEST :
				inventory = new EnderChestInventoryMock(owner);
				break;
			case HOPPER :
				inventory = new HopperInventoryMock(owner);
				break;
			case SHULKER_BOX :
				inventory = new ShulkerBoxInventoryMock(owner);
				break;
			case BARREL :
				inventory = new BarrelInventoryMock(owner);
				break;
			case LECTERN :
				inventory = new LecternInventoryMock(owner);
				break;
			case GRINDSTONE :
				inventory = new GrindstoneInventoryMock(owner);
				break;
			case STONECUTTER :
				inventory = new StonecutterInventoryMock(owner);
				break;
			case CARTOGRAPHY :
				inventory = new CartographyInventoryMock(owner);
				break;
			case SMOKER :
			case FURNACE :
			case BLAST_FURNACE :
				inventory = new FurnaceInventoryMock(owner);
				break;
			case LOOM :
				inventory = new LoomInventoryMock(owner);
				break;
			case ANVIL :
				inventory = new AnvilInventoryMock(owner);
				break;
			case SMITHING :
				inventory = new SmithingInventoryMock(owner);
				break;
			case BEACON :
				inventory = new BeaconInventoryMock(owner);
				break;
			case WORKBENCH :
				inventory = new WorkbenchInventoryMock(owner);
				break;
			case ENCHANTING :
				inventory = new EnchantingInventoryMock(owner);
				break;
			case BREWING :
				inventory = new BrewerInventoryMock(owner);
				break;
			case CRAFTER :
				inventory = new CrafterInventoryMock(owner);
				break;
			default :
				throw new UnimplementedOperationException("Inventory type not yet supported");
		}
		if (title != null)
		{
			inventory.setCustomTitle(title);
		}
		return inventory;
	}

}
