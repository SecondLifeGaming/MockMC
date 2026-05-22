// Auto-generated mechanical sanity test for SpellcasterBaseMock
package org.mockmc.mockmc.generated.server.org.bukkit.entity;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.mockmc.mockmc.generated.GeneratedTestBase;

class SpellcasterBaseMockTest extends GeneratedTestBase
{
	@Test
	void testSafeDefaults()
	{
		SpellcasterBaseMock mock = new Stub();
		assertNotNull(mock);
		assertSafeDefault(mock.getSpell());
	}

	private static class Stub implements SpellcasterBaseMock
	{
	}
}
