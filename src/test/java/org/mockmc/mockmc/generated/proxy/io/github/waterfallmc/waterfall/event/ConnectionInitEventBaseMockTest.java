// Auto-generated mechanical sanity test for ConnectionInitEventBaseMock
package org.mockmc.mockmc.generated.proxy.io.github.waterfallmc.waterfall.event;

import org.junit.jupiter.api.Test;
import org.mockmc.mockmc.generated.GeneratedTestBase;

public class ConnectionInitEventBaseMockTest extends GeneratedTestBase
{
	@Test
	public void testSafeDefaults()
	{
		ConnectionInitEventBaseMock mock = new ConnectionInitEventBaseMock()
		{
		};
		assertNotNull(mock);
		assertSafeDefault(mock.toString());
		assertSafeDefault(mock.getRemoteAddress());
		assertSafeDefault(mock.getRemoteSocketAddress());
	}
}
