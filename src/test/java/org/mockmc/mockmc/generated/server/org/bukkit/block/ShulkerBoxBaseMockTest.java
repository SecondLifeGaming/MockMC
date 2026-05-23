// Auto-generated mechanical sanity test for ShulkerBoxBaseMock
package org.mockmc.mockmc.generated.server.org.bukkit.block;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.mockmc.mockmc.generated.GeneratedTestBase;

class ShulkerBoxBaseMockTest extends GeneratedTestBase
{
	@Test
	void testSafeDefaults()
	{
		ShulkerBoxBaseMock mock = new Stub();
		assertNotNull(mock);
		try
		{
			assertSafeDefault(mock.getColor());
		} catch (Exception | LinkageError e)
		{
			// Ignore NPEs and LinkageErrors from Bukkit singletons
		}
	}

	private static class Stub implements ShulkerBoxBaseMock
	{
	}
}
