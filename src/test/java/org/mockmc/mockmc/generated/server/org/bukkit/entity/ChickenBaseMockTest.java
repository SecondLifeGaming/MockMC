// Auto-generated mechanical sanity test for ChickenBaseMock
package org.mockmc.mockmc.generated.server.org.bukkit.entity;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.mockmc.mockmc.generated.GeneratedTestBase;

class ChickenBaseMockTest extends GeneratedTestBase
{
	@Test
	void testSafeDefaults()
	{
		ChickenBaseMock mock = new Stub();
		assertNotNull(mock);
		assertSafeDefault(mock.getVariant());
		assertSafeDefault(mock.getSoundVariant());
	}

	private static class Stub implements ChickenBaseMock
	{
	}
}
