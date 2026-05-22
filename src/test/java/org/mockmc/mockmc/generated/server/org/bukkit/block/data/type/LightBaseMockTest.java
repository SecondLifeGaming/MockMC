// Auto-generated mechanical sanity test for LightBaseMock
package org.mockmc.mockmc.generated.server.org.bukkit.block.data.type;

import static org.junit.jupiter.api.Assertions.*;

import org.bukkit.block.data.type.Light;
import org.junit.jupiter.api.Test;
import org.mockmc.mockmc.generated.GeneratedTestBase;

class LightBaseMockTest extends GeneratedTestBase
{
	@Test
	void testSafeDefaults()
	{
		LightBaseMock mock = new Stub();
		assertNotNull(mock);
	}

	private static class Stub implements LightBaseMock
	{
		@Override
		public Light clone()
		{
			return null;
		}
	}
}
