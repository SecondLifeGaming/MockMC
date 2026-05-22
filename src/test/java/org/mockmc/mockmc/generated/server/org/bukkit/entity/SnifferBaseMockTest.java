// Auto-generated mechanical sanity test for SnifferBaseMock
package org.mockmc.mockmc.generated.server.org.bukkit.entity;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.mockmc.mockmc.generated.GeneratedTestBase;

class SnifferBaseMockTest extends GeneratedTestBase
{
	@Test
	void testSafeDefaults()
	{
		SnifferBaseMock mock = new Stub();
		assertNotNull(mock);
		assertSafeDefault(mock.getState());
		assertSafeDefault(mock.findPossibleDigLocation());
		assertSafeDefault(mock.getExploredLocations());
	}

	private static class Stub implements SnifferBaseMock
	{
	}
}
