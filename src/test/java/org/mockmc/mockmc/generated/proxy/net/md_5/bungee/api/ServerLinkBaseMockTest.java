// Auto-generated mechanical sanity test for ServerLinkBaseMock
package org.mockmc.mockmc.generated.proxy.net.md_5.bungee.api;

import org.junit.jupiter.api.Test;
import org.mockmc.mockmc.generated.GeneratedTestBase;

public class ServerLinkBaseMockTest extends GeneratedTestBase
{
	@Test
	public void testSafeDefaults()
	{
		ServerLinkBaseMock mock = new ServerLinkBaseMock()
		{
		};
		assertSafeDefault(mock.toString());
		assertSafeDefault(mock.getType());
		assertSafeDefault(mock.getLabel());
		assertSafeDefault(mock.getUrl());
	}
}
