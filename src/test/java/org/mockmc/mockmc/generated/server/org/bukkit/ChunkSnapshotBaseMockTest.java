// Auto-generated mechanical sanity test for ChunkSnapshotBaseMock
package org.mockmc.mockmc.generated.server.org.bukkit;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.mockmc.mockmc.generated.GeneratedTestBase;

class ChunkSnapshotBaseMockTest extends GeneratedTestBase
{
	@Test
	void testSafeDefaults()
	{
		ChunkSnapshotBaseMock mock = new Stub();
		assertNotNull(mock);
		assertSafeDefault(mock.getWorldName());
		assertSafeDefault(mock.getWorldKey());
	}

	private static class Stub implements ChunkSnapshotBaseMock
	{
	}
}
