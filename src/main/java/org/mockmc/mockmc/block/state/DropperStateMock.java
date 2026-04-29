package org.mockmc.mockmc.block.state;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.Dropper;
import org.jetbrains.annotations.NotNull;
import org.mockmc.mockmc.inventory.DropperInventoryMock;
import org.mockmc.mockmc.inventory.InventoryMock;

/**
 * Mock implementation of a {@link Dropper}.
 *
 * @see ContainerStateMock
 */
public class DropperStateMock extends ContainerStateMock
		implements
			Dropper,
			org.mockmc.mockmc.generated.org.bukkit.block.DropperBaseMock
{

	/**
	 * Constructs a new {@link DispenserStateMock} for the provided
	 * {@link Material}. Only supports {@link Material#DISPENSER}
	 *
	 * @param material
	 *            The material this state is for.
	 */
	public DropperStateMock(@NotNull Material material)
	{
		super(material);
		checkType(material, Material.DROPPER);
	}

	/**
	 * Constructs a new {@link DispenserStateMock} for the provided {@link Block}.
	 * Only supports {@link Material#DISPENSER}
	 *
	 * @param block
	 *            The block this state is for.
	 */
	protected DropperStateMock(@NotNull Block block)
	{
		super(block);
		checkType(block, Material.DROPPER);
	}

	/**
	 * Constructs a new {@link DispenserStateMock} by cloning the data from an
	 * existing one.
	 *
	 * @param state
	 *            The state to clone.
	 */
	protected DropperStateMock(@NotNull DropperStateMock state)
	{
		super(state);
	}

	@Override
	@NotNull
	protected InventoryMock createInventory()
	{
		return new DropperInventoryMock(this);
	}

	@Override
	@NotNull
	public DropperStateMock getSnapshot()
	{
		return new DropperStateMock(this);
	}

	@Override
	@NotNull
	public DropperStateMock copy()
	{
		return new DropperStateMock(this);
	}

	@Override
	public boolean equals(Object o)
	{
		if (this == o)
		{
			return true;
		}
		if (!(o instanceof DropperStateMock))
		{
			return false;
		}
		return super.equals(o);
	}
}
