// Auto-generated mechanical sanity test for BannerPatternRegistryEntryBaseMock
package org.mockmc.mockmc.generated.server.io.papermc.paper.registry.data;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.mockmc.mockmc.generated.GeneratedTestBase;

class BannerPatternRegistryEntryBaseMockTest extends GeneratedTestBase
{
	@Test
	void testSafeDefaults()
	{
		BannerPatternRegistryEntryBaseMock mock = new Stub();
		assertNotNull(mock);
		assertSafeDefault(mock.assetId());
		assertSafeDefault(mock.translationKey());
	}

	private static class Stub implements BannerPatternRegistryEntryBaseMock
	{
	}
}
