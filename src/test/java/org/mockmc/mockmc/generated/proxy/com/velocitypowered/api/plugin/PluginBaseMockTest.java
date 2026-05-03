// Auto-generated mechanical sanity test for PluginBaseMock
package org.mockmc.mockmc.generated.proxy.com.velocitypowered.api.plugin;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.mockmc.mockmc.generated.GeneratedTestBase;

class PluginBaseMockTest extends GeneratedTestBase
{
	@Test
	void testSafeDefaults() throws Exception
	{
		PluginBaseMock mock = new Stub();
		assertNotNull(mock);
		assertSafeDefault(mock.name());
		assertSafeDefault(mock.version());
		assertSafeDefault(mock.url());
		assertSafeDefault(mock.id());
		assertSafeDefault(mock.description());
		assertSafeDefault(mock.authors());
		assertSafeDefault(mock.dependencies());
	}

	private static class Stub implements PluginBaseMock
	{
	}
}
