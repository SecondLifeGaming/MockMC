// Auto-generated mechanical sanity test for AllayBaseMock
package org.mockmc.mockmc.generated.server.org.bukkit.entity;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.mockmc.mockmc.generated.GeneratedTestBase;

class AllayBaseMockTest extends GeneratedTestBase
{
	@Test
	void testSafeDefaults()
	{
		AllayBaseMock mock = new Stub();
		assertNotNull(mock);
		assertSafeDefault(mock.getJukebox());
		assertSafeDefault(mock.duplicateAllay());
	}

	private static class Stub implements AllayBaseMock
	{
	}
}
