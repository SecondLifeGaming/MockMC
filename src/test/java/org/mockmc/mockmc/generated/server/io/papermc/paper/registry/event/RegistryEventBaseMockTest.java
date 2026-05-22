// Auto-generated mechanical sanity test for RegistryEventBaseMock
package org.mockmc.mockmc.generated.server.io.papermc.paper.registry.event;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.mockmc.mockmc.generated.GeneratedTestBase;

class RegistryEventBaseMockTest extends GeneratedTestBase
{
	@Test
	void testSafeDefaults()
	{
		RegistryEventBaseMock<?> mock = new Stub<>();
		assertNotNull(mock);
		assertSafeDefault(mock.registryKey());
	}

	private static class Stub<T> implements RegistryEventBaseMock<T>
	{
	}
}
