// Auto-generated mechanical sanity test for VexBaseMock
package org.mockmc.mockmc.generated.server.org.bukkit.entity;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.mockmc.mockmc.generated.GeneratedTestBase;

class VexBaseMockTest extends GeneratedTestBase
{
	@Test
	void testSafeDefaults()
	{
		VexBaseMock mock = new Stub();
		assertNotNull(mock);
		assertSafeDefault(mock.getSummoner());
		assertSafeDefault(mock.getBound());
	}

	private static class Stub implements VexBaseMock
	{
	}
}
