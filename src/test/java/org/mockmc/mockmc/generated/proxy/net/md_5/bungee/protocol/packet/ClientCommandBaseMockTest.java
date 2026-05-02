// Auto-generated mechanical sanity test for ClientCommandBaseMock
package org.mockmc.mockmc.generated.proxy.net.md_5.bungee.protocol.packet;

import org.junit.jupiter.api.Test;
import org.mockmc.mockmc.generated.GeneratedTestBase;

public class ClientCommandBaseMockTest extends GeneratedTestBase
{
	@Test
	public void testSafeDefaults()
	{
		ClientCommandBaseMock mock = new ClientCommandBaseMock()
		{
		};
		assertNotNull(mock);
		assertSafeDefault(mock.toString());
		assertSafeDefault(mock.getCommand());
		assertSafeDefault(mock.getChain());
		assertSafeDefault(mock.getSignatures());
		assertSafeDefault(mock.getSeenMessages());
	}
}
