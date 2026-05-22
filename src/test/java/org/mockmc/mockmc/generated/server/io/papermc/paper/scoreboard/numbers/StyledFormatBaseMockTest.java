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
		assertSafeDefault(mock.style());
	}

	private static class Stub implements StyledFormatBaseMock
	{
	}
}
