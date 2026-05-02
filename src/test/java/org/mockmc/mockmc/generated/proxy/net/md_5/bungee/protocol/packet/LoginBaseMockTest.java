// Auto-generated mechanical sanity test for LoginBaseMock
package org.mockmc.mockmc.generated.proxy.net.md_5.bungee.protocol.packet;

import org.junit.jupiter.api.Test;
import org.mockmc.mockmc.generated.GeneratedTestBase;

public class LoginBaseMockTest extends GeneratedTestBase
{
	@Test
	public void testSafeDefaults()
	{
		LoginBaseMock mock = new LoginBaseMock()
		{
		};
		assertNotNull(mock);
		assertSafeDefault(mock.toString());
		assertSafeDefault(mock.getWorldName());
		assertSafeDefault(mock.getWorldNames());
		assertSafeDefault(mock.getDimensions());
		assertSafeDefault(mock.getDimension());
		assertSafeDefault(mock.getLevelType());
		assertSafeDefault(mock.getDeathLocation());
	}
}
