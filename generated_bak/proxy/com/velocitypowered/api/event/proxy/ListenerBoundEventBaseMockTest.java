// Auto-generated mechanical sanity test for ListenerBoundEventBaseMock
package org.mockmc.mockmc.generated.proxy.com.velocitypowered.api.event.proxy;

import org.junit.jupiter.api.Test;
import org.mockmc.mockmc.generated.GeneratedTestBase;

public class ListenerBoundEventBaseMockTest extends GeneratedTestBase
{
	@Test
	public void testSafeDefaults()
	{
		ListenerBoundEventBaseMock mock = new ListenerBoundEventBaseMock()
		{
		};
		assertSafeDefault(mock.toString());
		assertSafeDefault(mock.getAddress());
		assertSafeDefault(mock.getListenerType());
	}
}
