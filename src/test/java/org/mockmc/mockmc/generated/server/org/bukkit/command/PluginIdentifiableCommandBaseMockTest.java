// Auto-generated mechanical sanity test for PluginIdentifiableCommandBaseMock
package org.mockmc.mockmc.generated.server.org.bukkit.command;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.mockmc.mockmc.generated.GeneratedTestBase;

class PluginIdentifiableCommandBaseMockTest extends GeneratedTestBase
{
	@Test
	void testSafeDefaults()
	{
		PluginIdentifiableCommandBaseMock mock = new Stub();
		assertNotNull(mock);
		assertSafeDefault(mock.getPlugin());
	}

	private static class Stub implements PluginIdentifiableCommandBaseMock
	{
	}
}
