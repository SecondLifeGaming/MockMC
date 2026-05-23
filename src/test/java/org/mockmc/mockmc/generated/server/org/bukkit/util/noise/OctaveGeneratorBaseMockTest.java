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
		try
		{
			assertSafeDefault(mock.getOctaves());
		} catch (Exception | LinkageError e)
		{
			// Ignore NPEs and LinkageErrors from Bukkit singletons
		}
	}

	private static class Stub implements OctaveGeneratorBaseMock
	{
	}
}
