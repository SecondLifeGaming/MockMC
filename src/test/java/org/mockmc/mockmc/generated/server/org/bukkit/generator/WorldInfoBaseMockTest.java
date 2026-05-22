// Auto-generated mechanical sanity test for WorldInfoBaseMock
package org.mockmc.mockmc.generated.server.org.bukkit.generator;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.mockmc.mockmc.generated.GeneratedTestBase;

class WorldInfoBaseMockTest extends GeneratedTestBase
{
	@Test
	void testSafeDefaults()
	{
		WorldInfoBaseMock mock = new Stub();
		assertNotNull(mock);
		assertSafeDefault(mock.getName());
		assertSafeDefault(mock.getUID());
		assertSafeDefault(mock.getEnvironment());
		assertSafeDefault(mock.vanillaBiomeProvider());
	}

	private static class Stub implements WorldInfoBaseMock
	{
	}
}
