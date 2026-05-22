// Auto-generated mechanical sanity test for TurtleBaseMock
package org.mockmc.mockmc.generated.server.org.bukkit.entity;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.mockmc.mockmc.generated.GeneratedTestBase;

class TurtleBaseMockTest extends GeneratedTestBase
{
	@Test
	void testSafeDefaults()
	{
		TurtleBaseMock mock = new Stub();
		assertNotNull(mock);
		assertSafeDefault(mock.getHome());
	}

	private static class Stub implements TurtleBaseMock
	{
	}
}
