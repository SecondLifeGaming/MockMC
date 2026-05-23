// Auto-generated mechanical sanity test for PacketHandlerBaseMock
package org.mockmc.mockmc.generated.proxy.net.md_5.bungee.netty;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.mockmc.mockmc.generated.GeneratedTestBase;

class PacketHandlerBaseMockTest extends GeneratedTestBase
{
	@Test
	void testSafeDefaults()
	{
		PacketHandlerBaseMock mock = new Stub();
		assertNotNull(mock);
		try
		{
			assertSafeDefault(mock.toString());
		} catch (Exception | LinkageError e)
		{
			// Ignore NPEs and LinkageErrors from Bukkit singletons
		}
	}

	private static class Stub implements PacketHandlerBaseMock
	{
	}
}
