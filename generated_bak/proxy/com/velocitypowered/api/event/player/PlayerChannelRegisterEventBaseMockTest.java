// Auto-generated mechanical sanity test for PlayerChannelRegisterEventBaseMock
package org.mockmc.mockmc.generated.proxy.com.velocitypowered.api.event.player;

import org.junit.jupiter.api.Test;
import org.mockmc.mockmc.generated.GeneratedTestBase;

public class PlayerChannelRegisterEventBaseMockTest extends GeneratedTestBase
{
	@Test
	public void testSafeDefaults()
	{
		PlayerChannelRegisterEventBaseMock mock = new PlayerChannelRegisterEventBaseMock()
		{
		};
		assertSafeDefault(mock.toString());
		assertSafeDefault(mock.getPlayer());
		assertSafeDefault(mock.getChannels());
	}
}
