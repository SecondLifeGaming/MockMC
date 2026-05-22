// Auto-generated mechanical sanity test for MusicInstrumentBaseMock
package org.mockmc.mockmc.generated.server.org.bukkit;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.mockmc.mockmc.generated.GeneratedTestBase;

@SuppressWarnings(
{"deprecation", "removal", "java:S1874"})
class MusicInstrumentBaseMockTest extends GeneratedTestBase
{
	@Test
	void testSafeDefaults()
	{
		MusicInstrumentBaseMock mock = new Stub();
		assertNotNull(mock);
		assertSafeDefault(mock.getKey());
		assertSafeDefault(mock.key());
		assertSafeDefault(mock.description());
		assertSafeDefault(mock.getSound());
		assertSafeDefault(mock.translationKey());
	}

	private static class Stub implements MusicInstrumentBaseMock
	{
	}
}
