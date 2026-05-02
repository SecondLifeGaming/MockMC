// Auto-generated mechanical sanity test for ForgeServerHandlerBaseMock
package org.mockmc.mockmc.generated.proxy.net.md_5.bungee.forge;

import org.junit.jupiter.api.Test;
import org.mockmc.mockmc.generated.GeneratedTestBase;

public class ForgeServerHandlerBaseMockTest extends GeneratedTestBase
{
	@Test
	public void testSafeDefaults()
	{
		ForgeServerHandlerBaseMock mock = new ForgeServerHandlerBaseMock()
		{
		};
		assertNotNull(mock);
		assertSafeDefault(mock.getState());
		assertSafeDefault(mock.getCh());
	}
}
