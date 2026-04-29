package org.mockmc.mockmc.entity;

import org.bukkit.entity.EntityType;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockmc.mockmc.MockMCExtension;
import org.mockmc.mockmc.MockMCInject;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockMCExtension.class)
class WindChargeMockTest
{

	@MockMCInject
	private WindChargeMock windCharge;

	@Test
	void getType()
	{
		assertEquals(EntityType.WIND_CHARGE, windCharge.getType());
	}

	@Test
	void explode_WhenEntityIsNotInWorld()
	{
		assertDoesNotThrow(() -> windCharge.explode());
	}

}
