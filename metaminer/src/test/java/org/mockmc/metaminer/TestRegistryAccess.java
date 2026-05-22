package org.mockmc.metaminer;

import io.papermc.paper.registry.RegistryAccess;
import io.papermc.paper.registry.RegistryKey;
import org.bukkit.Keyed;
import org.bukkit.Material;
import org.bukkit.Registry;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.mockito.Mockito;

import java.util.Arrays;
import java.util.Collections;

@SuppressWarnings({ "unchecked" })
public class TestRegistryAccess implements RegistryAccess
{

	@Override
	public <T extends Keyed> @Nullable Registry<T> getRegistry(@NotNull RegistryKey<T> registryKey)
	{
		if (registryKey.key().value().equals("material"))
		{
			Registry<T> mock = Mockito.mock(Registry.class);
			Mockito.when(mock.iterator())
					.thenReturn((java.util.Iterator<T>) Arrays.asList(Material.values()).iterator());
			return mock;
		}

		Class<?> targetClass = org.mockmc.metaminer.keyed.KeyedClassTracker.getClassRegistryKeyRelation().get(registryKey);
		if (targetClass == null) {
			if (registryKey.key().value().equals("potion")) {
				targetClass = org.bukkit.potion.PotionType.class;
			} else {
				targetClass = Keyed.class;
			}
		}
		final Class<?> finalTargetClass = targetClass;

		boolean shouldMock = finalTargetClass == org.bukkit.inventory.ItemType.class ||
							 finalTargetClass == org.bukkit.damage.DamageType.class ||
							 finalTargetClass == org.bukkit.potion.PotionEffectType.class ||
							 finalTargetClass == org.bukkit.enchantments.Enchantment.class ||
							 finalTargetClass == org.bukkit.generator.structure.Structure.class ||
							 finalTargetClass == io.papermc.paper.datacomponent.DataComponentType.class ||
							 finalTargetClass == org.bukkit.GameRule.class ||
							 finalTargetClass == org.bukkit.potion.PotionType.class;

		return Mockito.mock(Registry.class, invocation -> {
			String methodName = invocation.getMethod().getName();
			if (!shouldMock) {
				if (methodName.equals("iterator")) return Collections.emptyIterator();
				return Mockito.RETURNS_DEFAULTS.answer(invocation);
			}

			if (methodName.equals("iterator") || methodName.equals("getOrThrow") || methodName.equals("get")) {
				if (finalTargetClass == org.bukkit.GameRule.class) {
					if (methodName.equals("iterator")) return Collections.emptyIterator();
					return null;
				}
				Class<?> classToMock = finalTargetClass;
				if (classToMock == org.bukkit.inventory.ItemType.class) {
					try {
						classToMock = Class.forName("org.bukkit.inventory.ItemType$Typed");
					} catch (Exception _) {
						// Fallback to ItemType.class
					}
				}
				T dummyObj = Mockito.mock((Class<T>) classToMock, Mockito.withSettings().defaultAnswer(Mockito.RETURNS_DEEP_STUBS));
				Mockito.when(dummyObj.getKey()).thenReturn(org.bukkit.NamespacedKey.minecraft("dummy_" + registryKey.key().value()));
				
				if (finalTargetClass == org.bukkit.potion.PotionType.class) {
					org.bukkit.potion.PotionEffect dummyEffect = Mockito.mock(org.bukkit.potion.PotionEffect.class, Mockito.RETURNS_DEEP_STUBS);
					Mockito.when(((org.bukkit.potion.PotionType) dummyObj).getPotionEffects()).thenReturn(Collections.singletonList(dummyEffect));
				}
				if (finalTargetClass == org.bukkit.inventory.ItemType.class) {
					Mockito.doReturn(org.bukkit.inventory.meta.ItemMeta.class).when((org.bukkit.inventory.ItemType) dummyObj).getItemMetaClass();
				}
				if (finalTargetClass == org.bukkit.generator.structure.Structure.class) {
					Mockito.when(((org.bukkit.generator.structure.Structure) dummyObj).getStructureType().getKey()).thenReturn(org.bukkit.NamespacedKey.minecraft("dummy_structure"));
				}
				if (finalTargetClass == org.bukkit.enchantments.Enchantment.class) {
					org.bukkit.enchantments.Enchantment enc = (org.bukkit.enchantments.Enchantment) dummyObj;
					Mockito.when(enc.description()).thenReturn(net.kyori.adventure.text.Component.empty());
					Mockito.when(enc.displayName(Mockito.anyInt())).thenReturn(net.kyori.adventure.text.Component.empty());
				}
				if (methodName.equals("iterator")) return Collections.singletonList(dummyObj).iterator();
				return dummyObj;
			}
			return Mockito.RETURNS_DEFAULTS.answer(invocation);
		});
	}

