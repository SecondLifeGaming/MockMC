// Auto-generated mechanical sanity test for RegistryEntryAddEventBaseMock
package org.mockmc.mockmc.generated.server.io.papermc.paper.registry.event;

import static org.junit.jupiter.api.Assertions.*;

import io.papermc.paper.registry.RegistryBuilder;
import org.junit.jupiter.api.Test;
import org.mockmc.mockmc.generated.GeneratedTestBase;

class RegistryEntryAddEventBaseMockTest extends GeneratedTestBase
{
	@Test
	void testSafeDefaults()
	{
		RegistryEntryAddEventBaseMock<?, ?> mock = new Stub<>();
		assertNotNull(mock);
		assertSafeDefault(mock.builder());
		assertSafeDefault(mock.key());
	}

	private static class Stub<T, B extends RegistryBuilder<T>> implements RegistryEntryAddEventBaseMock<T, B>
	{
	}
}
