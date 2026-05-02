// Auto-generated mechanical sanity test for EventHandlerMethodBaseMock
package org.mockmc.mockmc.generated.proxy.net.md_5.bungee.event;

import org.junit.jupiter.api.Test;
import org.mockmc.mockmc.generated.GeneratedTestBase;

public class EventHandlerMethodBaseMockTest extends GeneratedTestBase
{
	@Test
	public void testSafeDefaults()
	{
		EventHandlerMethodBaseMock mock = new EventHandlerMethodBaseMock()
		{
		};
		assertNotNull(mock);
		assertSafeDefault(mock.getMethod());
		assertSafeDefault(mock.getListener());
	}
}
