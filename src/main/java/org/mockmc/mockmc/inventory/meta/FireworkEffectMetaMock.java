package org.mockmc.mockmc.inventory.meta;

import org.bukkit.FireworkEffect;
import org.bukkit.configuration.serialization.DelegateDeserialization;
import java.util.Objects;
import org.bukkit.inventory.meta.FireworkEffectMeta;
import org.bukkit.inventory.meta.ItemMeta;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.mockmc.mockmc.inventory.SerializableMeta;
import java.util.Map;

/**
 * Mock implementation of an {@link FireworkEffectMeta}.
 *
 * @see ItemMetaMock
 */
@DelegateDeserialization(SerializableMeta.class)
public class FireworkEffectMetaMock extends ItemMetaMock
		implements
			org.mockmc.mockmc.generated.server.org.bukkit.inventory.meta.FireworkEffectMetaBaseMock
{

	@Nullable
	private FireworkEffect effect;

	/**
	 * Constructs a new {@link FireworkEffectMetaMock}.
	 */
	public FireworkEffectMetaMock()
	{
		super();
	}

	/**
	 * Constructs a new {@link FireworkEffectMetaMock}, cloning the data from
	 * another.
	 *
	 * @param meta
	 *            The meta to clone.
	 */
	public FireworkEffectMetaMock(@NotNull ItemMeta meta)
	{
		super(meta);
		if (meta instanceof FireworkEffectMeta fireworkEffectMeta)
		{
			this.effect = fireworkEffectMeta.getEffect();
		}
	}

	@Override
	public int hashCode()
	{
		final int prime = 31;
		int result = super.hashCode();
		return prime * result + (effect != null ? effect.hashCode() : 0);
	}

	@Override
	public boolean equals(Object obj)
	{
		if (this == obj)
		{
			return true;
		}
		if (!super.equals(obj))
		{
			return false;
		}
		if (obj.getClass() != this.getClass())
		{
			return false;
		}
		FireworkEffectMetaMock other = (FireworkEffectMetaMock) obj;
		return Objects.equals(effect, other.effect);
	}

	@Override
	@SuppressWarnings(
	{"MethodDoesntCallSuperMethod", "java:S2975", "java:S1182"})
	@NotNull
	public FireworkEffectMetaMock clone()
	{
		return new FireworkEffectMetaMock(this);
	}

	@Override
	public void setEffect(@Nullable FireworkEffect effect)
	{
		this.effect = effect;
	}

	@Override
	public boolean hasEffect()
	{
		return effect != null;
	}

	@Override
	@Nullable
	public FireworkEffect getEffect()
	{
		return effect;
	}

	/**
	 * Required method for Bukkit deserialization.
	 *
	 * @param args
	 *            A serialized FireworkEffectMetaMock object in a Map&lt;String,
	 *            Object&gt; format.
	 * @return A new instance of the FireworkEffectMetaMock class.
	 */
	@SuppressWarnings("unchecked")
	@NotNull
	public static FireworkEffectMetaMock deserialize(@NotNull Map<String, Object> args)
	{
		FireworkEffectMetaMock serialMock = new FireworkEffectMetaMock();
		serialMock.deserializeInternal(args);
		if (args.containsKey("effect"))
		{
			serialMock.effect = (FireworkEffect) FireworkEffect.deserialize((Map<String, Object>) args.get("effect"));
		}
		return serialMock;
	}

	/**
	 * Serializes the properties of an FireworkEffectMetaMock to a HashMap.
	 * Unimplemented properties are not present in the map.
	 *
	 * @return A HashMap of String, Object pairs representing the
	 *         FireworkEffectMetaMock.
	 */
	@Override
	@NotNull
	public Map<String, Object> serialize()
	{
		final Map<String, Object> serialized = super.serialize();
		if (effect != null)
		{
			serialized.put("effect", effect.serialize());
		}
		return serialized;
	}

	@Override
	protected String getTypeName()
	{
		return "FIREWORK_EFFECT";
	}
}
