// Auto-generated mechanical sanity test for PersistentDataAdapterContextBaseMock
package org.mockmc.mockmc.generated.server.org.bukkit.persistence;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.mockmc.mockmc.generated.GeneratedTestBase;

class PersistentDataAdapterContextBaseMockTest extends GeneratedTestBase
{
	@Test
	void testSafeDefaults()
	{
		PersistentDataAdapterContextBaseMock mock = new Stub();
		assertNotNull(mock);
		assertSafeDefault(mock.newPersistentDataContainer());
	}

	private static class Stub implements PersistentDataAdapterContextBaseMock
	{
	}
}
