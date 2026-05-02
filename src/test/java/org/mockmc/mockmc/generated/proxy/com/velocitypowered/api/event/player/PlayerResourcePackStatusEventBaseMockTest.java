// Auto-generated mechanical sanity test for PlayerResourcePackStatusEventBaseMock
package org.mockmc.mockmc.generated.proxy.com.velocitypowered.api.event.player;

import org.junit.jupiter.api.Test;
import org.mockmc.mockmc.generated.GeneratedTestBase;

public class PlayerResourcePackStatusEventBaseMockTest extends GeneratedTestBase
{
	@Test
	public void testSafeDefaults()
	{
		PlayerResourcePackStatusEventBaseMock mock = new PlayerResourcePackStatusEventBaseMock()
		{
		};
		assertNotNull(mock);
		assertSafeDefault(mock.toString());
		assertSafeDefault(mock.getPlayer());
		assertSafeDefault(mock.getStatus());
		assertSafeDefault(mock.getPackId());
		assertSafeDefault(mock.getPackInfo());
	}
}
