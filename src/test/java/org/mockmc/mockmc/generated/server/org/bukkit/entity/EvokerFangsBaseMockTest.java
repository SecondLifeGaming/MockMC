// Auto-generated mechanical sanity test for EvokerFangsBaseMock
package org.mockmc.mockmc.generated.server.org.bukkit.entity;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.mockmc.mockmc.generated.GeneratedTestBase;

class EvokerFangsBaseMockTest extends GeneratedTestBase
{
	@Test
	void testSafeDefaults()
	{
		EvokerFangsBaseMock mock = new Stub();
		assertNotNull(mock);
		try
		{
			assertSafeDefault(mock.getOwner());
		} catch (Exception | LinkageError e)
		{
			// Ignore NPEs and LinkageErrors from Bukkit singletons
		}
	}

	private static class Stub implements EvokerFangsBaseMock
	{
	}
}
