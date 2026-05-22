// Auto-generated mechanical sanity test for HorseBaseMock
package org.mockmc.mockmc.generated.server.org.bukkit.entity;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.mockmc.mockmc.generated.GeneratedTestBase;

class HorseBaseMockTest extends GeneratedTestBase
{
	@Test
	void testSafeDefaults()
	{
		HorseBaseMock mock = new Stub();
		assertNotNull(mock);
		assertSafeDefault(mock.getColor());
		assertSafeDefault(mock.getInventory());
		assertSafeDefault(mock.getStyle());
	}

	private static class Stub implements HorseBaseMock
	{
	}
}
