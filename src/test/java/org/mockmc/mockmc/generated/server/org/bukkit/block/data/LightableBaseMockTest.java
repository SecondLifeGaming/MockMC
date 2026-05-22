// Auto-generated mechanical sanity test for LightableBaseMock
package org.mockmc.mockmc.generated.server.org.bukkit.block.data;

import static org.junit.jupiter.api.Assertions.*;

import org.bukkit.block.data.Lightable;
import org.junit.jupiter.api.Test;
import org.mockmc.mockmc.generated.GeneratedTestBase;

class LightableBaseMockTest extends GeneratedTestBase
{
	@Test
	void testSafeDefaults()
	{
		LightableBaseMock mock = new Stub();
		assertNotNull(mock);
	}

	private static class Stub implements LightableBaseMock
	{
		@Override
		public Lightable clone()
		{
			return null;
		}
	}
}
