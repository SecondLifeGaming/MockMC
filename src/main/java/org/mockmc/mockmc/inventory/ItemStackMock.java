package org.mockmc.mockmc.inventory;

import com.google.common.base.Preconditions;
import com.google.gson.JsonObject;
import io.papermc.paper.datacomponent.DataComponentType;
import io.papermc.paper.persistence.PersistentDataContainerView;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.serializer.gson.GsonComponentSerializer;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.Registry;
import org.bukkit.configuration.serialization.DelegateDeserialization;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.LivingEntity;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ItemType;
import org.bukkit.inventory.meta.Damageable;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;
import org.checkerframework.checker.nullness.qual.NonNull;
import org.jetbrains.annotations.ApiStatus;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.jetbrains.annotations.Unmodifiable;
import org.mockmc.mockmc.exception.IncompatiblePaperVersionException;
import org.mockmc.mockmc.exception.ItemMetaInitException;
import org.mockmc.mockmc.inventory.meta.ItemMetaMock;
import org.mockmc.mockmc.persistence.PersistentDataContainerViewMock;

import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Locale;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.function.Consumer;
import java.util.function.Predicate;

/**
 * Mock implementation of an {@link ItemStack}.
 *
 * @mockmc.version 1.21-1.0.0
 */
@SuppressWarnings(
{"deprecation", "unchecked", "java:S1135"})
@DelegateDeserialization(ItemStack.class)
public class ItemStackMock extends ItemStack
{

	private static final String FIELD_AMOUNT = "amount";
	private static final String FIELD_COUNT = "count";
	private static final String FIELD_MATERIAL = "type";

	@NonNull
	@ApiStatus.Internal
	public static ItemStackMock fromJson(@NonNull JsonObject json)
	{
		Preconditions.checkNotNull(json, "json cannot be null");

		int amountValue = json.get(FIELD_AMOUNT).getAsInt();
		NamespacedKey materialKey = NamespacedKey.fromString(json.get(FIELD_MATERIAL).getAsString());
		Preconditions.checkArgument(materialKey != null, "Material field does not exist");

		try
		{
			Material materialValue = Material.valueOf(materialKey.getKey().toUpperCase(Locale.ROOT));
			return new ItemStackMock(materialValue, amountValue);
		} catch (IllegalArgumentException e)
		{
			throw new IncompatiblePaperVersionException(e);
		}
	}

	private final Map<DataComponentType, Object> components = new HashMap<>();

	private ItemType type = ItemType.AIR;
	private int amount = 1;
	private ItemMeta itemMeta;
	private short durability = -1;

	private static final ItemStackMock EMPTY = new ItemStackMock((Void) null);
	private static final String ITEM_META_INITIALIZATION_ERROR = "Failed to instanciate item meta class ";

	// Utility
	protected ItemStackMock()
	{
	}

	public ItemStackMock(@NotNull Material type)
	{
		this(type, 1);
	}

	public ItemStackMock(@NotNull ItemStack stack) throws IllegalArgumentException
	{
		this.type = stack.getType().asItemType();
		this.amount = stack.getAmount();
		this.durability = initDurability(this.type);
		setItemMeta(stack.getItemMeta());
		if (stack instanceof ItemStackMock stackMock)
		{
			this.components.putAll(stackMock.components);
		}
	}

	public ItemStackMock(@NotNull Material type, int amount)
	{
		this.type = type.asItemType();
		this.amount = amount;
		this.durability = initDurability(this.type);
		this.itemMeta = findItemMeta(type);
	}

	@SuppressWarnings("java:S1172")
	private ItemStackMock(@Nullable Void unused)
	{
		this.type = ItemType.AIR;
		this.durability = initDurability(type);
		this.amount = 0;
		this.itemMeta = null;
	}

	/**
	 * By some reason paper differentiates between an item with durability set and
	 * one without durability set
	 */
	private short initDurability(ItemType type)
	{
		if (type == null || type.getMaxDurability() == 0)
		{
			return -1;
		}
		return 0;
	}

	@Override
	public void setType(@NotNull Material type)
	{
		if (!type.isItem() || type.isAir())
		{
			this.type = ItemType.AIR;
			this.itemMeta = null;
			this.durability = initDurability(this.type);
			return;
		}
		if (type != Registry.MATERIAL.get(this.type.getKey()))
		{
			short oldDefault = initDurability(this.type);
			this.type = type.asItemType();
			if (this.itemMeta == null)
			{
				this.itemMeta = findItemMeta(type);
			} else
			{
				this.itemMeta = Bukkit.getItemFactory().asMetaFor(this.itemMeta, type);
			}
			short newDefault = initDurability(this.type);
			if (this.durability == oldDefault || newDefault == -1)
			{
				this.durability = newDefault;
				if (this.itemMeta instanceof Damageable damageable)
				{
					damageable.resetDamage();
				}
			} else
			{
				setDurability(this.durability);
			}
		}
	}

