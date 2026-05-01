// Auto-generated mechanical sanity test for PlayerSettingsChangedEventBaseMock
package org.mockmc.mockmc.generated.proxy.com.velocitypowered.api.event.player;

import org.junit.jupiter.api.Test;
import org.mockmc.mockmc.generated.GeneratedTestBase;

public class PlayerSettingsChangedEventBaseMockTest extends GeneratedTestBase
{
	@Test
	public void testSafeDefaults()
	{
		PlayerSettingsChangedEventBaseMock mock = new PlayerSettingsChangedEventBaseMock()
		{
		};
		assertSafeDefault(mock.toString());
		assertSafeDefault(mock.getPlayer());
		assertSafeDefault(mock.getPlayerSettings());
	}
}
