// Auto-generated mechanical sanity test for PlayerListItemUpdateBaseMock
package org.mockmc.mockmc.generated.proxy.net.md_5.bungee.protocol.packet;

import org.junit.jupiter.api.Test;
import org.mockmc.mockmc.generated.GeneratedTestBase;

public class PlayerListItemUpdateBaseMockTest extends GeneratedTestBase
{
	@Test
	public void testSafeDefaults()
	{
		PlayerListItemUpdateBaseMock mock = new PlayerListItemUpdateBaseMock()
		{
		};
		assertNotNull(mock);
		assertSafeDefault(mock.toString());
		assertSafeDefault(mock.getActions());
		assertSafeDefault(mock.getItems());
	}
}