	@Override
	public <T extends Keyed> @Nullable Registry<T> getRegistry(@NotNull Class<T> aClass)
	{
		if (aClass == Material.class)
		{
			Registry<T> mock = Mockito.mock(Registry.class);
			Mockito.when(mock.iterator())
					.thenReturn((java.util.Iterator<T>) Arrays.asList(Material.values()).iterator());
			return mock;
		}

		boolean shouldMock = aClass == org.bukkit.inventory.ItemType.class ||
							 aClass == org.bukkit.damage.DamageType.class ||
							 aClass == org.bukkit.potion.PotionEffectType.class ||
							 aClass == org.bukkit.enchantments.Enchantment.class ||
							 aClass == org.bukkit.generator.structure.Structure.class ||
							 aClass == io.papermc.paper.datacomponent.DataComponentType.class ||
							 aClass == org.bukkit.GameRule.class ||
							 aClass == org.bukkit.potion.PotionType.class;

		return Mockito.mock(Registry.class, invocation -> {
			String methodName = invocation.getMethod().getName();
			if (!shouldMock) {
				if (methodName.equals("iterator")) return Collections.emptyIterator();
				return Mockito.RETURNS_DEFAULTS.answer(invocation);
			}

			if (methodName.equals("iterator") || methodName.equals("getOrThrow") || methodName.equals("get")) {
				if (aClass == org.bukkit.GameRule.class) {
					if (methodName.equals("iterator")) return Collections.emptyIterator();
					return null;
				}

				Class<?> classToMock = aClass;
				if (classToMock == org.bukkit.inventory.ItemType.class) {
					try {
						classToMock = Class.forName("org.bukkit.inventory.ItemType$Typed");
					} catch (Exception _) {
						// Fallback to ItemType.class
					}
				}
				T dummyObj = Mockito.mock((Class<T>) classToMock, Mockito.withSettings().defaultAnswer(Mockito.RETURNS_DEEP_STUBS));
				Mockito.when(dummyObj.getKey()).thenReturn(org.bukkit.NamespacedKey.minecraft("dummy_" + aClass.getSimpleName().toLowerCase()));
				
				if (aClass == org.bukkit.potion.PotionType.class) {
					org.bukkit.potion.PotionEffect dummyEffect = Mockito.mock(org.bukkit.potion.PotionEffect.class, Mockito.RETURNS_DEEP_STUBS);
					Mockito.when(((org.bukkit.potion.PotionType) dummyObj).getPotionEffects()).thenReturn(Collections.singletonList(dummyEffect));
				}
				if (aClass == org.bukkit.inventory.ItemType.class) {
					Mockito.doReturn(org.bukkit.inventory.meta.ItemMeta.class).when((org.bukkit.inventory.ItemType) dummyObj).getItemMetaClass();
				}
				if (aClass == org.bukkit.generator.structure.Structure.class) {
					Mockito.when(((org.bukkit.generator.structure.Structure) dummyObj).getStructureType().getKey()).thenReturn(org.bukkit.NamespacedKey.minecraft("dummy_structure"));
				}
				if (aClass == org.bukkit.enchantments.Enchantment.class) {
					org.bukkit.enchantments.Enchantment enc = (org.bukkit.enchantments.Enchantment) dummyObj;
					Mockito.when(enc.description()).thenReturn(net.kyori.adventure.text.Component.empty());
					Mockito.when(enc.displayName(Mockito.anyInt())).thenReturn(net.kyori.adventure.text.Component.empty());
				}
				if (methodName.equals("iterator")) return Collections.singletonList(dummyObj).iterator();
				return dummyObj;
			}
			return Mockito.RETURNS_DEFAULTS.answer(invocation);
		});
	}
}
