// Auto-generated mechanical sanity test for RotatableBaseMock
package org.mockmc.mockmc.generated.server.org.bukkit.block.data;

import static org.junit.jupiter.api.Assertions.*;

import org.bukkit.block.data.Rotatable;
import org.junit.jupiter.api.Test;
import org.mockmc.mockmc.generated.GeneratedTestBase;

@SuppressWarnings("all")
class RotatableBaseMockTest extends GeneratedTestBase
{
	@Test
	void testSafeDefaults()
	{
		RotatableBaseMock mock = new Stub();
		assertNotNull(mock);
		try
		{
			assertSafeDefault(mock.getRotation());
		} catch (Exception | LinkageError _)
		{
			// Ignore NPEs and LinkageErrors from Bukkit singletons
		}
	}

	private static class Stub implements RotatableBaseMock
	{
		@Override
		public Rotatable clone()
		{
			return null;
		}
	}
}
