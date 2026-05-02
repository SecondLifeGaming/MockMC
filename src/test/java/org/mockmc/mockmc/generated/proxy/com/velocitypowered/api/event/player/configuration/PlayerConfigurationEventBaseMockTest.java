// Auto-generated mechanical sanity test for PlayerConfigurationEventBaseMock
package org.mockmc.mockmc.generated.proxy.com.velocitypowered.api.event.player.configuration;

import org.junit.jupiter.api.Test;
import org.mockmc.mockmc.generated.GeneratedTestBase;

public class PlayerConfigurationEventBaseMockTest extends GeneratedTestBase
{
	@Test
	public void testSafeDefaults()
	{
		PlayerConfigurationEventBaseMock mock = new PlayerConfigurationEventBaseMock()
		{
		};
		assertNotNull(mock);
		assertSafeDefault(mock.toString());
		assertSafeDefault(mock.server());
		assertSafeDefault(mock.player());
	}
}
