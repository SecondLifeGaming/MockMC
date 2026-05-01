// Auto-generated mechanical sanity test for PlayerClientBrandEventBaseMock
package org.mockmc.mockmc.generated.proxy.com.velocitypowered.api.event.player;

import org.junit.jupiter.api.Test;
import org.mockmc.mockmc.generated.GeneratedTestBase;

public class PlayerClientBrandEventBaseMockTest extends GeneratedTestBase
{
	@Test
	public void testSafeDefaults()
	{
		PlayerClientBrandEventBaseMock mock = new PlayerClientBrandEventBaseMock()
		{
		};
		assertSafeDefault(mock.toString());
		assertSafeDefault(mock.getPlayer());
		assertSafeDefault(mock.getBrand());
	}
}
