// Auto-generated mechanical sanity test for ConnectionRequestBuilderBaseMock
package org.mockmc.mockmc.generated.proxy.com.velocitypowered.api.proxy;

import org.junit.jupiter.api.Test;
import org.mockmc.mockmc.generated.GeneratedTestBase;

public class ConnectionRequestBuilderBaseMockTest extends GeneratedTestBase
{
	@Test
	public void testSafeDefaults()
	{
		ConnectionRequestBuilderBaseMock mock = new ConnectionRequestBuilderBaseMock()
		{
		};
		assertSafeDefault(mock.connect());
		assertSafeDefault(mock.getServer());
		assertSafeDefault(mock.connectWithIndication());
	}
}
