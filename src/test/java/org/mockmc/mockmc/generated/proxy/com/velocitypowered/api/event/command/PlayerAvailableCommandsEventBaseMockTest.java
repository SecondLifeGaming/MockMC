// Auto-generated mechanical sanity test for PlayerAvailableCommandsEventBaseMock
package org.mockmc.mockmc.generated.proxy.com.velocitypowered.api.event.command;

import org.junit.jupiter.api.Test;
import org.mockmc.mockmc.generated.GeneratedTestBase;

public class PlayerAvailableCommandsEventBaseMockTest extends GeneratedTestBase
{
	@Test
	public void testSafeDefaults()
	{
		PlayerAvailableCommandsEventBaseMock mock = new PlayerAvailableCommandsEventBaseMock()
		{
		};
		assertSafeDefault(mock.getPlayer());
		assertSafeDefault(mock.getRootNode());
	}
}
