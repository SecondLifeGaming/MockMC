// Auto-generated mechanical sanity test for InboundConnectionBaseMock
package org.mockmc.mockmc.generated.proxy.com.velocitypowered.api.proxy;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.mockmc.mockmc.generated.GeneratedTestBase;

public class InboundConnectionBaseMockTest extends GeneratedTestBase
{
	@Test
	public void testSafeDefaults() throws Exception
	{
		InboundConnectionBaseMock mock = new Stub();
		assertNotNull(mock);
		assertSafeDefault(mock.getProtocolVersion());
		assertSafeDefault(mock.getRemoteAddress());
		assertSafeDefault(mock.getVirtualHost());
		assertSafeDefault(mock.getRawVirtualHost());
		assertSafeDefault(mock.getProtocolState());
		assertSafeDefault(mock.getHandshakeIntent());
	}

	private static class Stub implements InboundConnectionBaseMock
	{
	}
}
