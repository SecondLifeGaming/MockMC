// Auto-generated mechanical sanity test for ChatBaseMock
package org.mockmc.mockmc.generated.proxy.net.md_5.bungee.protocol.packet;

import org.junit.jupiter.api.Test;
import org.mockmc.mockmc.generated.GeneratedTestBase;

public class ChatBaseMockTest extends GeneratedTestBase
{
	@Test
	public void testSafeDefaults()
	{
		ChatBaseMock mock = new ChatBaseMock()
		{
		};
		assertNotNull(mock);
		assertSafeDefault(mock.toString());
		assertSafeDefault(mock.getMessage());
		assertSafeDefault(mock.getSender());
	}
}
