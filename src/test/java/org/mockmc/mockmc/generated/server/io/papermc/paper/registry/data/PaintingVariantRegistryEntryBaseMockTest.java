// Auto-generated mechanical sanity test for PaintingVariantRegistryEntryBaseMock
package org.mockmc.mockmc.generated.server.io.papermc.paper.registry.data;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.mockmc.mockmc.generated.GeneratedTestBase;

class PaintingVariantRegistryEntryBaseMockTest extends GeneratedTestBase
{
	@Test
	void testSafeDefaults()
	{
		PaintingVariantRegistryEntryBaseMock mock = new Stub();
		assertNotNull(mock);
		assertSafeDefault(mock.title());
		assertSafeDefault(mock.author());
		assertSafeDefault(mock.assetId());
	}

	private static class Stub implements PaintingVariantRegistryEntryBaseMock
	{
	}
}
