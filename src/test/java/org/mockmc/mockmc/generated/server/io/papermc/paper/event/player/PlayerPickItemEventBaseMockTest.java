// Auto-generated mechanical sanity test for PlayerPickItemEventBaseMock
package org.mockmc.mockmc.generated.server.io.papermc.paper.event.player;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.mockmc.mockmc.generated.GeneratedTestBase;

class PlayerPickItemEventBaseMockTest extends GeneratedTestBase
{
	@Test
	void testSafeDefaults()
	{
		PlayerPickItemEventBaseMock mock = new Stub();
		assertNotNull(mock);
		assertSafeDefault(mock.getHandlers());
	}

	private static class Stub implements PlayerPickItemEventBaseMock
	{
	}
}
