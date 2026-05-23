// Auto-generated mechanical sanity test for JigsawBaseMock
package org.mockmc.mockmc.generated.server.org.bukkit.block.data.type;

import static org.junit.jupiter.api.Assertions.*;

import org.bukkit.block.data.type.Jigsaw;
import org.junit.jupiter.api.Test;
import org.mockmc.mockmc.generated.GeneratedTestBase;

class JigsawBaseMockTest extends GeneratedTestBase
{
	@Test
	void testSafeDefaults()
	{
		JigsawBaseMock mock = new Stub();
		assertNotNull(mock);
		try
		{
			assertSafeDefault(mock.getOrientation());
		} catch (Exception | LinkageError e)
		{
			// Ignore NPEs and LinkageErrors from Bukkit singletons
		}
	}

	private static class Stub implements JigsawBaseMock
	{
		@Override
		public Jigsaw clone()
		{
			return null;
		}
	}
}
