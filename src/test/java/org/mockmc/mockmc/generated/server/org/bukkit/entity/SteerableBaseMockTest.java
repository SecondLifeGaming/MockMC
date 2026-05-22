// Auto-generated mechanical sanity test for SteerableBaseMock
package org.mockmc.mockmc.generated.server.org.bukkit.entity;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.mockmc.mockmc.generated.GeneratedTestBase;

class SteerableBaseMockTest extends GeneratedTestBase
{
	@Test
	void testSafeDefaults()
	{
		SteerableBaseMock mock = new Stub();
		assertNotNull(mock);
		assertSafeDefault(mock.getSteerMaterial());
	}

	private static class Stub implements SteerableBaseMock
	{
	}
}
