// Auto-generated mechanical sanity test for CachedServerIconBaseMock
package org.mockmc.mockmc.generated.server.org.bukkit.util;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.mockmc.mockmc.generated.GeneratedTestBase;

class CachedServerIconBaseMockTest extends GeneratedTestBase
{
	@Test
	void testSafeDefaults()
	{
		CachedServerIconBaseMock mock = new Stub();
		assertNotNull(mock);
		try
		{
			assertSafeDefault(mock.getData());
		} catch (Exception | LinkageError _)
		{
			// Ignore NPEs and LinkageErrors from Bukkit singletons
		}
	}

	private static class Stub implements CachedServerIconBaseMock
	{
	}
}
