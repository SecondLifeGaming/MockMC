// Auto-generated mechanical sanity test for TileStateBaseMock
package org.mockmc.mockmc.generated.server.org.bukkit.block;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.mockmc.mockmc.generated.GeneratedTestBase;

class TileStateBaseMockTest extends GeneratedTestBase
{
	@Test
	void testSafeDefaults()
	{
		TileStateBaseMock mock = new Stub();
		assertNotNull(mock);
		assertSafeDefault(mock.getPersistentDataContainer());
	}

	private static class Stub implements TileStateBaseMock
	{
	}
}
