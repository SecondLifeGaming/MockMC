// Auto-generated mechanical sanity test for ColorableBaseMock
package org.mockmc.mockmc.generated.server.org.bukkit.material;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.mockmc.mockmc.generated.GeneratedTestBase;

class ColorableBaseMockTest extends GeneratedTestBase
{
	@Test
	void testSafeDefaults()
	{
		ColorableBaseMock mock = new Stub();
		assertNotNull(mock);
		try
		{
			assertSafeDefault(mock.getColor());
		} catch (Exception | LinkageError e)
		{
			// Ignore NPEs and LinkageErrors from Bukkit singletons
		}
	}

	private static class Stub implements ColorableBaseMock
	{
	}
}
