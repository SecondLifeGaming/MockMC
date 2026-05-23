// Auto-generated mechanical sanity test for ChunkEventBaseMock
package org.mockmc.mockmc.generated.server.org.bukkit.event.world;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.mockmc.mockmc.generated.GeneratedTestBase;

class ChunkEventBaseMockTest extends GeneratedTestBase
{
	@Test
	void testSafeDefaults()
	{
		ChunkEventBaseMock mock = new Stub();
		assertNotNull(mock);
		try
		{
			assertSafeDefault(mock.getChunk());
		} catch (Exception | LinkageError e)
		{
			// Ignore NPEs and LinkageErrors from Bukkit singletons
		}
	}

	private static class Stub implements ChunkEventBaseMock
	{
	}
}
