// Auto-generated mechanical sanity test for ServerBaseMock
package org.mockmc.mockmc.generated.proxy.net.md_5.bungee.api.connection;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.mockmc.mockmc.generated.GeneratedTestBase;

class ServerBaseMockTest extends GeneratedTestBase
{
	@Test
	void testSafeDefaults() throws Exception
	{
		ServerBaseMock mock = new Stub();
		assertNotNull(mock);
		assertSafeDefault(mock.getInfo());
	}

	private static class Stub implements ServerBaseMock
	{
	}
}
