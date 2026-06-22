// Auto-generated mechanical sanity test for AnvilViewBaseMock
package org.mockmc.mockmc.generated.server.org.bukkit.inventory.view;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.mockmc.mockmc.generated.GeneratedTestBase;

@SuppressWarnings("all")
class AnvilViewBaseMockTest extends GeneratedTestBase
{
	@Test
	void testSafeDefaults()
	{
		AnvilViewBaseMock mock = new Stub();
		assertNotNull(mock);
		try
		{
			assertSafeDefault(mock.getTopInventory());
		} catch (Exception | LinkageError _)
		{
			// Ignore NPEs and LinkageErrors from Bukkit singletons
		}
		try
		{
			assertSafeDefault(mock.getRenameText());
		} catch (Exception | LinkageError _)
		{
			// Ignore NPEs and LinkageErrors from Bukkit singletons
		}
	}

	private static class Stub implements AnvilViewBaseMock
	{
	}
}
