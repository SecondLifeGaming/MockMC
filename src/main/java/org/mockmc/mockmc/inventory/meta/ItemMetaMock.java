package org.mockmc.mockmc.inventory.meta;

import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableMultimap;
import com.google.common.collect.ImmutableSortedMap;
import com.google.common.collect.LinkedHashMultimap;
import com.google.common.collect.Multimap;
import com.google.common.collect.SetMultimap;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.serializer.bungeecord.BungeeComponentSerializer;
import net.kyori.adventure.text.serializer.gson.GsonComponentSerializer;
import net.kyori.adventure.text.serializer.legacy.LegacyComponentSerializer;
import net.md_5.bungee.api.chat.BaseComponent;
import org.bukkit.Bukkit;
import org.bukkit.NamespacedKey;
import org.bukkit.Registry;
import org.bukkit.Tag;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeModifier;
import org.bukkit.configuration.serialization.DelegateDeserialization;
import org.bukkit.damage.DamageType;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemRarity;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.Damageable;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.Repairable;
import org.bukkit.inventory.meta.components.CustomModelDataComponent;
import org.bukkit.inventory.meta.components.EquippableComponent;
import org.bukkit.inventory.meta.components.FoodComponent;
import org.bukkit.inventory.meta.components.JukeboxPlayableComponent;
import org.bukkit.inventory.meta.components.ToolComponent;
import org.bukkit.inventory.meta.components.UseCooldownComponent;
import org.bukkit.persistence.PersistentDataContainer;
import org.jetbrains.annotations.ApiStatus;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.mockmc.mockmc.inventory.SerializableMeta;
import org.mockmc.mockmc.inventory.meta.components.CustomModelDataComponentMock;
import org.mockmc.mockmc.inventory.meta.components.EquippableComponentMock;
import org.mockmc.mockmc.inventory.meta.components.FoodComponentMock;
import org.mockmc.mockmc.inventory.meta.components.JukeboxPlayableComponentMock;
import org.mockmc.mockmc.inventory.meta.components.ToolComponentMock;
import org.mockmc.mockmc.inventory.meta.components.UseCooldownComponentMock;
import org.mockmc.mockmc.persistence.PersistentDataContainerMock;
import org.mockmc.mockmc.util.NbtParser;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Comparator;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;
import static java.util.Objects.nonNull;

/**
 * Mock implementation of an {@link ItemMeta}, {@link Damageable}, and
 * {@link Repairable}.
 */
