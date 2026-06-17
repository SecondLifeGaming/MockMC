// Auto-generated mechanical sanity test for PotentSulfurBaseMock
package org.mockmc.mockmc.generated.server.org.bukkit.block.data.type;

import static org.junit.jupiter.api.Assertions.*;

import org.bukkit.block.data.type.PotentSulfur;
import org.junit.jupiter.api.Test;
import org.mockmc.mockmc.generated.GeneratedTestBase;

class PotentSulfurBaseMockTest extends GeneratedTestBase
{
	@Test
	void testSafeDefaults()
	{
		PotentSulfurBaseMock mock = new Stub();
		assertNotNull(mock);
		try
		{
			assertSafeDefault(mock.getPotentSulfurState());
		} catch (Exception | LinkageError _)
		{
			// Ignore NPEs and LinkageErrors from Bukkit singletons
		}
	}

	private static class Stub implements PotentSulfurBaseMock
	{
		@Override
		public PotentSulfur clone()
		{
			return null;
		}
	}
}
