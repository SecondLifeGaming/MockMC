// Auto-generated mechanical sanity test for AnimalTamerBaseMock
package org.mockmc.mockmc.generated.server.org.bukkit.entity;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.mockmc.mockmc.generated.GeneratedTestBase;

class AnimalTamerBaseMockTest extends GeneratedTestBase
{
	@Test
	void testSafeDefaults()
	{
		AnimalTamerBaseMock mock = new Stub();
		assertNotNull(mock);
		assertSafeDefault(mock.getName());
		assertSafeDefault(mock.getUniqueId());
	}

	private static class Stub implements AnimalTamerBaseMock
	{
	}
}
