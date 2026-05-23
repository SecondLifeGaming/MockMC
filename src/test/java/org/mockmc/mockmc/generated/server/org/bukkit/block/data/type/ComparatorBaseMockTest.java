// Auto-generated mechanical sanity test for ComparatorBaseMock
package org.mockmc.mockmc.generated.server.org.bukkit.block.data.type;

import static org.junit.jupiter.api.Assertions.*;

import org.bukkit.block.data.type.Comparator;
import org.junit.jupiter.api.Test;
import org.mockmc.mockmc.generated.GeneratedTestBase;

class ComparatorBaseMockTest extends GeneratedTestBase
{
	@Test
	void testSafeDefaults()
	{
		ComparatorBaseMock mock = new Stub();
		assertNotNull(mock);
		try
		{
			assertSafeDefault(mock.getMode());
		} catch (Exception | LinkageError _)
		{
			// Ignore NPEs and LinkageErrors from Bukkit singletons
		}
	}

	private static class Stub implements ComparatorBaseMock
	{
		@Override
		public Comparator clone()
		{
			return null;
		}
	}
}
