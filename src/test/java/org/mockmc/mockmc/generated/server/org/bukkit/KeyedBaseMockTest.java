// Auto-generated mechanical sanity test for KeyedBaseMock
package org.mockmc.mockmc.generated.server.org.bukkit;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.mockmc.mockmc.generated.GeneratedTestBase;

class KeyedBaseMockTest extends GeneratedTestBase
{
	@Test
	void testSafeDefaults()
	{
		KeyedBaseMock mock = new Stub();
		assertNotNull(mock);
		assertSafeDefault(mock.getKey());
		assertSafeDefault(mock.key());
	}

	private static class Stub implements KeyedBaseMock
	{
	}
}
