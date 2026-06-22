// Auto-generated mechanical sanity test for BrushableBlockBaseMock
package org.mockmc.mockmc.generated.server.org.bukkit.block;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.mockmc.mockmc.generated.GeneratedTestBase;

@SuppressWarnings("all")
class BrushableBlockBaseMockTest extends GeneratedTestBase
{
	@Test
	void testSafeDefaults()
	{
		BrushableBlockBaseMock mock = new Stub();
		assertNotNull(mock);
		try
		{
			assertSafeDefault(mock.getItem());
		} catch (Exception | LinkageError _)
		{
			// Ignore NPEs and LinkageErrors from Bukkit singletons
		}
	}

	private static class Stub implements BrushableBlockBaseMock
	{
	}
}
