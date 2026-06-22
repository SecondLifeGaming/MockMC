// Auto-generated mechanical sanity test for TrialSpawnerBaseMock
package org.mockmc.mockmc.generated.server.org.bukkit.block.data.type;

import static org.junit.jupiter.api.Assertions.*;

import org.bukkit.block.data.type.TrialSpawner;
import org.junit.jupiter.api.Test;
import org.mockmc.mockmc.generated.GeneratedTestBase;

@SuppressWarnings("all")
class TrialSpawnerBaseMockTest extends GeneratedTestBase
{
	@Test
	void testSafeDefaults()
	{
		TrialSpawnerBaseMock mock = new Stub();
		assertNotNull(mock);
		try
		{
			assertSafeDefault(mock.getTrialSpawnerState());
		} catch (Exception | LinkageError _)
		{
			// Ignore NPEs and LinkageErrors from Bukkit singletons
		}
	}

	private static class Stub implements TrialSpawnerBaseMock
	{
		@Override
		public TrialSpawner clone()
		{
			return null;
		}
	}
}
