// Auto-generated mechanical sanity test for ClientChatBaseMock
package org.mockmc.mockmc.generated.proxy.net.md_5.bungee.protocol.packet;

import org.junit.jupiter.api.Test;
import org.mockmc.mockmc.generated.GeneratedTestBase;

public class ClientChatBaseMockTest extends GeneratedTestBase
{
	@Test
	public void testSafeDefaults()
	{
		ClientChatBaseMock mock = new ClientChatBaseMock()
		{
		};
		assertNotNull(mock);
		assertSafeDefault(mock.toString());
		assertSafeDefault(mock.getMessage());
		assertSafeDefault(mock.getSignature());
		assertSafeDefault(mock.getChain());
		assertSafeDefault(mock.getSeenMessages());
	}
}
