package org.mockmc.mockmc.block.state;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.Hopper;
import org.jetbrains.annotations.NotNull;
import org.mockmc.mockmc.inventory.HopperInventoryMock;
import org.mockmc.mockmc.inventory.InventoryMock;

/**
 * Mock implementation of a {@link Hopper}.
 *
 * @see ContainerStateMock
 */
public class HopperStateMock extends ContainerStateMock
		implements
			Hopper,
			org.mockmc.mockmc.generated.org.bukkit.block.HopperBaseMock
{

	/**
	 * Constructs a new {@link HopperStateMock} for the provided {@link Material}.
	 * Only supports {@link Material#HOPPER}
	 *
	 * @param material
	 *            The material this state is for.
	 */
	public HopperStateMock(@NotNull Material material)
	{
		super(material);
		checkType(material, Material.HOPPER);
	}

	/**
	 * Constructs a new {@link HopperStateMock} for the provided {@link Block}. Only
	 * supports {@link Material#HOPPER}
	 *
	 * @param block
	 *            The block this state is for.
	 */
	protected HopperStateMock(@NotNull Block block)
	{
		super(block);
		checkType(block, Material.HOPPER);
	}

	/**
	 * Constructs a new {@link HopperStateMock} by cloning the data from an existing
	 * one.
	 *
	 * @param state
	 *            The state to clone.
	 */
	protected HopperStateMock(@NotNull HopperStateMock state)
	{
		super(state);
	}

	@Override
	@NotNull
	protected InventoryMock createInventory()
	{
		return new HopperInventoryMock(this);
	}

	@Override
	@NotNull
	public HopperStateMock getSnapshot()
	{
		return new HopperStateMock(this);
	}

	@Override
	@NotNull
	public HopperStateMock copy()
	{
		return new HopperStateMock(this);
	}

	@Override
	public boolean equals(Object o)
	{
		if (this == o)
		{
			return true;
		}
		if (!(o instanceof HopperStateMock))
		{
			return false;
		}
		return super.equals(o);
	}
}
