package org.mockmc.mockmc.potion;

import com.google.gson.JsonObject;
import org.bukkit.NamespacedKey;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.jetbrains.annotations.NotNull;
import org.mockmc.mockmc.util.PotionUtils;
import org.mockmc.mockmc.util.ResourceLoader;
import java.util.List;

@SuppressWarnings("removal")
public class InternalPotionDataMock
		implements
			org.mockmc.mockmc.generated.org.bukkit.potion.PotionTypeInternalPotionDataBaseMock
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
		this.potionEffects = PotionUtils.getPotionEffectsFromData(data);
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
			throw new IllegalStateException("Unimplemented potion: " + namespacedKey);
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

}
