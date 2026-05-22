// Auto-generated mechanical sanity test for JukeboxPlayableBaseMock
package org.mockmc.mockmc.generated.server.io.papermc.paper.datacomponent.item;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.mockmc.mockmc.generated.GeneratedTestBase;

class JukeboxPlayableBaseMockTest extends GeneratedTestBase
{
	@Test
	void testSafeDefaults()
	{
		JukeboxPlayableBaseMock mock = new Stub();
		assertNotNull(mock);
		assertSafeDefault(mock.jukeboxSong());
	}

	private static class Stub implements JukeboxPlayableBaseMock
	{
	}
}
