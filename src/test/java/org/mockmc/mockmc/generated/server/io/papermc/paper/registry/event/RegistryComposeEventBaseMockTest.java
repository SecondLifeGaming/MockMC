// Auto-generated mechanical sanity test for RegistryComposeEventBaseMock
package org.mockmc.mockmc.generated.server.io.papermc.paper.registry.event;

import static org.junit.jupiter.api.Assertions.*;

import io.papermc.paper.registry.RegistryBuilder;
import org.junit.jupiter.api.Test;
import org.mockmc.mockmc.generated.GeneratedTestBase;

class RegistryComposeEventBaseMockTest extends GeneratedTestBase
{
	@Test
	void testSafeDefaults()
	{
		RegistryComposeEventBaseMock<?, ?> mock = new Stub<>();
		assertNotNull(mock);
		assertSafeDefault(mock.registry());
	}

	private static class Stub<T, B extends RegistryBuilder<T>> implements RegistryComposeEventBaseMock<T, B>
	{
	}
}
