// Auto-generated mechanical sanity test for PiglinBaseMock
package org.mockmc.mockmc.generated.server.org.bukkit.entity;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.mockmc.mockmc.generated.GeneratedTestBase;

class PiglinBaseMockTest extends GeneratedTestBase
{
	@Test
	void testSafeDefaults()
	{
		PiglinBaseMock mock = new Stub();
		assertNotNull(mock);
		assertSafeDefault(mock.getInterestList());
		assertSafeDefault(mock.getBarterList());
	}

	private static class Stub implements PiglinBaseMock
	{
	}
}
