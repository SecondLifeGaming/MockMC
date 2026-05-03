// Auto-generated mechanical sanity test for ProxyConfigBaseMock
package org.mockmc.mockmc.generated.proxy.com.velocitypowered.api.proxy.config;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.mockmc.mockmc.generated.GeneratedTestBase;

class ProxyConfigBaseMockTest extends GeneratedTestBase
{
	@Test
	void testSafeDefaults() throws Exception
	{
		ProxyConfigBaseMock mock = new Stub();
		assertNotNull(mock);
		assertSafeDefault(mock.getMotd());
		assertSafeDefault(mock.getQueryMap());
		assertSafeDefault(mock.getServers());
		assertSafeDefault(mock.getAttemptConnectionOrder());
		assertSafeDefault(mock.getForcedHosts());
		assertSafeDefault(mock.getFavicon());
	}

	private static class Stub implements ProxyConfigBaseMock
	{
	}
}
