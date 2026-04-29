package org.mockmc.mockmc.inventory.meta;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockmc.mockmc.MockMCExtension;
import org.mockmc.mockmc.MockMCInject;

import static org.junit.jupiter.api.Assertions.assertThrowsExactly;

@ExtendWith(MockMCExtension.class)
class SpawnEggMetaMockTest
{

	@MockMCInject
	private SpawnEggMetaMock meta;

	@Test
	void getSpawnedType_ThrowsException()
	{
		assertThrowsExactly(UnsupportedOperationException.class, () -> meta.getSpawnedType());
	}

	@Test
	void setSpawnedType_ThrowsException()
	{
		assertThrowsExactly(UnsupportedOperationException.class, () -> meta.setSpawnedType(null));
	}

}
