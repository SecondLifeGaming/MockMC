// Auto-generated mechanical sanity test for PlayerConfigurationConnectionBaseMock
package org.mockmc.mockmc.generated.server.io.papermc.paper.connection;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.mockmc.mockmc.generated.GeneratedTestBase;

class PlayerConfigurationConnectionBaseMockTest extends GeneratedTestBase
{
	@Test
	void testSafeDefaults()
	{
		PlayerConfigurationConnectionBaseMock mock = new Stub();
		assertNotNull(mock);
		try
		{
			assertSafeDefault(mock.getAudience());
		} catch (Exception | LinkageError _)
		{
			// Ignore NPEs and LinkageErrors from Bukkit singletons
		}
		try
		{
			assertSafeDefault(mock.getProfile());
		} catch (Exception | LinkageError _)
		{
			// Ignore NPEs and LinkageErrors from Bukkit singletons
		}
	}

	private static class Stub implements PlayerConfigurationConnectionBaseMock
	{
	}
}
