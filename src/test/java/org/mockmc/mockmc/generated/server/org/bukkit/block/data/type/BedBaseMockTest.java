// Auto-generated mechanical sanity test for BedBaseMock
package org.mockmc.mockmc.generated.server.org.bukkit.block.data.type;

import static org.junit.jupiter.api.Assertions.*;

import org.bukkit.block.data.type.Bed;
import org.junit.jupiter.api.Test;
import org.mockmc.mockmc.generated.GeneratedTestBase;

class BedBaseMockTest extends GeneratedTestBase
{
	@Test
	void testSafeDefaults()
	{
		BedBaseMock mock = new Stub();
		assertNotNull(mock);
		assertSafeDefault(mock.getPart());
	}

	private static class Stub implements BedBaseMock
	{
		@Override
		public Bed clone()
		{
			return null;
		}
	}
}
