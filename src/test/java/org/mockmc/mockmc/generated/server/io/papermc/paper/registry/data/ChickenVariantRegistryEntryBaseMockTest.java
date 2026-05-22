// Auto-generated mechanical sanity test for ChickenVariantRegistryEntryBaseMock
package org.mockmc.mockmc.generated.server.io.papermc.paper.registry.data;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.mockmc.mockmc.generated.GeneratedTestBase;

class ChickenVariantRegistryEntryBaseMockTest extends GeneratedTestBase
{
	@Test
	void testSafeDefaults()
	{
		ChickenVariantRegistryEntryBaseMock mock = new Stub();
		assertNotNull(mock);
		assertSafeDefault(mock.model());
		assertSafeDefault(mock.clientTextureAsset());
		assertSafeDefault(mock.babyClientTextureAsset());
	}

	private static class Stub implements ChickenVariantRegistryEntryBaseMock
	{
	}
}
