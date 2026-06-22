// Auto-generated mechanical sanity test for BeehiveBaseMock
package org.mockmc.mockmc.generated.server.org.bukkit.block.data.type;

import static org.junit.jupiter.api.Assertions.*;

import org.bukkit.block.data.type.Beehive;
import org.junit.jupiter.api.Test;
import org.mockmc.mockmc.generated.GeneratedTestBase;

@SuppressWarnings("all")
class BeehiveBaseMockTest extends GeneratedTestBase
{
	@Test
	void testSafeDefaults()
	{
		BeehiveBaseMock mock = new Stub();
		assertNotNull(mock);
	}

	private static class Stub implements BeehiveBaseMock
	{
		@Override
		public Beehive clone()
		{
			return null;
		}
	}
}
