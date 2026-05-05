package org.mockmc.mockmc.util;

import org.bukkit.Keyed;
import org.bukkit.NamespacedKey;
import org.bukkit.util.OldEnum;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 * Base class for legacy Keyed Enums.
 *
 * @mockmc.version 1.21-1.0.0
 */
@SuppressWarnings(
{"deprecation", "removal"})
public abstract class OldKeyedEnumMock<T extends OldEnum<T>> extends OldEnumMock<T> implements Keyed
{

	private transient NamespacedKey key;

	protected OldKeyedEnumMock(String name, int ordinal, NamespacedKey key)
	{
		super(name, ordinal);
		this.key = key;
	}

	@Override
	public @NotNull NamespacedKey getKey()
	{
		return key;
	}

	private void writeObject(ObjectOutputStream out) throws IOException
	{
		out.defaultWriteObject();
		out.writeUTF(key.asString());
	}

	private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException
	{
		in.defaultReadObject();
		this.key = NamespacedKey.fromString(in.readUTF());
	}

}
