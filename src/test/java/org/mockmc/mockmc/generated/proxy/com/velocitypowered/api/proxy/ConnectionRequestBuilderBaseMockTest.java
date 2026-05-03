// Auto-generated mechanical sanity test for ConnectionRequestBuilderBaseMock
package org.mockmc.mockmc.generated.proxy.com.velocitypowered.api.proxy;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.mockmc.mockmc.generated.GeneratedTestBase;

public class ConnectionRequestBuilderBaseMockTest extends GeneratedTestBase
{
	@Test
	public void testSafeDefaults() throws Exception
	{
		ConnectionRequestBuilderBaseMock mock = new Stub();
		assertNotNull(mock);
		assertSafeDefault(mock.connect());
		assertSafeDefault(mock.getServer());
		assertSafeDefault(mock.connectWithIndication());
	}

	private static class Stub implements ConnectionRequestBuilderBaseMock
	{
	}
}
