// Auto-generated mechanical sanity test for SalmonBaseMock
package org.mockmc.mockmc.generated.server.org.bukkit.entity;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.mockmc.mockmc.generated.GeneratedTestBase;

class SalmonBaseMockTest extends GeneratedTestBase
{
	@Test
	void testSafeDefaults()
	{
		SalmonBaseMock mock = new Stub();
		assertNotNull(mock);
		assertSafeDefault(mock.getVariant());
	}

	private static class Stub implements SalmonBaseMock
	{
	}
}
