// Auto-generated mechanical sanity test for ComplexLivingEntityBaseMock
package org.mockmc.mockmc.generated.server.org.bukkit.entity;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.mockmc.mockmc.generated.GeneratedTestBase;

class ComplexLivingEntityBaseMockTest extends GeneratedTestBase
{
	@Test
	void testSafeDefaults()
	{
		ComplexLivingEntityBaseMock mock = new Stub();
		assertNotNull(mock);
		try
		{
			assertSafeDefault(mock.getParts());
		} catch (Exception | LinkageError e)
		{
			// Ignore NPEs and LinkageErrors from Bukkit singletons
		}
	}

	private static class Stub implements ComplexLivingEntityBaseMock
	{
	}
}
