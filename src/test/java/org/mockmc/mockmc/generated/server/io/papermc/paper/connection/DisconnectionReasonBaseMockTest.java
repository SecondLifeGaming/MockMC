// Auto-generated mechanical sanity test for DisconnectionReasonBaseMock
package org.mockmc.mockmc.generated.server.io.papermc.paper.connection;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.mockmc.mockmc.generated.GeneratedTestBase;

@SuppressWarnings("all")
class DisconnectionReasonBaseMockTest extends GeneratedTestBase
{
	@Test
	void testSafeDefaults()
	{
		DisconnectionReasonBaseMock mock = new Stub();
		assertNotNull(mock);
		try
		{
			assertSafeDefault(mock.game());
		} catch (Exception | LinkageError _)
		{
			// Ignore NPEs and LinkageErrors from Bukkit singletons
		}
	}

	private static class Stub implements DisconnectionReasonBaseMock
	{
	}
}
