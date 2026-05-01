// Auto-generated mechanical sanity test for ServerKickEventBaseMock
package org.mockmc.mockmc.generated.proxy.net.md_5.bungee.api.event;

import org.junit.jupiter.api.Test;
import org.mockmc.mockmc.generated.GeneratedTestBase;

public class ServerKickEventBaseMockTest extends GeneratedTestBase
{
	@Test
	public void testSafeDefaults()
	{
		ServerKickEventBaseMock mock = new ServerKickEventBaseMock()
		{
		};
		assertSafeDefault(mock.getCause());
		assertSafeDefault(mock.toString());
		assertSafeDefault(mock.getState());
		assertSafeDefault(mock.getReason());
		assertSafeDefault(mock.getPlayer());
		assertSafeDefault(mock.getKickReasonComponent());
		assertSafeDefault(mock.getKickReason());
		assertSafeDefault(mock.getCancelServer());
		assertSafeDefault(mock.getKickedFrom());
	}
}
