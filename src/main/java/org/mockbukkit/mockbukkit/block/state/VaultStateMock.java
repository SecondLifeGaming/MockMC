package org.mockbukkit.mockbukkit.block.state;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.Vault;
import org.jetbrains.annotations.NotNull;

/**
 * Mock implementation of a {@link Vault}.
 *
 * @see TileStateMock
 */
public class VaultStateMock extends TileStateMock
		implements
			Vault,
			org.mockbukkit.mockbukkit.generated.org.bukkit.block.VaultBaseMock
{

	protected VaultStateMock(@NotNull Block block)
	{
		super(block);
	}

	public VaultStateMock(@NotNull Material material)
	{
		super(material);
	}

	protected VaultStateMock(@NotNull VaultStateMock state)
	{
		super(state);
	}

	@Override
	@NotNull
	public VaultStateMock getSnapshot()
	{
		return new VaultStateMock(this);
	}

	@Override
	@NotNull
	public VaultStateMock copy()
	{
		return new VaultStateMock(this);
	}
}
