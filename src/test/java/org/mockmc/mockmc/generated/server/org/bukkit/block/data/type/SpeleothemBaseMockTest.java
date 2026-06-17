// Auto-generated mechanical sanity test for SpeleothemBaseMock
package org.mockmc.mockmc.generated.server.org.bukkit.block.data.type;

import static org.junit.jupiter.api.Assertions.*;

import org.bukkit.block.data.type.Speleothem;
import org.junit.jupiter.api.Test;
import org.mockmc.mockmc.generated.GeneratedTestBase;

class SpeleothemBaseMockTest extends GeneratedTestBase
{
	@Test
	void testSafeDefaults()
	{
		SpeleothemBaseMock mock = new Stub();
		assertNotNull(mock);
		try
		{
			assertSafeDefault(mock.getVerticalDirection());
		} catch (Exception | LinkageError _)
		{
			// Ignore NPEs and LinkageErrors from Bukkit singletons
		}
		try
		{
			assertSafeDefault(mock.getVerticalDirections());
		} catch (Exception | LinkageError _)
		{
			// Ignore NPEs and LinkageErrors from Bukkit singletons
		}
		try
		{
			assertSafeDefault(mock.getThickness());
		} catch (Exception | LinkageError _)
		{
			// Ignore NPEs and LinkageErrors from Bukkit singletons
		}
	}

	private static class Stub implements SpeleothemBaseMock
	{
		@Override
		public Speleothem clone()
		{
			return null;
		}
	}
}
