package org.mockmc.mockmc.inventory.meta;
import java.util.Objects;

import org.bukkit.configuration.serialization.DelegateDeserialization;
import org.bukkit.entity.Axolotl;
import org.bukkit.inventory.meta.AxolotlBucketMeta;
import org.bukkit.inventory.meta.ItemMeta;
import org.jetbrains.annotations.NotNull;
import org.mockmc.mockmc.inventory.SerializableMeta;
import org.mockmc.mockmc.util.NbtParser;
import java.util.Map;

/**
 * Mock implementation of an {@link AxolotlBucketMeta}.
 *
 * @see ItemMetaMock
 */
@DelegateDeserialization(SerializableMeta.class)
public class AxolotlBucketMetaMock extends ItemMetaMock
		implements
			org.mockmc.mockmc.generated.server.org.bukkit.inventory.meta.AxolotlBucketMetaBaseMock
{

	private Axolotl.Variant variant;

	/**
	 * Constructs a new {@link AxolotlBucketMetaMock}.
	 */
	public AxolotlBucketMetaMock()
	{
		super();
	}

	/**
	 * Constructs a new {@link AxolotlBucketMetaMock}, cloning the data from
	 * another.
	 *
	 * @param meta
	 *            The meta to clone.
	 */
	public AxolotlBucketMetaMock(@NotNull ItemMeta meta)
	{
		super(meta);
		if (meta instanceof AxolotlBucketMeta bucketMeta)
		{
			variant = bucketMeta.getVariant();
		}
	}

	@Override
	@NotNull
	public Axolotl.Variant getVariant()
	{
		return this.variant;
	}

	@Override
	public void setVariant(Axolotl.Variant variant)
	{
		if (variant == null)
		{
			variant = Axolotl.Variant.LUCY;
		}
		this.variant = variant;
	}

	@Override
	public boolean hasVariant()
	{
		return this.variant != null;
	}

	@Override
	public int hashCode()
	{
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + (this.variant != null ? this.variant.hashCode() : 0);
		return result;
	}

	@Override
	public boolean equals(Object obj)
	{
		if (obj == null || obj.getClass() != this.getClass())
		{
			return false;
		}
		AxolotlBucketMetaMock other = (AxolotlBucketMetaMock) obj;
		return super.equals(obj) && Objects.equals(this.variant, other.variant);
	}

	@Override
	@SuppressWarnings(
	{"MethodDoesntCallSuperMethod", "java:S2975", "java:S1182"})
	@NotNull
	public AxolotlBucketMetaMock clone()
	{
		return new AxolotlBucketMetaMock(this);
	}

	/**
	 * Required method for Bukkit deserialization.
	 *
	 * @param args
	 *            A serialized AxolotlBucketMetaMock object in a Map&lt;String,
	 *            Object&gt; format.
	 * @return A new instance of the AxolotlBucketMetaMock class.
	 */
	@NotNull
	public static AxolotlBucketMetaMock deserialize(@NotNull Map<String, Object> args)
	{
		AxolotlBucketMetaMock serialMock = new AxolotlBucketMetaMock();
		serialMock.deserializeInternal(args);
		serialMock.variant = NbtParser.parseEnum(args.get("variant"), Axolotl.Variant.class);
		return serialMock;
	}

	/**
	 * Serializes the properties of an AxolotlBucketMetaMock to a HashMap.
	 * Unimplemented properties are not present in the map.
	 *
	 * @return A HashMap of String, Object pairs representing the
	 *         AxolotlBucketMetaMock.
	 */
	@Override
	@NotNull
	public Map<String, Object> serialize()
	{
		final Map<String, Object> serialized = super.serialize();
		serialized.put("variant", this.variant);
		return serialized;
	}

	@Override
	protected String getTypeName()
	{
		return "AXOLOTL_BUCKET";
	}
}
