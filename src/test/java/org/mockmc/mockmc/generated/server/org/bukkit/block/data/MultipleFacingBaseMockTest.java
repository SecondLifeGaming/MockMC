// Auto-generated mechanical sanity test for MultipleFacingBaseMock
package org.mockmc.mockmc.generated.server.org.bukkit.block.data;

import static org.junit.jupiter.api.Assertions.*;

import org.bukkit.block.data.MultipleFacing;
import org.junit.jupiter.api.Test;
import org.mockmc.mockmc.generated.GeneratedTestBase;

@SuppressWarnings("all")
class MultipleFacingBaseMockTest extends GeneratedTestBase
{
	@Test
	void testSafeDefaults()
	{
		MultipleFacingBaseMock mock = new Stub();
		assertNotNull(mock);
		try
		{
			assertSafeDefault(mock.getFaces());
		} catch (Exception | LinkageError _)
		{
			// Ignore NPEs and LinkageErrors from Bukkit singletons
		}
		try
		{
			assertSafeDefault(mock.getAllowedFaces());
		} catch (Exception | LinkageError _)
		{
			// Ignore NPEs and LinkageErrors from Bukkit singletons
		}
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
