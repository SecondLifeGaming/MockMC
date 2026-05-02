// Auto-generated mechanical sanity test for PlayerFinishConfigurationEventBaseMock
package org.mockmc.mockmc.generated.proxy.com.velocitypowered.api.event.player.configuration;

import org.junit.jupiter.api.Test;
import org.mockmc.mockmc.generated.GeneratedTestBase;

public class PlayerFinishConfigurationEventBaseMockTest extends GeneratedTestBase
{
	@Test
	public void testSafeDefaults()
	{
		PlayerFinishConfigurationEventBaseMock mock = new PlayerFinishConfigurationEventBaseMock()
		{
		};
		assertNotNull(mock);
		assertSafeDefault(mock.toString());
		assertSafeDefault(mock.server());
		assertSafeDefault(mock.player());
	}
}
