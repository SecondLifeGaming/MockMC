// Auto-generated mechanical sanity test for JukeboxSongBaseMock
package org.mockmc.mockmc.generated.server.org.bukkit;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.mockmc.mockmc.generated.GeneratedTestBase;

@SuppressWarnings(
{"deprecation", "removal", "java:S1874"})
class JukeboxSongBaseMockTest extends GeneratedTestBase
{
	@Test
	void testSafeDefaults()
	{
		JukeboxSongBaseMock mock = new Stub();
		assertNotNull(mock);
		assertSafeDefault(mock.getSound());
		assertSafeDefault(mock.getTranslationKey());
		assertSafeDefault(mock.getDescription());
	}

	private static class Stub implements JukeboxSongBaseMock
	{
	}
}
