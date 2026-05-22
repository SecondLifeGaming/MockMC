// Auto-generated mechanical sanity test for CrafterBaseMock
package org.mockmc.mockmc.generated.server.org.bukkit.block.data.type;

import static org.junit.jupiter.api.Assertions.*;

import org.bukkit.block.data.type.Crafter;
import org.junit.jupiter.api.Test;
import org.mockmc.mockmc.generated.GeneratedTestBase;

class CrafterBaseMockTest extends GeneratedTestBase
{
	@Test
	void testSafeDefaults()
	{
		CrafterBaseMock mock = new Stub();
		assertNotNull(mock);
		assertSafeDefault(mock.getOrientation());
	}

	private static class Stub implements CrafterBaseMock
	{
		@Override
		public Crafter clone()
		{
			return null;
		}
	}
}
