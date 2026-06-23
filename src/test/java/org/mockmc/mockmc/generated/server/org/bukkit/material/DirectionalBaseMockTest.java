// Auto-generated mechanical sanity test for DirectionalBaseMock
package org.mockmc.mockmc.generated.server.org.bukkit.material;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.mockmc.mockmc.generated.GeneratedTestBase;

@SuppressWarnings("all")
class DirectionalBaseMockTest extends GeneratedTestBase
{
	@Test
	void testSafeDefaults()
	{
		DirectionalBaseMock mock = new Stub();
		assertNotNull(mock);
		try
		{
			assertSafeDefault(mock.getFacing());
		} catch (Exception | LinkageError _)
		{
			// Ignore NPEs and LinkageErrors from Bukkit singletons
		}
	}

	private static class Stub implements DirectionalBaseMock
	{
	}
}
