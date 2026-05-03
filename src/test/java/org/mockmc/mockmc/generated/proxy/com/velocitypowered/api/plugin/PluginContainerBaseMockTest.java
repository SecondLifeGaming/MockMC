// Auto-generated mechanical sanity test for PluginContainerBaseMock
package org.mockmc.mockmc.generated.proxy.com.velocitypowered.api.plugin;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.mockmc.mockmc.generated.GeneratedTestBase;

public class PluginContainerBaseMockTest extends GeneratedTestBase
{
	@Test
	public void testSafeDefaults() throws Exception
	{
		PluginContainerBaseMock mock = new Stub();
		assertNotNull(mock);
		assertSafeDefault(mock.getInstance());
		assertSafeDefault(mock.getDescription());
		assertSafeDefault(mock.getExecutorService());
	}

	private static class Stub implements PluginContainerBaseMock
	{
	}
}
