// Auto-generated mechanical sanity test for PigBaseMock
package org.mockmc.mockmc.generated.server.org.bukkit.entity;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.mockmc.mockmc.generated.GeneratedTestBase;

class PigBaseMockTest extends GeneratedTestBase
{
	@Test
	void testSafeDefaults()
	{
		PigBaseMock mock = new Stub();
		assertNotNull(mock);
		assertSafeDefault(mock.getVariant());
		assertSafeDefault(mock.getSoundVariant());
	}

	private static class Stub implements PigBaseMock
	{
	}
}
