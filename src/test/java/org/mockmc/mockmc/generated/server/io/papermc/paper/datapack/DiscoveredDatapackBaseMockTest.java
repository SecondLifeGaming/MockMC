// Auto-generated mechanical sanity test for DiscoveredDatapackBaseMock
package org.mockmc.mockmc.generated.server.io.papermc.paper.datapack;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.mockmc.mockmc.generated.GeneratedTestBase;

class DiscoveredDatapackBaseMockTest extends GeneratedTestBase
{
	@Test
	void testSafeDefaults()
	{
		DiscoveredDatapackBaseMock mock = new Stub();
		assertNotNull(mock);
		assertSafeDefault(mock.getName());
		assertSafeDefault(mock.getSource());
		assertSafeDefault(mock.getDescription());
		assertSafeDefault(mock.getTitle());
		assertSafeDefault(mock.getCompatibility());
		assertSafeDefault(mock.getRequiredFeatures());
	}

	private static class Stub implements DiscoveredDatapackBaseMock
	{
	}
}
