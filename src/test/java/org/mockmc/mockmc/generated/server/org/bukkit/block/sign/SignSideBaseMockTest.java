// Auto-generated mechanical sanity test for SignSideBaseMock
package org.mockmc.mockmc.generated.server.org.bukkit.block.sign;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.mockmc.mockmc.generated.GeneratedTestBase;

@SuppressWarnings(
{"deprecation", "java:S1874"})
class SignSideBaseMockTest extends GeneratedTestBase
{
	@Test
	void testSafeDefaults()
	{
		SignSideBaseMock mock = new Stub();
		assertNotNull(mock);
		try
		{
			assertSafeDefault(mock.lines());
		} catch (Exception | LinkageError _)
		{
			// Ignore NPEs and LinkageErrors from Bukkit singletons
		}
		try
		{
			assertSafeDefault(mock.getLines());
		} catch (Exception | LinkageError _)
		{
			// Ignore NPEs and LinkageErrors from Bukkit singletons
		}
	}

	private static class Stub implements SignSideBaseMock
	{
	}
}
