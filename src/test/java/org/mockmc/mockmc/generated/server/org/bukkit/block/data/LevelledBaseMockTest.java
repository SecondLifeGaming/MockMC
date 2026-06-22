// Auto-generated mechanical sanity test for LevelledBaseMock
package org.mockmc.mockmc.generated.server.org.bukkit.block.data;

import static org.junit.jupiter.api.Assertions.*;

import org.bukkit.block.data.Levelled;
import org.junit.jupiter.api.Test;
import org.mockmc.mockmc.generated.GeneratedTestBase;

@SuppressWarnings("all")
class LevelledBaseMockTest extends GeneratedTestBase
{
	@Test
	void testSafeDefaults()
	{
		LevelledBaseMock mock = new Stub();
		assertNotNull(mock);
	}

	private static class Stub implements LevelledBaseMock
	{
		@Override
		public Levelled clone()
		{
			return null;
		}
	}
}
