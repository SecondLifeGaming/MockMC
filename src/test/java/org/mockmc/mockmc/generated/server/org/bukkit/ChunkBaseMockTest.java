// Auto-generated mechanical sanity test for ChunkBaseMock
package org.mockmc.mockmc.generated.server.org.bukkit;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.mockmc.mockmc.generated.GeneratedTestBase;

class ChunkBaseMockTest extends GeneratedTestBase
{
	@Test
	void testSafeDefaults()
	{
		ChunkBaseMock mock = new Stub();
		assertNotNull(mock);
		assertSafeDefault(mock.getChunkSnapshot());
		assertSafeDefault(mock.getTileEntities());
		assertSafeDefault(mock.getEntities());
		assertSafeDefault(mock.getPluginChunkTickets());
		assertSafeDefault(mock.getLoadLevel());
		assertSafeDefault(mock.getStructures());
		assertSafeDefault(mock.getPlayersSeeingChunk());
		assertSafeDefault(mock.getWorld());
	}

	private static class Stub implements ChunkBaseMock
	{
	}
}
