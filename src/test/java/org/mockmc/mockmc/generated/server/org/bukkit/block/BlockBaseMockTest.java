// Auto-generated mechanical sanity test for BlockBaseMock
package org.mockmc.mockmc.generated.server.org.bukkit.block;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.mockmc.mockmc.generated.GeneratedTestBase;

@SuppressWarnings(
{"deprecation", "removal", "java:S1874"})
class BlockBaseMockTest extends GeneratedTestBase
{
	@Test
	void testSafeDefaults()
	{
		BlockBaseMock mock = new Stub();
		assertNotNull(mock);
		assertSafeDefault(mock.getLocation());
		assertSafeDefault(mock.getState());
		assertSafeDefault(mock.getType());
		assertSafeDefault(mock.getBlockData());
		assertSafeDefault(mock.getWorld());
		assertSafeDefault(mock.getBiome());
		assertSafeDefault(mock.getTranslationKey());
		assertSafeDefault(mock.getChunk());
		assertSafeDefault(mock.getComputedBiome());
		assertSafeDefault(mock.getDrops());
		assertSafeDefault(mock.getPistonMoveReaction());
		assertSafeDefault(mock.getBoundingBox());
		assertSafeDefault(mock.getCollisionShape());
		assertSafeDefault(mock.getSoundGroup());
		assertSafeDefault(mock.getBlockSoundGroup());
	}

	private static class Stub implements BlockBaseMock
	{
	}
}
