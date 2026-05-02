// Auto-generated mechanical sanity test for ProxyPluginMessageExceptionBaseMock
package org.mockmc.mockmc.generated.proxy.io.github.waterfallmc.waterfall.exception;

import org.junit.jupiter.api.Test;
import org.mockmc.mockmc.generated.GeneratedTestBase;

public class ProxyPluginMessageExceptionBaseMockTest extends GeneratedTestBase
{
	@Test
	public void testSafeDefaults()
	{
		ProxyPluginMessageExceptionBaseMock mock = new ProxyPluginMessageExceptionBaseMock()
		{
		};
		assertNotNull(mock);
		assertSafeDefault(mock.getChannel());
		assertSafeDefault(mock.getData());
		assertSafeDefault(mock.getPlayer());
	}
}
