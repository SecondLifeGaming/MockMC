// Auto-generated mechanical sanity test for StyledFormatBaseMock
package org.mockmc.mockmc.generated.server.io.papermc.paper.scoreboard.numbers;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.mockmc.mockmc.generated.GeneratedTestBase;

class StyledFormatBaseMockTest extends GeneratedTestBase
{
	@Test
	void testSafeDefaults()
	{
		StyledFormatBaseMock mock = new Stub();
		assertNotNull(mock);
		try
		{
			assertSafeDefault(mock.style());
		} catch (Exception | LinkageError _)
		{
			// Ignore NPEs and LinkageErrors from Bukkit singletons
		}
	}

	private static class Stub implements StyledFormatBaseMock
	{
	}
}
