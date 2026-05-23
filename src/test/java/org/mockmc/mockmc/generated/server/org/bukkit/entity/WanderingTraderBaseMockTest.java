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
		try
		{
			assertSafeDefault(mock.getWanderingTowards());
		} catch (Exception | LinkageError e)
		{
			// Ignore NPEs and LinkageErrors from Bukkit singletons
		}
	}

	private static class Stub implements WanderingTraderBaseMock
	{
	}
}
