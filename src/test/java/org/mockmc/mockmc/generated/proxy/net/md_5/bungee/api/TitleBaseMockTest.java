// Auto-generated mechanical sanity test for TitleBaseMock
package org.mockmc.mockmc.generated.proxy.net.md_5.bungee.api;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.mockmc.mockmc.generated.GeneratedTestBase;

@SuppressWarnings("all")
class TitleBaseMockTest extends GeneratedTestBase
{
	@Test
	void testSafeDefaults()
	{
		TitleBaseMock mock = new Stub();
		assertNotNull(mock);
		try
		{
			assertSafeDefault(mock.reset());
		} catch (Exception | LinkageError _)
		{
			// Ignore NPEs and LinkageErrors from Bukkit singletons
		}
		try
		{
			assertSafeDefault(mock.clear());
		} catch (Exception | LinkageError _)
		{
			// Ignore NPEs and LinkageErrors from Bukkit singletons
		}
	}

	private static class Stub implements TitleBaseMock
	{
	}
}
