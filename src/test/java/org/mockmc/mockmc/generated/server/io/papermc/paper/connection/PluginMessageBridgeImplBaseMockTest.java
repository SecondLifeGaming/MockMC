// Auto-generated mechanical sanity test for PluginMessageBridgeImplBaseMock
package org.mockmc.mockmc.generated.server.io.papermc.paper.connection;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.mockmc.mockmc.generated.GeneratedTestBase;

@SuppressWarnings("all")
class PluginMessageBridgeImplBaseMockTest extends GeneratedTestBase
{
	@Test
	void testSafeDefaults()
	{
		PluginMessageBridgeImplBaseMock mock = new Stub();
		assertNotNull(mock);
		try
		{
			assertSafeDefault(mock.channels());
		} catch (Exception | LinkageError _)
		{
			// Ignore NPEs and LinkageErrors from Bukkit singletons
		}
	}

	private static class Stub implements PluginMessageBridgeImplBaseMock
	{
	}
}
