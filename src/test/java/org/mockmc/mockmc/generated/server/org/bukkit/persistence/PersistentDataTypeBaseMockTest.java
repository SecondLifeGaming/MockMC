// Auto-generated mechanical sanity test for PersistentDataTypeBaseMock
package org.mockmc.mockmc.generated.server.org.bukkit.persistence;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.mockmc.mockmc.generated.GeneratedTestBase;

class PersistentDataTypeBaseMockTest extends GeneratedTestBase
{
	@Test
	void testSafeDefaults()
	{
		PersistentDataTypeBaseMock<?, ?> mock = new Stub<>();
		assertNotNull(mock);
		assertSafeDefault(mock.getPrimitiveType());
		assertSafeDefault(mock.getComplexType());
	}

	private static class Stub<P, C> implements PersistentDataTypeBaseMock<P, C>
	{
	}
}
