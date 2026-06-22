// Auto-generated mechanical sanity test for ServerLinksBaseMock
package org.mockmc.mockmc.generated.server.org.bukkit;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.mockmc.mockmc.generated.GeneratedTestBase;

@SuppressWarnings("all")
class ServerLinksBaseMockTest extends GeneratedTestBase
{
	@Test
	void testSafeDefaults()
	{
		ServerLinksBaseMock mock = new Stub();
		assertNotNull(mock);
		try
		{
			assertSafeDefault(mock.copy());
		} catch (Exception | LinkageError _)
		{
			// Ignore NPEs and LinkageErrors from Bukkit singletons
		}
		try
		{
			assertSafeDefault(mock.getLinks());
		} catch (Exception | LinkageError _)
		{
			// Ignore NPEs and LinkageErrors from Bukkit singletons
		}
	}

	private static class Stub implements ServerLinksBaseMock
	{
	}
}
