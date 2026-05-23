// Auto-generated mechanical sanity test for FeatureFlagSetHolderBaseMock
package org.mockmc.mockmc.generated.server.io.papermc.paper.world.flag;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.mockmc.mockmc.generated.GeneratedTestBase;

class FeatureFlagSetHolderBaseMockTest extends GeneratedTestBase
{
	@Test
	void testSafeDefaults()
	{
		FeatureFlagSetHolderBaseMock mock = new Stub();
		assertNotNull(mock);
		try
		{
			assertSafeDefault(mock.getFeatureFlags());
		} catch (Exception | LinkageError e)
		{
			// Ignore NPEs and LinkageErrors from Bukkit singletons
		}
	}

	private static class Stub implements FeatureFlagSetHolderBaseMock
	{
	}
}