	@Override
	@NotNull
	public Material getType()
	{
		return Objects.requireNonNull(Registry.MATERIAL.get(this.type.getKey()));
	}

	@Override
	public int getAmount()
	{
		return this.amount;
	}

	@Override
	public void setAmount(int amount)
	{
		this.amount = amount;
	}

	@Override
	public boolean isEmpty()
	{
		return this == EMPTY || this.type == ItemType.AIR || this.amount <= 0;
	}

	@Override
	public boolean setItemMeta(@Nullable ItemMeta itemMeta)
	{
		if (itemMeta == null || ItemType.AIR.equals(this.type))
		{
			this.itemMeta = findItemMeta(getType());
			this.durability = initDurability(this.type);
			return true;
		}
		if (!Bukkit.getItemFactory().isApplicable(itemMeta, this))
		{
			return false;
		}

		itemMeta = Bukkit.getItemFactory().asMetaFor(itemMeta, this);
		if (itemMeta == null)
		{
			return true;
		}
		this.itemMeta = itemMeta;

		if (this.itemMeta instanceof Damageable damageable)
		{
			short defaultDurability = initDurability(this.type);
			if (!damageable.hasDamageValue())
			{
				durability = defaultDurability;
			} else
			{
				short value = (short) Math.min(Short.MAX_VALUE, damageable.getDamage());
				setDurability(value);
				if (durability == defaultDurability)
				{
					damageable.resetDamage();
				}
			}
		}
		return true;
	}

	@Override
	public ItemMeta getItemMeta()
	{
		return this.itemMeta != null ? this.itemMeta.clone() : null;
	}

	@Override
	public boolean hasItemMeta()
	{
		return this.itemMeta != null && !Bukkit.getItemFactory().equals(itemMeta, null);
	}

	@Override
	public @NotNull Component effectiveName()
	{
		ItemMeta meta = getItemMeta();
		if (meta != null && meta.hasDisplayName())
		{
			return Objects.requireNonNull(meta.displayName());
		}
		return Component.translatable(getType().translationKey());
	}

	@Override
	public int getMaxStackSize()
	{
		if (this.itemMeta == null)
		{
			return 0;
		}

		return this.itemMeta.hasMaxStackSize() ? this.itemMeta.getMaxStackSize() : this.type.getMaxStackSize();
	}

	@Override
	public short getDurability()
	{
		if (this.type == ItemType.AIR)
		{
			return -1;
		}

		return (short) Math.max(this.durability, 0);
	}

	@Override
	public void setDurability(short durability)
	{
		short oldDurability = this.durability;
		this.durability = (short) Math.clamp(durability, 0, this.type.getMaxDurability());
		if ((this.itemMeta instanceof Damageable damageable) && this.durability != oldDurability)
		{
			damageable.setDamage(this.durability);
		}
	}

	@Override
	public void addUnsafeEnchantment(@NotNull Enchantment ench, int level)
	{
		Preconditions.checkArgument(ench != null, "Enchantment cannot be null");

		if (this.itemMeta != null)
		{
			this.itemMeta.addEnchant(ench, level, true);
		}
	}

	@Override
	public int getEnchantmentLevel(Enchantment ench)
	{
		Preconditions.checkArgument(ench != null, "Enchantment cannot be null");

		final ItemMeta meta = this.itemMeta;
		Preconditions.checkNotNull(meta, "Meta must not be null");

		return meta.getEnchantLevel(ench);
	}

	@Override
	public @NotNull Map<Enchantment, Integer> getEnchantments()
	{
		return this.hasItemMeta() ? itemMeta.getEnchants() : Map.of();
	}

	@Override
	public boolean isSimilar(@Nullable ItemStack stack)
	{
		if (stack == null)
		{
			return false;
		}
		if (!(stack instanceof final ItemStackMock bukkit))
		{
			return stack.isSimilar(this);
		}
		if (this == bukkit)
		{
			return true;
		}
		return this.type == bukkit.type && hasSimilarItemMeta(stack.getItemMeta());
	}

