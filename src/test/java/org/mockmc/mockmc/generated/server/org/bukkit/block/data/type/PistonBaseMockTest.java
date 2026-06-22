// Auto-generated mechanical sanity test for PistonBaseMock
package org.mockmc.mockmc.generated.server.org.bukkit.block.data.type;

import static org.junit.jupiter.api.Assertions.*;

import org.bukkit.block.data.type.Piston;
import org.junit.jupiter.api.Test;
import org.mockmc.mockmc.generated.GeneratedTestBase;

@SuppressWarnings("all")
class PistonBaseMockTest extends GeneratedTestBase
{
	@Test
	void testSafeDefaults()
	{
		PistonBaseMock mock = new Stub();
		assertNotNull(mock);
	}

	private static class Stub implements PistonBaseMock
	{
		@Override
		public Piston clone()
		{
			return null;
		}
	}
}
