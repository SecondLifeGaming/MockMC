// Auto-generated mechanical sanity test for ListPersistentDataTypeBaseMock
package org.mockmc.mockmc.generated.server.org.bukkit.persistence;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.mockmc.mockmc.generated.GeneratedTestBase;

class ListPersistentDataTypeBaseMockTest extends GeneratedTestBase
{
	@Test
	void testSafeDefaults()
	{
		ListPersistentDataTypeBaseMock<?, ?> mock = new Stub<>();
		assertNotNull(mock);
		assertSafeDefault(mock.elementType());
	}

	private static class Stub<P, C> implements ListPersistentDataTypeBaseMock<P, C>
	{
	}
}
