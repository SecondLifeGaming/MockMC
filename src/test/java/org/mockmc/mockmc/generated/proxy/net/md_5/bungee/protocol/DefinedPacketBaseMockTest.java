// Auto-generated mechanical sanity test for DefinedPacketBaseMock
package org.mockmc.mockmc.generated.proxy.net.md_5.bungee.protocol;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.mockmc.mockmc.generated.GeneratedTestBase;

class DefinedPacketBaseMockTest extends GeneratedTestBase
{
	@Test
	void testSafeDefaults() throws Exception
	{
		DefinedPacketBaseMock mock = new Stub();
		assertNotNull(mock);
		assertSafeDefault(mock.toString());
		assertSafeDefault(mock.nextProtocol());
	}

	private static class Stub implements DefinedPacketBaseMock
	{
	}
}
