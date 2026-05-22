// Auto-generated mechanical sanity test for EnchantmentRegistryEntryBaseMock
package org.mockmc.mockmc.generated.server.io.papermc.paper.registry.data;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.mockmc.mockmc.generated.GeneratedTestBase;

class EnchantmentRegistryEntryBaseMockTest extends GeneratedTestBase
{
	@Test
	void testSafeDefaults()
	{
		EnchantmentRegistryEntryBaseMock mock = new Stub();
		assertNotNull(mock);
		assertSafeDefault(mock.description());
		assertSafeDefault(mock.exclusiveWith());
		assertSafeDefault(mock.supportedItems());
		assertSafeDefault(mock.primaryItems());
		assertSafeDefault(mock.minimumCost());
		assertSafeDefault(mock.maximumCost());
		assertSafeDefault(mock.activeSlots());
	}

	private static class Stub implements EnchantmentRegistryEntryBaseMock
	{
	}
}
