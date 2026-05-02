// Auto-generated mechanical sanity test for PlayerHandshakeEventBaseMock
package org.mockmc.mockmc.generated.proxy.net.md_5.bungee.api.event;

import org.junit.jupiter.api.Test;
import org.mockmc.mockmc.generated.GeneratedTestBase;

public class PlayerHandshakeEventBaseMockTest extends GeneratedTestBase
{
	@Test
	public void testSafeDefaults()
	{
		PlayerHandshakeEventBaseMock mock = new PlayerHandshakeEventBaseMock()
		{
		};
		assertNotNull(mock);
		assertSafeDefault(mock.toString());
		assertSafeDefault(mock.getConnection());
		assertSafeDefault(mock.getHandshake());
	}
}
