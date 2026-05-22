// Auto-generated mechanical sanity test for SnowableBaseMock
package org.mockmc.mockmc.generated.server.org.bukkit.block.data;

import static org.junit.jupiter.api.Assertions.*;

import org.bukkit.block.data.Snowable;
import org.junit.jupiter.api.Test;
import org.mockmc.mockmc.generated.GeneratedTestBase;

class SnowableBaseMockTest extends GeneratedTestBase
{
	@Test
	void testSafeDefaults()
	{
		SnowableBaseMock mock = new Stub();
		assertNotNull(mock);
	}

	private static class Stub implements SnowableBaseMock
	{
		@Override
		public Snowable clone()
		{
			return null;
		}
	}
}
