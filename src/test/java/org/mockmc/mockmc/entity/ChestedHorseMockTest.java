package org.mockmc.mockmc.entity;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockmc.mockmc.MockMCExtension;
import org.mockmc.mockmc.MockMCInject;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

@ExtendWith(MockMCExtension.class)
class ChestedHorseMockTest
{

	@MockMCInject
	private MuleMock chestedHorse;

	@Test
	void testIsCarryingChestDefault()
	{
		assertFalse(chestedHorse.isCarryingChest());
	}

	@Test
	void testIsCarryingChest()
	{
		chestedHorse.setCarryingChest(true);
		assertTrue(chestedHorse.isCarryingChest());
	}

	@Test
	void testSetCarryingChestOnAlreadyTrue()
	{
		chestedHorse.setCarryingChest(true);
		chestedHorse.setCarryingChest(true);
		assertTrue(chestedHorse.isCarryingChest());
	}

}
