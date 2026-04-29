package org.mockmc.mockmc.entity;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockmc.mockmc.MockMCExtension;
import org.mockmc.mockmc.MockMCInject;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockMCExtension.class)
class VehicleMockTest
{

	@MockMCInject
	private VehicleMock vehicle;

	@Test
	void testToString()
	{
		assertEquals("VehicleMock{passenger=null}", vehicle.toString());
	}

}
