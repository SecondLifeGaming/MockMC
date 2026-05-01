// Auto-generated mechanical sanity test for CookieRequestEventBaseMock
package org.mockmc.mockmc.generated.proxy.com.velocitypowered.api.event.player;

import org.junit.jupiter.api.Test;
import org.mockmc.mockmc.generated.GeneratedTestBase;

public class CookieRequestEventBaseMockTest extends GeneratedTestBase
{
	@Test
	public void testSafeDefaults()
	{
		CookieRequestEventBaseMock mock = new CookieRequestEventBaseMock()
		{
		};
		assertSafeDefault(mock.toString());
		assertSafeDefault(mock.getResult());
		assertSafeDefault(mock.getPlayer());
		assertSafeDefault(mock.getOriginalKey());
	}
}
