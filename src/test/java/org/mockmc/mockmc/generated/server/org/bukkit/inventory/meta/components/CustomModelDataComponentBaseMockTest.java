// Auto-generated mechanical sanity test for CustomModelDataComponentBaseMock
package org.mockmc.mockmc.generated.server.org.bukkit.inventory.meta.components;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.mockmc.mockmc.generated.GeneratedTestBase;

class CustomModelDataComponentBaseMockTest extends GeneratedTestBase
{
	@Test
	void testSafeDefaults()
	{
		CustomModelDataComponentBaseMock mock = new Stub();
		assertNotNull(mock);
		assertSafeDefault(mock.getStrings());
		assertSafeDefault(mock.getFlags());
		assertSafeDefault(mock.getColors());
		assertSafeDefault(mock.getFloats());
	}

	private static class Stub implements CustomModelDataComponentBaseMock
	{
	}
}
