// Auto-generated mechanical sanity test for ProfileBaseMock
package org.mockmc.mockmc.generated.proxy.net.md_5.bungee.api.chat.player;

import org.junit.jupiter.api.Test;
import org.mockmc.mockmc.generated.GeneratedTestBase;

public class ProfileBaseMockTest extends GeneratedTestBase
{
	@Test
	public void testSafeDefaults()
	{
		ProfileBaseMock mock = new ProfileBaseMock()
		{
		};
		assertNotNull(mock);
		assertSafeDefault(mock.getName());
		assertSafeDefault(mock.toString());
		assertSafeDefault(mock.getProperties());
		assertSafeDefault(mock.getUuid());
	}
}
