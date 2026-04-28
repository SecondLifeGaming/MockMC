package org.mockbukkit.mockbukkit.inventory.meta;
import java.util.Objects;

import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableList;
import org.bukkit.FireworkEffect;
import org.bukkit.configuration.serialization.DelegateDeserialization;
import org.bukkit.inventory.meta.FireworkMeta;
import org.bukkit.inventory.meta.ItemMeta;
import org.jetbrains.annotations.NotNull;
import org.mockbukkit.mockbukkit.inventory.SerializableMeta;
import org.mockbukkit.mockbukkit.util.NbtParser;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Mock implementation of an {@link FireworkMeta}.
 *
 * @see ItemMetaMock
 */
@DelegateDeserialization(SerializableMeta.class)
public class FireworkMetaMock extends ItemMetaMock
		implements
			org.mockbukkit.mockbukkit.generated.org.bukkit.inventory.meta.FireworkMetaBaseMock
{

	static final String POWER_KEY = "power";

	@NotNull
	private List<FireworkEffect> effects = new ArrayList<>();

	private Integer power;

	/**
	 * Constructs a new {@link FireworkMetaMock}.
	 */
	public FireworkMetaMock()
	{
		super();
	}

	/**
	 * Constructs a new {@link FireworkMetaMock}, cloning the data from another.
	 *
	 * @param meta
	 *            The meta to clone.
	 */
	public FireworkMetaMock(@NotNull ItemMeta meta)
	{
		super(meta);
		if (meta instanceof FireworkMeta fireworkMeta)
		{
			if (fireworkMeta.hasEffects())
			{
				this.effects.addAll(fireworkMeta.getEffects());
			}
			if (fireworkMeta.hasPower())
			{
				this.power = fireworkMeta.getPower();
			}
		}
	}

	@Override
	public int hashCode()
	{
		return Objects.hash(super.hashCode(), effects, power);
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
		FireworkMetaMock other = (FireworkMetaMock) obj;
		return super.equals(obj) && Objects.equals(this.effects, other.effects) && this.power == other.power;
	}

	@Override
	@SuppressWarnings(
	{"MethodDoesntCallSuperMethod", "java:S2975", "java:S1182"})
	@NotNull
	public FireworkMetaMock clone()
	{
		return new FireworkMetaMock(this);
	}

	@Override
	public void addEffect(@NotNull FireworkEffect effect)
	{
		Preconditions.checkNotNull(effect, "effect must never be null");
		effects.add(effect);
	}

	@Override
	public void addEffects(@NotNull FireworkEffect @NotNull [] effects)
	{
		Preconditions.checkNotNull(effects, "effects must never be null");
		for (FireworkEffect effect : effects)
		{
			addEffect(effect);
		}
	}

	@Override
	public void addEffects(@NotNull Iterable<FireworkEffect> effects)
	{
		Preconditions.checkNotNull(effects, "effects must never be null");
		for (FireworkEffect effect : effects)
		{
			addEffect(effect);
		}
	}

	@Override
	@NotNull
	public List<FireworkEffect> getEffects()
	{
		return ImmutableList.copyOf(effects);
	}

	@Override
	public int getEffectsSize()
	{
		return effects.size();
	}

	@Override
	public void removeEffect(int index)
	{
		effects.remove(index);
	}

	@Override
	public void clearEffects()
	{
		effects.clear();
	}

	@Override
	public boolean hasEffects()
	{
		return !effects.isEmpty();
	}

	@Override
	public boolean hasPower()
	{
		return power != null;
	}

	@Override
	public int getPower()
	{
		return power != null ? power : 0;
	}

	@Override
	public void setPower(int power)
	{
		Preconditions.checkArgument(power >= 0, "power cannot be less than zero: %s", power);
		Preconditions.checkArgument(power <= 255, "power cannot be more than 255: %s", power);
		this.power = power;
	}

	/**
	 * Required method for Bukkit deserialization.
	 *
	 * @param args
	 *            A serialized FireworkMetaMock object in a Map&lt;String,
	 *            Object&gt; format.
	 * @return A new instance of the FireworkMetaMock class.
	 */
	@SuppressWarnings("unchecked")
	@NotNull
	public static FireworkMetaMock deserialize(@NotNull Map<String, Object> args)
	{
		FireworkMetaMock serialMock = new FireworkMetaMock();
		serialMock.deserializeInternal(args);
		List<FireworkEffect> effects = NbtParser.parseList(args.get("effects"),
				e -> (FireworkEffect) FireworkEffect.deserialize((Map<String, Object>) e));
		if (effects != null)
		{
			serialMock.addEffects(effects.toArray(new FireworkEffect[0]));
		}
		if (args.containsKey(POWER_KEY))
		{
			serialMock.power = NbtParser.parseInteger(args.get(POWER_KEY));
		}
		return serialMock;
	}

	/**
	 * Serializes the properties of an FireworkMetaMock to a HashMap. Unimplemented
	 * properties are not present in the map.
	 *
	 * @return A HashMap of String, Object pairs representing the FireworkMetaMock.
	 */
	@Override
	@NotNull
	public Map<String, Object> serialize()
	{
		final Map<String, Object> serialized = super.serialize();
		if (hasPower())
		{
			serialized.put(POWER_KEY, power);
		}
		serialized.put("effects", effects.stream().map(FireworkEffect::serialize).toList());
		return serialized;
	}

	@Override
	protected String getTypeName()
	{
		return "FIREWORK";
	}
}
