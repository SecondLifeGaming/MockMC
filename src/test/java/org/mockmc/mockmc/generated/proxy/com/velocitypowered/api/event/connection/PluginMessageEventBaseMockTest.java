// Auto-generated mechanical sanity test for PluginMessageEventBaseMock
package org.mockmc.mockmc.generated.proxy.com.velocitypowered.api.event.connection;

import org.junit.jupiter.api.Test;
import org.mockmc.mockmc.generated.GeneratedTestBase;

public class PluginMessageEventBaseMockTest extends GeneratedTestBase
{
	@Test
	public void testSafeDefaults()
	{
		PluginMessageEventBaseMock mock = new PluginMessageEventBaseMock()
		{
		};
		assertNotNull(mock);
		assertSafeDefault(mock.toString());
		assertSafeDefault(mock.getTarget());
		assertSafeDefault(mock.getResult());
		assertSafeDefault(mock.getData());
		assertSafeDefault(mock.getSource());
		assertSafeDefault(mock.getIdentifier());
		assertSafeDefault(mock.dataAsInputStream());
		assertSafeDefault(mock.dataAsDataStream());
	}
}
