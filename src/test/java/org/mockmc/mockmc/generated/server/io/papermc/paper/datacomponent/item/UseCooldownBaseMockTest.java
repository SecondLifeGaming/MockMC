// Auto-generated mechanical sanity test for UseCooldownBaseMock
package org.mockmc.mockmc.generated.server.io.papermc.paper.datacomponent.item;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.mockmc.mockmc.generated.GeneratedTestBase;

class UseCooldownBaseMockTest extends GeneratedTestBase
{
	@Test
	void testSafeDefaults()
	{
		UseCooldownBaseMock mock = new Stub();
		assertNotNull(mock);
		try
		{
			assertSafeDefault(mock.cooldownGroup());
		} catch (Exception | LinkageError e)
		{
			// Ignore NPEs and LinkageErrors from Bukkit singletons
		}
	}

	private static class Stub implements UseCooldownBaseMock
	{
	}
}
