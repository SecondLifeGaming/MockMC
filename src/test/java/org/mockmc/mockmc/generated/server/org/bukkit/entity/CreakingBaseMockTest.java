// Auto-generated mechanical sanity test for CreakingBaseMock
package org.mockmc.mockmc.generated.server.org.bukkit.entity;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.mockmc.mockmc.generated.GeneratedTestBase;

class CreakingBaseMockTest extends GeneratedTestBase
{
	@Test
	void testSafeDefaults()
	{
		CreakingBaseMock mock = new Stub();
		assertNotNull(mock);
		assertSafeDefault(mock.getHome());
	}

	private static class Stub implements CreakingBaseMock
	{
	}
}
