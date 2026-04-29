package org.mockmc.mockmc.fluid;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockmc.mockmc.MockMCExtension;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith(MockMCExtension.class)
class FluidMockTest
{

	@Test
	void registryNonNull()
	{
		assertNotNull(FluidMock.EMPTY);
	}

}
