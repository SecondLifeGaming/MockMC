// Auto-generated mechanical sanity test for InstrumentRegistryEntryBaseMock
package org.mockmc.mockmc.generated.server.io.papermc.paper.registry.data;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.mockmc.mockmc.generated.GeneratedTestBase;

class InstrumentRegistryEntryBaseMockTest extends GeneratedTestBase
{
	@Test
	void testSafeDefaults()
	{
		InstrumentRegistryEntryBaseMock mock = new Stub();
		assertNotNull(mock);
		assertSafeDefault(mock.description());
		assertSafeDefault(mock.soundEvent());
	}

	private static class Stub implements InstrumentRegistryEntryBaseMock
	{
	}
}
