// Auto-generated mechanical sanity test for ServerLinkBaseMock
package org.mockmc.mockmc.generated.proxy.com.velocitypowered.api.util;

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
		assertSafeDefault(mock.getUrl());
		assertSafeDefault(mock.getCustomLabel());
		assertSafeDefault(mock.getBuiltInType());
	}
}
