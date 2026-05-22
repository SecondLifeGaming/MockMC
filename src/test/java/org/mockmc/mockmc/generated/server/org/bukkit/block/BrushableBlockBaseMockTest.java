// Auto-generated mechanical sanity test for BrushableBlockBaseMock
package org.mockmc.mockmc.generated.server.org.bukkit.block;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.mockmc.mockmc.generated.GeneratedTestBase;

class BrushableBlockBaseMockTest extends GeneratedTestBase
{
	@Test
	void testSafeDefaults()
	{
		BrushableBlockBaseMock mock = new Stub();
		assertNotNull(mock);
		assertSafeDefault(mock.getItem());
	}

	private static class Stub implements BrushableBlockBaseMock
	{
	}
}
