// Auto-generated mechanical sanity test for SoundGroupBaseMock
package org.mockmc.mockmc.generated.server.org.bukkit;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.mockmc.mockmc.generated.GeneratedTestBase;

class SoundGroupBaseMockTest extends GeneratedTestBase
{
	@Test
	void testSafeDefaults()
	{
		SoundGroupBaseMock mock = new Stub();
		assertNotNull(mock);
		assertSafeDefault(mock.getBreakSound());
		assertSafeDefault(mock.getStepSound());
		assertSafeDefault(mock.getPlaceSound());
		assertSafeDefault(mock.getHitSound());
		assertSafeDefault(mock.getFallSound());
	}

	private static class Stub implements SoundGroupBaseMock
	{
	}
}
