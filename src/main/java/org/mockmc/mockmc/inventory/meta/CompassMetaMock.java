package org.mockmc.mockmc.inventory.meta;

import com.google.common.base.Preconditions;
import org.bukkit.Location;
import org.bukkit.configuration.serialization.DelegateDeserialization;
import java.util.Objects;
import org.bukkit.inventory.meta.CompassMeta;
import org.bukkit.inventory.meta.ItemMeta;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.mockmc.mockmc.inventory.SerializableMeta;
import org.mockmc.mockmc.util.NbtParser;
import org.mockmc.mockmc.inventory.serializer.SerializationUtils;
import java.util.Map;

/**
 * Mock implementation of a {@link CompassMeta}.
 *
 * @see ItemMetaMock
 */
@DelegateDeserialization(SerializableMeta.class)
@SuppressWarnings("unchecked")
public class CompassMetaMock extends ItemMetaMock
		implements
			org.mockmc.mockmc.generated.server.org.bukkit.inventory.meta.CompassMetaBaseMock
{

	public static final String LODESTONE_KEY = "lodestone";

	@Nullable
	private Location lodestone;

	private boolean tracked;

	/**
	 * Constructs a new {@link CompassMetaMock}.
	 */
	public CompassMetaMock()
	{
		super();
	}

	/**
	 * Constructs a new {@link CompassMetaMock}, cloning the data from another.
	 *
	 * @param meta
	 *            The meta to clone.
	 */
	public CompassMetaMock(@NotNull ItemMeta meta)
	{
		super(meta);
		if (meta instanceof CompassMeta compass)
		{
			this.lodestone = (compass.getLodestone() != null ? compass.getLodestone() : null);
			this.tracked = compass.isLodestoneTracked();
		}
	}

	@Override
	public boolean hasLodestone()
	{
		return this.lodestone != null;
	}

	@Override
	@Nullable
	public Location getLodestone()
	{
		return this.lodestone;
	}

	@Override
	public void setLodestone(@Nullable Location lodestone)
	{
		Preconditions.checkArgument(lodestone == null || lodestone.getWorld() != null, "world is null");
		this.lodestone = lodestone;
	}

	@Override
	public boolean isLodestoneTracked()
	{
		return this.tracked;
	}

	@Override
	public void setLodestoneTracked(boolean tracked)
	{
		this.tracked = tracked;
	}

	@Override
	public int hashCode()
	{
		final int prime = 31;
		int hash = super.hashCode();
		hash = prime * hash + (this.lodestone != null ? this.lodestone.hashCode() : 0);
		hash = prime * hash + (this.tracked ? 1 : 0);
		return hash;
	}

	@Override
	public boolean equals(Object obj)
	{
		if (obj == null || obj.getClass() != this.getClass())
		{
			return false;
		}
		CompassMetaMock other = (CompassMetaMock) obj;
		return super.equals(obj) && Objects.equals(this.lodestone, other.getLodestone())
				&& this.tracked == other.isLodestoneTracked();
	}

	@Override
	@SuppressWarnings(
	{"MethodDoesntCallSuperMethod", "java:S2975", "java:S1182"})
	@NotNull
	public CompassMetaMock clone()
	{
		return new CompassMetaMock(this);
	}

	/**
	 * Required method for Bukkit deserialization.
	 *
	 * @param args
	 *            A serialized CompassMetaMock object in a Map&lt;String, Object&gt;
	 *            format.
	 * @return A new instance of the CompassMetaMock class.
	 */
	@NotNull
	public static CompassMetaMock deserialize(@NotNull Map<String, Object> args)
	{
		CompassMetaMock serialMock = new CompassMetaMock();
		serialMock.deserializeInternal(args);
		if (args.containsKey(LODESTONE_KEY))
		{
			serialMock.lodestone = Location.deserialize((Map<String, Object>) args.get(LODESTONE_KEY));
		}
		serialMock.tracked = NbtParser.parseBoolean(args.get("tracked"), false);
		return serialMock;
	}

	/**
	 * Serializes the properties of an CompassMetaMock to a HashMap. Unimplemented
	 * properties are not present in the map.
	 *
	 * @return A HashMap of String, Object pairs representing the CompassMetaMock.
	 */
	@Override
	@NotNull
	public Map<String, Object> serialize()
	{
		final Map<String, Object> serialized = super.serialize();
		if (this.lodestone != null)
		{
			serialized.put(LODESTONE_KEY, SerializationUtils.serialize(this.lodestone));
		}
		serialized.put("tracked", this.tracked);
		return serialized;
	}

	@Override
	protected String getTypeName()
	{
		return "COMPASS";
	}
}
