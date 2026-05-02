// Auto-generated mechanical sanity test for ProxyQueryEventBaseMock
package org.mockmc.mockmc.generated.proxy.com.velocitypowered.api.event.query;

import org.junit.jupiter.api.Test;
import org.mockmc.mockmc.generated.GeneratedTestBase;

public class ProxyQueryEventBaseMockTest extends GeneratedTestBase
{
	@Test
	public void testSafeDefaults()
	{
		ProxyQueryEventBaseMock mock = new ProxyQueryEventBaseMock()
		{
		};
		assertNotNull(mock);
		assertSafeDefault(mock.toString());
		assertSafeDefault(mock.getQueryType());
		assertSafeDefault(mock.getQuerierAddress());
		assertSafeDefault(mock.getResponse());
	}
}
