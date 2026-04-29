package org.mockmc.mockmc.block.state;

import org.bukkit.Material;
import org.bukkit.block.Bell;
import org.bukkit.block.Block;
import org.jetbrains.annotations.NotNull;

/**
 * Mock implementation of a {@link Bell}.
 *
 * @see TileStateMock
 */
public class BellStateMock extends TileStateMock
		implements
			Bell,
			org.mockmc.mockmc.generated.org.bukkit.block.BellBaseMock
{

	/**
	 * Constructs a new {@link BellStateMock} for the provided {@link Material}.
	 * Only supports {@link Material#BELL}
	 *
	 * @param material
	 *            The material this state is for.
	 */
	public BellStateMock(@NotNull Material material)
	{
		super(material);
		checkType(material, Material.BELL);
	}

	/**
	 * Constructs a new {@link BellStateMock} for the provided {@link Block}. Only
	 * supports {@link Material#BELL}
	 *
	 * @param block
	 *            The block this state is for.
	 */
	protected BellStateMock(@NotNull Block block)
	{
		super(block);
		checkType(block, Material.BELL);
	}

	/**
	 * Constructs a new {@link BellStateMock} by cloning the data from an existing
	 * one.
	 *
	 * @param state
	 *            The state to clone.
	 */
	protected BellStateMock(@NotNull BellStateMock state)
	{
		super(state);
	}

	@Override
	@NotNull
	public BellStateMock getSnapshot()
	{
		return new BellStateMock(this);
	}

	@Override
	@NotNull
	public BellStateMock copy()
	{
		return new BellStateMock(this);
	}
}
