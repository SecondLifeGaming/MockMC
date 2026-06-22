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
					return Mockito.mock(org.bukkit.GameRule.class, invocation2 -> {
						String mName = invocation2.getMethod().getName();
						if (mName.equals("getName")) {
							return registryKey.key().value();
						}
						if (mName.equals("getType")) {
							return Boolean.class;
						}
						return Mockito.RETURNS_DEFAULTS.answer(invocation2);
					});
				}
				Class<?> classToMock = finalTargetClass;
				if (classToMock == org.bukkit.inventory.ItemType.class) {
					try {
						classToMock = Class.forName("org.bukkit.inventory.ItemType$Typed");
					} catch (Exception _) {
						// Fallback to ItemType.class
					}
				}
				T dummyObj = Mockito.mock((Class<T>) classToMock, invocation2 -> {
					String mName = invocation2.getMethod().getName();
					if (mName.equals("getKey")) {
						return org.bukkit.NamespacedKey.minecraft("dummy_" + registryKey.key().value());
					}
					if (finalTargetClass == org.bukkit.potion.PotionEffectType.class) {
						if (mName.equals("getColor")) {
							return org.bukkit.Color.RED;
						}
						if (mName.equals("getEffectCategory")) {
							return org.bukkit.potion.PotionEffectType.Category.HARMFUL;
						}
					}
					if (finalTargetClass == org.bukkit.potion.PotionType.class && mName.equals("getPotionEffects")) {
						org.bukkit.potion.PotionEffect dummyEffect = Mockito.mock(org.bukkit.potion.PotionEffect.class, Mockito.RETURNS_DEFAULTS);
						return Collections.singletonList(dummyEffect);
					}
					if (finalTargetClass == org.bukkit.inventory.ItemType.class && mName.equals("getItemMetaClass")) {
						return org.bukkit.inventory.meta.ItemMeta.class;
					}
					if (finalTargetClass == org.bukkit.damage.DamageType.class) {
						if (mName.equals("getDamageScaling")) {
							return org.bukkit.damage.DamageScaling.ALWAYS;
						}
						if (mName.equals("getDamageEffect")) {
							return Mockito.mock(org.bukkit.damage.DamageEffect.class, inv3 -> {
								if (inv3.getMethod().getName().equals("getSound")) {
									return org.bukkit.Sound.ENTITY_GENERIC_HURT;
								}
								return Mockito.RETURNS_DEFAULTS.answer(inv3);
							});
						}
						if (mName.equals("getDeathMessageType")) {
							return org.bukkit.damage.DeathMessageType.DEFAULT;
						}
					}
					if (finalTargetClass == org.bukkit.generator.structure.Structure.class && mName.equals("getStructureType")) {
						return Mockito.mock(org.bukkit.generator.structure.StructureType.class, inv3 -> {
							if (inv3.getMethod().getName().equals("getKey")) {
								return org.bukkit.NamespacedKey.minecraft("dummy_structure");
							}
							return Mockito.RETURNS_DEFAULTS.answer(inv3);
						});
					}
					if (finalTargetClass == org.bukkit.enchantments.Enchantment.class && (mName.equals("description") || mName.equals("displayName"))) {
						return net.kyori.adventure.text.Component.empty();
					}
					return Mockito.RETURNS_DEFAULTS.answer(invocation2);
				});

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
					return Mockito.mock(org.bukkit.GameRule.class, invocation2 -> {
						String mName = invocation2.getMethod().getName();
						if (mName.equals("getName")) {
							return aClass.getSimpleName().toLowerCase();
						}
						if (mName.equals("getType")) {
							return Boolean.class;
						}
						return Mockito.RETURNS_DEFAULTS.answer(invocation2);
					});
				}

				Class<?> classToMock = aClass;
				if (classToMock == org.bukkit.inventory.ItemType.class) {
					try {
						classToMock = Class.forName("org.bukkit.inventory.ItemType$Typed");
					} catch (Exception _) {
						// Fallback to ItemType.class
					}
				}
				T dummyObj = Mockito.mock((Class<T>) classToMock, invocation2 -> {
					String mName = invocation2.getMethod().getName();
					if (mName.equals("getKey")) {
						return org.bukkit.NamespacedKey.minecraft("dummy_" + aClass.getSimpleName().toLowerCase());
					}
					if (aClass == org.bukkit.potion.PotionEffectType.class) {
						if (mName.equals("getColor")) {
							return org.bukkit.Color.RED;
						}
						if (mName.equals("getEffectCategory")) {
							return org.bukkit.potion.PotionEffectType.Category.HARMFUL;
						}
					}
					if (aClass == org.bukkit.potion.PotionType.class && mName.equals("getPotionEffects")) {
						org.bukkit.potion.PotionEffect dummyEffect = Mockito.mock(org.bukkit.potion.PotionEffect.class, Mockito.RETURNS_DEFAULTS);
						return Collections.singletonList(dummyEffect);
					}
					if (aClass == org.bukkit.inventory.ItemType.class && mName.equals("getItemMetaClass")) {
						return org.bukkit.inventory.meta.ItemMeta.class;
					}
					if (aClass == org.bukkit.damage.DamageType.class) {
						if (mName.equals("getDamageScaling")) {
							return org.bukkit.damage.DamageScaling.ALWAYS;
						}
						if (mName.equals("getDamageEffect")) {
							return Mockito.mock(org.bukkit.damage.DamageEffect.class, inv3 -> {
								if (inv3.getMethod().getName().equals("getSound")) {
									return org.bukkit.Sound.ENTITY_GENERIC_HURT;
								}
								return Mockito.RETURNS_DEFAULTS.answer(inv3);
							});
						}
						if (mName.equals("getDeathMessageType")) {
							return org.bukkit.damage.DeathMessageType.DEFAULT;
						}
					}
					if (aClass == org.bukkit.generator.structure.Structure.class && mName.equals("getStructureType")) {
						return Mockito.mock(org.bukkit.generator.structure.StructureType.class, inv3 -> {
							if (inv3.getMethod().getName().equals("getKey")) {
								return org.bukkit.NamespacedKey.minecraft("dummy_structure");
							}
							return Mockito.RETURNS_DEFAULTS.answer(inv3);
						});
					}
					if (aClass == org.bukkit.enchantments.Enchantment.class && (mName.equals("description") || mName.equals("displayName"))) {
						return net.kyori.adventure.text.Component.empty();
					}
					return Mockito.RETURNS_DEFAULTS.answer(invocation2);
				});

				if (methodName.equals("iterator")) return Collections.singletonList(dummyObj).iterator();
				return dummyObj;
			}
			return Mockito.RETURNS_DEFAULTS.answer(invocation);
		});
	}
}
