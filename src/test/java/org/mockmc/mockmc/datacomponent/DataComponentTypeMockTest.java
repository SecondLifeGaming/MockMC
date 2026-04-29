package org.mockmc.mockmc.datacomponent;

import io.papermc.paper.datacomponent.DataComponentTypes;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockmc.mockmc.MockMCExtension;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith(MockMCExtension.class)
class DataComponentTypeMockTest
{

	@Test
	void valuedNonNull()
	{
		assertNotNull(DataComponentTypes.BLOCK_DATA);
	}

}
