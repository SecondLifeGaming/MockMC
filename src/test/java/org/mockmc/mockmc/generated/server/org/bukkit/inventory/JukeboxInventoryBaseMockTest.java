// Auto-generated mechanical sanity test for JukeboxInventoryBaseMock
package org.mockmc.mockmc.generated.server.org.bukkit.inventory;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.mockmc.mockmc.generated.GeneratedTestBase;

class JukeboxInventoryBaseMockTest extends GeneratedTestBase
{
	@Test
	void testSafeDefaults()
	{
		JukeboxInventoryBaseMock mock = new Stub();
		assertNotNull(mock);
		assertSafeDefault(mock.getHolder());
		assertSafeDefault(mock.getRecord());
	}

	private static class Stub implements JukeboxInventoryBaseMock
	{
	}
}
