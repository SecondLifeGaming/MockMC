// Auto-generated mechanical sanity test for SerializedPluginDescriptionBaseMock
package org.mockmc.mockmc.generated.proxy.com.velocitypowered.api.plugin.ap;

import org.junit.jupiter.api.Test;
import org.mockmc.mockmc.generated.GeneratedTestBase;

public class SerializedPluginDescriptionBaseMockTest extends GeneratedTestBase
{
	@Test
	public void testSafeDefaults()
	{
		SerializedPluginDescriptionBaseMock mock = new SerializedPluginDescriptionBaseMock()
		{
		};
		assertNotNull(mock);
		assertSafeDefault(mock.getName());
		assertSafeDefault(mock.toString());
		assertSafeDefault(mock.getId());
		assertSafeDefault(mock.getVersion());
		assertSafeDefault(mock.getDescription());
		assertSafeDefault(mock.getUrl());
		assertSafeDefault(mock.getAuthors());
		assertSafeDefault(mock.getMain());
		assertSafeDefault(mock.getDependencies());
	}
}
