// Auto-generated mechanical sanity test for WanderingTraderBaseMock
package org.mockmc.mockmc.generated.server.org.bukkit.entity;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.mockmc.mockmc.generated.GeneratedTestBase;

class WanderingTraderBaseMockTest extends GeneratedTestBase
{
	@Test
	void testSafeDefaults()
	{
		WanderingTraderBaseMock mock = new Stub();
		assertNotNull(mock);
		assertSafeDefault(mock.getWanderingTowards());
	}

	private static class Stub implements WanderingTraderBaseMock
	{
	}
}
