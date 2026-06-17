// Auto-generated mechanical sanity test for ApiMirrorRootNodeBaseMock
package org.mockmc.mockmc.generated.server.io.papermc.paper.command.brigadier;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.mockmc.mockmc.generated.GeneratedTestBase;

class ApiMirrorRootNodeBaseMockTest extends GeneratedTestBase
{
	@Test
	void testSafeDefaults()
	{
		ApiMirrorRootNodeBaseMock mock = new Stub();
		assertNotNull(mock);
		try
		{
			assertSafeDefault(mock.getChildren());
		} catch (Exception | LinkageError _)
		{
			// Ignore NPEs and LinkageErrors from Bukkit singletons
		}
		try
		{
			assertSafeDefault(mock.getDispatcher());
		} catch (Exception | LinkageError _)
		{
			// Ignore NPEs and LinkageErrors from Bukkit singletons
		}
	}

	private static class Stub implements ApiMirrorRootNodeBaseMock
	{
	}
}
