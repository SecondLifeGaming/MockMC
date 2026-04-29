package org.mockmc.mockmc.entity;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockmc.mockmc.MockMCExtension;
import org.mockmc.mockmc.MockMCInject;
import org.mockmc.mockmc.ServerMock;

import static org.junit.jupiter.api.Assertions.assertSame;

@ExtendWith(MockMCExtension.class)
class AmbientMockTest
{

	@MockMCInject
	private ServerMock serverMock;
	@MockMCInject
	private AmbientMock ambient;

	@Test
	void testToString()
	{
		assertSame("AmbientMock", ambient.toString());
	}

}
