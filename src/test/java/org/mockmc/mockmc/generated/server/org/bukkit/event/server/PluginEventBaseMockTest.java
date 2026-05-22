// Auto-generated mechanical sanity test for PluginEventBaseMock
package org.mockmc.mockmc.generated.server.org.bukkit.event.server;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.mockmc.mockmc.generated.GeneratedTestBase;

class PluginEventBaseMockTest extends GeneratedTestBase
{
	@Test
	void testSafeDefaults()
	{
		PluginEventBaseMock mock = new Stub();
		assertNotNull(mock);
		assertSafeDefault(mock.getPlugin());
	}

	private static class Stub implements PluginEventBaseMock
	{
	}
}
