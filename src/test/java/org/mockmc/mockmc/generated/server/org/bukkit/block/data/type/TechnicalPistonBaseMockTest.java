// Auto-generated mechanical sanity test for TechnicalPistonBaseMock
package org.mockmc.mockmc.generated.server.org.bukkit.block.data.type;

import static org.junit.jupiter.api.Assertions.*;

import org.bukkit.block.data.type.TechnicalPiston;
import org.junit.jupiter.api.Test;
import org.mockmc.mockmc.generated.GeneratedTestBase;

class TechnicalPistonBaseMockTest extends GeneratedTestBase
{
	@Test
	void testSafeDefaults()
	{
		TechnicalPistonBaseMock mock = new Stub();
		assertNotNull(mock);
		assertSafeDefault(mock.getType());
	}

	private static class Stub implements TechnicalPistonBaseMock
	{
		@Override
		public TechnicalPiston clone()
		{
			return null;
		}
	}
}
