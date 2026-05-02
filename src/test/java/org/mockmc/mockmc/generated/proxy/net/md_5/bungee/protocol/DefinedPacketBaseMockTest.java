// Auto-generated mechanical sanity test for DefinedPacketBaseMock
package org.mockmc.mockmc.generated.proxy.net.md_5.bungee.protocol;

import org.junit.jupiter.api.Test;
import org.mockmc.mockmc.generated.GeneratedTestBase;

public class DefinedPacketBaseMockTest extends GeneratedTestBase
{
	@Test
	public void testSafeDefaults()
	{
		DefinedPacketBaseMock mock = new DefinedPacketBaseMock()
		{
		};
		assertNotNull(mock);
		assertSafeDefault(mock.toString());
		assertSafeDefault(mock.nextProtocol());
	}
}
