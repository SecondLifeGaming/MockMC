package org.mockmc.mockmc.entity;

import org.bukkit.Location;
import org.bukkit.entity.EntityType;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockmc.mockmc.MockMCExtension;
import org.mockmc.mockmc.MockMCInject;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

@ExtendWith(MockMCExtension.class)
class BatMockTest
{

	@MockMCInject
	private BatMock bat;

	@Test
	void testIsAwakeDefault()
	{
		assertTrue(bat.isAwake());
	}

	@Test
	void testSetAwake()
	{
		bat.setAwake(false);
		assertFalse(bat.isAwake());
	}

	@Test
	void testEntityType()
	{
		assertEquals(EntityType.BAT, bat.getType());
	}

	@Test
	void testTargetLocationDefault()
	{
		assertNull(bat.getTargetLocation());
	}

	@Test
	void testTargetLocation()
	{
		bat.setTargetLocation(bat.getLocation());
		assertEquals(bat.getLocation(), bat.getTargetLocation());
	}

	@Test
	void testSetTargetLocationNull()
	{
		bat.setTargetLocation(new Location(null, 0, 0, 0));
		assertNotNull(bat.getTargetLocation());
		bat.setTargetLocation(null);
		assertNull(bat.getTargetLocation());
	}

}
