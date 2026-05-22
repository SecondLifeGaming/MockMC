// Auto-generated mechanical sanity test for ShulkerBaseMock
package org.mockmc.mockmc.generated.server.org.bukkit.entity;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.mockmc.mockmc.generated.GeneratedTestBase;

class ShulkerBaseMockTest extends GeneratedTestBase
{
	@Test
	void testSafeDefaults()
	{
		ShulkerBaseMock mock = new Stub();
		assertNotNull(mock);
		assertSafeDefault(mock.getAttachedFace());
	}

	private static class Stub implements ShulkerBaseMock
	{
	}
}
