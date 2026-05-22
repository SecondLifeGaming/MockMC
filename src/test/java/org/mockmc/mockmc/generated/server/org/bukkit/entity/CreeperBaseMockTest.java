// Auto-generated mechanical sanity test for CreeperBaseMock
package org.mockmc.mockmc.generated.server.org.bukkit.entity;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.mockmc.mockmc.generated.GeneratedTestBase;

class CreeperBaseMockTest extends GeneratedTestBase
{
	@Test
	void testSafeDefaults()
	{
		CreeperBaseMock mock = new Stub();
		assertNotNull(mock);
		assertSafeDefault(mock.getIgniter());
	}

	private static class Stub implements CreeperBaseMock
	{
	}
}
