// Auto-generated mechanical sanity test for SuspiciousStewMetaBaseMock
package org.mockmc.mockmc.generated.server.org.bukkit.inventory.meta;

import static org.junit.jupiter.api.Assertions.*;

import org.bukkit.inventory.meta.SuspiciousStewMeta;
import org.junit.jupiter.api.Test;
import org.mockmc.mockmc.generated.GeneratedTestBase;

class SuspiciousStewMetaBaseMockTest extends GeneratedTestBase
{
	@Test
	void testSafeDefaults()
	{
		SuspiciousStewMetaBaseMock mock = new Stub();
		assertNotNull(mock);
		assertSafeDefault(mock.clone());
		assertSafeDefault(mock.getCustomEffects());
	}

	private static class Stub implements SuspiciousStewMetaBaseMock
	{
		@Override
		public SuspiciousStewMeta clone()
		{
			return null;
		}
	}
}
