// Auto-generated mechanical sanity test for WritableRegistryBaseMock
package org.mockmc.mockmc.generated.server.io.papermc.paper.registry.event;

import static org.junit.jupiter.api.Assertions.*;

import io.papermc.paper.registry.RegistryBuilder;
import org.junit.jupiter.api.Test;
import org.mockmc.mockmc.generated.GeneratedTestBase;

class WritableRegistryBaseMockTest extends GeneratedTestBase
{
	@Test
	void testSafeDefaults()
	{
		WritableRegistryBaseMock<?, ?> mock = new Stub<>();
		assertNotNull(mock);
	}

	private static class Stub<T, B extends RegistryBuilder<T>> implements WritableRegistryBaseMock<T, B>
	{
	}
}
