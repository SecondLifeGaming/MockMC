// Auto-generated mechanical sanity test for CommandMinecartBaseMock
package org.mockmc.mockmc.generated.server.org.bukkit.entity.minecart;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.mockmc.mockmc.generated.GeneratedTestBase;

class CommandMinecartBaseMockTest extends GeneratedTestBase
{
	@Test
	void testSafeDefaults()
	{
		CommandMinecartBaseMock mock = new Stub();
		assertNotNull(mock);
		assertSafeDefault(mock.getCommand());
	}

	private static class Stub implements CommandMinecartBaseMock
	{
	}
}
