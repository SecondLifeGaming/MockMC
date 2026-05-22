// Auto-generated mechanical sanity test for NetworkClientBaseMock
package org.mockmc.mockmc.generated.server.com.destroystokyo.paper.network;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.mockmc.mockmc.generated.GeneratedTestBase;

class NetworkClientBaseMockTest extends GeneratedTestBase
{
	@Test
	void testSafeDefaults()
	{
		NetworkClientBaseMock mock = new Stub();
		assertNotNull(mock);
		assertSafeDefault(mock.getAddress());
		assertSafeDefault(mock.getVirtualHost());
	}

	private static class Stub implements NetworkClientBaseMock
	{
	}
}
