package org.mockmc.mockmc.inventory.meta;

import java.util.Objects;

import com.google.common.base.Preconditions;
import org.bukkit.configuration.serialization.DelegateDeserialization;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.OminousBottleMeta;
import org.jetbrains.annotations.NotNull;
import org.mockmc.mockmc.inventory.SerializableMeta;
import org.mockmc.mockmc.util.NbtParser;
import java.util.Map;

@DelegateDeserialization(SerializableMeta.class)
public class OminousBottleMetaMock extends ItemMetaMock
		implements
			org.mockmc.mockmc.generated.server.org.bukkit.inventory.meta.OminousBottleMetaBaseMock
{

	private Integer amplifier;

	private static final String AMPLIFIER_KEY = "amplifier";

	/**
	 * Constructs a new {@link OminousBottleMetaMock}.
	 */
	public OminousBottleMetaMock()
	{
		super();
	}

	/**
	 * Constructs a new {@link OminousBottleMetaMock}, cloning the data from
	 * another.
	 *
	 * @param meta
	 *            The meta to clone.
	 */
	public OminousBottleMetaMock(ItemMeta meta)
	{
		super(meta);
		if (meta instanceof OminousBottleMeta bottleMeta)
		{
			this.amplifier = bottleMeta.hasAmplifier() ? bottleMeta.getAmplifier() : null;
		}
	}

	@Override
	public boolean hasAmplifier()
	{
		return this.amplifier != null;
	}

	@Override
	public int getAmplifier()
	{
		Preconditions.checkState(this.hasAmplifier(),
				"'ominous_bottle_amplifier' data component is absent. Check hasAmplifier first!");
		return this.amplifier;
	}

	@Override
	public void setAmplifier(int amplifier)
	{
		Preconditions.checkArgument(0 <= amplifier && amplifier <= 4, "Amplifier must be in range [0, 4]");
		this.amplifier = amplifier;
	}

	@Override
	public boolean equals(Object obj)
	{
		if (this == obj)
		{
			return true;
		}
		if (obj == null || obj.getClass() != this.getClass())
		{
			return false;
		}
		if (!super.equals(obj))
		{
			return false;
		}
		OminousBottleMetaMock other = (OminousBottleMetaMock) obj;
		return Objects.equals(this.amplifier, other.amplifier);
	}

	@Override
	public int hashCode()
	{
		return Objects.hash(super.hashCode(), amplifier);
	}

	@Override
	@SuppressWarnings(
	{"MethodDoesntCallSuperMethod", "java:S2975", "java:S1182"})
	@NotNull
	public OminousBottleMetaMock clone()
	{
		return new OminousBottleMetaMock(this);
	}

	@Override
	protected void deserializeInternal(@NotNull Map<String, Object> args)
	{
		super.deserializeInternal(args);
		if (args.containsKey(AMPLIFIER_KEY))
		{
			this.amplifier = NbtParser.parseInteger(args.get(AMPLIFIER_KEY));
		}
	}

	@NotNull
	public static OminousBottleMetaMock deserialize(@NotNull Map<String, Object> args)
	{
		OminousBottleMetaMock serialMock = new OminousBottleMetaMock();
		serialMock.deserializeInternal(args);
		return serialMock;
	}

	@Override
	@NotNull
	public Map<String, Object> serialize()
	{
		final Map<String, Object> serialized = super.serialize();
		if (this.hasAmplifier())
		{
			serialized.put(AMPLIFIER_KEY, amplifier);
		}
		return serialized;
	}

	@Override
	protected String getTypeName()
	{
		return "OMINOUS_BOTTLE";
	}
}
