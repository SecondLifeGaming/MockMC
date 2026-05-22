// Auto-generated mechanical sanity test for OrientableBaseMock
package org.mockmc.mockmc.generated.server.org.bukkit.block.data;

import static org.junit.jupiter.api.Assertions.*;

import org.bukkit.block.data.Orientable;
import org.junit.jupiter.api.Test;
import org.mockmc.mockmc.generated.GeneratedTestBase;

class OrientableBaseMockTest extends GeneratedTestBase
{
	@Test
	void testSafeDefaults()
	{
		OrientableBaseMock mock = new Stub();
		assertNotNull(mock);
		assertSafeDefault(mock.getAxes());
		assertSafeDefault(mock.getAxis());
	}

	private static class Stub implements OrientableBaseMock
	{
		@Override
		public Orientable clone()
		{
			return null;
		}
	}
}
