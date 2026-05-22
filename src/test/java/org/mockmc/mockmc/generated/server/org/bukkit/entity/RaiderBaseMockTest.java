// Auto-generated mechanical sanity test for RaiderBaseMock
package org.mockmc.mockmc.generated.server.org.bukkit.entity;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.mockmc.mockmc.generated.GeneratedTestBase;

class RaiderBaseMockTest extends GeneratedTestBase
{
	@Test
	void testSafeDefaults()
	{
		RaiderBaseMock mock = new Stub();
		assertNotNull(mock);
		assertSafeDefault(mock.getRaid());
		assertSafeDefault(mock.getPatrolTarget());
		assertSafeDefault(mock.getCelebrationSound());
	}

	private static class Stub implements RaiderBaseMock
	{
	}
}
