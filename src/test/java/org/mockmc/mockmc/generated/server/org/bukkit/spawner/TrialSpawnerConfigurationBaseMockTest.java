// Auto-generated mechanical sanity test for TrialSpawnerConfigurationBaseMock
package org.mockmc.mockmc.generated.server.org.bukkit.spawner;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.mockmc.mockmc.generated.GeneratedTestBase;

class TrialSpawnerConfigurationBaseMockTest extends GeneratedTestBase
{
	@Test
	void testSafeDefaults()
	{
		TrialSpawnerConfigurationBaseMock mock = new Stub();
		assertNotNull(mock);
		try
		{
			assertSafeDefault(mock.getPossibleRewards());
		} catch (Exception | LinkageError _)
		{
			// Ignore NPEs and LinkageErrors from Bukkit singletons
		}
	}

	private static class Stub implements TrialSpawnerConfigurationBaseMock
	{
	}
}
