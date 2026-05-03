package org.mockmc.mockmc.block.state;

import org.bukkit.Material;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.mockmc.mockmc.MockMCExtension;
import org.mockmc.mockmc.MockMCInject;
import org.mockmc.mockmc.ServerMock;
import org.junit.jupiter.api.extension.ExtendWith;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

@ExtendWith(MockMCExtension.class)
class CrafterStateMockTest
{

	@MockMCInject
	private ServerMock server;
	private CrafterStateMock crafter;

	@BeforeEach
	void setUp()
	{
		crafter = new CrafterStateMock(Material.CRAFTER);
	}

	@Test
	void testSlotDisabled()
	{
		assertFalse(crafter.isSlotDisabled(0));
		crafter.setSlotDisabled(0, true);
		assertTrue(crafter.isSlotDisabled(0));
	}

	@Test
	void testTriggered()
	{
		assertFalse(crafter.isTriggered());
		crafter.setTriggered(true);
		assertTrue(crafter.isTriggered());
	}

	@Test
	void testCraftingTicks()
	{
		assertEquals(0, crafter.getCraftingTicks());
		crafter.setCraftingTicks(10);
		assertEquals(10, crafter.getCraftingTicks());
	}

	@Test
	void testSnapshot()
	{
		crafter.setCraftingTicks(5);
		crafter.setSlotDisabled(2, true);
		CrafterStateMock snapshot = crafter.getSnapshot();
		assertEquals(5, snapshot.getCraftingTicks());
		assertTrue(snapshot.isSlotDisabled(2));

		crafter.setCraftingTicks(10);
		assertEquals(5, snapshot.getCraftingTicks());
	}
}
