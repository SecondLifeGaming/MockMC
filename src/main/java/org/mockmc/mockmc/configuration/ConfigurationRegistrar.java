package org.mockmc.mockmc.configuration;

import org.bukkit.Color;
import org.bukkit.FireworkEffect;
import org.bukkit.Location;
import org.bukkit.attribute.AttributeModifier;
import org.bukkit.block.banner.Pattern;
import org.bukkit.block.spawner.SpawnRule;
import org.bukkit.configuration.serialization.ConfigurationSerialization;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.util.BlockVector;
import org.bukkit.util.BoundingBox;
import org.bukkit.util.Vector;
import org.mockmc.mockmc.entity.OfflinePlayerMock;
import org.mockmc.mockmc.inventory.ItemStackMock;
import org.mockmc.mockmc.inventory.SerializableMeta;
import org.mockmc.mockmc.inventory.meta.components.CustomModelDataComponentMock;
import org.mockmc.mockmc.inventory.meta.components.EquippableComponentMock;
import org.mockmc.mockmc.inventory.meta.components.FoodComponentMock;
import org.mockmc.mockmc.inventory.meta.components.JukeboxPlayableComponentMock;
import org.mockmc.mockmc.inventory.meta.components.ToolComponentMock;
import org.mockmc.mockmc.inventory.meta.components.UseCooldownComponentMock;
import org.mockmc.mockmc.profile.PlayerProfileMock;
import org.jetbrains.annotations.ApiStatus;

@ApiStatus.Internal
public final class ConfigurationRegistrar
{
	private ConfigurationRegistrar()
	{
		throw new UnsupportedOperationException("Utility class");
	}

	public static void register()
	{
		ConfigurationSerialization.registerClass(Vector.class);
		ConfigurationSerialization.registerClass(BlockVector.class);
		ConfigurationSerialization.registerClass(ItemStack.class);
		ConfigurationSerialization.registerClass(ItemStackMock.class);
		ConfigurationSerialization.registerClass(Color.class);
		ConfigurationSerialization.registerClass(PotionEffect.class);
		ConfigurationSerialization.registerClass(FireworkEffect.class);
		ConfigurationSerialization.registerClass(Pattern.class);
		ConfigurationSerialization.registerClass(BoundingBox.class);
		ConfigurationSerialization.registerClass(Location.class);
		ConfigurationSerialization.registerClass(AttributeModifier.class);
		ConfigurationSerialization.registerClass(SpawnRule.class);
		ConfigurationSerialization.registerClass(PlayerProfileMock.class);
		ConfigurationSerialization.registerClass(OfflinePlayerMock.class);
		ConfigurationSerialization.registerClass(SerializableMeta.class);
		ConfigurationSerialization.registerClass(CustomModelDataComponentMock.class);
		ConfigurationSerialization.registerClass(EquippableComponentMock.class);
		ConfigurationSerialization.registerClass(FoodComponentMock.class);
		ConfigurationSerialization.registerClass(ToolComponentMock.class);
		ConfigurationSerialization.registerClass(ToolComponentMock.ToolRuleMock.class);
		ConfigurationSerialization.registerClass(JukeboxPlayableComponentMock.class);
		ConfigurationSerialization.registerClass(UseCooldownComponentMock.class);
	}
}
