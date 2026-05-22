// Auto-generated mechanical sanity test for CowBaseMock
package org.mockmc.mockmc.generated.server.org.bukkit.entity;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.mockmc.mockmc.generated.GeneratedTestBase;

class CowBaseMockTest extends GeneratedTestBase
{
	@Test
	void testSafeDefaults()
	{
		CowBaseMock mock = new Stub();
		assertNotNull(mock);
		assertSafeDefault(mock.getVariant());
		assertSafeDefault(mock.getSoundVariant());
	}

	private static class Stub implements CowBaseMock
	{
	}
}
