// Auto-generated mechanical sanity test for ZombieNautilusVariantRegistryEntryBaseMock
package org.mockmc.mockmc.generated.server.io.papermc.paper.registry.data;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.mockmc.mockmc.generated.GeneratedTestBase;

class ZombieNautilusVariantRegistryEntryBaseMockTest extends GeneratedTestBase
{
	@Test
	void testSafeDefaults()
	{
		ZombieNautilusVariantRegistryEntryBaseMock mock = new Stub();
		assertNotNull(mock);
		assertSafeDefault(mock.model());
		assertSafeDefault(mock.clientTextureAsset());
	}

	private static class Stub implements ZombieNautilusVariantRegistryEntryBaseMock
	{
	}
}
