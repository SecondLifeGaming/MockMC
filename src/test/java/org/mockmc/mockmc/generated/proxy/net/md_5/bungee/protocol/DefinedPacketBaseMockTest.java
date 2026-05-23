// Auto-generated mechanical sanity test for DefinedPacketBaseMock
package org.mockmc.mockmc.generated.proxy.net.md_5.bungee.protocol;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.mockmc.mockmc.generated.GeneratedTestBase;

class DefinedPacketBaseMockTest extends GeneratedTestBase
{
	@Test
	void testSafeDefaults()
	{
		DefinedPacketBaseMock mock = new Stub();
		assertNotNull(mock);
		try
		{
			assertSafeDefault(mock.toString());
		} catch (Exception | LinkageError _)
		{
			// Ignore NPEs and LinkageErrors from Bukkit singletons
		}
		try
		{
			assertSafeDefault(mock.nextProtocol());
		} catch (Exception | LinkageError _)
		{
			// Ignore NPEs and LinkageErrors from Bukkit singletons
		}
	}

	private static class Stub implements DefinedPacketBaseMock
	{
	}
}
