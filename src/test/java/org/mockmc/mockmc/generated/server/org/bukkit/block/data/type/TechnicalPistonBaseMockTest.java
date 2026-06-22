// Auto-generated mechanical sanity test for TechnicalPistonBaseMock
package org.mockmc.mockmc.generated.server.org.bukkit.block.data.type;

import static org.junit.jupiter.api.Assertions.*;

import org.bukkit.block.data.type.TechnicalPiston;
import org.junit.jupiter.api.Test;
import org.mockmc.mockmc.generated.GeneratedTestBase;

@SuppressWarnings("all")
class TechnicalPistonBaseMockTest extends GeneratedTestBase
{
	@Test
	void testSafeDefaults()
	{
		TechnicalPistonBaseMock mock = new Stub();
		assertNotNull(mock);
		try
		{
			assertSafeDefault(mock.getType());
		} catch (Exception | LinkageError _)
		{
			// Ignore NPEs and LinkageErrors from Bukkit singletons
		}
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
