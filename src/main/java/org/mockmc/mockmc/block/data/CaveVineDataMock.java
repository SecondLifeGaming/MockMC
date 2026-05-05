package org.mockmc.mockmc.block.data;

import org.bukkit.Material;
import org.bukkit.block.data.type.CaveVines;
import org.jetbrains.annotations.NotNull;

import static org.mockmc.mockmc.block.data.BlockDataKey.BERRIES;

public class CaveVineDataMock extends AgeableDataMock implements CaveVines
{

	public CaveVineDataMock(@NotNull Material material)
	{
		super(material);
	}

	/**
	 * Copy constructor used for deep copying the state through the hierarchy.
	 */
	protected CaveVineDataMock(@NotNull CaveVineDataMock other)
	{
		super(other);
	}

	@Override
	public boolean hasBerries()
	{
		return super.get(BERRIES);
	}

	@Override
	public void setBerries(boolean berries)
	{
		super.set(BERRIES, berries);
	}

	/**
	 * We use a constructor-based clone to avoid the pitfalls of Object.clone(). The
	 * suppressions acknowledge that we are intentionally bypassing super.clone().
	 */
	@Override
	@SuppressWarnings(
	{"squid:S2975", "java:S1182"})
	public @NotNull CaveVineDataMock clone()
	{
		return new CaveVineDataMock(this);
	}
}
