// Auto-generated mechanical sanity test for ChannelIdentifierBaseMock
package org.mockmc.mockmc.generated.proxy.com.velocitypowered.api.proxy.messages;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.mockmc.mockmc.generated.GeneratedTestBase;

class ChannelIdentifierBaseMockTest extends GeneratedTestBase
{
	@Test
	void testSafeDefaults()
	{
		ChannelIdentifierBaseMock mock = new Stub();
		assertNotNull(mock);
		try
		{
			assertSafeDefault(mock.getId());
		} catch (Exception | LinkageError e)
		{
			// Ignore NPEs and LinkageErrors from Bukkit singletons
		}
	}

	private static class Stub implements ChannelIdentifierBaseMock
	{
	}
}
