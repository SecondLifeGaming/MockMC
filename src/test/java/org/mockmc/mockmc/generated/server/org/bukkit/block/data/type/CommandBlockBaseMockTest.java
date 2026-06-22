// Auto-generated mechanical sanity test for CommandBlockBaseMock
package org.mockmc.mockmc.generated.server.org.bukkit.block.data.type;

import static org.junit.jupiter.api.Assertions.*;

import org.bukkit.block.data.type.CommandBlock;
import org.junit.jupiter.api.Test;
import org.mockmc.mockmc.generated.GeneratedTestBase;

@SuppressWarnings("all")
class CommandBlockBaseMockTest extends GeneratedTestBase
{
	@Test
	void testSafeDefaults()
	{
		CommandBlockBaseMock mock = new Stub();
		assertNotNull(mock);
	}

	private static class Stub implements CommandBlockBaseMock
	{
		@Override
		public CommandBlock clone()
		{
			return null;
		}
	}
}
