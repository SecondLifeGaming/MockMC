// Auto-generated mechanical sanity test for ProxyConfigBaseMock
package org.mockmc.mockmc.generated.proxy.com.velocitypowered.api.proxy.config;

import org.junit.jupiter.api.Test;
import org.mockmc.mockmc.generated.GeneratedTestBase;

public class ProxyConfigBaseMockTest extends GeneratedTestBase
{
	@Test
	public void testSafeDefaults()
	{
		ProxyConfigBaseMock mock = new ProxyConfigBaseMock()
		{
		};
		assertNotNull(mock);
		assertSafeDefault(mock.getMotd());
		assertSafeDefault(mock.getQueryMap());
		assertSafeDefault(mock.getServers());
		assertSafeDefault(mock.getAttemptConnectionOrder());
		assertSafeDefault(mock.getFavicon());
		assertSafeDefault(mock.getForcedHosts());
	}
}
