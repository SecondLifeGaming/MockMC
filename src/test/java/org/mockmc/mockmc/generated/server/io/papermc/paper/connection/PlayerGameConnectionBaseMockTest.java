// Auto-generated mechanical sanity test for PlayerGameConnectionBaseMock
package org.mockmc.mockmc.generated.server.io.papermc.paper.connection;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.mockmc.mockmc.generated.GeneratedTestBase;

@SuppressWarnings("all")
class PlayerGameConnectionBaseMockTest extends GeneratedTestBase
{
	@Test
	void testSafeDefaults()
	{
		PlayerGameConnectionBaseMock mock = new Stub();
		assertNotNull(mock);
		try
		{
			assertSafeDefault(mock.getPlayer());
		} catch (Exception | LinkageError _)
		{
			// Ignore NPEs and LinkageErrors from Bukkit singletons
		}
	}

	private static class Stub implements PlayerGameConnectionBaseMock
	{
	}
}
