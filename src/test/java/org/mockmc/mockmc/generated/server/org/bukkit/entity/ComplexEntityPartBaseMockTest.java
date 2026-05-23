// Auto-generated mechanical sanity test for ComplexEntityPartBaseMock
package org.mockmc.mockmc.generated.server.org.bukkit.entity;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.mockmc.mockmc.generated.GeneratedTestBase;

class ComplexEntityPartBaseMockTest extends GeneratedTestBase
{
	@Test
	void testSafeDefaults()
	{
		ComplexEntityPartBaseMock mock = new Stub();
		assertNotNull(mock);
		try
		{
			assertSafeDefault(mock.getParent());
		} catch (Exception | LinkageError _)
		{
			// Ignore NPEs and LinkageErrors from Bukkit singletons
		}
	}

	private static class Stub implements ComplexEntityPartBaseMock
	{
	}
}
