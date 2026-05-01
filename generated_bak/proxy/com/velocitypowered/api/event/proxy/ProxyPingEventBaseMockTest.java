// Auto-generated mechanical sanity test for ProxyPingEventBaseMock
package org.mockmc.mockmc.generated.proxy.com.velocitypowered.api.event.proxy;

import org.junit.jupiter.api.Test;
import org.mockmc.mockmc.generated.GeneratedTestBase;

public class ProxyPingEventBaseMockTest extends GeneratedTestBase
{
	@Test
	public void testSafeDefaults()
	{
		ProxyPingEventBaseMock mock = new ProxyPingEventBaseMock()
		{
		};
		assertSafeDefault(mock.toString());
		assertSafeDefault(mock.getResult());
		assertSafeDefault(mock.getPing());
		assertSafeDefault(mock.getConnection());
	}
}
