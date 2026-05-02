// Auto-generated mechanical sanity test for ListenerCloseEventBaseMock
package org.mockmc.mockmc.generated.proxy.com.velocitypowered.api.event.proxy;

import org.junit.jupiter.api.Test;
import org.mockmc.mockmc.generated.GeneratedTestBase;

public class ListenerCloseEventBaseMockTest extends GeneratedTestBase
{
	@Test
	public void testSafeDefaults()
	{
		ListenerCloseEventBaseMock mock = new ListenerCloseEventBaseMock()
		{
		};
		assertNotNull(mock);
		assertSafeDefault(mock.toString());
		assertSafeDefault(mock.getAddress());
		assertSafeDefault(mock.getListenerType());
	}
}