@SuppressWarnings(
{"deprecation", "removal"})
@DelegateDeserialization(SerializableMeta.class)
public class ItemMetaMock
		implements
			Damageable,
			Repairable,
			org.mockmc.mockmc.generated.server.org.bukkit.inventory.meta.ItemMetaBaseMock
{

	public static final String ATTRIBUTE_MODIFIERS = "attribute-modifiers";
	private static final String ATTRIBUTE_CANNOT_BE_NULL = "Attribute cannot be null";

	public static final String BLOCK_DATA = "BlockStateTag";

	public static final String CUSTOM_MODEL_DATA = "custom-model-data";

	public static final String DAMAGE = "Damage";

	public static final String DAMAGE_RESISTANT = "damage-resistant";

	public static final String DISPLAY_NAME = "display-name";

	public static final String ENCHANTABLE = "enchantable";

	public static final String ENCHANTMENT_GLINT_OVERRIDE = "enchantment-glint-override";

	public static final String ENCHANTMENTS = "enchantments";

	public static final String EQUIPPABLE = "equippable";

	public static final String FIRE_RESISTANT = "FireResistant";

	public static final String FOOD = "food";

	public static final String GLIDER = "glider";

	public static final String HIDE_TOOLTIP = "HideTooltip";

	public static final String ITEM_FLAGS = "ItemFlags";

	public static final String ITEM_MODEL = "item-model";

	public static final String ITEM_NAME = "item-name";

	public static final String JUKEBOX_PLAYABLE = "jukebox-playable";

	public static final String LORE_KEY = "lore";

	public static final String MAX_DAMAGE = "max-damage";

	public static final String MAX_STACK_SIZE = "max-stack-size";

	public static final String META_TYPE = "meta-type";

	public static final String PUBLIC_BUKKIT_VALUES = "PublicBukkitValues";

	public static final String RARITY = "rarity";

	public static final String REPAIR_COST = "repair-cost";

	public static final String TOOL = "tool";

	public static final String TOOL_TIP_STYLE = "tool-tip-style";

	public static final String UNBREAKABLE = "Unbreakable";

	public static final String USE_COOLDOWN = "use-cooldown";

	public static final String USE_REMAINDER = "use-remainder";

	private static final int ABSOLUTE_MAX_STACK_SIZE = 99;

	// We store the raw JSON representation of all text data. See SPIGOT-5063,
	// SPIGOT-5656, SPIGOT-5304
	@Nullable
	private String displayNameData = null;

	@Nullable
	private List<String> loreData = null;

	@Nullable
	private Integer damageData = null;

	@Nullable
	private Integer maxDamage;

	private int repairCost = 0;

	@NotNull
	private Map<Enchantment, Integer> enchants = new HashMap<>();

	@NotNull
	private Multimap<Attribute, AttributeModifier> attributeModifiers = LinkedHashMultimap.create();

	@NotNull
	private Set<ItemFlag> hideFlags = EnumSet.noneOf(ItemFlag.class);

	@NotNull
	private PersistentDataContainerMock persistentDataContainer = new PersistentDataContainerMock();

	private boolean unbreakableData = false;

	@Nullable
	private Integer customModelDataData = null;

	private boolean hideTooltip;

	private boolean fireResistant;

	private boolean gliderData;

	@Nullable
	private Integer maxStackSizeData = null;

	@Nullable
	private Boolean enchantmentGlintOverride = null;

	@Nullable
	private ItemRarity rarityData;

	@Nullable
	private Component itemNameData = null;

	@Nullable
	private Integer enchantableData;

	@Nullable
	private ItemStack useRemainder;

	@Nullable
	private NamespacedKey itemModel;

	@Nullable
	private NamespacedKey tooltipStyle;

	@Nullable
	private NamespacedKey damageResistant;

	@Nullable
	private CustomModelDataComponent customModelDataComponent;

	@Nullable
	private UseCooldownComponent useCooldown;

	@Nullable
	private FoodComponent foodComponent;

	@Nullable
	private ToolComponent toolComponent;

	@Nullable
	private EquippableComponent equippableComponent;

	@Nullable
	private JukeboxPlayableComponent jukeboxPlayableComponent;

	@Nullable
	private Map<String, String> blockData = null;

	/**
	 * Constructs a new {@link ItemMetaMock}.
	 */
	public ItemMetaMock()
	{
	}

	/**
	 * Constructs a new {@link ItemMetaMock}, copying the data from another.
	 *
	 * @param meta
	 *            The meta to copy.
	 */
	public ItemMetaMock(@NotNull ItemMeta meta)
	{
		copyDisplayNameAndLore(meta);
		copyDamageAndRepair(meta);
		copyEnchantsAndModifiers(meta);
		copyFlagsAndPDC(meta);
		copyMiscellaneous(meta);
		copyComponents(meta);
	}

	private void copyDisplayNameAndLore(@NotNull ItemMeta meta)
	{
		if (meta.hasDisplayName())
		{
			displayNameData = GsonComponentSerializer.gson().serialize(meta.displayName());
		}
		if (meta.hasLore())
		{
			loreData = meta.lore().stream().map(c -> GsonComponentSerializer.gson().serialize(c)).toList();
		}
	}

	private void copyDamageAndRepair(@NotNull ItemMeta meta)
	{
		if (meta instanceof Damageable d)
		{
			this.damageData = d.hasDamageValue() ? d.getDamage() : null;
			this.maxDamage = d.hasMaxDamage() ? d.getMaxDamage() : null;
		}
		if (meta instanceof Repairable r)
		{
			this.repairCost = r.getRepairCost();
		}
	}

	private void copyEnchantsAndModifiers(@NotNull ItemMeta meta)
	{
		if (meta.hasEnchants())
		{
			enchants = new HashMap<>(meta.getEnchants());
		}
		if (meta.hasAttributeModifiers())
		{
			this.attributeModifiers = LinkedHashMultimap.create(meta.getAttributeModifiers());
		}
	}

	private void copyFlagsAndPDC(@NotNull ItemMeta meta)
	{
		var tmpHideFlags = meta.getItemFlags();
		if (!tmpHideFlags.isEmpty())
		{
			hideFlags = EnumSet.copyOf(tmpHideFlags);
		}
		if (meta instanceof ItemMetaMock m)
		{
			this.persistentDataContainer = new PersistentDataContainerMock(m.persistentDataContainer);
		}
	}

	private void copyMiscellaneous(@NotNull ItemMeta meta)
	{
		unbreakableData = meta.isUnbreakable();
		customModelDataData = meta.hasCustomModelData() ? meta.getCustomModelData() : null;
		hideTooltip = meta.isHideTooltip();
		fireResistant = meta.isFireResistant();
		if (meta.hasMaxStackSize())
		{
			maxStackSizeData = meta.getMaxStackSize();
		}
		if (meta.hasEnchantmentGlintOverride())
		{
			enchantmentGlintOverride = meta.getEnchantmentGlintOverride();
		}
		if (meta.hasRarity())
		{
			rarityData = meta.getRarity();
		}
		if (meta.hasItemName())
		{
			itemNameData = meta.itemName();
		}
		if (meta.hasEnchantable())
		{
			enchantableData = meta.getEnchantable();
		}
		gliderData = meta.isGlider();
	}

	private void copyComponents(@NotNull ItemMeta meta)
	{
		if (meta.hasUseRemainder())
		{
			this.useRemainder = meta.getUseRemainder();
		}
		if (meta.hasItemModel())
		{
			this.itemModel = meta.getItemModel();
		}
		if (meta.hasTooltipStyle())
		{
			this.tooltipStyle = meta.getTooltipStyle();
		}
		if (meta.hasDamageResistant())
		{
			this.damageResistant = meta.getDamageResistant().getKey();
		}
		if (meta.hasUseCooldown())
		{
			this.useCooldown = meta.getUseCooldown();
		}
		if (meta.hasFood())
		{
			this.foodComponent = meta.getFood();
		}
		if (meta.hasTool())
		{
			this.toolComponent = meta.getTool();
		}
		if (meta.hasEquippable())
		{
			this.equippableComponent = meta.getEquippable();
		}
		if (meta.hasJukeboxPlayable())
		{
			this.jukeboxPlayableComponent = meta.getJukeboxPlayable();
		}
		if (meta instanceof ItemMetaMock m && m.hasBlockData())
		{
			this.blockData = new HashMap<>(m.getBlockData());
		}
	}

	static boolean checkConflictingEnchants(@Nullable Map<Enchantment, Integer> enchantments, @NotNull Enchantment ench)
	{
		if (enchantments == null || enchantments.isEmpty())
		{
			return false;
		}
		Iterator<Enchantment> var2 = enchantments.keySet().iterator();
		Enchantment enchant;
		do
		{
			if (!var2.hasNext())
			{
				return false;
			}
			enchant = var2.next();
		} while (!enchant.conflictsWith(ench));
		return true;
	}

	@Override
	public boolean hasDisplayName()
	{
		return this.displayNameData != null;
	}

	@Override
	public boolean hasCustomName()
	{
		return hasDisplayName();
	}

	@Override
	@Nullable
	public Component customName()
	{
		return displayNameData == null ? null : GsonComponentSerializer.gson().deserialize(displayNameData);
	}

	@Override
	public void customName(@Nullable Component component)
	{
		this.displayNameData = component == null ? null : GsonComponentSerializer.gson().serialize(component);
	}

	@Override
	@NotNull
	public String getDisplayName()
	{
		return this.displayNameData == null
				? ""
				: LegacyComponentSerializer.legacySection()
						.serialize(GsonComponentSerializer.gson().deserialize(this.displayNameData));
	}

	/**
	 * @deprecated Use {@link #displayName()} instead.
	 */
	@Override
	@Deprecated(since = "1.16")
	@NotNull
	public BaseComponent @NotNull [] getDisplayNameComponent()
	{
		return BungeeComponentSerializer.get()
				.serialize(GsonComponentSerializer.gson().deserialize(this.displayNameData));
	}

	@Override
	public void displayName(@Nullable net.kyori.adventure.text.Component displayName)
	{
		this.displayNameData = displayName == null
				? null
				: net.kyori.adventure.text.serializer.gson.GsonComponentSerializer.gson().serialize(displayName);
	}

	@Override
	@Nullable
	public net.kyori.adventure.text.Component displayName()
	{
		return this.displayNameData == null
				? null
				: net.kyori.adventure.text.serializer.gson.GsonComponentSerializer.gson()
						.deserialize(this.displayNameData);
	}

	@Override
	public void setDisplayName(@Nullable String name)
	{
		this.displayNameData = name == null
				? null
				: GsonComponentSerializer.gson().serialize(LegacyComponentSerializer.legacySection().deserialize(name));
	}

	/**
	 * @deprecated Use {@link #displayName(Component)} instead.
	 */
	@Override
	@Deprecated(since = "1.16")
	public void setDisplayNameComponent(BaseComponent @Nullable [] components)
	{
		this.displayNameData = GsonComponentSerializer.gson().serialize(BungeeComponentSerializer.get()
				.deserialize(Arrays.stream(components).filter(Objects::nonNull).toArray(BaseComponent[]::new)));
	}

	@Override
	public int hashCode()
	{
		return Objects.hash(displayNameData, loreData, damageData, repairCost, enchants, attributeModifiers, hideFlags,
				persistentDataContainer, unbreakableData, customModelDataData, maxDamage, hideTooltip, fireResistant,
				gliderData, maxStackSizeData, enchantmentGlintOverride, rarityData, itemNameData, enchantableData,
				useRemainder, itemModel, tooltipStyle, damageResistant, useCooldown, foodComponent, toolComponent,
				equippableComponent, jukeboxPlayableComponent, blockData);
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
		ItemMetaMock meta = (ItemMetaMock) obj;
		return isEquivalent(displayName(), meta.displayName()) && isEquivalent(lore(), meta.lore())
				&& isEquivalent(damageData, meta.damageData) && isEquivalent(repairCost, meta.repairCost)
				&& isEquivalent(enchants, meta.enchants) && isEquivalent(attributeModifiers, meta.attributeModifiers)
				&& isEquivalent(hideFlags, meta.hideFlags)
				&& isEquivalent(persistentDataContainer, meta.persistentDataContainer)
				&& isEquivalent(unbreakableData, meta.unbreakableData)
				&& isEquivalent(customModelDataData, meta.customModelDataData)
				&& isEquivalent(maxDamage, meta.maxDamage) && isEquivalent(hideTooltip, meta.hideTooltip)
				&& isEquivalent(fireResistant, meta.fireResistant) && isEquivalent(gliderData, meta.gliderData)
				&& isEquivalent(maxStackSizeData, meta.maxStackSizeData)
				&& isEquivalent(enchantmentGlintOverride, meta.enchantmentGlintOverride)
				&& isEquivalent(rarityData, meta.rarityData) && isEquivalent(itemNameData, meta.itemNameData)
				&& isEquivalent(enchantableData, meta.enchantableData) && isEquivalent(useRemainder, meta.useRemainder)
				&& isEquivalent(itemModel, meta.itemModel) && isEquivalent(tooltipStyle, meta.tooltipStyle)
				&& isEquivalent(damageResistant, meta.damageResistant) && isEquivalent(useCooldown, meta.useCooldown)
				&& isEquivalent(foodComponent, meta.foodComponent) && isEquivalent(toolComponent, meta.toolComponent)
				&& isEquivalent(equippableComponent, meta.equippableComponent)
				&& isEquivalent(jukeboxPlayableComponent, meta.jukeboxPlayableComponent)
				&& isEquivalent(blockData, meta.blockData);
	}

	protected boolean isEquivalent(@Nullable Object o1, @Nullable Object o2)
	{
		if (o1 == o2)
		{
			return true;
		}
		if (o1 == null)
		{
			return isDefaultValue(o2);
		}
		if (o2 == null)
		{
			return isDefaultValue(o1);
		}
		return Objects.equals(o1, o2);
	}

	private boolean isDefaultValue(@Nullable Object obj)
	{
		if (obj == null)
		{
			return true;
		}
		if (obj instanceof Collection<?> c)
		{
			return c.isEmpty();
		}
		if (obj instanceof Map<?, ?> m)
		{
			return m.isEmpty();
		}
		if (obj instanceof Multimap<?, ?> mm)
		{
			return mm.isEmpty();
		}
		if (obj instanceof PersistentDataContainer pdc)
		{
			return pdc.isEmpty();
		}
		if (obj instanceof Integer i)
		{
			return i == 0;
		}
		if (obj instanceof Boolean b)
		{
			return !b;
		}
		if (obj instanceof org.bukkit.inventory.ItemRarity r)
		{
			return r == org.bukkit.inventory.ItemRarity.COMMON;
		}
		return false;
	}

	@Override
	@SuppressWarnings(
	{"MethodDoesntCallSuperMethod", "java:S2975", "java:S1182"})
	public @NotNull ItemMetaMock clone()
	{
		return new ItemMetaMock(this);
	}

	@Override
	public boolean hasLore()
	{
		return this.loreData != null && !this.loreData.isEmpty();
	}

	@Override
	@Nullable
	public List<Component> lore()
	{
		return this.loreData == null
				? null
				: new ArrayList<>(
						this.loreData.stream().map(s -> GsonComponentSerializer.gson().deserialize(s)).toList());
	}

	@Override
	public void lore(@Nullable List<? extends Component> lore)
	{
		this.loreData = lore == null
				? null
				: lore.stream().map(c -> GsonComponentSerializer.gson().serialize(c)).toList();
	}

	@Override
	@Nullable
	public List<String> getLore()
	{
		return this.loreData == null
				? null
				: new ArrayList<>(this.loreData.stream().map(s -> LegacyComponentSerializer.legacySection()
						.serialize(GsonComponentSerializer.gson().deserialize(s))).toList());
	}

	@Override
	@Nullable
	public List<BaseComponent[]> getLoreComponents()
	{
		return this.loreData == null
				? null
				: this.loreData.stream().map(
						c -> BungeeComponentSerializer.get().serialize(GsonComponentSerializer.gson().deserialize(c)))
						.toList();
	}

	@Override
	public void setLore(@Nullable List<String> lore)
	{
		this.loreData = lore == null
				? null
				: lore.stream().map(s -> GsonComponentSerializer.gson()
						.serialize(LegacyComponentSerializer.legacySection().deserialize(s))).toList();
	}

	@Override
	public void setLoreComponents(@Nullable List<BaseComponent[]> lore)
	{
		lore(lore == null ? null : lore.stream().map(c -> BungeeComponentSerializer.get().deserialize(c)).toList());
	}

	/**
	 * Asserts if the lore contains the given lines in order.
	 *
	 * @param lines
	 *            The lines the lore should contain
	 */
	public void assertComponentLore(@NotNull List<? extends Component> lines)
	{
		if (this.loreData == null)
		{
			throw new AssertionError("No lore was set");
		}
		if (this.loreData.size() != lines.size())
		{
			throw new AssertionError(
					"Lore size mismatch: expected " + lines.size() + " but was " + this.loreData.size());
		}
		for (int i = 0; i < this.loreData.size(); i++)
		{
			if (GsonComponentSerializer.gson().deserialize(this.loreData.get(i)).equals(lines.get(i)))
			{
				continue;
			}
			throw new AssertionError(
					String.format("Line %d should be '%s' but was '%s'", i, lines.get(i), this.loreData.get(i)));
		}
	}

	/**
	 * @deprecated Component-based lore should be used instead.
	 */
	@Deprecated(forRemoval = true, since = "4.0.0")
	@SuppressWarnings("java:S1133")
	public void assertLore(@NotNull String... lines)
	{
		assertLore(Arrays.asList(lines));
	}

	/**
	 * @deprecated Component-based lore should be used instead.
	 */
	@Deprecated(forRemoval = true, since = "4.0.0")
	@SuppressWarnings("java:S1133")
	public void assertLore(@NotNull List<String> lines)
	{
		if (this.loreData == null)
		{
			throw new AssertionError("No lore was set");
		}
		if (this.loreData.size() != lines.size())
		{
			throw new AssertionError(
					"Lore size mismatch: expected " + lines.size() + " but was " + this.loreData.size());
		}
		for (int i = 0; i < this.loreData.size(); i++)
		{
			String line = LegacyComponentSerializer.legacySection()
					.serialize(GsonComponentSerializer.gson().deserialize(this.loreData.get(i)));
			if (line.equals(lines.get(i)))
			{
				continue;
			}
			throw new AssertionError(String.format("Line %d should be '%s' but was '%s'", i, lines.get(i), line));
		}
	}

	/**
	 * @deprecated Component-based lore should be used instead.
	 */
	@Deprecated(forRemoval = true, since = "4.0.0")
	@SuppressWarnings("java:S1133")
	public void assertHasLore()
	{
		if (this.loreData == null || this.loreData.isEmpty())
		{
			throw new AssertionError("Lore was not set");
		}
	}

	/**
	 * @deprecated Component-based lore should be used instead.
	 */
	@Deprecated(forRemoval = true, since = "4.0.0")
	@SuppressWarnings("java:S1133")
	public void assertHasNoLore()
	{
		if (this.loreData != null && !this.loreData.isEmpty())
		{
			throw new AssertionError("Lore was set");
		}
	}

	/**
	 * Serializes the properties of an ItemMetaMock to a HashMap. Unimplemented
	 * properties are not present in the map.
	 *
	 * @return A HashMap of String, Object pairs representing the ItemMetaMock.
	 */
	@Override
	@NotNull
	public Map<String, Object> serialize()
	{
		// Make new map and add relevant properties to it.
		Map<String, Object> map = new HashMap<>();
		serializeBasicMeta(map);
		serializeAdvancedMeta(map);
		serializeComponents(map);
		return map;
	}

	private void serializeBasicMeta(@NotNull Map<String, Object> map)
	{
		if (this.hasDisplayName())
		{
			map.put(DISPLAY_NAME, toComponentString(this.displayName()));
		}
		if (this.hasLore())
		{
			map.put(LORE_KEY, this.lore().stream().map(ItemMetaMock::toComponentString).toList());
		}
		if (this.hasDamage())
		{
			map.put(DAMAGE, this.getDamage());
		}
		if (this.hasMaxDamage())
		{
			map.put(MAX_DAMAGE, this.getMaxDamage());
		}
		if (this.hasRepairCost())
		{
			map.put(REPAIR_COST, this.repairCost);
		}
		if (this.hasEnchants())
		{
			map.put(ENCHANTMENTS, this.enchants.entrySet().stream()
					.collect(Collectors.toMap(entry -> entry.getKey().getKey().asString(), Map.Entry::getValue)));
		}
		if (this.hasAttributeModifiers())
		{
			map.put(ATTRIBUTE_MODIFIERS, this.getAttributeModifiers());
		}
		if (!this.getItemFlags().isEmpty())
		{
			map.put(ITEM_FLAGS, this.getItemFlags().stream().sorted().toList());
		}
		if (!this.persistentDataContainer.isEmpty())
		{
			map.put(PUBLIC_BUKKIT_VALUES, this.persistentDataContainer.serialize());
		}
	}

	private void serializeAdvancedMeta(@NotNull Map<String, Object> map)
	{
		if (this.hasTooltipStyle())
		{
			map.put(TOOL_TIP_STYLE, this.getTooltipStyle().asString());
		}
		if (this.hasItemModel())
		{
			map.put(ITEM_MODEL, this.getItemModel().asString());
		}
		if (this.isUnbreakable())
		{
			map.put(UNBREAKABLE, this.isUnbreakable());
		}
		if (this.hasCustomModelData())
		{
			map.put(CUSTOM_MODEL_DATA, this.getCustomModelData());
		}
		if (this.isHideTooltip())
		{
			map.put(HIDE_TOOLTIP, this.hideTooltip);
		}
		if (this.isFireResistant())
		{
			map.put(FIRE_RESISTANT, this.fireResistant);
		}
		if (this.hasDamageResistant())
		{
			map.put(DAMAGE_RESISTANT, this.getDamageResistant().getKey().asString());
		}
		if (this.hasMaxStackSize())
		{
			map.put(MAX_STACK_SIZE, this.getMaxStackSize());
		}
		if (this.hasEnchantmentGlintOverride())
		{
			map.put(ENCHANTMENT_GLINT_OVERRIDE, this.getEnchantmentGlintOverride());
		}
		if (this.isGlider())
		{
			map.put(GLIDER, this.isGlider());
		}
		if (this.hasRarity())
		{
			map.put(RARITY, this.getRarity());
		}
	}

	private void serializeComponents(@NotNull Map<String, Object> map)
	{
		if (this.hasUseRemainder())
		{
			map.put(USE_REMAINDER, this.getUseRemainder().serialize());
		}
		if (this.hasUseCooldown())
		{
			map.put(USE_COOLDOWN, this.getUseCooldown().serialize());
		}
		if (this.hasFood())
		{
			map.put(FOOD, this.getFood().serialize());
		}
		if (this.hasTool())
		{
			map.put(TOOL, this.getTool().serialize());
		}
		if (this.hasEquippable())
		{
			map.put(EQUIPPABLE, this.getEquippable().serialize());
		}
		if (this.hasJukeboxPlayable())
		{
			map.put(JUKEBOX_PLAYABLE, this.getJukeboxPlayable().serialize());
		}
		if (this.hasItemName())
		{
			map.put(ITEM_NAME, toComponentString(this.itemName()));
		}
		if (this.hasEnchantable())
		{
			map.put(ENCHANTABLE, this.getEnchantable());
		}
		if (this.hasBlockData())
		{
			map.put(BLOCK_DATA, this.getBlockData());
		}
		map.put(META_TYPE, getTypeName());
	}

	/**
	 * Required method for Bukkit deserialization.
	 *
	 * @param args
	 *            A serialized ItemMetaMock object in a Map&lt;String, Object&gt;
	 *            format.
	 * @return A new instance of the ItemMetaMock class.
	 */
	@NotNull
	public static ItemMetaMock deserialize(@NotNull Map<String, Object> args)
	{
		ItemMetaMock serialMock = new ItemMetaMock();
		serialMock.deserializeInternal(args);
		return serialMock;
	}

	@SuppressWarnings("unchecked")
	@ApiStatus.Internal
	protected void deserializeInternal(@NotNull Map<String, Object> args)
	{
		@Nullable
		Component displayName = NbtParser.parseComponent(args.get(DISPLAY_NAME));
		if (displayName != null)
		{
			displayName(displayName);
		}
		@Nullable
		Component itemName = NbtParser.parseComponent(args.get(ITEM_NAME));
		if (itemName != null)
		{
			itemName(itemName);
		}
		@Nullable
		List<@Nullable Component> loreList = NbtParser.parseList(args.get(LORE_KEY), NbtParser::parseComponent);
		if (loreList != null)
		{
			lore(loreList);
		}
		customModelDataData = NbtParser.parseInteger(args.get(CUSTOM_MODEL_DATA));
		enchantableData = NbtParser.parseInteger(args.get(ENCHANTABLE));
		damageData = NbtParser.parseInteger(args.get(DAMAGE));
		maxDamage = NbtParser.parseInteger(args.get(MAX_DAMAGE));
		repairCost = NbtParser.parseInteger(args.get(REPAIR_COST), 0);
		tooltipStyle = NbtParser.parseNamespacedKey(args.get(TOOL_TIP_STYLE));
		itemModel = NbtParser.parseNamespacedKey(args.get(ITEM_MODEL));
		unbreakableData = NbtParser.parseBoolean(args.get(UNBREAKABLE), false);
		hideTooltip = NbtParser.parseBoolean(args.get(HIDE_TOOLTIP), false);
		fireResistant = NbtParser.parseBoolean(args.get(FIRE_RESISTANT), false);
		maxStackSizeData = NbtParser.parseInteger(args.get(MAX_STACK_SIZE));
		enchantmentGlintOverride = NbtParser.parseBoolean(args.get(ENCHANTMENT_GLINT_OVERRIDE));
		gliderData = NbtParser.parseBoolean(args.get(GLIDER), false);
		rarityData = NbtParser.parseEnum(args.get(RARITY), ItemRarity.class);
		damageResistant = NbtParser.parseNamespacedKey(args.get(DAMAGE_RESISTANT));
		enchants = new HashMap<>();
		Map<String, Integer> enchantMap = NbtParser.parseMap(args.get(ENCHANTMENTS), NbtParser::parseInteger);
		if (enchantMap != null)
		{
			for (Map.Entry<String, Integer> entry : enchantMap.entrySet())
			{
				Enchantment enchantment = Registry.ENCHANTMENT.get(NamespacedKey.fromString(entry.getKey()));
				if (enchantment != null)
				{
					enchants.put(enchantment, entry.getValue());
				}
			}
		}
		setAttributeModifiers((Multimap<Attribute, AttributeModifier>) args.get(ATTRIBUTE_MODIFIERS));
		Set<ItemFlag> tempSet = NbtParser.parseSet(args.get(ITEM_FLAGS), o -> NbtParser.parseEnum(o, ItemFlag.class));
		if (tempSet != null)
		{
			hideFlags = tempSet;
		}
		Map<String, Object> map = NbtParser.parseMap(args.get(PUBLIC_BUKKIT_VALUES), Function.identity());
		if (map != null)
		{
			persistentDataContainer = PersistentDataContainerMock.deserialize(map);
		}
		var useRemainderData = NbtParser.parseMap(args.get(USE_REMAINDER), Function.identity());
		useRemainder = (useRemainderData == null ? null : Bukkit.getUnsafe().deserializeStack(useRemainderData));
	}

	/**
	 * @deprecated Legacy method.
	 */
	@Override
	@Deprecated(forRemoval = true, since = "1.20.6")
	@SuppressWarnings("java:S1133")
	public boolean hasLocalizedName()
	{
		return hasDisplayName();
	}

	/**
	 * @deprecated Legacy method.
	 */
	@Override
	@Deprecated(forRemoval = true, since = "1.20.6")
	@SuppressWarnings("java:S1133")
	public String getLocalizedName()
	{
		return getDisplayName();
	}

	/**
	 * @deprecated Legacy method.
	 */
	@Override
	@Deprecated(forRemoval = true, since = "1.20.6")
	@SuppressWarnings("java:S1133")
	public void setLocalizedName(String name)
	{
		setDisplayName(name);
	}

	/**
	 * @deprecated Legacy method.
	 */
	@Override
	@Deprecated(forRemoval = true, since = "1.20.6")
	@SuppressWarnings("java:S1133")
	public boolean hasCustomModelData()
	{
		return this.customModelDataData != null;
	}

	/**
	 * @deprecated Legacy method.
	 */
	@Override
	@Deprecated(forRemoval = true, since = "1.20.6")
	@SuppressWarnings("java:S1133")
	public int getCustomModelData()
	{
		Preconditions.checkState(hasCustomModelData(),
				"We don't have CustomModelData! Check hasCustomModelData first!");
		return this.customModelDataData;
	}

	/**
	 * @deprecated Legacy method.
	 */
	@Override
	@Deprecated(forRemoval = true, since = "1.20.6")
	@SuppressWarnings("java:S1133")
	public void setCustomModelData(Integer data)
	{
		this.customModelDataData = data;
	}

	/**
	 * @deprecated Legacy method.
	 */
	@Override
	@Deprecated(forRemoval = true, since = "1.20.6")
	@SuppressWarnings("java:S1133")
	public boolean hasPlaceableKeys()
	{
		return false;
	}

	/**
	 * @deprecated Legacy method.
	 */
	@Override
	@Deprecated(forRemoval = true, since = "1.20.6")
	@SuppressWarnings("java:S1133")
	public boolean hasDestroyableKeys()
	{
		return false;
	}

	@Override
	public boolean hasEnchants()
	{
		return !enchants.isEmpty();
	}

	@Override
	public boolean hasEnchant(Enchantment ench)
	{
		return enchants.containsKey(ench);
	}

	@Override
	public int getEnchantLevel(Enchantment ench)
	{
		return hasEnchant(ench) ? enchants.get(ench) : 0;
	}

	@Override
	@NotNull
	public Map<Enchantment, Integer> getEnchants()
	{
		return !enchants.isEmpty()
				? ImmutableSortedMap.copyOf(enchants, Comparator.comparing(o -> o.getKey().toString()))
				: ImmutableMap.of();
	}

	@Override
	public boolean addEnchant(@NotNull Enchantment ench, int level, boolean ignoreLevelRestriction)
	{
		Integer existingLevel = this.enchants.get(ench);
		if (nonNull(existingLevel) && existingLevel.equals(level))
		{
			// Already exists with the same level
			return false;
		}
		if (ignoreLevelRestriction || (level >= ench.getStartLevel() && level <= ench.getMaxLevel()))
		{
			this.enchants.put(ench, level);
			return true;
		} else
		{
			return false;
		}
	}

	@Override
	public boolean removeEnchant(Enchantment ench)
	{
		return nonNull(this.enchants.remove(ench));
	}

	@Override
	public void removeEnchantments()
	{
		this.enchants.clear();
	}

	@Override
	public boolean hasConflictingEnchant(Enchantment ench)
	{
		return checkConflictingEnchants(this.enchants, ench);
	}

	@Override
	public void addItemFlags(ItemFlag... itemFlags)
	{
		hideFlags.addAll(List.of(itemFlags));
	}

	@Override
	public void removeItemFlags(ItemFlag... itemFlags)
	{
		List.of(itemFlags).forEach(hideFlags::remove);
	}

	@Override
	@NotNull
	public Set<ItemFlag> getItemFlags()
	{
		return Set.copyOf(hideFlags);
	}

	@Override
	public boolean hasItemFlag(@NotNull ItemFlag flag)
	{
		return hideFlags.contains(flag);
	}

	@Override
	public boolean isUnbreakable()
	{
		return unbreakableData;
	}

	@Override
	public void setUnbreakable(boolean unbreakable)
	{
		this.unbreakableData = unbreakable;
	}

	@Override
	public boolean hasDamage()
	{
		return damageData != null && damageData > 0;
	}

	@Override
	public int getDamage()
	{
		return damageData == null ? 0 : damageData;
	}

	@Override
	public void setDamage(int damage)
	{
		Preconditions.checkState(damage >= 0, "damage cannot be negative");
		this.damageData = damage;
	}

	@Override
	public boolean hasDamageValue()
	{
		return damageData != null;
	}

	@Override
	public void resetDamage()
	{
		this.damageData = null;
	}

	@Override
	public boolean hasRepairCost()
	{
		return repairCost > 0;
	}

	@Override
	public int getRepairCost()
	{
		return repairCost;
	}

	@Override
	public void setRepairCost(int cost)
	{
		this.repairCost = cost;
	}

	@Override
	public boolean hasAttributeModifiers()
	{
		return !attributeModifiers.isEmpty();
	}

	@Override
	public Multimap<Attribute, AttributeModifier> getAttributeModifiers()
	{
		return this.hasAttributeModifiers() ? ImmutableMultimap.copyOf(attributeModifiers) : null;
	}

	@Override
	public void setAttributeModifiers(@Nullable Multimap<Attribute, AttributeModifier> attributeModifiers)
	{
		if (attributeModifiers == null || attributeModifiers.isEmpty())
		{
			this.attributeModifiers = LinkedHashMultimap.create();
			return;
		}
		this.attributeModifiers.clear();
		attributeModifiers.entries().stream().filter(entry -> entry.getKey() != null && entry.getValue() != null)
				.forEach(entry -> this.attributeModifiers.put(entry.getKey(), entry.getValue()));
	}

	@Override
	@NotNull
	public Multimap<Attribute, AttributeModifier> getAttributeModifiers(@NotNull EquipmentSlot slot)
	{
		SetMultimap<Attribute, AttributeModifier> result = LinkedHashMultimap.create();
		this.attributeModifiers.entries().stream()
				.filter(entry -> entry.getValue().getSlot() != null && entry.getValue().getSlot() == slot)
				.forEach(entry -> result.put(entry.getKey(), entry.getValue()));
		return result;
	}

	@Override
	public Collection<AttributeModifier> getAttributeModifiers(@NotNull Attribute attribute)
	{
		Preconditions.checkNotNull(attribute, ATTRIBUTE_CANNOT_BE_NULL);
		return this.attributeModifiers.containsKey(attribute)
				? ImmutableList.copyOf(this.attributeModifiers.get(attribute))
				: null;
	}

	@Override
	public boolean addAttributeModifier(@NotNull Attribute attribute, @NotNull AttributeModifier modifier)
	{
		Preconditions.checkNotNull(attribute, ATTRIBUTE_CANNOT_BE_NULL);
		Preconditions.checkNotNull(modifier, "AttributeModifier cannot be null");
		for (Map.Entry<Attribute, AttributeModifier> entry : this.attributeModifiers.entries())
		{
			Preconditions.checkArgument(!entry.getValue().getKey().equals(modifier.getKey()),
					"Cannot register AttributeModifier. Modifier is already applied! %s", modifier);
		}
		return this.attributeModifiers.put(attribute, modifier);
	}

	@Override
	public boolean removeAttributeModifier(@NotNull Attribute attribute)
	{
		Preconditions.checkNotNull(attribute, ATTRIBUTE_CANNOT_BE_NULL);
		return !this.attributeModifiers.removeAll(attribute).isEmpty();
	}

	@Override
	public boolean removeAttributeModifier(@NotNull EquipmentSlot slot)
	{
		// Match against null because as of 1.13, AttributeModifiers without a set slot
		// are active in any slot.
		return this.attributeModifiers.entries()
				.removeIf(entry -> entry.getValue().getSlot() == null || entry.getValue().getSlot() == slot);
	}

	@Override
	public boolean removeAttributeModifier(@NotNull Attribute attribute, @NotNull AttributeModifier modifier)
	{
		Preconditions.checkNotNull(attribute, ATTRIBUTE_CANNOT_BE_NULL);
		Preconditions.checkNotNull(modifier, "AttributeModifier cannot be null");
		return this.attributeModifiers.entries().removeIf(entry -> (entry.getKey() == null || entry.getValue() == null)
				|| (entry.getKey() == attribute && entry.getValue().getKey().equals(modifier.getKey())));
	}

	@Override
	@NotNull
	public PersistentDataContainer getPersistentDataContainer()
	{
		return this.persistentDataContainer;
	}

	@Override
	@NotNull
	public CustomModelDataComponent getCustomModelDataComponent()
	{
		return this.hasCustomModelDataComponent()
				? this.customModelDataComponent
				: CustomModelDataComponentMock.useDefault();
	}

	@Override
	public boolean hasCustomModelDataComponent()
	{
		return this.customModelDataComponent != null;
	}

	@Override
	public void setCustomModelDataComponent(@Nullable CustomModelDataComponent customModelDataComponent)
	{
		this.customModelDataComponent = customModelDataComponent;
	}

	@Override
	public boolean hasEnchantable()
	{
		return this.enchantableData != null;
	}
	@Override
	public int getEnchantable()
	{
		Preconditions.checkState(this.hasEnchantable(), "We don't have Enchantable! Check hasEnchantable first!");
		return this.enchantableData;
	}
	@Override
	public void setEnchantable(@Nullable Integer data)
	{
		// Paper
		Preconditions.checkArgument(data == null || data > 0, "Enchantability must be positive");
		this.enchantableData = data;
	}

	@Override
	public void setVersion(int version)
	{
		// No use yet
	}

	/**
	 * @deprecated Use {@link #hasMaxDamage()} instead.
	 */
	@Deprecated(since = "1.20")
	@Override
	public boolean hasMaxDamage()
	{
		return this.maxDamage != null;
	}

	@Override
	public int getMaxDamage()
	{
		Preconditions.checkState(this.hasMaxDamage(), "We don't have max-damage! Check hasMaxDamage first!");
		return this.maxDamage;
	}

	@Override
	public void setMaxDamage(@Nullable Integer maxDamage)
	{
		this.maxDamage = maxDamage;
	}

	@Override
	public boolean hasItemName()
	{
		return this.itemNameData != null;
	}
	@Override
	@NotNull
	public Component itemName()
	{
		return this.itemNameData == null ? Component.empty() : this.itemNameData;
	}
	@Override
	public void itemName(@Nullable Component name)
	{
		this.itemNameData = name;
	}

	/**
	 * @deprecated Use {@link #itemName()} instead.
	 */
	@Override
	@Deprecated(since = "1.21", forRemoval = true)
	@SuppressWarnings("java:S1133")
	@NotNull
	public String getItemName()
	{
		return LegacyComponentSerializer.legacySection().serialize(itemName());
	}
	/**
	 * @deprecated Use {@link #itemName(Component)} instead.
	 */
	@Override
	@Deprecated(since = "1.21", forRemoval = true)
	@SuppressWarnings("java:S1133")
	public void setItemName(@Nullable String name)
	{
		if (name == null)
		{
			this.itemNameData = null;
		} else
		{
			this.itemNameData = LegacyComponentSerializer.legacySection().deserialize(name);
		}
	}

	@Override
	public boolean isHideTooltip()
	{
		return this.hideTooltip;
	}

	@Override
	public void setHideTooltip(boolean hideTooltip)
	{
		this.hideTooltip = hideTooltip;
	}

	@Override
	public boolean hasTooltipStyle()
	{
		return this.tooltipStyle != null;
	}

	@Override
	@Nullable
	public NamespacedKey getTooltipStyle()
	{
		return this.tooltipStyle;
	}

	@Override
	public void setTooltipStyle(@Nullable NamespacedKey tooltipStyle)
	{
		this.tooltipStyle = tooltipStyle;
	}

	@Override
	public boolean hasItemModel()
	{
		return this.itemModel != null;
	}

	@Override
	@Nullable
	public NamespacedKey getItemModel()
	{
		return this.itemModel;
	}

	@Override
	public void setItemModel(@Nullable NamespacedKey namespacedKey)
	{
		this.itemModel = namespacedKey;
	}

	@Override
	public boolean isGlider()
	{
		return this.gliderData;
	}
	@Override
	public void setGlider(boolean glider)
	{
		this.gliderData = glider;
	}

	@Override
	public boolean hasDamageResistant()
	{
		return this.damageResistant != null;
	}

	@Override
	@Nullable
	public Tag<DamageType> getDamageResistant()
	{
		return this.damageResistant != null
				? Bukkit.getTag("damage_types", this.damageResistant, DamageType.class)
				: null;
	}

	@Override
	public void setDamageResistant(@Nullable Tag<DamageType> tag)
	{
		this.damageResistant = (tag == null ? null : tag.getKey());
	}

	@Override
	public boolean hasUseRemainder()
	{
		return this.useRemainder != null;
	}

	@Override
	@Nullable
	public ItemStack getUseRemainder()
	{
		return this.useRemainder;
	}

	@Override
	public void setUseRemainder(@Nullable ItemStack itemStack)
	{
		Preconditions.checkArgument(itemStack == null || !itemStack.isEmpty(), "Item cannot be empty");
		this.useRemainder = itemStack;
	}

	@Override
	public boolean hasUseCooldown()
	{
		return this.useCooldown != null;
	}

	@Override
	public UseCooldownComponent getUseCooldown()
	{
		return this.hasUseCooldown() ? this.useCooldown : UseCooldownComponentMock.useDefault();
	}

	@Override
	public void setUseCooldown(@Nullable UseCooldownComponent useCooldown)
	{
		this.useCooldown = useCooldown;
	}

	@Override
	public boolean hasEquippable()
	{
		return this.equippableComponent != null;
	}

	@Override
	@NotNull
	public EquippableComponent getEquippable()
	{
		return this.hasEquippable() ? this.equippableComponent : EquippableComponentMock.useDefault();
	}

	@Override
	public void setEquippable(@Nullable EquippableComponent equippableComponent)
	{
		this.equippableComponent = equippableComponent;
	}

	@Override
	public boolean hasEnchantmentGlintOverride()
	{
		return this.enchantmentGlintOverride != null;
	}

	@Override
	@NotNull
	public Boolean getEnchantmentGlintOverride()
	{
		Preconditions.checkState(this.hasEnchantmentGlintOverride(),
				"We don't have enchantment_glint_override! Check hasEnchantmentGlintOverride first!");
		return this.enchantmentGlintOverride;
	}

	@Override
	public void setEnchantmentGlintOverride(@Nullable Boolean override)
	{
		this.enchantmentGlintOverride = override;
	}

	@Override
	public boolean isFireResistant()
	{
		return this.fireResistant;
	}

	@Override
	public void setFireResistant(boolean fireResistant)
	{
		this.fireResistant = fireResistant;
	}

	@Override
	public boolean hasMaxStackSize()
	{
		return this.maxStackSizeData != null;
	}
	@Override
	public int getMaxStackSize()
	{
		Preconditions.checkState(hasMaxStackSize(), "We don't have max_stack_size! Check hasMaxStackSize first!");
		return this.maxStackSizeData;
	}
	@Override
	public void setMaxStackSize(@Nullable Integer max)
	{
		Preconditions.checkArgument(max == null || max > 0, "max_stack_size must be > 0");
		Preconditions.checkArgument(max == null || max <= ABSOLUTE_MAX_STACK_SIZE, "max_stack_size must be <= 99");
		this.maxStackSizeData = max;
	}

	@Override
	public boolean hasRarity()
	{
		return this.rarityData != null;
	}
	@Override
	@NotNull
	public ItemRarity getRarity()
	{
		Preconditions.checkState(this.hasRarity(), "We don't have rarity! Check hasRarity first!");
		return this.rarityData;
	}
	@Override
	public void setRarity(@Nullable ItemRarity rarity)
	{
		this.rarityData = rarity;
	}

	@Override
	public boolean hasFood()
	{
		return this.foodComponent != null;
	}

	@Override
	@NotNull
	public FoodComponent getFood()
	{
		return this.hasFood() ? this.foodComponent : FoodComponentMock.useDefault();
	}

	@Override
	public void setFood(@Nullable FoodComponent food)
	{
		this.foodComponent = food;
	}

	@ApiStatus.Internal
	protected String getTypeName()
	{
		return "UNSPECIFIC";
	}

	@Override
	public boolean hasTool()
	{
		return this.toolComponent != null;
	}

	@Override
	@NotNull
	public ToolComponent getTool()
	{
		return this.hasTool() ? this.toolComponent : ToolComponentMock.useDefault();
	}

	@Override
	public void setTool(@Nullable ToolComponent toolComponent)
	{
		this.toolComponent = toolComponent;
	}

	@Override
	public boolean hasJukeboxPlayable()
	{
		return this.jukeboxPlayableComponent != null;
	}

	@Override
	@NotNull
	public JukeboxPlayableComponent getJukeboxPlayable()
	{
		return this.hasJukeboxPlayable() ? this.jukeboxPlayableComponent : JukeboxPlayableComponentMock.useDefault();
	}

	@Override
	public void setJukeboxPlayable(@Nullable JukeboxPlayableComponent jukeboxPlayable)
	{
		this.jukeboxPlayableComponent = jukeboxPlayable;
	}

	@Override
	public String toString()
	{
		StringBuilder stringBuilder = new StringBuilder(getTypeName() + "(");
		Map<String, Object> data = this.serialize();
		for (Map.Entry<String, Object> entry : data.entrySet())
		{
			stringBuilder.append(entry.getKey());
			stringBuilder.append("=");
			stringBuilder.append(entry.getValue());
			stringBuilder.append(", ");
		}
		stringBuilder.deleteCharAt(stringBuilder.length() - 1);
		stringBuilder.append(")");
		return stringBuilder.toString();
	}

	/**
	 * Check if the item meta has block data.
	 *
	 * @return {@code true} if has block data, otherwise {@code false}.
	 */
	public boolean hasBlockData()
	{
		return this.blockData != null;
	}

	/**
	 * Get the current item block data.
	 *
	 * @return The block data if available, otherwise {@code null}
	 */
	@Nullable
	public Map<String, String> getBlockData()
	{
		return this.blockData;
	}

	/**
	 * Set the block data in the item.
	 *
	 * @param blockData
	 *            new block data.
	 */
	public void setBlockData(@Nullable Map<String, String> blockData)
	{
		this.blockData = blockData;
	}

	private static String toComponentString(Component component)
	{
		return GsonComponentSerializer.gson().serialize(component);
	}
}
