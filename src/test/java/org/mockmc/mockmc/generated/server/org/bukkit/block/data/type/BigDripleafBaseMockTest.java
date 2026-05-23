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
		try
		{
			assertSafeDefault(mock.getTilt());
		} catch (Exception | LinkageError e)
		{
			// Ignore NPEs and LinkageErrors from Bukkit singletons
		}
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