	/**
	 * Validate that the item meta is equal for both items.
	 *
	 * @param itemMeta
	 *            The item meta to be validated.
	 *
	 * @return {@code true} if equal, or {@code false} otherwise.
	 */
	private boolean hasSimilarItemMeta(@Nullable ItemMeta itemMeta)
	{
		if (this.itemMeta == null)
		{
			return itemMeta == null;
		}

		return this.itemMeta.equals(itemMeta);
	}

	private final PersistentDataContainerView pdcView = new PersistentDataContainerViewMock()
	{
		@Override
		public <P, C> @Nullable C get(@NotNull NamespacedKey key, @NotNull PersistentDataType<P, C> type)
		{
			if (itemMeta == null)
			{
				return null;
			}

			return itemMeta.getPersistentDataContainer().get(key, type);
		}

		@Override
		public @NotNull Set<NamespacedKey> getKeys()
		{
			if (itemMeta == null)
			{
				return Set.of();
			}

			return itemMeta.getPersistentDataContainer().getKeys();
		}

		@Override
		public int getSize()
		{
			if (itemMeta == null)
			{
				return 0;
			}

			return itemMeta.getPersistentDataContainer().getSize();
		}

	};

	@Override
	public @NotNull PersistentDataContainerView getPersistentDataContainer()
	{
		return pdcView;
	}

	@Override
	public boolean editPersistentDataContainer(@NotNull Consumer<PersistentDataContainer> consumer)
	{
		if (itemMeta == null)
		{
			return false;
		}

		consumer.accept(itemMeta.getPersistentDataContainer());
		return true;
	}

	@Override
	public @NotNull ItemStack withType(@NotNull Material type)
	{
		ItemStackMock item = new ItemStackMock(type, getAmount());
		if (this.durability != -1)
		{
			item.setDurability(this.durability);
		}
		item.setItemMeta(this.itemMeta);

		return item;
	}

	@Override
	public boolean containsEnchantment(@NotNull Enchantment ench)
	{
		if (this.itemMeta == null)
		{
			return false;
		}

		return this.itemMeta.getEnchants().containsKey(ench);
	}

	@Override
	public int removeEnchantment(@NotNull Enchantment ench)
	{
		if (this.itemMeta == null)
		{
			return 0;
		}

		int level = this.itemMeta.getEnchantLevel(ench);
		this.itemMeta.removeEnchant(ench);
		return level;
	}

	@Override
	public void removeEnchantments()
	{
		if (this.itemMeta == null)
		{
			return;
		}

		this.itemMeta.removeEnchantments();
	}

	@Override
	public int getMaxItemUseDuration(@NotNull LivingEntity entity)
	{
		return 0;
	}

	public static ItemStack empty()
	{
		return EMPTY.clone();
	}

	@SuppressWarnings(
	{"MethodDoesntCallSuperMethod", "java:S2975", "java:S1182"})
	@Override
	public @NotNull ItemStack clone()
	{
		return new ItemStackMock(this);
	}

	@Override
	public @Nullable <T> T getData(DataComponentType.@NotNull Valued<T> type)
	{
		return (T) this.components.get(type);
	}

	@Override
	public boolean hasData(@NotNull DataComponentType type)
	{
		return this.components.containsKey(type);
	}

	@Override
	public @Unmodifiable Set<@NotNull DataComponentType> getDataTypes()
	{
		return this.components.keySet();
	}

	@Override
	public <T> void setData(DataComponentType.@NotNull Valued<T> type, @NonNull T value)
	{
		this.components.put(type, value);
	}

	@Override
	public void setData(DataComponentType.@NotNull NonValued type)
	{
		this.components.put(type, null);
	}

	@Override
	public void unsetData(@NotNull DataComponentType type)
	{
		this.components.remove(type);
	}

	@Override
	public void resetData(@NotNull DataComponentType type)
	{
		unsetData(type);
	}

	@Override
	public void copyDataFrom(@NotNull ItemStack source, @NotNull Predicate<@NotNull DataComponentType> filter)
	{
		Preconditions.checkArgument(source != null, "source cannot be null");
		Preconditions.checkArgument(filter != null, "filter cannot be null");

		if (isEmpty() || source.isEmpty())
		{
			return;
		}

		for (DataComponentType dataType : source.getDataTypes())
		{
			if (filter.test(dataType))
			{
				if (dataType instanceof DataComponentType.Valued<?> valuedType)
				{
					copyValuedData(source, valuedType);
				} else if (dataType instanceof DataComponentType.NonValued nonValuedType)
				{
					this.setData(nonValuedType);
				}
			}
		}
	}

