// Auto-generated mechanical sanity test for PlayerSettingsBaseMock
package org.mockmc.mockmc.generated.proxy.com.velocitypowered.api.proxy.player;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.mockmc.mockmc.generated.GeneratedTestBase;

class PlayerSettingsBaseMockTest extends GeneratedTestBase
{
	@Test
	void testSafeDefaults() throws Exception
	{
		PlayerSettingsBaseMock mock = new Stub();
		assertNotNull(mock);
		assertSafeDefault(mock.getLocale());
		assertSafeDefault(mock.getMainHand());
		assertSafeDefault(mock.getSkinParts());
		assertSafeDefault(mock.getChatMode());
		assertSafeDefault(mock.getParticleStatus());
	}

	private static class Stub implements PlayerSettingsBaseMock
	{
	}
}
