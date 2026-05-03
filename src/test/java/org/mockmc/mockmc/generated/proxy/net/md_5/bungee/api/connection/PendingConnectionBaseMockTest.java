// Auto-generated mechanical sanity test for PendingConnectionBaseMock
package org.mockmc.mockmc.generated.proxy.net.md_5.bungee.api.connection;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.mockmc.mockmc.generated.GeneratedTestBase;

class PendingConnectionBaseMockTest extends GeneratedTestBase
{
	@Test
	void testSafeDefaults() throws Exception
	{
		PendingConnectionBaseMock mock = new Stub();
		assertNotNull(mock);
		assertSafeDefault(mock.getName());
		assertSafeDefault(mock.getUniqueId());
		assertSafeDefault(mock.getVirtualHost());
		assertSafeDefault(mock.getListener());
		assertSafeDefault(mock.getUUID());
	}

	private static class Stub implements PendingConnectionBaseMock
	{
	}
}
