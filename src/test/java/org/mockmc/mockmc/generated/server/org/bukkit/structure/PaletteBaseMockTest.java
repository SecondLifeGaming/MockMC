// Auto-generated mechanical sanity test for PaletteBaseMock
package org.mockmc.mockmc.generated.server.org.bukkit.structure;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.mockmc.mockmc.generated.GeneratedTestBase;

class PaletteBaseMockTest extends GeneratedTestBase
{
	@Test
	void testSafeDefaults()
	{
		PaletteBaseMock mock = new Stub();
		assertNotNull(mock);
		assertSafeDefault(mock.getBlocks());
	}

	private static class Stub implements PaletteBaseMock
	{
	}
}
