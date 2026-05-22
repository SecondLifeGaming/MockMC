// Auto-generated mechanical sanity test for DirectionalBaseMock
package org.mockmc.mockmc.generated.server.org.bukkit.material;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.mockmc.mockmc.generated.GeneratedTestBase;

class DirectionalBaseMockTest extends GeneratedTestBase
{
	@Test
	void testSafeDefaults()
	{
		DirectionalBaseMock mock = new Stub();
		assertNotNull(mock);
		assertSafeDefault(mock.getFacing());
	}

	private static class Stub implements DirectionalBaseMock
	{
	}
}
