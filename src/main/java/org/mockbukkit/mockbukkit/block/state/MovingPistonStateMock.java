package org.mockbukkit.mockbukkit.block.state;

import io.papermc.paper.block.MovingPiston;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.jetbrains.annotations.NotNull;

/**
 * Mock implementation of a {@link MovingPiston}.
 *
 * @see BlockStateMock
 */
public class MovingPistonStateMock extends BlockStateMock
		implements
			MovingPiston,
			org.mockbukkit.mockbukkit.generated.io.papermc.paper.block.MovingPistonBaseMock
{

	protected MovingPistonStateMock(@NotNull Block block)
	{
		super(block);
	}

	public MovingPistonStateMock(@NotNull Material material)
	{
		super(material);
	}

	protected MovingPistonStateMock(@NotNull MovingPistonStateMock state)
	{
		super(state);
	}

	@Override
	@NotNull
	public MovingPistonStateMock getSnapshot()
	{
		return new MovingPistonStateMock(this);
	}

	@Override
	@NotNull
	public MovingPistonStateMock copy()
	{
		return new MovingPistonStateMock(this);
	}
}
