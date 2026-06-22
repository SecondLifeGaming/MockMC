// Auto-generated mechanical sanity test for SpellcasterBaseMock
package org.mockmc.mockmc.generated.server.org.bukkit.entity;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.mockmc.mockmc.generated.GeneratedTestBase;

@SuppressWarnings("all")
class SpellcasterBaseMockTest extends GeneratedTestBase
{
	@Test
	void testSafeDefaults()
	{
		SpellcasterBaseMock mock = new Stub();
		assertNotNull(mock);
		try
		{
			assertSafeDefault(mock.getSpell());
		} catch (Exception | LinkageError _)
		{
			// Ignore NPEs and LinkageErrors from Bukkit singletons
		}
	}

	private static class Stub implements SpellcasterBaseMock
	{
	}
}
