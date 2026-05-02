// Auto-generated mechanical sanity test for KeybindComponentBaseMock
package org.mockmc.mockmc.generated.proxy.net.md_5.bungee.api.chat;

import org.junit.jupiter.api.Test;
import org.mockmc.mockmc.generated.GeneratedTestBase;

public class KeybindComponentBaseMockTest extends GeneratedTestBase
{
	@Test
	public void testSafeDefaults()
	{
		KeybindComponentBaseMock mock = new KeybindComponentBaseMock()
		{
		};
		assertNotNull(mock);
		assertSafeDefault(mock.toString());
		assertSafeDefault(mock.duplicate());
		assertSafeDefault(mock.getKeybind());
	}
}
