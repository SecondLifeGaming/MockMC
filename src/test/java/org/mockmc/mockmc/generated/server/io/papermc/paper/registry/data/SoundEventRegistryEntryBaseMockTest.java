// Auto-generated mechanical sanity test for SoundEventRegistryEntryBaseMock
package org.mockmc.mockmc.generated.server.io.papermc.paper.registry.data;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.mockmc.mockmc.generated.GeneratedTestBase;

class SoundEventRegistryEntryBaseMockTest extends GeneratedTestBase
{
	@Test
	void testSafeDefaults()
	{
		SoundEventRegistryEntryBaseMock mock = new Stub();
		assertNotNull(mock);
		assertSafeDefault(mock.location());
		assertSafeDefault(mock.fixedRange());
	}

	private static class Stub implements SoundEventRegistryEntryBaseMock
	{
	}
}
