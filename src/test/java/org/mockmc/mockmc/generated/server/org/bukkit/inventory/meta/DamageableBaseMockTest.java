// Auto-generated mechanical sanity test for DamageableBaseMock
package org.mockmc.mockmc.generated.server.org.bukkit.inventory.meta;

import static org.junit.jupiter.api.Assertions.*;

import org.bukkit.inventory.meta.Damageable;
import org.junit.jupiter.api.Test;
import org.mockmc.mockmc.generated.GeneratedTestBase;

class DamageableBaseMockTest extends GeneratedTestBase
{
	@Test
	void testSafeDefaults()
	{
		DamageableBaseMock mock = new Stub();
		assertNotNull(mock);
		assertSafeDefault(mock.clone());
	}

	private static class Stub implements DamageableBaseMock
	{
		@Override
		public Damageable clone()
		{
			return null;
		}
	}
}
