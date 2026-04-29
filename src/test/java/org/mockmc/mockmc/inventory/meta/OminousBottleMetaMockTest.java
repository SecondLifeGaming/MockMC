package org.mockmc.mockmc.inventory.meta;

import org.bukkit.Server;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockmc.mockmc.MockMCExtension;
import org.mockmc.mockmc.MockMCInject;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

@ExtendWith(MockMCExtension.class)
class OminousBottleMetaMockTest
{

	@MockMCInject
	private Server server;
	@MockMCInject
	private OminousBottleMetaMock meta;
	private final int amplifier = 1;

	@Test
	void hasAmplifier_default()
	{
		assertFalse(meta.hasAmplifier());
	}

	@Test
	void setAmplifier_valid()
	{
		meta.setAmplifier(amplifier);
		assertEquals(amplifier, meta.getAmplifier());
		assertTrue(meta.hasAmplifier());
	}

	@Test
	void setAmplifier_toSmall()
	{
		assertThrows(IllegalArgumentException.class, () -> meta.setAmplifier(-1));
	}

	@Test
	void setAmplifier_toBig()
	{
		assertThrows(IllegalArgumentException.class, () -> meta.setAmplifier(100));
	}

	@Test
	void getAmplifier_invalid()
	{
		assertThrows(IllegalStateException.class, () -> meta.getAmplifier());
	}

	@Test
	void testClone()
	{
		OminousBottleMetaMock ominousMeta = new OminousBottleMetaMock();
		ominousMeta.setAmplifier(amplifier);

		OminousBottleMetaMock clone = ominousMeta.clone();

		assertEquals(ominousMeta, clone);
		assertEquals(ominousMeta.getAmplifier(), clone.getAmplifier());
	}

}
