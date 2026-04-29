package org.mockmc.mockmc.util;

import org.bukkit.util.OldEnum;
import org.jetbrains.annotations.NotNull;

import java.io.Serializable;

@SuppressWarnings(
{"deprecation", "removal"})
public abstract class OldEnumMock<T extends OldEnum<T>> implements OldEnum<T>, Serializable
{

	private final String name;

	private final int ordinal;

	public OldEnumMock(String name, int ordinal)
	{
		this.name = name;
		this.ordinal = ordinal;
	}

	@Override
	public int compareTo(@NotNull T other)
	{
		return Integer.compare(this.ordinal(), other.ordinal());
	}

	@Override
	@NotNull
	public String name()
	{
		return name;
	}

	@Override
	public int ordinal()
	{
		return ordinal;
	}
}
