// Auto-generated mechanical sanity test for VaultBaseMock
package org.mockmc.mockmc.generated.server.org.bukkit.block.data.type;

import static org.junit.jupiter.api.Assertions.*;

import org.bukkit.block.data.type.Vault;
import org.junit.jupiter.api.Test;
import org.mockmc.mockmc.generated.GeneratedTestBase;

@SuppressWarnings(
{"deprecation", "removal", "java:S1874"})
class VaultBaseMockTest extends GeneratedTestBase
{
	@Test
	void testSafeDefaults()
	{
		VaultBaseMock mock = new Stub();
		assertNotNull(mock);
		assertSafeDefault(mock.getTrialSpawnerState());
		assertSafeDefault(mock.getVaultState());
	}

	private static class Stub implements VaultBaseMock
	{
		@Override
		public Vault clone()
		{
			return null;
		}
	}
}
