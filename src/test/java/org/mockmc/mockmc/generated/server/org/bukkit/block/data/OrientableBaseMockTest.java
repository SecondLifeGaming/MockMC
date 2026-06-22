// Auto-generated mechanical sanity test for OrientableBaseMock
package org.mockmc.mockmc.generated.server.org.bukkit.block.data;

import static org.junit.jupiter.api.Assertions.*;

import org.bukkit.block.data.Orientable;
import org.junit.jupiter.api.Test;
import org.mockmc.mockmc.generated.GeneratedTestBase;

@SuppressWarnings("all")
class OrientableBaseMockTest extends GeneratedTestBase
{
	@Test
	void testSafeDefaults()
	{
		OrientableBaseMock mock = new Stub();
		assertNotNull(mock);
		try
		{
			assertSafeDefault(mock.getAxis());
		} catch (Exception | LinkageError _)
		{
			// Ignore NPEs and LinkageErrors from Bukkit singletons
		}
		try
		{
			assertSafeDefault(mock.getAxes());
		} catch (Exception | LinkageError _)
		{
			// Ignore NPEs and LinkageErrors from Bukkit singletons
		}
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
