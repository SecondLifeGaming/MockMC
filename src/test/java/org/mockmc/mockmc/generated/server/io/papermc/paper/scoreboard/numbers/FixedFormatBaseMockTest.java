// Auto-generated mechanical sanity test for FixedFormatBaseMock
package org.mockmc.mockmc.generated.server.io.papermc.paper.scoreboard.numbers;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.mockmc.mockmc.generated.GeneratedTestBase;

class FixedFormatBaseMockTest extends GeneratedTestBase
{
	@Test
	void testSafeDefaults()
	{
		FixedFormatBaseMock mock = new Stub();
		assertNotNull(mock);
		try
		{
			assertSafeDefault(mock.component());
		} catch (Exception | LinkageError _)
		{
			// Ignore NPEs and LinkageErrors from Bukkit singletons
		}
	}

	private static class Stub implements FixedFormatBaseMock
	{
	}
}
