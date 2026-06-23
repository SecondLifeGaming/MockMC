// Auto-generated mechanical sanity test for ConstraintBaseMock
package org.mockmc.mockmc.generated.server.io.papermc.paper.configuration.constraint;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.mockmc.mockmc.generated.GeneratedTestBase;

@SuppressWarnings("all")
class ConstraintBaseMockTest extends GeneratedTestBase
{
	@Test
	void testSafeDefaults()
	{
		ConstraintBaseMock mock = new Stub();
		assertNotNull(mock);
		try
		{
			assertSafeDefault(mock.value());
		} catch (Exception | LinkageError _)
		{
			// Ignore NPEs and LinkageErrors from Bukkit singletons
		}
	}

	private static class Stub implements ConstraintBaseMock
	{
	}
}
