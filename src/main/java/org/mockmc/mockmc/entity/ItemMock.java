package org.mockmc.mockmc.entity;

import com.google.common.base.Preconditions;
import net.kyori.adventure.util.TriState;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Item;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;
import org.mockmc.mockmc.ServerMock;
import java.util.UUID;

/**
 * Mock implementation of an {@link Item}.
 *
 * @see EntityMock
 */
public class ItemMock extends EntityMock
		implements
			Item,
			org.mockmc.mockmc.generated.server.org.bukkit.entity.ItemBaseMock
{

	private ItemStack item;

	// The default pickup delay
	private int delay = 10;

	private TriState frictionState = TriState.NOT_SET;

	/**
	 * Constructs a new {@link ItemMock} on the provided {@link ServerMock} with a
	 * specified {@link UUID} and {@link ItemStack}.
	 *
	 * @param server
	 *            The server to create the entity on.
	 * @param uuid
	 *            The UUID of the entity.
	 * @param item
	 *            The item this entity represents.
	 */
	public ItemMock(@NotNull ServerMock server, @NotNull UUID uuid, @NotNull ItemStack item)
	{
		Preconditions.checkNotNull(item, "Item cannot be null");
		super(server, uuid);
		this.item = item.clone();
	}

	@Override
	@NotNull
	public EntityType getType()
	{
		return EntityType.ITEM;
	}

	@Override
	@NotNull
	public ItemStack getItemStack()
	{
		return item;
	}

	@Override
	public void setItemStack(@NotNull ItemStack stack)
	{
		Preconditions.checkNotNull(stack, "Item cannot be null");
		// "stack" is actually nullable here, but it seems like Spigot also throws an
		// Exception
		// in that case anyway. Besides a "null" Item does not really make sense anyway.
		this.item = stack.clone();
	}

	@Override
	public int getPickupDelay()
	{
		return delay;
	}

	@Override
	public void setPickupDelay(int delay)
	{
		this.delay = Math.min(delay, 32767);
	}

	@Override
	@NotNull
	public TriState getFrictionState()
	{
		return this.frictionState;
	}

	@Override
	public void setFrictionState(@NotNull TriState state)
	{
		Preconditions.checkNotNull(state, "State cannot be null");
		this.frictionState = state;
	}
}
