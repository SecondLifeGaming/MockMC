// Auto-generated mechanical sanity test for SizedFireballBaseMock
package org.mockmc.mockmc.generated.server.org.bukkit.entity;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.mockmc.mockmc.generated.GeneratedTestBase;

@SuppressWarnings("all")
class SizedFireballBaseMockTest extends GeneratedTestBase
{
	@Test
	void testSafeDefaults()
	{
		SizedFireballBaseMock mock = new Stub();
		assertNotNull(mock);
		try
		{
			assertSafeDefault(mock.getDisplayItem());
		} catch (Exception | LinkageError _)
		{
			// Ignore NPEs and LinkageErrors from Bukkit singletons
		}
	}

	private static class Stub implements SizedFireballBaseMock
	{
	}
}
