// Auto-generated mechanical sanity test for CreakingHeartBaseMock
package org.mockmc.mockmc.generated.server.org.bukkit.block.data.type;

import static org.junit.jupiter.api.Assertions.*;

import org.bukkit.block.data.type.CreakingHeart;
import org.junit.jupiter.api.Test;
import org.mockmc.mockmc.generated.GeneratedTestBase;

class CreakingHeartBaseMockTest extends GeneratedTestBase
{
	@Test
	void testSafeDefaults()
	{
		CreakingHeartBaseMock mock = new Stub();
		assertNotNull(mock);
		try
		{
			assertSafeDefault(mock.getCreakingHeartState());
		} catch (Exception | LinkageError _)
		{
			// Ignore NPEs and LinkageErrors from Bukkit singletons
		}
	}

	private static class Stub implements CreakingHeartBaseMock
	{
		@Override
		public CreakingHeart clone()
		{
			return null;
		}
	}
}
