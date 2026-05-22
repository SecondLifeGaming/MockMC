// Auto-generated mechanical sanity test for PersistentDataContainerViewBaseMock
package org.mockmc.mockmc.generated.server.io.papermc.paper.persistence;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.mockmc.mockmc.generated.GeneratedTestBase;

class PersistentDataContainerViewBaseMockTest extends GeneratedTestBase
{
	@Test
	void testSafeDefaults() throws Exception
	{
		PersistentDataContainerViewBaseMock mock = new Stub();
		assertNotNull(mock);
		assertSafeDefault(mock.getKeys());
		assertSafeDefault(mock.getAdapterContext());
		assertSafeDefault(mock.serializeToBytes());
	}

	private static class Stub implements PersistentDataContainerViewBaseMock
	{
	}
}
