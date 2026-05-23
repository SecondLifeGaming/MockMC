// Auto-generated mechanical sanity test for MessageBaseMock
package org.mockmc.mockmc.generated.server.com.mojang.brigadier;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.mockmc.mockmc.generated.GeneratedTestBase;

class MessageBaseMockTest extends GeneratedTestBase
{
	@Test
	void testSafeDefaults()
	{
		MessageBaseMock mock = new Stub();
		assertNotNull(mock);
		try
		{
			assertSafeDefault(mock.getString());
		} catch (Exception | LinkageError e)
		{
			// Ignore NPEs and LinkageErrors from Bukkit singletons
		}
	}

	private static class Stub implements MessageBaseMock
	{
	}
}
