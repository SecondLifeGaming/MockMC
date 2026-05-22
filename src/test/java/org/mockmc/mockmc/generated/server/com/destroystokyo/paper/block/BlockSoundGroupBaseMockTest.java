// Auto-generated mechanical sanity test for BlockSoundGroupBaseMock
package org.mockmc.mockmc.generated.server.com.destroystokyo.paper.block;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.mockmc.mockmc.generated.GeneratedTestBase;

@SuppressWarnings(
{"deprecation", "removal", "java:S1874"})
class BlockSoundGroupBaseMockTest extends GeneratedTestBase
{
	@Test
	void testSafeDefaults()
	{
		BlockSoundGroupBaseMock mock = new Stub();
		assertNotNull(mock);
		assertSafeDefault(mock.getBreakSound());
		assertSafeDefault(mock.getStepSound());
		assertSafeDefault(mock.getPlaceSound());
		assertSafeDefault(mock.getHitSound());
		assertSafeDefault(mock.getFallSound());
	}

	private static class Stub implements BlockSoundGroupBaseMock
	{
	}
}
