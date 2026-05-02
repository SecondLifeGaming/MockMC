// Auto-generated mechanical sanity test for StartConfigurationBaseMock
package org.mockmc.mockmc.generated.proxy.net.md_5.bungee.protocol.packet;

import org.junit.jupiter.api.Test;
import org.mockmc.mockmc.generated.GeneratedTestBase;

public class StartConfigurationBaseMockTest extends GeneratedTestBase
{
	@Test
	public void testSafeDefaults()
	{
		StartConfigurationBaseMock mock = new StartConfigurationBaseMock()
		{
		};
		assertNotNull(mock);
		assertSafeDefault(mock.toString());
		assertSafeDefault(mock.nextProtocol());
	}
}
