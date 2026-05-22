// Auto-generated mechanical sanity test for PhantomBaseMock
package org.mockmc.mockmc.generated.server.org.bukkit.entity;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.mockmc.mockmc.generated.GeneratedTestBase;

class PhantomBaseMockTest extends GeneratedTestBase
{
	@Test
	void testSafeDefaults()
	{
		PhantomBaseMock mock = new Stub();
		assertNotNull(mock);
		assertSafeDefault(mock.getSpawningEntity());
		assertSafeDefault(mock.getAnchorLocation());
	}

	private static class Stub implements PhantomBaseMock
	{
	}
}
