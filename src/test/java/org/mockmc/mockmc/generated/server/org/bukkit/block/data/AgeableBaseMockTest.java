// Auto-generated mechanical sanity test for AgeableBaseMock
package org.mockmc.mockmc.generated.server.org.bukkit.block.data;

import static org.junit.jupiter.api.Assertions.*;

import org.bukkit.block.data.Ageable;
import org.junit.jupiter.api.Test;
import org.mockmc.mockmc.generated.GeneratedTestBase;

class AgeableBaseMockTest extends GeneratedTestBase
{
	@Test
	void testSafeDefaults()
	{
		AgeableBaseMock mock = new Stub();
		assertNotNull(mock);
	}

	private static class Stub implements AgeableBaseMock
	{
		@Override
		public Ageable clone()
		{
			return null;
		}
	}
}
