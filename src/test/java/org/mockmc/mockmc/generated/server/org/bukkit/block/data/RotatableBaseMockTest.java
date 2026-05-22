// Auto-generated mechanical sanity test for RotatableBaseMock
package org.mockmc.mockmc.generated.server.org.bukkit.block.data;

import static org.junit.jupiter.api.Assertions.*;

import org.bukkit.block.data.Rotatable;
import org.junit.jupiter.api.Test;
import org.mockmc.mockmc.generated.GeneratedTestBase;

class RotatableBaseMockTest extends GeneratedTestBase
{
	@Test
	void testSafeDefaults()
	{
		RotatableBaseMock mock = new Stub();
		assertNotNull(mock);
		assertSafeDefault(mock.getRotation());
	}

	private static class Stub implements RotatableBaseMock
	{
		@Override
		public Rotatable clone()
		{
			return null;
		}
	}
}
