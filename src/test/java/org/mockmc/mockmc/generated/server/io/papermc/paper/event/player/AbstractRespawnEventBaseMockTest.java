// Auto-generated mechanical sanity test for AbstractRespawnEventBaseMock
package org.mockmc.mockmc.generated.server.io.papermc.paper.event.player;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.mockmc.mockmc.generated.GeneratedTestBase;

@SuppressWarnings("all")
class AbstractRespawnEventBaseMockTest extends GeneratedTestBase
{
	@Test
	void testSafeDefaults()
	{
		AbstractRespawnEventBaseMock mock = new Stub();
		assertNotNull(mock);
		try
		{
			assertSafeDefault(mock.getRespawnReason());
		} catch (Exception | LinkageError _)
		{
			// Ignore NPEs and LinkageErrors from Bukkit singletons
		}
		try
		{
			assertSafeDefault(mock.getRespawnFlags());
		} catch (Exception | LinkageError _)
		{
			// Ignore NPEs and LinkageErrors from Bukkit singletons
		}
		try
		{
			assertSafeDefault(mock.getRespawnLocation());
		} catch (Exception | LinkageError _)
		{
			// Ignore NPEs and LinkageErrors from Bukkit singletons
		}
	}

	private static class Stub implements AbstractRespawnEventBaseMock
	{
	}
}
