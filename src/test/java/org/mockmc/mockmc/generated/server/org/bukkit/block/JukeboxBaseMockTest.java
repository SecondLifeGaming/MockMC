// Auto-generated mechanical sanity test for JukeboxBaseMock
package org.mockmc.mockmc.generated.server.org.bukkit.block;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.mockmc.mockmc.generated.GeneratedTestBase;

class JukeboxBaseMockTest extends GeneratedTestBase
{
	@Test
	void testSafeDefaults()
	{
		JukeboxBaseMock mock = new Stub();
		assertNotNull(mock);
		assertSafeDefault(mock.getInventory());
		assertSafeDefault(mock.getSnapshotInventory());
		assertSafeDefault(mock.getPlaying());
		assertSafeDefault(mock.getRecord());
	}

	private static class Stub implements JukeboxBaseMock
	{
	}
}
