// Auto-generated mechanical sanity test for PlayerFinishedConfigurationEventBaseMock
package org.mockmc.mockmc.generated.proxy.com.velocitypowered.api.event.player.configuration;

import org.junit.jupiter.api.Test;
import org.mockmc.mockmc.generated.GeneratedTestBase;

public class PlayerFinishedConfigurationEventBaseMockTest extends GeneratedTestBase
{
	@Test
	public void testSafeDefaults()
	{
		PlayerFinishedConfigurationEventBaseMock mock = new PlayerFinishedConfigurationEventBaseMock()
		{
		};
		assertSafeDefault(mock.toString());
		assertSafeDefault(mock.server());
		assertSafeDefault(mock.player());
	}
}
