package org.mockmc.mockmc.inventory.meta;

import java.util.Objects;

import com.google.common.collect.ImmutableList;
import org.bukkit.Color;
import org.bukkit.NamespacedKey;
import org.bukkit.Registry;
import org.bukkit.configuration.serialization.DelegateDeserialization;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.PotionMeta;
import org.bukkit.potion.PotionData;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.potion.PotionType;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.mockmc.mockmc.inventory.SerializableMeta;
import org.mockmc.mockmc.util.NbtParser;
import org.mockmc.mockmc.potion.PotionUtils;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * Mock implementation of a {@link PotionMeta}.
 *
 * @see ItemMetaMock
 */
@DelegateDeserialization(SerializableMeta.class)
@SuppressWarnings(
{"removal", "unchecked"})
public class PotionMetaMock extends ItemMetaMock
		implements
			org.mockmc.mockmc.generated.server.org.bukkit.inventory.meta.PotionMetaBaseMock
{

	public static final String BASE_POTION_TYPE = "base-potion-type";

	@Nullable
	private PotionType type;

	@NotNull
	private List<PotionEffect> effects = new ArrayList<>();

	@Nullable
	private Color color;

	@Nullable
	private String customName;

	/**
	 * Constructs a new {@link PotionMetaMock}.
	 */
	public PotionMetaMock()
	{
		super();
	}

	/**
	 * Constructs a new {@link PotionMetaMock}, cloning the data from another.
	 *
	 * @param meta
	 *            The meta to clone.
	 */
	public PotionMetaMock(@NotNull ItemMeta meta)
	{
		super(meta);
		if (meta instanceof PotionMeta potionMeta)
		{
			this.effects = new ArrayList<>(potionMeta.getCustomEffects());
			this.type = potionMeta.getBasePotionType();
			this.color = potionMeta.getColor();
			this.customName = potionMeta.getCustomPotionName();
		}
	}

	@Override
	public int hashCode()
	{
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + effects.hashCode();
		result = prime * result + Objects.hashCode(type);
		result = prime * result + Objects.hashCode(color);
		result = prime * result + Objects.hashCode(customName);
		return result;
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
		PotionMetaMock other = (PotionMetaMock) obj;
		return Objects.equals(this.type, other.type) && Objects.equals(this.color, other.color)
				&& Objects.equals(this.effects, other.effects) && Objects.equals(this.customName, other.customName);
	}

	@Override
	@SuppressWarnings(
	{"MethodDoesntCallSuperMethod", "java:S2975", "java:S1182"})
	@NotNull
	public PotionMetaMock clone()
	{
		return new PotionMetaMock(this);
	}

	@Override
	public boolean addCustomEffect(@NotNull PotionEffect effect, boolean overwrite)
	{
		int index = indexOf(effect.getType());
		if (index == -1)
		{
			effects.add(effect);
			return true;
		}
		if (!overwrite)
		{
			return false;
		}
		PotionEffect prev = effects.get(index);
		if (prev.getDuration() == effect.getDuration())
		{
			return false;
		}
		effects.set(index, effect);
		return true;
	}

	@Override
	public boolean clearCustomEffects()
	{
		boolean empty = effects.isEmpty();
		effects.clear();
		return !empty;
	}

	@Override
	@NotNull
	public List<PotionEffect> getCustomEffects()
	{
		return ImmutableList.copyOf(effects);
	}

	@Override
	public boolean hasCustomEffect(@NotNull PotionEffectType type)
	{
		return indexOf(type) != -1;
	}

	@Override
	public boolean hasCustomEffects()
	{
		return !effects.isEmpty();
	}

	@Override
	public boolean removeCustomEffect(@NotNull PotionEffectType type)
	{
		Iterator<PotionEffect> iterator = effects.iterator();
		boolean changed = false;
		while (iterator.hasNext())
		{
			PotionEffect effect = iterator.next();
			if (type.equals(effect.getType()))
			{
				iterator.remove();
				changed = true;
			}
		}
		return changed;
	}

	private int indexOf(PotionEffectType type)
	{
		for (int i = 0; i < effects.size(); ++i)
		{
			if (effects.get(i).getType().equals(type))
			{
				return i;
			}
		}
		return -1;
	}

	@Override
	public boolean hasColor()
	{
		return color != null;
	}

	@Override
	@Nullable
	public Color getColor()
	{
		// Return an immutable copy
		return color == null ? null : Color.fromRGB(color.asRGB());
	}

	@Override
	public void setColor(@Nullable Color color)
	{
		this.color = color == null ? null : Color.fromRGB(color.asRGB());
	}

	@Override
	public boolean hasCustomPotionName()
	{
		return this.customName != null;
	}

	@Override
	@Nullable
	public String getCustomPotionName()
	{
		return this.customName;
	}

	@Override
	public void setCustomPotionName(@Nullable String customName)
	{
		this.customName = customName;
	}

	@Override
	public void setBasePotionData(@Nullable PotionData data)
	{
		setBasePotionType(PotionUtils.fromBukkit(data));
	}

	@Override
	@Nullable
	public PotionData getBasePotionData()
	{
		return PotionUtils.toBukkit(getBasePotionType());
	}

	@Override
	public void setBasePotionType(@Nullable PotionType type)
	{
		this.type = type;
	}

	@Override
	@Nullable
	public PotionType getBasePotionType()
	{
		return this.type;
	}

	@Override
	public boolean hasBasePotionType()
	{
		return this.type != null;
	}

	@Override
	protected void deserializeInternal(@NotNull Map<String, Object> args)
	{
		super.deserializeInternal(args);
		this.effects = NbtParser.parseList(args.get("effects"), o -> new PotionEffect((Map<String, Object>) o));
		if (this.effects == null)
		{
			this.effects = new ArrayList<>();
		}
		if (args.containsKey(BASE_POTION_TYPE))
		{
			this.type = Registry.POTION.get(NamespacedKey.fromString((String) args.get(BASE_POTION_TYPE)));
		}
		if (args.containsKey("custom-color"))
		{
			this.color = (Color) args.get("custom-color");
		}
		if (args.containsKey("custom-potion-name"))
		{
			this.customName = (String) args.get("custom-potion-name");
		}
	}

	/**
	 * Required method for Bukkit deserialization.
	 *
	 * @param args
	 *            A serialized PotionMetaMock object in a Map&lt;String, Object&gt;
	 *            format.
	 * @return A new instance of the PotionMetaMock class.
	 */
	@NotNull
	public static PotionMetaMock deserialize(@NotNull Map<String, Object> args)
	{
		PotionMetaMock serialMock = new PotionMetaMock();
		serialMock.deserializeInternal(args);
		return serialMock;
	}

	/**
	 * Serializes the properties of an PotionMetaMock to a HashMap. Unimplemented
	 * properties are not present in the map.
	 *
	 * @return A HashMap of String, Object pairs representing the PotionMetaMock.
	 */
	@Override
	@NotNull
	public Map<String, Object> serialize()
	{
		final Map<String, Object> serialized = super.serialize();
		serialized.put("effects", this.effects.stream().map(PotionEffect::serialize).toList());
		if (hasBasePotionType())
		{
			serialized.put(BASE_POTION_TYPE, this.getBasePotionType().key().toString());
		}
		if (color != null)
		{
			serialized.put("custom-color", color);
		}
		if (customName != null)
		{
			serialized.put("custom-potion-name", customName);
		}
		return serialized;
	}

	@Override
	protected String getTypeName()
	{
		return "POTION";
	}
}
