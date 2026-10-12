package org.mockmc.mockmc.block.state;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.CreakingHeart;
import org.jetbrains.annotations.NotNull;

/**
 * Mock implementation of a {@link CreakingHeart}.
 *
 * @see TileStateMock
 */
public class CreakingHeartStateMock extends TileStateMock implements CreakingHeart
{

	protected CreakingHeartStateMock(@NotNull Block block)
	{
		super(block);
	}

	protected CreakingHeartStateMock(@NotNull Material material)
	{
		super(material);
	}

	protected CreakingHeartStateMock(@NotNull CreakingHeartStateMock state)
	{
		super(state);
		this.creaking = state.creaking;
	}

	@Override
	public @NotNull CreakingHeartStateMock getSnapshot()
	{
		return new CreakingHeartStateMock(this);
	}

	private org.bukkit.entity.@org.jspecify.annotations.Nullable Creaking creaking;

	@Override
	public org.bukkit.Location spreadResin()
	{
		return null;
	}

	@Override
	public org.bukkit.entity.Creaking spawnCreaking()
	{
		return null;
	}

	@Override
	public void setCreaking(org.bukkit.entity.@org.jspecify.annotations.Nullable Creaking creaking)
	{
		this.creaking = creaking;
	}

	@Override
	public org.bukkit.entity.@org.jspecify.annotations.Nullable Creaking getCreaking()
	{
		return this.creaking;
	}

	@Override
	public boolean equals(Object o)
	{
		if (this == o)
		{
			return true;
		}
		if (!(o instanceof CreakingHeartStateMock that))
		{
			return false;
		}
		if (!super.equals(o))
		{
			return false;
		}
		return java.util.Objects.equals(creaking, that.creaking);
	}

	@Override
	public int hashCode()
	{
		return java.util.Objects.hash(super.hashCode(), creaking);
	}

}
