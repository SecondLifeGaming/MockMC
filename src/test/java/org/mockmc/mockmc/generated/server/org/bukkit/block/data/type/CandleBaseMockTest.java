// Auto-generated mechanical sanity test for CandleBaseMock
package org.mockmc.mockmc.generated.server.org.bukkit.block.data.type;

import static org.junit.jupiter.api.Assertions.*;

import org.bukkit.block.data.type.Candle;
import org.junit.jupiter.api.Test;
import org.mockmc.mockmc.generated.GeneratedTestBase;

@SuppressWarnings("all")
class CandleBaseMockTest extends GeneratedTestBase
{
	@Test
	void testSafeDefaults()
	{
		CandleBaseMock mock = new Stub();
		assertNotNull(mock);
	}

	private static class Stub implements CandleBaseMock
	{
		@Override
		public Candle clone()
		{
			return null;
		}
	}
}
