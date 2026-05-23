// Auto-generated mechanical sanity test for BungeeChannelInitializerBaseMock
package org.mockmc.mockmc.generated.proxy.net.md_5.bungee.protocol.channel;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.mockmc.mockmc.generated.GeneratedTestBase;

class BungeeChannelInitializerBaseMockTest extends GeneratedTestBase
{
	@Test
	void testSafeDefaults()
	{
		BungeeChannelInitializerBaseMock mock = new Stub();
		assertNotNull(mock);
		try
		{
			assertSafeDefault(mock.getChannelInitializer());
		} catch (Exception | LinkageError _)
		{
			// Ignore NPEs and LinkageErrors from Bukkit singletons
		}
		try
		{
			assertSafeDefault(mock.getChannelAcceptor());
		} catch (Exception | LinkageError _)
		{
			// Ignore NPEs and LinkageErrors from Bukkit singletons
		}
	}

	private static class Stub implements BungeeChannelInitializerBaseMock
	{
	}
}
