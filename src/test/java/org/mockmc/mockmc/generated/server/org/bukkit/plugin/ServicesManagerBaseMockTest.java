// Auto-generated mechanical sanity test for ServicesManagerBaseMock
package org.mockmc.mockmc.generated.server.org.bukkit.plugin;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.mockmc.mockmc.generated.GeneratedTestBase;

class ServicesManagerBaseMockTest extends GeneratedTestBase
{
	@Test
	void testSafeDefaults()
	{
		ServicesManagerBaseMock mock = new Stub();
		assertNotNull(mock);
		assertSafeDefault(mock.getKnownServices());
	}

	private static class Stub implements ServicesManagerBaseMock
	{
	}
}
