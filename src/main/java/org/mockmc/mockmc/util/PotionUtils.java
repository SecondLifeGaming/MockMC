package org.mockmc.mockmc.util;

import com.google.common.base.Preconditions;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import org.bukkit.NamespacedKey;
import org.bukkit.Registry;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.jetbrains.annotations.ApiStatus;
import org.jetbrains.annotations.NotNull;
import java.util.List;

/**
 * Utility class for potion-related operations.
 */
@ApiStatus.Internal
public final class PotionUtils
{

	private PotionUtils()
	{
		throw new UnsupportedOperationException("Utility class");
	}

	/**
	 * Deserializes a {@link PotionEffect} from JSON data.
	 *
	 * @param potionEffectData
	 *            The JSON data representing the potion effect.
	 * @return The deserialized {@link PotionEffect}.
	 */
	@NotNull
	public static PotionEffect getPotionEffectFromData(@NotNull JsonElement potionEffectData)
	{
		JsonObject potionEffectDataObj = potionEffectData.getAsJsonObject();
		NamespacedKey potionEffectTypeKey = Preconditions.checkNotNull(
				NamespacedKey.fromString(potionEffectDataObj.get("type").getAsString()), "type can't be null");
		PotionEffectType potionEffectType = java.util.Objects
				.requireNonNull(Registry.POTION_EFFECT_TYPE.get(potionEffectTypeKey), "potionEffectType can't be null");
		int duration = potionEffectDataObj.get("duration").getAsInt();
		int amplifier = potionEffectDataObj.get("amplifier").getAsInt();
		boolean ambient = potionEffectDataObj.get("ambient").getAsBoolean();
		boolean particles = potionEffectDataObj.get("particles").getAsBoolean();
		boolean icon = potionEffectDataObj.get("icon").getAsBoolean();
		return new PotionEffect(potionEffectType, duration, amplifier, ambient, particles, icon);
	}

	/**
	 * Deserializes a list of {@link PotionEffect} from a JSON object.
	 *
	 * @param data
	 *            The JSON object containing the effects array.
	 * @return A list of deserialized {@link PotionEffect}.
	 */
	@NotNull
	public static List<PotionEffect> getPotionEffectsFromData(@NotNull JsonObject data)
	{
		return data.get("effects").getAsJsonArray().asList().stream().map(PotionUtils::getPotionEffectFromData)
				.toList();
	}

}
