// Auto-generated mechanical sanity test for PlayerSettingsBaseMock
package org.mockmc.mockmc.generated.proxy.com.velocitypowered.api.proxy.player;

import org.junit.jupiter.api.Test;
import org.mockmc.mockmc.generated.GeneratedTestBase;

public class PlayerSettingsBaseMockTest extends GeneratedTestBase
{
	@Test
	public void testSafeDefaults()
	{
		PlayerSettingsBaseMock mock = new PlayerSettingsBaseMock()
		{
		};
		assertNotNull(mock);
		assertSafeDefault(mock.getLocale());
		assertSafeDefault(mock.getMainHand());
		assertSafeDefault(mock.getSkinParts());
		assertSafeDefault(mock.getChatMode());
		assertSafeDefault(mock.getParticleStatus());
	}
}
