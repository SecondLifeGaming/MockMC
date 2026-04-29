package org.mockmc.mockmc.entity;

import org.bukkit.entity.Endermite;
import org.bukkit.entity.EntityType;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockmc.mockmc.MockMCExtension;
import org.mockmc.mockmc.MockMCInject;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

@SuppressWarnings(
{"deprecation", "removal", "unchecked"})
@ExtendWith(MockMCExtension.class)
class EndermiteMockTest
{

	@MockMCInject
	private Endermite enderMite;

	@Test
	void testGetType()
	{
		assertEquals(EntityType.ENDERMITE, enderMite.getType());
	}

	@Test
	void testIsPlayerSpawned()
	{
		assertFalse(enderMite.isPlayerSpawned());
	}

	@Test
	void testSetPlayerSpawned()
	{
		assertDoesNotThrow(() -> enderMite.setPlayerSpawned(true));
		assertFalse(enderMite.isPlayerSpawned());
	}

	@Test
	void testGetLifetimeTicksDefault()
	{
		assertEquals(0, enderMite.getLifetimeTicks());
	}

	@Test
	void testSetLifetimeTicks()
	{
		enderMite.setLifetimeTicks(123);
		assertEquals(123, enderMite.getLifetimeTicks());
	}

}
