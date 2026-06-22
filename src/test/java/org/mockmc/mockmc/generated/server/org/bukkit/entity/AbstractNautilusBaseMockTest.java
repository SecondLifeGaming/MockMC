// Auto-generated mechanical sanity test for AbstractNautilusBaseMock
package org.mockmc.mockmc.generated.server.org.bukkit.entity;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.mockmc.mockmc.generated.GeneratedTestBase;

@SuppressWarnings("all")
class AbstractNautilusBaseMockTest extends GeneratedTestBase
{
	@Test
	void testSafeDefaults()
	{
		AbstractNautilusBaseMock mock = new Stub();
		assertNotNull(mock);
		try
		{
			assertSafeDefault(mock.getInventory());
		} catch (Exception | LinkageError _)
		{
			// Ignore NPEs and LinkageErrors from Bukkit singletons
		}
	}

	private static class Stub implements AbstractNautilusBaseMock
	{
	}
}
