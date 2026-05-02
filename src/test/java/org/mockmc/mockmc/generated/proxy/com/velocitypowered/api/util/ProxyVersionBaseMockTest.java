// Auto-generated mechanical sanity test for ProxyVersionBaseMock
package org.mockmc.mockmc.generated.proxy.com.velocitypowered.api.util;

import org.junit.jupiter.api.Test;
import org.mockmc.mockmc.generated.GeneratedTestBase;

public class ProxyVersionBaseMockTest extends GeneratedTestBase
{
	@Test
	public void testSafeDefaults()
	{
		ProxyVersionBaseMock mock = new ProxyVersionBaseMock()
		{
		};
		assertNotNull(mock);
		assertSafeDefault(mock.getName());
		assertSafeDefault(mock.toString());
		assertSafeDefault(mock.getVersion());
		assertSafeDefault(mock.getVendor());
	}
}
