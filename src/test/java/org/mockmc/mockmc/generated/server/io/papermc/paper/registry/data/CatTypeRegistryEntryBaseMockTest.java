// Auto-generated mechanical sanity test for CatTypeRegistryEntryBaseMock
package org.mockmc.mockmc.generated.server.io.papermc.paper.registry.data;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.mockmc.mockmc.generated.GeneratedTestBase;

class CatTypeRegistryEntryBaseMockTest extends GeneratedTestBase
{
	@Test
	void testSafeDefaults()
	{
		CatTypeRegistryEntryBaseMock mock = new Stub();
		assertNotNull(mock);
		assertSafeDefault(mock.clientTextureAsset());
		assertSafeDefault(mock.babyClientTextureAsset());
	}

	private static class Stub implements CatTypeRegistryEntryBaseMock
	{
	}
}
