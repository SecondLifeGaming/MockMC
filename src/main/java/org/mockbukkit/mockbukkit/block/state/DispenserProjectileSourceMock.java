package org.mockbukkit.mockbukkit.block.state;

import org.bukkit.block.Block;
import org.bukkit.projectiles.BlockProjectileSource;
import org.jetbrains.annotations.NotNull;

/**
 * Mock implementation of a {@link BlockProjectileSource}, specifically for the
 * {@link DispenserStateMock}.
 *
 * @see DispenserStateMock
 */
class DispenserProjectileSourceMock
		implements
			BlockProjectileSource,
			org.mockbukkit.mockbukkit.generated.org.bukkit.projectiles.BlockProjectileSourceBaseMock
{

	@NotNull
	private final DispenserStateMock dispenser;

	/**
	 * Constructs a new {@link DispenserProjectileSourceMock} for the provided
	 * {@link DispenserStateMock}.
	 *
	 * @param dispenser
	 *            The dispenser this projectile source is for.
	 */
	DispenserProjectileSourceMock(@NotNull DispenserStateMock dispenser)
	{
		this.dispenser = dispenser;
	}

	@Override
	@NotNull
	public Block getBlock()
	{
		return dispenser.getBlock();
	}
}
