// Auto-generated mechanical sanity test for SignBaseMock
package org.mockmc.mockmc.generated.server.org.bukkit.block.data.type;

import static org.junit.jupiter.api.Assertions.*;

import org.bukkit.block.data.type.Sign;
import org.junit.jupiter.api.Test;
import org.mockmc.mockmc.generated.GeneratedTestBase;

@SuppressWarnings("all")
class SignBaseMockTest extends GeneratedTestBase
{
	@Test
	void testSafeDefaults()
	{
		SignBaseMock mock = new Stub();
		assertNotNull(mock);
	}

	private static class Stub implements SignBaseMock
	{
		@Override
		public Sign clone()
		{
			return null;
		}
	}
}
