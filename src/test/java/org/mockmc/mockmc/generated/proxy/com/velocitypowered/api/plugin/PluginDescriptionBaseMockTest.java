// Auto-generated mechanical sanity test for PluginDescriptionBaseMock
package org.mockmc.mockmc.generated.proxy.com.velocitypowered.api.plugin;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.mockmc.mockmc.generated.GeneratedTestBase;

class PluginDescriptionBaseMockTest extends GeneratedTestBase
{
	@Test
	void testSafeDefaults() throws Exception
	{
		PluginDescriptionBaseMock mock = new Stub();
		assertNotNull(mock);
		assertSafeDefault(mock.getName());
		assertSafeDefault(mock.getId());
		assertSafeDefault(mock.getVersion());
		assertSafeDefault(mock.getSource());
		assertSafeDefault(mock.getDescription());
		assertSafeDefault(mock.getUrl());
		assertSafeDefault(mock.getAuthors());
		assertSafeDefault(mock.getDependencies());
	}

	private static class Stub implements PluginDescriptionBaseMock
	{
	}
}
