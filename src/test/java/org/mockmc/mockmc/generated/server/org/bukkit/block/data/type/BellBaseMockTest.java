// Auto-generated mechanical sanity test for BellBaseMock
package org.mockmc.mockmc.generated.server.org.bukkit.block.data.type;

import static org.junit.jupiter.api.Assertions.*;

import org.bukkit.block.data.type.Bell;
import org.junit.jupiter.api.Test;
import org.mockmc.mockmc.generated.GeneratedTestBase;

class BellBaseMockTest extends GeneratedTestBase
{
	@Test
	void testSafeDefaults()
	{
		BellBaseMock mock = new Stub();
		assertNotNull(mock);
		assertSafeDefault(mock.getAttachment());
	}

	private static class Stub implements BellBaseMock
	{
		@Override
		public Bell clone()
		{
			return null;
		}
	}
}
