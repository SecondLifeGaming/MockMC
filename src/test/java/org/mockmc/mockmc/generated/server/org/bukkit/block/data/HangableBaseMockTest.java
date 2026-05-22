// Auto-generated mechanical sanity test for HangableBaseMock
package org.mockmc.mockmc.generated.server.org.bukkit.block.data;

import static org.junit.jupiter.api.Assertions.*;

import org.bukkit.block.data.Hangable;
import org.junit.jupiter.api.Test;
import org.mockmc.mockmc.generated.GeneratedTestBase;

class HangableBaseMockTest extends GeneratedTestBase
{
	@Test
	void testSafeDefaults()
	{
		HangableBaseMock mock = new Stub();
		assertNotNull(mock);
	}

	private static class Stub implements HangableBaseMock
	{
		@Override
		public Hangable clone()
		{
			return null;
		}
	}
}
