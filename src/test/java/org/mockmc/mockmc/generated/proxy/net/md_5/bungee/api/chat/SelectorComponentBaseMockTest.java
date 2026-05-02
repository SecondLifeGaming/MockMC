// Auto-generated mechanical sanity test for SelectorComponentBaseMock
package org.mockmc.mockmc.generated.proxy.net.md_5.bungee.api.chat;

import org.junit.jupiter.api.Test;
import org.mockmc.mockmc.generated.GeneratedTestBase;

public class SelectorComponentBaseMockTest extends GeneratedTestBase
{
	@Test
	public void testSafeDefaults()
	{
		SelectorComponentBaseMock mock = new SelectorComponentBaseMock()
		{
		};
		assertNotNull(mock);
		assertSafeDefault(mock.toString());
		assertSafeDefault(mock.duplicate());
		assertSafeDefault(mock.getSeparator());
		assertSafeDefault(mock.getSelector());
	}
}
