package org.mockmc.mockmc.entity;

import org.bukkit.Location;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Sniffer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockmc.mockmc.MockMCExtension;
import org.mockmc.mockmc.MockMCInject;
import org.mockmc.mockmc.ServerMock;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

@ExtendWith(MockMCExtension.class)
class SnifferMockTest
{

	@MockMCInject
	private ServerMock server;
	private SnifferMock sniffer;

	@BeforeEach
	void setUp()
	{
		sniffer = new SnifferMock(server, java.util.UUID.randomUUID());
	}

	@Test
	void testGetType()
	{
		assertEquals(EntityType.SNIFFER, sniffer.getType());
	}

	@Test
	void testStateDefault()
	{
		assertEquals(Sniffer.State.IDLING, sniffer.getState());
	}

	@Test
	void testSetState()
	{
		sniffer.setState(Sniffer.State.DIGGING);
		assertEquals(Sniffer.State.DIGGING, sniffer.getState());
	}

	@Test
	void testExploredLocations()
	{
		Location loc = new Location(sniffer.getWorld(), 10, 64, 10);
		sniffer.addExploredLocation(loc);
		assertEquals(1, sniffer.getExploredLocations().size());
		assertTrue(sniffer.getExploredLocations().contains(loc.toBlockLocation()));

		sniffer.removeExploredLocation(loc);
		assertEquals(0, sniffer.getExploredLocations().size());
	}

	@Test
	void testCanDigDefault()
	{
		assertTrue(sniffer.canDig());
	}

	@Test
	void testSetCanDig()
	{
		sniffer.setCanDig(false);
		assertFalse(sniffer.canDig());
	}

	@Test
	void testFindPossibleDigLocation()
	{
		Location loc = sniffer.getLocation();
		assertNotNull(sniffer.findPossibleDigLocation());
		assertEquals(loc.getY() - 1, sniffer.findPossibleDigLocation().getY());
	}
}
