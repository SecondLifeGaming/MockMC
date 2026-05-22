// Auto-generated mechanical sanity test for CopperGolemBaseMock
package org.mockmc.mockmc.generated.server.org.bukkit.entity;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.mockmc.mockmc.generated.GeneratedTestBase;

class CopperGolemBaseMockTest extends GeneratedTestBase
{
	@Test
	void testSafeDefaults()
	{
		CopperGolemBaseMock mock = new Stub();
		assertNotNull(mock);
		assertSafeDefault(mock.getOxidizing());
		assertSafeDefault(mock.getGolemState());
		assertSafeDefault(mock.getWeatheringState());
	}

	private static class Stub implements CopperGolemBaseMock
	{
	}
}
