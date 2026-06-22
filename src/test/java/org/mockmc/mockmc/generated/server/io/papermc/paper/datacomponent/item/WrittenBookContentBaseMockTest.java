// Auto-generated mechanical sanity test for WrittenBookContentBaseMock
package org.mockmc.mockmc.generated.server.io.papermc.paper.datacomponent.item;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.mockmc.mockmc.generated.GeneratedTestBase;

@SuppressWarnings("all")
class WrittenBookContentBaseMockTest extends GeneratedTestBase
{
	@Test
	void testSafeDefaults()
	{
		WrittenBookContentBaseMock mock = new Stub();
		assertNotNull(mock);
		try
		{
			assertSafeDefault(mock.title());
		} catch (Exception | LinkageError _)
		{
			// Ignore NPEs and LinkageErrors from Bukkit singletons
		}
		try
		{
			assertSafeDefault(mock.author());
		} catch (Exception | LinkageError _)
		{
			// Ignore NPEs and LinkageErrors from Bukkit singletons
		}
		try
		{
			assertSafeDefault(mock.pages());
		} catch (Exception | LinkageError _)
		{
			// Ignore NPEs and LinkageErrors from Bukkit singletons
		}
	}

	private static class Stub implements WrittenBookContentBaseMock
	{
	}
}
