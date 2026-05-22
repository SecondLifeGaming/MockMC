// Auto-generated mechanical sanity test for OctaveGeneratorBaseMock
package org.mockmc.mockmc.generated.server.org.bukkit.util.noise;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.mockmc.mockmc.generated.GeneratedTestBase;

class OctaveGeneratorBaseMockTest extends GeneratedTestBase
{
	@Test
	void testSafeDefaults()
	{
		OctaveGeneratorBaseMock mock = new Stub();
		assertNotNull(mock);
		assertSafeDefault(mock.getOctaves());
	}

	private static class Stub implements OctaveGeneratorBaseMock
	{
	}
}
