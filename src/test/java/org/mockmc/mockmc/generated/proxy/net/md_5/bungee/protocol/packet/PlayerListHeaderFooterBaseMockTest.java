// Auto-generated mechanical sanity test for PlayerListHeaderFooterBaseMock
package org.mockmc.mockmc.generated.proxy.net.md_5.bungee.protocol.packet;

import org.junit.jupiter.api.Test;
import org.mockmc.mockmc.generated.GeneratedTestBase;

public class PlayerListHeaderFooterBaseMockTest extends GeneratedTestBase
{
	@Test
	public void testSafeDefaults()
	{
		PlayerListHeaderFooterBaseMock mock = new PlayerListHeaderFooterBaseMock()
		{
		};
		assertSafeDefault(mock.toString());
		assertSafeDefault(mock.getHeader());
		assertSafeDefault(mock.getFooter());
	}
}
