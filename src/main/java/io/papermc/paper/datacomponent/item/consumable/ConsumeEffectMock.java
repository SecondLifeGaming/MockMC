package io.papermc.paper.datacomponent.item.consumable;

import io.papermc.paper.registry.set.RegistryKeySet;
import net.kyori.adventure.key.Key;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.jspecify.annotations.NullMarked;

import java.util.List;

@NullMarked
@SuppressWarnings(
{"NonExtendableApiUsage", "UnstableApiUsage"})
public final class ConsumeEffectMock implements ConsumeEffect
{

	private ConsumeEffectMock()
	{
		throw new UnsupportedOperationException("This is a utility class and cannot be instantiated");
	}

	record TeleportRandomlyMock(float diameter) implements TeleportRandomly
	{
	}

	record RemoveStatusEffectsMock(RegistryKeySet<PotionEffectType> removeEffects) implements RemoveStatusEffects
	{
	}

	record PlaySoundMock(Key sound) implements PlaySound
	{
	}

	record ClearAllStatusEffectsMock() implements ClearAllStatusEffects
	{
	}

	record ApplyStatusEffectsMock(List<PotionEffect> effects, float probability) implements ApplyStatusEffects
	{
	}

}
