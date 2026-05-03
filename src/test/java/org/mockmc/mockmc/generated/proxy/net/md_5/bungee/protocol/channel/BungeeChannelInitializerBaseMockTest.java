// Auto-generated mechanical sanity test for BungeeChannelInitializerBaseMock
package org.mockmc.mockmc.generated.proxy.net.md_5.bungee.protocol.channel;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.mockmc.mockmc.generated.GeneratedTestBase;

public class BungeeChannelInitializerBaseMockTest extends GeneratedTestBase
{
	@Test
	public void testSafeDefaults() throws Exception
	{
		BungeeChannelInitializerBaseMock mock = new Stub();
		assertNotNull(mock);
		assertSafeDefault(mock.getChannelInitializer());
		assertSafeDefault(mock.getChannelAcceptor());
	}

	private static class Stub implements BungeeChannelInitializerBaseMock
	{
	}
}
