package org.mockmc.mockmc.entity;

import org.bukkit.entity.EntityType;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockmc.mockmc.MockMCExtension;
import org.mockmc.mockmc.MockMCInject;
import org.mockmc.mockmc.ServerMock;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

@ExtendWith(MockMCExtension.class)
class MarkerMockTest
{

	@MockMCInject
	private ServerMock server;
	@MockMCInject
	private MarkerMock marker;

	@Test
	void testType()
	{
		assertEquals(EntityType.MARKER, marker.getType());
	}

	@Test
	void testCannotAddPassenger()
	{
		assertFalse(marker.addPassenger(new SimpleEntityMock(server)));
		assertEquals(0, marker.getPassengers().size());
	}

}
