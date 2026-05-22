// Auto-generated mechanical sanity test for SkullBaseMock
package org.mockmc.mockmc.generated.server.org.bukkit.block.data.type;

import static org.junit.jupiter.api.Assertions.*;

import org.bukkit.block.data.type.Skull;
import org.junit.jupiter.api.Test;
import org.mockmc.mockmc.generated.GeneratedTestBase;

class SkullBaseMockTest extends GeneratedTestBase
{
	@Test
	void testSafeDefaults()
	{
		SkullBaseMock mock = new Stub();
		assertNotNull(mock);
	}

	private static class Stub implements SkullBaseMock
	{
		@Override
		public Skull clone()
		{
			return null;
		}
	}
}
