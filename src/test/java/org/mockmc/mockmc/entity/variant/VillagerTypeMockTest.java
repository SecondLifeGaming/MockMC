package org.mockmc.mockmc.entity.variant;

import org.bukkit.NamespacedKey;
import org.bukkit.entity.Villager;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockmc.mockmc.MockMCExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SuppressWarnings(
{"deprecation", "removal", "unchecked"})
@ExtendWith(MockMCExtension.class)
class VillagerTypeMockTest
{

	@Test
	void successfullyLoaded()
	{
		Villager.Type type = Villager.Type.JUNGLE;
		assertEquals("JUNGLE", type.name());
		assertEquals(NamespacedKey.fromString("minecraft:jungle"), type.getKey());
	}

}
