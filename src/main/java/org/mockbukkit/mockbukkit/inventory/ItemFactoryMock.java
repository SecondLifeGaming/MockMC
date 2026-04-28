package org.mockbukkit.mockbukkit.inventory;

import com.google.common.base.Preconditions;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.event.HoverEvent;
import net.kyori.adventure.text.event.HoverEventSource;
import net.kyori.adventure.text.format.Style;
import net.kyori.adventure.text.format.TextDecoration;
import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.inventory.ItemFactory;
import org.bukkit.inventory.ItemRarity;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.jetbrains.annotations.NotNull;
import org.mockbukkit.mockbukkit.exception.ItemMetaInitException;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.Objects;

/**
 * Mock implementation of an {@link ItemFactory}.
 */
@SuppressWarnings("deprecation")
public class ItemFactoryMock implements org.mockbukkit.mockbukkit.generated.org.bukkit.inventory.ItemFactoryBaseMock
{

	private final Color defaultLeatherColor = Color.fromRGB(10511680);

	@NotNull
	private Class<? extends ItemMeta> getItemMetaClass(@NotNull Material material)
	{
		if (material.isAir() || !material.isItem())
		{
			return ItemMeta.class;
		}
		return material.asItemType().getItemMetaClass();
	}

	@Override
	@NotNull
	public ItemMeta getItemMeta(@NotNull Material material)
	{
		Preconditions.checkNotNull(material, "Material cannot be null");
		Class<? extends ItemMeta> clazz = null;
		try
		{
			clazz = getItemMetaClass(material);
			for (var ctor : clazz.getDeclaredConstructors())
			{
				if (ctor.getParameterCount() == 1 && ctor.getParameters()[0].getType() == Material.class)
				{
					return (ItemMeta) ctor.newInstance(material);
				}
			}
			return clazz.getDeclaredConstructor().newInstance();
		} catch (ReflectiveOperationException e)
		{
			throw new UnsupportedOperationException("Can't instantiate class '" + clazz + "'", e);
		}
	}

	@Override
	public boolean isApplicable(ItemMeta meta, ItemStack stack)
	{
		return stack != null && isApplicable(meta, stack.getType());
	}

	@Override
	public boolean isApplicable(ItemMeta meta, Material material)
	{
		if (material == null)
		{
			return false;
		}
		Class<? extends ItemMeta> target = getItemMetaClass(material);
		return target.isInstance(meta);
	}

	@Override
	public boolean equals(ItemMeta meta1, ItemMeta meta2)
	{
		// Returns true if both metas are null or equal.
		return Objects.equals(meta1, meta2);
	}

	@Override
	public ItemMeta asMetaFor(@NotNull ItemMeta meta, @NotNull ItemStack stack)
	{
		return asMetaFor(meta, stack.getType());
	}

	@Override
	public ItemMeta asMetaFor(@NotNull ItemMeta meta, @NotNull Material material)
	{
		Class<? extends ItemMeta> target = getItemMetaClass(material);
		try
		{
			for (Constructor<?> constructor : target.getDeclaredConstructors())
			{
				// This will make sure we find the most suitable constructor for this
				if (constructor.getParameterCount() == 1
						&& constructor.getParameterTypes()[0].isAssignableFrom(meta.getClass()))
				{
					return (ItemMeta) constructor.newInstance(meta);
				}
			}
			throw new NoSuchMethodException(
					"Cannot find an ItemMeta constructor for the class \"" + meta.getClass().getName() + "\"");
		} catch (SecurityException | InstantiationException | IllegalAccessException | InvocationTargetException
				| NoSuchMethodException e)
		{
			throw new ItemMetaInitException(
					String.format("Meta %s and material %s", meta.getClass().getName(), material.name()), e);
		}
	}

	@Override
	@NotNull
	public Color getDefaultLeatherColor()
	{
		return defaultLeatherColor;
	}

	@Override
	@NotNull
	public Component displayName(@NotNull ItemStack itemStack)
	{
		Component component;
		ItemMeta itemMeta = itemStack.getItemMeta();
		if (itemMeta != null && itemMeta.customName() != null)
		{
			component = Component.empty().append(itemMeta.customName()).style(Style.style(TextDecoration.ITALIC));
		} else
		{
			component = Component.translatable(itemStack.getType().asItemType().translationKey());
		}
		component = Component.translatable("chat.square_brackets", component);
		if (!itemStack.isEmpty() && itemMeta != null)
		{
			ItemRarity rarity = itemMeta.hasRarity() ? itemMeta.getRarity() : ItemRarity.COMMON;
			component = component.style(Style.style(rarity.color())).hoverEvent(HoverEventSource
					.unbox(HoverEvent.showItem(itemStack.getType().asItemType(), itemStack.getAmount())));
		}
		return component;
	}

	public ItemStack deserializeItem(byte[] bytes)
	{
		return org.bukkit.Bukkit.getUnsafe().deserializeItem(bytes);
	}
}
