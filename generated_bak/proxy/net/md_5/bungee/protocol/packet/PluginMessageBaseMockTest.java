// Auto-generated mechanical sanity test for PluginMessageBaseMock
package org.mockmc.mockmc.generated.proxy.net.md_5.bungee.protocol.packet;

import org.junit.jupiter.api.Test;
import org.mockmc.mockmc.generated.GeneratedTestBase;

public class PluginMessageBaseMockTest extends GeneratedTestBase
{
	@Test
	public void testSafeDefaults()
	{
		PluginMessageBaseMock mock = new PluginMessageBaseMock()
		{
		};
		assertSafeDefault(mock.toString());
		assertSafeDefault(mock.getData());
		assertSafeDefault(mock.getTag());
		assertSafeDefault(mock.getStream());
	}
}
