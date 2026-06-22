// Auto-generated mechanical sanity test for JukeboxBaseMock
package org.mockmc.mockmc.generated.server.org.bukkit.block.data.type;

import static org.junit.jupiter.api.Assertions.*;

import org.bukkit.block.data.type.Jukebox;
import org.junit.jupiter.api.Test;
import org.mockmc.mockmc.generated.GeneratedTestBase;

@SuppressWarnings("all")
class JukeboxBaseMockTest extends GeneratedTestBase
{
	@Test
	void testSafeDefaults()
	{
		JukeboxBaseMock mock = new Stub();
		assertNotNull(mock);
	}

	private static class Stub implements JukeboxBaseMock
	{
		@Override
		public Jukebox clone()
		{
			return null;
		}
	}
}
