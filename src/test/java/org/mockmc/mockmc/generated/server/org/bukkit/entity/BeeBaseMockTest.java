// Auto-generated mechanical sanity test for BeeBaseMock
package org.mockmc.mockmc.generated.server.org.bukkit.entity;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.mockmc.mockmc.generated.GeneratedTestBase;

class BeeBaseMockTest extends GeneratedTestBase
{
	@Test
	void testSafeDefaults()
	{
		BeeBaseMock mock = new Stub();
		assertNotNull(mock);
		assertSafeDefault(mock.getFlower());
		assertSafeDefault(mock.getHive());
		assertSafeDefault(mock.getRollingOverride());
	}

	private static class Stub implements BeeBaseMock
	{
	}
}
