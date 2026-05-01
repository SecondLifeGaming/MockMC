// Auto-generated mechanical sanity test for CookieStoreEventBaseMock
package org.mockmc.mockmc.generated.proxy.com.velocitypowered.api.event.player;

import org.junit.jupiter.api.Test;
import org.mockmc.mockmc.generated.GeneratedTestBase;

public class CookieStoreEventBaseMockTest extends GeneratedTestBase
{
	@Test
	public void testSafeDefaults()
	{
		CookieStoreEventBaseMock mock = new CookieStoreEventBaseMock()
		{
		};
		assertSafeDefault(mock.toString());
		assertSafeDefault(mock.getResult());
		assertSafeDefault(mock.getPlayer());
		assertSafeDefault(mock.getOriginalKey());
		assertSafeDefault(mock.getOriginalData());
	}
}
