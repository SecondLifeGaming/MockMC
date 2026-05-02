// Auto-generated mechanical sanity test for GameProfileRequestEventBaseMock
package org.mockmc.mockmc.generated.proxy.com.velocitypowered.api.event.player;

import org.junit.jupiter.api.Test;
import org.mockmc.mockmc.generated.GeneratedTestBase;

public class GameProfileRequestEventBaseMockTest extends GeneratedTestBase
{
	@Test
	public void testSafeDefaults()
	{
		GameProfileRequestEventBaseMock mock = new GameProfileRequestEventBaseMock()
		{
		};
		assertNotNull(mock);
		assertSafeDefault(mock.toString());
		assertSafeDefault(mock.getConnection());
		assertSafeDefault(mock.getGameProfile());
		assertSafeDefault(mock.getUsername());
		assertSafeDefault(mock.getOriginalProfile());
	}
}
