package org.mockmc.mockmc.potion;

import com.google.common.base.Preconditions;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import org.bukkit.NamespacedKey;
import org.bukkit.Registry;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.potion.PotionType;
import org.jetbrains.annotations.ApiStatus;
import org.jetbrains.annotations.NotNull;
import org.mockmc.mockmc.exception.UnimplementedOperationException;
import org.mockmc.mockmc.util.ResourceLoader;
import java.util.List;

@SuppressWarnings(
{"deprecation", "removal", "unchecked"})
public class InternalPotionDataMock
		implements
			PotionType.InternalPotionData,
			org.mockmc.mockmc.generated.org.bukkit.potion.PotionType_InternalPotionDataBaseMock
{

	private final NamespacedKey namespacedKey;

	private final boolean upgradeable;

	private final boolean extendable;

	private final int maxLevel;

	private final List<PotionEffect> potionEffects;

	/**
	 * @mockmc.version 26.1-2.1.4
	 */
	public InternalPotionDataMock(NamespacedKey namespacedKey)
	{
		this.namespacedKey = namespacedKey;
		JsonObject data = loadData(namespacedKey);
		this.potionEffects = getPotionEffectsFromData(data);
		this.upgradeable = data.get("upgradeable").getAsBoolean();
		this.extendable = data.get("extendable").getAsBoolean();
		this.maxLevel = data.get("maxLevel").getAsInt();
	}

	private JsonObject loadData(@NotNull NamespacedKey namespacedKey)
	{
		String path = "/potion/" + namespacedKey.getKey() + ".json";
		return ResourceLoader.loadResource(path).getAsJsonObject();
	}

	@Override
	public PotionEffectType getEffectType()
	{
		return this.getPotionEffects().isEmpty() ? null : this.getPotionEffects().get(0).getType();
	}

	@Override
	public List<PotionEffect> getPotionEffects()
	{
		if (potionEffects == null)
		{
			throw new UnimplementedOperationException("Unimplemented potion: " + namespacedKey);
		}
		return potionEffects;
	}

	@Override
	public boolean isUpgradeable()
	{
		return upgradeable;
	}

	@Override
	public boolean isExtendable()
	{
		return extendable;
	}

	@Override
	public int getMaxLevel()
	{
		return maxLevel;
	}

	// TODO probably not the right solution to make this public static, but where
	// should this utility method be located?
	@ApiStatus.Internal
	public static PotionEffect getPotionEffectFromData(JsonElement potionEffectData)
	{
		JsonObject potionEffectDataObj = potionEffectData.getAsJsonObject();
		NamespacedKey potionEffectTypeKey = Preconditions
				.checkNotNull(NamespacedKey.fromString(potionEffectDataObj.get("type").getAsString()));
		PotionEffectType potionEffectType = Registry.POTION_EFFECT_TYPE.get(potionEffectTypeKey);
		int duration = potionEffectDataObj.get("duration").getAsInt();
		int amplifier = potionEffectDataObj.get("amplifier").getAsInt();
		boolean ambient = potionEffectDataObj.get("ambient").getAsBoolean();
		boolean particles = potionEffectDataObj.get("particles").getAsBoolean();
		boolean icon = potionEffectDataObj.get("icon").getAsBoolean();
		return new PotionEffect(potionEffectType, duration, amplifier, ambient, particles, icon);
	}

	@ApiStatus.Internal
	public static List<PotionEffect> getPotionEffectsFromData(JsonObject data)
	{
		return data.get("effects").getAsJsonArray().asList().stream()
				.map(InternalPotionDataMock::getPotionEffectFromData).toList();
	}
}
