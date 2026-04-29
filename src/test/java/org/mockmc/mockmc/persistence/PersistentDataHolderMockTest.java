package org.mockmc.mockmc.persistence;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockmc.mockmc.MockMCExtension;
import org.mockmc.mockmc.MockMCInject;

import static org.junit.jupiter.api.Assertions.assertInstanceOf;

@ExtendWith(MockMCExtension.class)
class PersistentDataHolderMockTest
{

	@MockMCInject
	private PersistentDataHolderMock holder;

	@Test
	void testGetPersistentDataContainer()
	{
		assertInstanceOf(PersistentDataContainerMock.class, holder.getPersistentDataContainer());
	}

}
