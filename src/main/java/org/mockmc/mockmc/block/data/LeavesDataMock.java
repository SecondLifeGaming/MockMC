package org.mockmc.mockmc.block.data;

import com.google.common.base.Preconditions;
import org.bukkit.Material;
import org.bukkit.block.data.type.Leaves;
import org.jetbrains.annotations.NotNull;

import static org.mockmc.mockmc.block.data.BlockDataKey.DISTANCE;
import static org.mockmc.mockmc.block.data.BlockDataKey.PERSISTENT;

public class LeavesDataMock extends BlockDataMock implements Leaves
{

	private final int maxDistance = this.getLimitationValue(BlockDataLimitation.Type.MAX_DISTANCE);
	private final int minDistance = this.getLimitationValue(BlockDataLimitation.Type.MIN_DISTANCE);

	public LeavesDataMock(@NotNull Material material)
	{
		super(material);
	}

	protected LeavesDataMock(@NotNull LeavesDataMock other)
	{
		super(other);
	}

	@Override
	public boolean isPersistent()
	{
		return super.get(PERSISTENT);
	}

	@Override
	public void setPersistent(boolean persistent)
	{
		super.set(PERSISTENT, persistent);
	}

	@Override
	public int getDistance()
	{
		return super.get(DISTANCE);
	}

	@Override
	public void setDistance(int distance)
	{
		Preconditions.checkArgument(distance >= minDistance, "The distance must be >= %s", minDistance);
		Preconditions.checkArgument(distance <= maxDistance, "The distance must be <= %s", maxDistance);

		super.set(DISTANCE, distance);
	}

	@Override
	public int getMaximumDistance()
	{
		return maxDistance;
	}

	@Override
	public int getMinimumDistance()
	{
		return minDistance;
	}

	@Override
	public boolean isWaterlogged()
	{
		return super.get(BlockDataKey.WATERLOGGED);
	}

	@Override
	public void setWaterlogged(boolean waterlogged)
	{
		super.set(BlockDataKey.WATERLOGGED, waterlogged);
	}

	/**
	 * We use a copy constructor here to ensure that the final fields and internal
	 * state are properly initialized in the new instance.
	 */
	@Override
	@SuppressWarnings(
	{"squid:S2975", "java:S1182"})
	public @NotNull LeavesDataMock clone()
	{
		return new LeavesDataMock(this);
	}
}
