package org.mockbukkit.mockbukkit.block.state;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.TrialSpawner;
import org.jetbrains.annotations.NotNull;

/**
 * Mock implementation of a {@link TrialSpawner}.
 *
 * @see TileStateMock
 */
public class TrialSpawnerStateMock extends TileStateMock
		implements
			TrialSpawner,
			org.mockbukkit.mockbukkit.generated.org.bukkit.block.TrialSpawnerBaseMock
{

	protected TrialSpawnerStateMock(@NotNull Block block)
	{
		super(block);
	}

	public TrialSpawnerStateMock(@NotNull Material material)
	{
		super(material);
	}

	protected TrialSpawnerStateMock(@NotNull TrialSpawnerStateMock state)
	{
		super(state);
	}

	@Override
	@NotNull
	public TrialSpawnerStateMock getSnapshot()
	{
		return new TrialSpawnerStateMock(this);
	}

	@Override
	@NotNull
	public TrialSpawnerStateMock copy()
	{
		return new TrialSpawnerStateMock(this);
	}
}
