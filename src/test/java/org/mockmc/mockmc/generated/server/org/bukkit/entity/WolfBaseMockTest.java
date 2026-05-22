// Auto-generated mechanical sanity test for WolfBaseMock
package org.mockmc.mockmc.generated.server.org.bukkit.entity;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.mockmc.mockmc.generated.GeneratedTestBase;

class WolfBaseMockTest extends GeneratedTestBase
{
	@Test
	void testSafeDefaults()
	{
		WolfBaseMock mock = new Stub();
		assertNotNull(mock);
		assertSafeDefault(mock.getVariant());
		assertSafeDefault(mock.getSoundVariant());
		assertSafeDefault(mock.getCollarColor());
	}

	private static class Stub implements WolfBaseMock
	{
	}
}
