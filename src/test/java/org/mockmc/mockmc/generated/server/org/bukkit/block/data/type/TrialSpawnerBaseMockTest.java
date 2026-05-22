// Auto-generated mechanical sanity test for TrialSpawnerBaseMock
package org.mockmc.mockmc.generated.server.org.bukkit.block.data.type;

import static org.junit.jupiter.api.Assertions.*;

import org.bukkit.block.data.type.TrialSpawner;
import org.junit.jupiter.api.Test;
import org.mockmc.mockmc.generated.GeneratedTestBase;

class TrialSpawnerBaseMockTest extends GeneratedTestBase
{
	@Test
	void testSafeDefaults()
	{
		TrialSpawnerBaseMock mock = new Stub();
		assertNotNull(mock);
		assertSafeDefault(mock.getTrialSpawnerState());
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
