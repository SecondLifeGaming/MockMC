// Auto-generated mechanical sanity test for MultipleFacingBaseMock
package org.mockmc.mockmc.generated.server.org.bukkit.block.data;

import static org.junit.jupiter.api.Assertions.*;

import org.bukkit.block.data.MultipleFacing;
import org.junit.jupiter.api.Test;
import org.mockmc.mockmc.generated.GeneratedTestBase;

class MultipleFacingBaseMockTest extends GeneratedTestBase
{
	@Test
	void testSafeDefaults()
	{
		MultipleFacingBaseMock mock = new Stub();
		assertNotNull(mock);
		assertSafeDefault(mock.getFaces());
		assertSafeDefault(mock.getAllowedFaces());
	}

	private static class Stub implements MultipleFacingBaseMock
	{
		@Override
		public MultipleFacing clone()
		{
			return null;
		}
	}
}
