// Auto-generated mechanical sanity test for CatBaseMock
package org.mockmc.mockmc.generated.server.org.bukkit.entity;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.mockmc.mockmc.generated.GeneratedTestBase;

class CatBaseMockTest extends GeneratedTestBase
{
	@Test
	void testSafeDefaults()
	{
		CatBaseMock mock = new Stub();
		assertNotNull(mock);
		assertSafeDefault(mock.getSoundVariant());
		assertSafeDefault(mock.getCollarColor());
		assertSafeDefault(mock.getCatType());
	}

	private static class Stub implements CatBaseMock
	{
	}
}
