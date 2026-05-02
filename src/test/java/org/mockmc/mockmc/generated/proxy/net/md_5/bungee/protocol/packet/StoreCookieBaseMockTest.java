// Auto-generated mechanical sanity test for StoreCookieBaseMock
package org.mockmc.mockmc.generated.proxy.net.md_5.bungee.protocol.packet;

import org.junit.jupiter.api.Test;
import org.mockmc.mockmc.generated.GeneratedTestBase;

public class StoreCookieBaseMockTest extends GeneratedTestBase
{
	@Test
	public void testSafeDefaults()
	{
		StoreCookieBaseMock mock = new StoreCookieBaseMock()
		{
		};
		assertNotNull(mock);
		assertSafeDefault(mock.toString());
		assertSafeDefault(mock.getKey());
		assertSafeDefault(mock.getData());
	}
}