	private <T> void copyValuedData(@NotNull ItemStack source, DataComponentType.Valued<T> valuedType)
	{
		T value = source.getData(valuedType);
		if (value != null)
		{
			this.setData(valuedType, value);
		}
	}

	@Override
	public boolean isDataOverridden(@NotNull DataComponentType type)
	{
		return !isEmpty() && this.components.containsKey(type);
	}

	@Override
	public boolean matchesWithoutData(@NotNull ItemStack item, @NotNull Set<@NotNull DataComponentType> excludeTypes,
			boolean ignoreCount)
	{
		if (this.getType() != item.getType())
		{
			return false;
		}
		if (!ignoreCount && this.getAmount() != item.getAmount())
		{
			return false;
		}

		for (DataComponentType componentType : this.getDataTypes())
		{
			if (excludeTypes.contains(componentType))
			{
				continue;
			}
			if (!item.hasData(componentType))
			{
				return false;
			}
			if (componentType instanceof DataComponentType.Valued<?> valuedType
					&& !Objects.equals(this.getData(valuedType), item.getData(valuedType)))
			{
				return false;
			}
		}

		for (DataComponentType componentType : item.getDataTypes())
		{
			if (!excludeTypes.contains(componentType) && !this.hasData(componentType))
			{
				return false;
			}
		}

		return true;
	}

	@Override
	public boolean equals(Object obj)
	{
		if (obj == null)
		{
			return false;
		}
		if (!(obj instanceof ItemStack stack))
		{
			return false;
		}
		if (stack instanceof ItemStackMock bukkit)
		{
			return isSimilar(bukkit) && this.getAmount() == bukkit.getAmount()
					&& this.getDurability() == bukkit.getDurability()
					&& Objects.equals(this.getItemMeta(), bukkit.getItemMeta());
		} else
		{
			// will delegate back to this method / no stack overflow as obj then will be
			// item stack mock instance
			return stack.equals(this);
		}
	}

	@Override
	public int hashCode()
	{
		if (type == ItemType.AIR && this != EMPTY)
		{
			return EMPTY.hashCode();
		} else
		{
			int hash = Objects.hash(type, durability, lore(), getEnchantments());
			hash = hash * 31 + this.getAmount();
			return hash;
		}
	}

	private static @Nullable ItemMeta findItemMeta(Material material)
	{
		if (!material.isItem() || material == Material.AIR)
		{
			return null;
		}
		final Class<? extends ItemMeta> itemMetaClass = Objects.requireNonNull(Registry.ITEM.get(material.getKey()))
				.getItemMetaClass();
		if (ItemMetaMock.class.isAssignableFrom(itemMetaClass))
		{
			try
			{
				for (var ctor : itemMetaClass.getDeclaredConstructors())
				{
					if (ctor.getParameterCount() == 1 && ctor.getParameters()[0].getType() == Material.class)
					{
						return (ItemMeta) ctor.newInstance(material);
					}
				}
				return itemMetaClass.getConstructor().newInstance();
			} catch (InstantiationException | IllegalAccessException | InvocationTargetException
					| NoSuchMethodException e)
			{
				throw new ItemMetaInitException(ITEM_META_INITIALIZATION_ERROR + itemMetaClass, e);
			}
		}
		return new ItemMetaMock();
	}

	@NotNull
	@Override
	public Map<String, Object> serialize()
	{
		Map<String, Object> result = new LinkedHashMap<>();
		result.put(FIELD_MATERIAL, this.getType().name());
		result.put("id", this.getType().getKey().toString());
		result.put(FIELD_AMOUNT, this.getAmount());
		result.put(FIELD_COUNT, this.getAmount());
		Map<String, Object> componentsMap = new LinkedHashMap<>();
		if (this.hasItemMeta())
		{
			componentsMap.putAll(this.getItemMeta().serialize());
		}

		if (!this.components.isEmpty())
		{
			for (Map.Entry<DataComponentType, Object> entry : this.components.entrySet())
			{
				Object value = entry.getValue();
				if (value instanceof Component component)
				{
					value = GsonComponentSerializer.gson().serialize(component);
				}
				componentsMap.put(entry.getKey().getKey().toString(), value);
			}
		}

		if (!componentsMap.isEmpty())
		{
			result.put("components", componentsMap);
			result.put("meta", componentsMap);
		}

		return result;
	}

	@NotNull
	public static ItemStack deserialize(@NotNull Map<String, Object> args)
	{
		return Bukkit.getUnsafe().deserializeStack(args);
	}

	@Override
	public byte @NotNull [] serializeAsBytes()
	{
		return Bukkit.getUnsafe().serializeItem(this);
	}

}
