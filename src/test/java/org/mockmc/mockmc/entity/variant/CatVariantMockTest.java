package org.mockmc.mockmc.entity.variant;

import org.bukkit.NamespacedKey;
import org.bukkit.entity.Cat;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockmc.mockmc.MockMCExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SuppressWarnings(
{"deprecation", "removal", "unchecked"})
@ExtendWith(MockMCExtension.class)
class CatVariantMockTest
{

	@Test
	void testSuccessfullyLoaded()
	{
		Cat.Type catVariant = Cat.Type.BLACK;
		assertEquals("BLACK", catVariant.name());
		assertEquals(NamespacedKey.fromString("minecraft:black"), catVariant.getKey());
	}

}
