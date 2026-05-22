// Auto-generated mechanical sanity test for StructureBlockBaseMock
package org.mockmc.mockmc.generated.server.org.bukkit.block.data.type;

import static org.junit.jupiter.api.Assertions.*;

import org.bukkit.block.data.type.StructureBlock;
import org.junit.jupiter.api.Test;
import org.mockmc.mockmc.generated.GeneratedTestBase;

class StructureBlockBaseMockTest extends GeneratedTestBase
{
	@Test
	void testSafeDefaults()
	{
		StructureBlockBaseMock mock = new Stub();
		assertNotNull(mock);
		assertSafeDefault(mock.getMode());
	}

	private static class Stub implements StructureBlockBaseMock
	{
		@Override
		public StructureBlock clone()
		{
			return null;
		}
	}
}
