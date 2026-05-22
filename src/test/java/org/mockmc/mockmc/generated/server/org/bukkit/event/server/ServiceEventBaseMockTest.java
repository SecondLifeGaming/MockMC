// Auto-generated mechanical sanity test for ServiceEventBaseMock
package org.mockmc.mockmc.generated.server.org.bukkit.event.server;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.mockmc.mockmc.generated.GeneratedTestBase;

class ServiceEventBaseMockTest extends GeneratedTestBase
{
	@Test
	void testSafeDefaults()
	{
		ServiceEventBaseMock mock = new Stub();
		assertNotNull(mock);
		assertSafeDefault(mock.getProvider());
	}

	private static class Stub implements ServiceEventBaseMock
	{
	}
}
