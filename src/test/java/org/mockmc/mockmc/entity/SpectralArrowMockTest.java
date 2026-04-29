package org.mockmc.mockmc.entity;

import org.bukkit.entity.EntityType;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockmc.mockmc.MockMCExtension;
import org.mockmc.mockmc.MockMCInject;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockMCExtension.class)
class SpectralArrowMockTest
{

	@MockMCInject
	private SpectralArrowMock spectralArrow;

	@Test
	void getDamage_default()
	{
		assertEquals(6.0, spectralArrow.getDamage());
	}

	@Test
	void getGlowingTicks_default()
	{
		assertEquals(200, spectralArrow.getGlowingTicks());
	}

	@Test
	void setGlowingTicks()
	{
		spectralArrow.setGlowingTicks(4);
		assertEquals(4, spectralArrow.getGlowingTicks());
	}

	@Test
	void getType()
	{
		assertEquals(EntityType.SPECTRAL_ARROW, spectralArrow.getType());
	}

}
