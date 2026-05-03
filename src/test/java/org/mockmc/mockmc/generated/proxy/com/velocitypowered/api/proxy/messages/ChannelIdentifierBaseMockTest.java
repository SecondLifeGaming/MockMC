// Auto-generated mechanical sanity test for ChannelIdentifierBaseMock
package org.mockmc.mockmc.generated.proxy.com.velocitypowered.api.proxy.messages;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.mockmc.mockmc.generated.GeneratedTestBase;

class ChannelIdentifierBaseMockTest extends GeneratedTestBase
{
	@Test
	void testSafeDefaults() throws Exception
	{
		ChannelIdentifierBaseMock mock = new Stub();
		assertNotNull(mock);
		assertSafeDefault(mock.getId());
	}

	private static class Stub implements ChannelIdentifierBaseMock
	{
	}
}
