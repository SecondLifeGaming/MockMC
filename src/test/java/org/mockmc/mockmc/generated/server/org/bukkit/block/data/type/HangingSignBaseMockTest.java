// Auto-generated mechanical sanity test for HangingSignBaseMock
package org.mockmc.mockmc.generated.server.org.bukkit.block.data.type;

import static org.junit.jupiter.api.Assertions.*;

import org.bukkit.block.data.type.HangingSign;
import org.junit.jupiter.api.Test;
import org.mockmc.mockmc.generated.GeneratedTestBase;

@SuppressWarnings("all")
class HangingSignBaseMockTest extends GeneratedTestBase
{
	@Test
	void testSafeDefaults()
	{
		HangingSignBaseMock mock = new Stub();
		assertNotNull(mock);
	}

	private static class Stub implements HangingSignBaseMock
	{
		@Override
		public HangingSign clone()
		{
			return null;
		}
	}
}
