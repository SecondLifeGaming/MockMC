// Auto-generated mechanical sanity test for YamlConfigBaseMock
package org.mockmc.mockmc.generated.proxy.net.md_5.bungee.conf;

import org.junit.jupiter.api.Test;
import org.mockmc.mockmc.generated.GeneratedTestBase;

public class YamlConfigBaseMockTest extends GeneratedTestBase
{
	@Test
	public void testSafeDefaults()
	{
		YamlConfigBaseMock mock = new YamlConfigBaseMock()
		{
		};
		assertNotNull(mock);
		assertSafeDefault(mock.getListeners());
		assertSafeDefault(mock.getServers());
	}
}
