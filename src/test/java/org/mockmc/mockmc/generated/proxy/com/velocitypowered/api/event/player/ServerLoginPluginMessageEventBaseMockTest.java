// Auto-generated mechanical sanity test for ServerLoginPluginMessageEventBaseMock
package org.mockmc.mockmc.generated.proxy.com.velocitypowered.api.event.player;

import org.junit.jupiter.api.Test;
import org.mockmc.mockmc.generated.GeneratedTestBase;

public class ServerLoginPluginMessageEventBaseMockTest extends GeneratedTestBase
{
	@Test
	public void testSafeDefaults()
	{
		ServerLoginPluginMessageEventBaseMock mock = new ServerLoginPluginMessageEventBaseMock()
		{
		};
		assertNotNull(mock);
		assertSafeDefault(mock.toString());
		assertSafeDefault(mock.getResult());
		assertSafeDefault(mock.getContents());
		assertSafeDefault(mock.getIdentifier());
		assertSafeDefault(mock.getConnection());
		assertSafeDefault(mock.contentsAsInputStream());
		assertSafeDefault(mock.contentsAsDataStream());
	}
}
