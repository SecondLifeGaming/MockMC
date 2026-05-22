// Auto-generated mechanical sanity test for PersistentDataViewHolderBaseMock
package org.mockmc.mockmc.generated.server.io.papermc.paper.persistence;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.mockmc.mockmc.generated.GeneratedTestBase;

class PersistentDataViewHolderBaseMockTest extends GeneratedTestBase
{
	@Test
	void testSafeDefaults()
	{
		PersistentDataViewHolderBaseMock mock = new Stub();
		assertNotNull(mock);
		assertSafeDefault(mock.getPersistentDataContainer());
	}

	private static class Stub implements PersistentDataViewHolderBaseMock
	{
	}
}
