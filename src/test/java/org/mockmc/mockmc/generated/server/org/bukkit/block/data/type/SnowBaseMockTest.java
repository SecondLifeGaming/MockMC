// Auto-generated mechanical sanity test for SnowBaseMock
package org.mockmc.mockmc.generated.server.org.bukkit.block.data.type;

import static org.junit.jupiter.api.Assertions.*;

import org.bukkit.block.data.type.Snow;
import org.junit.jupiter.api.Test;
import org.mockmc.mockmc.generated.GeneratedTestBase;

class SnowBaseMockTest extends GeneratedTestBase
{
	@Test
	void testSafeDefaults()
	{
		SnowBaseMock mock = new Stub();
		assertNotNull(mock);
	}

	private static class Stub implements SnowBaseMock
	{
		@Override
		public Snow clone()
		{
			return null;
		}
	}
}
