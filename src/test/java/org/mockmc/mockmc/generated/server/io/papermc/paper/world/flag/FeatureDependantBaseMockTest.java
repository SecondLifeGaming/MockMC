// Auto-generated mechanical sanity test for FeatureDependantBaseMock
package org.mockmc.mockmc.generated.server.io.papermc.paper.world.flag;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.mockmc.mockmc.generated.GeneratedTestBase;

@SuppressWarnings("all")
class FeatureDependantBaseMockTest extends GeneratedTestBase
{
	@Test
	void testSafeDefaults()
	{
		FeatureDependantBaseMock mock = new Stub();
		assertNotNull(mock);
		try
		{
			assertSafeDefault(mock.requiredFeatures());
		} catch (Exception | LinkageError _)
		{
			// Ignore NPEs and LinkageErrors from Bukkit singletons
		}
	}

	private static class Stub implements FeatureDependantBaseMock
	{
	}
}
