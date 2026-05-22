// Auto-generated mechanical sanity test for WolfVariantRegistryEntryBaseMock
package org.mockmc.mockmc.generated.server.io.papermc.paper.registry.data;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.mockmc.mockmc.generated.GeneratedTestBase;

class WolfVariantRegistryEntryBaseMockTest extends GeneratedTestBase
{
	@Test
	void testSafeDefaults()
	{
		WolfVariantRegistryEntryBaseMock mock = new Stub();
		assertNotNull(mock);
		assertSafeDefault(mock.angryClientTextureAsset());
		assertSafeDefault(mock.wildClientTextureAsset());
		assertSafeDefault(mock.tameClientTextureAsset());
		assertSafeDefault(mock.babyAngryClientTextureAsset());
		assertSafeDefault(mock.babyWildClientTextureAsset());
		assertSafeDefault(mock.babyTameClientTextureAsset());
	}

	private static class Stub implements WolfVariantRegistryEntryBaseMock
	{
	}
}
