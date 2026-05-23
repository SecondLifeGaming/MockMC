// Auto-generated mechanical sanity test for BlockStateBaseMock
package org.mockmc.mockmc.generated.server.org.bukkit.block;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.mockmc.mockmc.generated.GeneratedTestBase;

@SuppressWarnings(
{"deprecation", "removal", "java:S1874"})
class BlockStateBaseMockTest extends GeneratedTestBase
{
	@Test
	void testSafeDefaults()
	{
		BlockStateBaseMock mock = new Stub();
		assertNotNull(mock);
		assertSafeDefault(mock.copy());
		assertSafeDefault(mock.getLocation());
		assertSafeDefault(mock.getType());
		assertSafeDefault(mock.getData());
		assertSafeDefault(mock.getBlockData());
		assertSafeDefault(mock.getWorld());
		assertSafeDefault(mock.getBlock());
		assertSafeDefault(mock.getChunk());
		assertSafeDefault(mock.getDrops());
	}

	private static class Stub implements BlockStateBaseMock
	{
	}
}
