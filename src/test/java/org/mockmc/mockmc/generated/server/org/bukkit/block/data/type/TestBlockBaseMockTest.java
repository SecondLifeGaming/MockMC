// Auto-generated mechanical sanity test for TestBlockBaseMock
package org.mockmc.mockmc.generated.server.org.bukkit.block.data.type;

import static org.junit.jupiter.api.Assertions.*;

import org.bukkit.block.data.type.TestBlock;
import org.junit.jupiter.api.Test;
import org.mockmc.mockmc.generated.GeneratedTestBase;

class TestBlockBaseMockTest extends GeneratedTestBase
{
	@Test
	void testSafeDefaults()
	{
		TestBlockBaseMock mock = new Stub();
		assertNotNull(mock);
		assertSafeDefault(mock.getMode());
	}

	private static class Stub implements TestBlockBaseMock
	{
		@Override
		public TestBlock clone()
		{
			return null;
		}
	}
}
