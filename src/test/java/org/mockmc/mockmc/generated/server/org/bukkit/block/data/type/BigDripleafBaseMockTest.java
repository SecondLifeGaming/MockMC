// Auto-generated mechanical sanity test for BigDripleafBaseMock
package org.mockmc.mockmc.generated.server.org.bukkit.block.data.type;

import static org.junit.jupiter.api.Assertions.*;

import org.bukkit.block.data.type.BigDripleaf;
import org.junit.jupiter.api.Test;
import org.mockmc.mockmc.generated.GeneratedTestBase;

class BigDripleafBaseMockTest extends GeneratedTestBase
{
	@Test
	void testSafeDefaults()
	{
		BigDripleafBaseMock mock = new Stub();
		assertNotNull(mock);
		assertSafeDefault(mock.getTilt());
	}

	private static class Stub implements BigDripleafBaseMock
	{
		@Override
		public BigDripleaf clone()
		{
			return null;
		}
	}
}
