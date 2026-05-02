// Auto-generated mechanical sanity test for ConfigurationBaseMock
package org.mockmc.mockmc.generated.proxy.net.md_5.bungee.config;

import org.junit.jupiter.api.Test;
import org.mockmc.mockmc.generated.GeneratedTestBase;

public class ConfigurationBaseMockTest extends GeneratedTestBase
{
	@Test
	public void testSafeDefaults()
	{
		ConfigurationBaseMock mock = new ConfigurationBaseMock()
		{
		};
		assertNotNull(mock);
		assertSafeDefault(mock.getKeys());
	}
}
