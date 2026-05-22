// Auto-generated mechanical sanity test for BatBaseMock
package org.mockmc.mockmc.generated.server.org.bukkit.entity;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.mockmc.mockmc.generated.GeneratedTestBase;

class BatBaseMockTest extends GeneratedTestBase
{
	@Test
	void testSafeDefaults()
	{
		BatBaseMock mock = new Stub();
		assertNotNull(mock);
		assertSafeDefault(mock.getTargetLocation());
	}

	private static class Stub implements BatBaseMock
	{
	}
}
