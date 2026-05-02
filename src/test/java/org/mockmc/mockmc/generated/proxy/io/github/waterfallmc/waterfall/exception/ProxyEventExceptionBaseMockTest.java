// Auto-generated mechanical sanity test for ProxyEventExceptionBaseMock
package org.mockmc.mockmc.generated.proxy.io.github.waterfallmc.waterfall.exception;

import org.junit.jupiter.api.Test;
import org.mockmc.mockmc.generated.GeneratedTestBase;

public class ProxyEventExceptionBaseMockTest extends GeneratedTestBase
{
	@Test
	public void testSafeDefaults()
	{
		ProxyEventExceptionBaseMock mock = new ProxyEventExceptionBaseMock()
		{
		};
		assertNotNull(mock);
		assertSafeDefault(mock.getEvent());
		assertSafeDefault(mock.getListener());
	}
}
