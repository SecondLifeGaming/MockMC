// Auto-generated mechanical sanity test for FireBaseMock
package org.mockmc.mockmc.generated.server.org.bukkit.block.data.type;

import static org.junit.jupiter.api.Assertions.*;

import org.bukkit.block.data.type.Fire;
import org.junit.jupiter.api.Test;
import org.mockmc.mockmc.generated.GeneratedTestBase;

class FireBaseMockTest extends GeneratedTestBase
{
	@Test
	void testSafeDefaults()
	{
		FireBaseMock mock = new Stub();
		assertNotNull(mock);
	}

	private static class Stub implements FireBaseMock
	{
		@Override
		public Fire clone()
		{
			return null;
		}
	}
}
