// Auto-generated mechanical sanity test for ChannelWrapperBaseMock
package org.mockmc.mockmc.generated.proxy.net.md_5.bungee.netty;

import org.junit.jupiter.api.Test;
import org.mockmc.mockmc.generated.GeneratedTestBase;

public class ChannelWrapperBaseMockTest extends GeneratedTestBase
{
	@Test
	public void testSafeDefaults()
	{
		ChannelWrapperBaseMock mock = new ChannelWrapperBaseMock()
		{
		};
		assertNotNull(mock);
		assertSafeDefault(mock.getHandle());
		assertSafeDefault(mock.getRemoteAddress());
		assertSafeDefault(mock.getEncodeProtocol());
		assertSafeDefault(mock.getDecodeProtocol());
		assertSafeDefault(mock.getMinecraftDecoder());
		assertSafeDefault(mock.getMinecraftEncoder());
	}
}
