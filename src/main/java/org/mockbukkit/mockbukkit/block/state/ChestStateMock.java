package org.mockbukkit.mockbukkit.block.state;

import org.bukkit.Material;
import org.bukkit.Tag;
import org.bukkit.block.Block;
import org.bukkit.block.Chest;
import org.bukkit.inventory.Inventory;
import org.jetbrains.annotations.NotNull;
import org.mockbukkit.mockbukkit.inventory.ChestInventoryMock;
import org.mockbukkit.mockbukkit.inventory.InventoryMock;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

/**
 * Mock implementation of a {@link Chest}.
 *
 * @see TileStateMock
 */
public class ChestStateMock extends ContainerStateMock
		implements
			Chest,
			org.mockbukkit.mockbukkit.generated.org.bukkit.block.ChestBaseMock
{

	private boolean isOpen = false;

	/**
	 * Constructs a new {@link ChestStateMock} for the provided {@link Material}.
	 * Only supports {@link Material#CHEST} and {@link Material#TRAPPED_CHEST}.
	 *
	 * @param material
	 *            The material this state is for.
	 */
	public ChestStateMock(@NotNull Material material)
	{
		super(material);
		Set<Material> possibleValues = new HashSet<>();
		possibleValues.add(Material.CHEST);
		possibleValues.add(Material.TRAPPED_CHEST);
		possibleValues.addAll(Tag.COPPER_CHESTS.getValues());
		checkType(material, possibleValues.toArray(Material[]::new));
	}

	/**
	 * Constructs a new {@link ChestStateMock} for the provided {@link Block}. Only
	 * supports {@link Material#CHEST} and {@link Material#TRAPPED_CHEST}.
	 *
	 * @param block
	 *            The block this state is for.
	 */
	protected ChestStateMock(@NotNull Block block)
	{
		super(block);
		Set<Material> possibleValues = new HashSet<>();
		possibleValues.add(Material.CHEST);
		possibleValues.add(Material.TRAPPED_CHEST);
		possibleValues.addAll(Tag.COPPER_CHESTS.getValues());
		checkType(block, possibleValues.toArray(Material[]::new));
	}

	/**
	 * Constructs a new {@link ChestStateMock} by cloning the data from an existing
	 * one.
	 *
	 * @param state
	 *            The state to clone.
	 */
	protected ChestStateMock(@NotNull ChestStateMock state)
	{
		super(state);
		this.isOpen = state.isOpen;
	}

	@Override
	public void open()
	{
		isOpen = true;
	}

	@Override
	public void close()
	{
		isOpen = false;
	}

	@Override
	public boolean isOpen()
	{
		return isOpen;
	}

	@Override
	@NotNull
	public Inventory getBlockInventory()
	{
		return getInventory();
	}

	@Override
	@NotNull
	protected InventoryMock createInventory()
	{
		return new ChestInventoryMock(this, 27);
	}

	@Override
	@NotNull
	public ChestStateMock getSnapshot()
	{
		return new ChestStateMock(this);
	}

	@Override
	@NotNull
	public ChestStateMock copy()
	{
		return new ChestStateMock(this);
	}

	@Override
	public boolean equals(Object o)
	{
		if (this == o)
		{
			return true;
		}
		if (!(o instanceof ChestStateMock that))
		{
			return false;
		}
		if (!super.equals(o))
		{
			return false;
		}
		return isOpen == that.isOpen;
	}

	@Override
	public int hashCode()
	{
		return Objects.hash(super.hashCode(), isOpen);
	}

	@Override
	protected String toStringInternal()
	{
		return super.toStringInternal() + ", isOpen=" + isOpen;
	}
}
