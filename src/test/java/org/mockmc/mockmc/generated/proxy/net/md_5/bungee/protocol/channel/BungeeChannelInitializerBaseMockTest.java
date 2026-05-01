// Auto-generated mechanical sanity test for BungeeChannelInitializerBaseMock
package org.mockmc.mockmc.generated.proxy.net.md_5.bungee.protocol.channel;

import org.junit.jupiter.api.Test;
import org.mockmc.mockmc.generated.GeneratedTestBase;

public class BungeeChannelInitializerBaseMockTest extends GeneratedTestBase
{
	@Test
	public void testSafeDefaults()
	{
		BungeeChannelInitializerBaseMock mock = new BungeeChannelInitializerBaseMock()
		{
		};
		assertSafeDefault(mock.getChannelInitializer());
		assertSafeDefault(mock.getChannelAcceptor());
	}
}
