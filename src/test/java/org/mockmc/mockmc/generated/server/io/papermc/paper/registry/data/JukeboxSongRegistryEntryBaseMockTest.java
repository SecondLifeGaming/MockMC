// Auto-generated mechanical sanity test for JukeboxSongRegistryEntryBaseMock
package org.mockmc.mockmc.generated.server.io.papermc.paper.registry.data;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.mockmc.mockmc.generated.GeneratedTestBase;

class JukeboxSongRegistryEntryBaseMockTest extends GeneratedTestBase
{
	@Test
	void testSafeDefaults()
	{
		JukeboxSongRegistryEntryBaseMock mock = new Stub();
		assertNotNull(mock);
		assertSafeDefault(mock.description());
		assertSafeDefault(mock.soundEvent());
	}

	private static class Stub implements JukeboxSongRegistryEntryBaseMock
	{
	}
}
