// Auto-generated mechanical sanity test for PlayerChatEventBaseMock
package org.mockmc.mockmc.generated.proxy.com.velocitypowered.api.event.player;

import org.junit.jupiter.api.Test;
import org.mockmc.mockmc.generated.GeneratedTestBase;

public class PlayerChatEventBaseMockTest extends GeneratedTestBase
{
	@Test
	public void testSafeDefaults()
	{
		PlayerChatEventBaseMock mock = new PlayerChatEventBaseMock()
		{
		};
		assertSafeDefault(mock.toString());
		assertSafeDefault(mock.getMessage());
		assertSafeDefault(mock.getResult());
		assertSafeDefault(mock.getPlayer());
	}
}
