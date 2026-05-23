// Auto-generated mechanical sanity test for SignedMessageResolverBaseMock
package org.mockmc.mockmc.generated.server.io.papermc.paper.command.brigadier.argument;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.mockmc.mockmc.generated.GeneratedTestBase;

class SignedMessageResolverBaseMockTest extends GeneratedTestBase
{
	@Test
	void testSafeDefaults()
	{
		SignedMessageResolverBaseMock mock = new Stub();
		assertNotNull(mock);
		try
		{
			assertSafeDefault(mock.content());
		} catch (Exception | LinkageError e)
		{
			// Ignore NPEs and LinkageErrors from Bukkit singletons
		}
	}

	private static class Stub implements SignedMessageResolverBaseMock
	{
	}
}
