// Auto-generated mechanical sanity test for MinecraftChannelIdentifierBaseMock
package org.mockmc.mockmc.generated.proxy.com.velocitypowered.api.proxy.messages;

import org.junit.jupiter.api.Test;
import org.mockmc.mockmc.generated.GeneratedTestBase;

public class MinecraftChannelIdentifierBaseMockTest extends GeneratedTestBase
{
	@Test
	public void testSafeDefaults()
	{
		MinecraftChannelIdentifierBaseMock mock = new MinecraftChannelIdentifierBaseMock()
		{
		};
		assertNotNull(mock);
		assertSafeDefault(mock.getName());
		assertSafeDefault(mock.toString());
		assertSafeDefault(mock.getId());
		assertSafeDefault(mock.getNamespace());
		assertSafeDefault(mock.asKey());
	}
}
