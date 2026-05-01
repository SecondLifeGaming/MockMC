// Auto-generated mechanical sanity test for PlayerEnteredConfigurationEventBaseMock
package org.mockmc.mockmc.generated.proxy.com.velocitypowered.api.event.player.configuration;

import org.junit.jupiter.api.Test;
import org.mockmc.mockmc.generated.GeneratedTestBase;

public class PlayerEnteredConfigurationEventBaseMockTest extends GeneratedTestBase
{
	@Test
	public void testSafeDefaults()
	{
		PlayerEnteredConfigurationEventBaseMock mock = new PlayerEnteredConfigurationEventBaseMock()
		{
		};
		assertSafeDefault(mock.toString());
		assertSafeDefault(mock.server());
		assertSafeDefault(mock.player());
	}
}
