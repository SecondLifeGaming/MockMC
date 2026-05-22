// Auto-generated mechanical sanity test for PersistentDataHolderBaseMock
package org.mockmc.mockmc.generated.server.org.bukkit.persistence;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.mockmc.mockmc.generated.GeneratedTestBase;

class PersistentDataHolderBaseMockTest extends GeneratedTestBase
{
	@Test
	void testSafeDefaults()
	{
		PersistentDataHolderBaseMock mock = new Stub();
		assertNotNull(mock);
		assertSafeDefault(mock.getPersistentDataContainer());
	}

	private static class Stub implements PersistentDataHolderBaseMock
	{
	}
}
