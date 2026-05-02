// Auto-generated mechanical sanity test for CookieResponseBaseMock
package org.mockmc.mockmc.generated.proxy.net.md_5.bungee.protocol.packet;

import org.junit.jupiter.api.Test;
import org.mockmc.mockmc.generated.GeneratedTestBase;

public class CookieResponseBaseMockTest extends GeneratedTestBase
{
	@Test
	public void testSafeDefaults()
	{
		CookieResponseBaseMock mock = new CookieResponseBaseMock()
		{
		};
		assertNotNull(mock);
		assertSafeDefault(mock.toString());
		assertSafeDefault(mock.getData());
		assertSafeDefault(mock.getCookie());
	}
}
