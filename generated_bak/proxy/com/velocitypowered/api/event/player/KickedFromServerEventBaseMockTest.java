// Auto-generated mechanical sanity test for KickedFromServerEventBaseMock
package org.mockmc.mockmc.generated.proxy.com.velocitypowered.api.event.player;

import org.junit.jupiter.api.Test;
import org.mockmc.mockmc.generated.GeneratedTestBase;

public class KickedFromServerEventBaseMockTest extends GeneratedTestBase
{
	@Test
	public void testSafeDefaults()
	{
		KickedFromServerEventBaseMock mock = new KickedFromServerEventBaseMock()
		{
		};
		assertSafeDefault(mock.getResult());
		assertSafeDefault(mock.getPlayer());
		assertSafeDefault(mock.getServer());
		assertSafeDefault(mock.getServerKickReason());
	}
}
