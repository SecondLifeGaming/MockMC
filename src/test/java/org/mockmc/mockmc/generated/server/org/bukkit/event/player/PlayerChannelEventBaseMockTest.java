// Auto-generated mechanical sanity test for PlayerChannelEventBaseMock
package org.mockmc.mockmc.generated.server.org.bukkit.event.player;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.mockmc.mockmc.generated.GeneratedTestBase;

class PlayerChannelEventBaseMockTest extends GeneratedTestBase
{
	@Test
	void testSafeDefaults()
	{
		PlayerChannelEventBaseMock mock = new Stub();
		assertNotNull(mock);
		assertSafeDefault(mock.getChannel());
		assertSafeDefault(mock.getHandlers());
	}

	private static class Stub implements PlayerChannelEventBaseMock
	{
	}
}
