// Auto-generated mechanical sanity test for PigVariantRegistryEntryBaseMock
package org.mockmc.mockmc.generated.server.io.papermc.paper.registry.data;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.mockmc.mockmc.generated.GeneratedTestBase;

class PigVariantRegistryEntryBaseMockTest extends GeneratedTestBase
{
	@Test
	void testSafeDefaults()
	{
		PigVariantRegistryEntryBaseMock mock = new Stub();
		assertNotNull(mock);
		assertSafeDefault(mock.model());
		assertSafeDefault(mock.clientTextureAsset());
		assertSafeDefault(mock.babyClientTextureAsset());
	}

	private static class Stub implements PigVariantRegistryEntryBaseMock
	{
	}
}
