// Auto-generated mechanical sanity test for UnsignedClientCommandBaseMock
package org.mockmc.mockmc.generated.proxy.net.md_5.bungee.protocol.packet;

import org.junit.jupiter.api.Test;
import org.mockmc.mockmc.generated.GeneratedTestBase;

public class UnsignedClientCommandBaseMockTest extends GeneratedTestBase
{
	@Test
	public void testSafeDefaults()
	{
		UnsignedClientCommandBaseMock mock = new UnsignedClientCommandBaseMock()
		{
		};
		assertNotNull(mock);
		assertSafeDefault(mock.toString());
		assertSafeDefault(mock.getCommand());
	}
}
