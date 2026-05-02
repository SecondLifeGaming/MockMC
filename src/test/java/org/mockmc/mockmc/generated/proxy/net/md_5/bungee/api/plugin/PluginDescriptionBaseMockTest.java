// Auto-generated mechanical sanity test for PluginDescriptionBaseMock
package org.mockmc.mockmc.generated.proxy.net.md_5.bungee.api.plugin;

import org.junit.jupiter.api.Test;
import org.mockmc.mockmc.generated.GeneratedTestBase;

public class PluginDescriptionBaseMockTest extends GeneratedTestBase
{
	@Test
	public void testSafeDefaults()
	{
		PluginDescriptionBaseMock mock = new PluginDescriptionBaseMock()
		{
		};
		assertNotNull(mock);
		assertSafeDefault(mock.getName());
		assertSafeDefault(mock.toString());
		assertSafeDefault(mock.getFile());
		assertSafeDefault(mock.getVersion());
		assertSafeDefault(mock.getDescription());
		assertSafeDefault(mock.getAuthor());
		assertSafeDefault(mock.getMain());
		assertSafeDefault(mock.getLibraries());
		assertSafeDefault(mock.getDepends());
		assertSafeDefault(mock.getSoftDepends());
	}
}
