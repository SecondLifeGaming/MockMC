// Auto-generated mechanical sanity test for PlayerBucketEventBaseMock
package org.mockmc.mockmc.generated.server.org.bukkit.event.player;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.mockmc.mockmc.generated.GeneratedTestBase;

class PlayerBucketEventBaseMockTest extends GeneratedTestBase
{
	@Test
	void testSafeDefaults()
	{
		PlayerBucketEventBaseMock mock = new Stub();
		assertNotNull(mock);
		assertSafeDefault(mock.getItemStack());
		assertSafeDefault(mock.getBlock());
		assertSafeDefault(mock.getHand());
		assertSafeDefault(mock.getBlockFace());
		assertSafeDefault(mock.getBucket());
		assertSafeDefault(mock.getBlockClicked());
	}

	private static class Stub implements PlayerBucketEventBaseMock
	{
	}
}
