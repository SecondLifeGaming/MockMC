// Auto-generated mechanical sanity test for CriteriaBaseMock
package org.mockmc.mockmc.generated.server.org.bukkit.scoreboard;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.mockmc.mockmc.generated.GeneratedTestBase;

class CriteriaBaseMockTest extends GeneratedTestBase
{
	@Test
	void testSafeDefaults()
	{
		CriteriaBaseMock mock = new Stub();
		assertNotNull(mock);
		assertSafeDefault(mock.getName());
		assertSafeDefault(mock.getDefaultRenderType());
	}

	private static class Stub implements CriteriaBaseMock
	{
	}
}
