// Auto-generated mechanical sanity test for ChatColorBaseMock
package org.mockmc.mockmc.generated.proxy.net.md_5.bungee.api;

import org.junit.jupiter.api.Test;
import org.mockmc.mockmc.generated.GeneratedTestBase;

public class ChatColorBaseMockTest extends GeneratedTestBase
{
	@Test
	public void testSafeDefaults()
	{
		ChatColorBaseMock mock = new ChatColorBaseMock()
		{
		};
		assertNotNull(mock);
		assertSafeDefault(mock.getName());
		assertSafeDefault(mock.name());
		assertSafeDefault(mock.toString());
		assertSafeDefault(mock.getColor());
	}
}
