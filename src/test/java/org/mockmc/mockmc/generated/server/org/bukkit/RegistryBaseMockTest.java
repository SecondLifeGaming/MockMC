// Auto-generated mechanical sanity test for RegistryBaseMock
package org.mockmc.mockmc.generated.server.org.bukkit;

import static org.junit.jupiter.api.Assertions.*;

import org.bukkit.Keyed;
import org.junit.jupiter.api.Test;
import org.mockmc.mockmc.generated.GeneratedTestBase;

class RegistryBaseMockTest extends GeneratedTestBase
{
	@Test
	void testSafeDefaults()
	{
		RegistryBaseMock<?> mock = new Stub<>();
		assertNotNull(mock);
		assertSafeDefault(mock.stream());
		assertSafeDefault(mock.getTags());
		assertSafeDefault(mock.keyStream());
	}

	private static class Stub<T extends Keyed> implements RegistryBaseMock<T>
	{
	}
}
