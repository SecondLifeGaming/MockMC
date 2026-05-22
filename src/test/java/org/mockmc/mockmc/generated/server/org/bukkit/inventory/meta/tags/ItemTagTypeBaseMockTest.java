// Auto-generated mechanical sanity test for ItemTagTypeBaseMock
package org.mockmc.mockmc.generated.server.org.bukkit.inventory.meta.tags;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.mockmc.mockmc.generated.GeneratedTestBase;

@SuppressWarnings(
{"deprecation", "removal", "java:S1874"})
class ItemTagTypeBaseMockTest extends GeneratedTestBase
{
	@Test
	void testSafeDefaults()
	{
		ItemTagTypeBaseMock<?, ?> mock = new Stub<>();
		assertNotNull(mock);
		assertSafeDefault(mock.getPrimitiveType());
		assertSafeDefault(mock.getComplexType());
	}

	private static class Stub<T, Z> implements ItemTagTypeBaseMock<T, Z>
	{
	}
}
