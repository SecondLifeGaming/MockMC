// Auto-generated mechanical sanity test for RegistryKeySetBaseMock
package org.mockmc.mockmc.generated.server.io.papermc.paper.registry.set;

import static org.junit.jupiter.api.Assertions.*;

import org.bukkit.Keyed;
import org.junit.jupiter.api.Test;
import org.mockmc.mockmc.generated.GeneratedTestBase;

class RegistryKeySetBaseMockTest extends GeneratedTestBase
{
	@Test
	void testSafeDefaults()
	{
		RegistryKeySetBaseMock<?> mock = new Stub<>();
		assertNotNull(mock);
		assertSafeDefault(mock.values());
		assertSafeDefault(mock.iterator());
	}

	private static class Stub<T extends Keyed> implements RegistryKeySetBaseMock<T>
	{
	}
}
