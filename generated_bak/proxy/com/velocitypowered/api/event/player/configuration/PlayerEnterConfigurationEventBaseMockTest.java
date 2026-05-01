// Auto-generated mechanical sanity test for PlayerEnterConfigurationEventBaseMock
package org.mockmc.mockmc.generated.proxy.com.velocitypowered.api.event.player.configuration;

import org.junit.jupiter.api.Test;
import org.mockmc.mockmc.generated.GeneratedTestBase;

public class PlayerEnterConfigurationEventBaseMockTest extends GeneratedTestBase
{
	@Test
	public void testSafeDefaults()
	{
		PlayerEnterConfigurationEventBaseMock mock = new PlayerEnterConfigurationEventBaseMock()
		{
		};
		assertSafeDefault(mock.toString());
		assertSafeDefault(mock.server());
		assertSafeDefault(mock.player());
	}
}
