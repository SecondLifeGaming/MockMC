// Auto-generated mechanical sanity test for ImmutableStringReaderBaseMock
package org.mockmc.mockmc.generated.server.com.mojang.brigadier;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.mockmc.mockmc.generated.GeneratedTestBase;

class ImmutableStringReaderBaseMockTest extends GeneratedTestBase
{
	@Test
	void testSafeDefaults()
	{
		ImmutableStringReaderBaseMock mock = new Stub();
		assertNotNull(mock);
		try
		{
			assertSafeDefault(mock.getString());
		} catch (Exception | LinkageError e)
		{
			// Ignore NPEs and LinkageErrors from Bukkit singletons
		}
		try
		{
			assertSafeDefault(mock.getRemaining());
		} catch (Exception | LinkageError e)
		{
			// Ignore NPEs and LinkageErrors from Bukkit singletons
		}
		try
		{
			assertSafeDefault(mock.getRead());
		} catch (Exception | LinkageError e)
		{
			// Ignore NPEs and LinkageErrors from Bukkit singletons
		}
	}

	private static class Stub implements ImmutableStringReaderBaseMock
	{
	}
}
