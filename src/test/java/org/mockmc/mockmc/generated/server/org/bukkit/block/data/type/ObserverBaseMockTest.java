// Auto-generated mechanical sanity test for ObserverBaseMock
package org.mockmc.mockmc.generated.server.org.bukkit.block.data.type;

import static org.junit.jupiter.api.Assertions.*;

import org.bukkit.block.data.type.Observer;
import org.junit.jupiter.api.Test;
import org.mockmc.mockmc.generated.GeneratedTestBase;

class ObserverBaseMockTest extends GeneratedTestBase
{
	@Test
	void testSafeDefaults()
	{
		ObserverBaseMock mock = new Stub();
		assertNotNull(mock);
	}

	private static class Stub implements ObserverBaseMock
	{
		@Override
		public Observer clone()
		{
			return null;
		}
	}
}
