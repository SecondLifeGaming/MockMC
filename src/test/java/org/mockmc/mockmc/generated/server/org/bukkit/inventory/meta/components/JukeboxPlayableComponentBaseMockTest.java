// Auto-generated mechanical sanity test for JukeboxPlayableComponentBaseMock
package org.mockmc.mockmc.generated.server.org.bukkit.inventory.meta.components;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.mockmc.mockmc.generated.GeneratedTestBase;

class JukeboxPlayableComponentBaseMockTest extends GeneratedTestBase
{
	@Test
	void testSafeDefaults()
	{
		JukeboxPlayableComponentBaseMock mock = new Stub();
		assertNotNull(mock);
		assertSafeDefault(mock.getSong());
		assertSafeDefault(mock.getSongKey());
	}

	private static class Stub implements JukeboxPlayableComponentBaseMock
	{
	}
}
