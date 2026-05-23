// Auto-generated mechanical sanity test for RedstoneWireBaseMock
package org.mockmc.mockmc.generated.server.org.bukkit.block.data.type;

import static org.junit.jupiter.api.Assertions.*;

import org.bukkit.block.data.type.RedstoneWire;
import org.junit.jupiter.api.Test;
import org.mockmc.mockmc.generated.GeneratedTestBase;

class RedstoneWireBaseMockTest extends GeneratedTestBase
{
	@Test
	void testSafeDefaults()
	{
		RedstoneWireBaseMock mock = new Stub();
		assertNotNull(mock);
		try
		{
			assertSafeDefault(mock.getAllowedFaces());
		} catch (Exception | LinkageError _)
		{
			// Ignore NPEs and LinkageErrors from Bukkit singletons
		}
	}

	private static class Stub implements RedstoneWireBaseMock
	{
		@Override
		public RedstoneWire clone()
		{
			return null;
		}
	}
}
