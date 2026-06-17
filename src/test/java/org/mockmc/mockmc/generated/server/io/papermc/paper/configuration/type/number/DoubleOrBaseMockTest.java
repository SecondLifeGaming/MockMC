// Auto-generated mechanical sanity test for DoubleOrBaseMock
package org.mockmc.mockmc.generated.server.io.papermc.paper.configuration.type.number;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.mockmc.mockmc.generated.GeneratedTestBase;

class DoubleOrBaseMockTest extends GeneratedTestBase
{
	@Test
	void testSafeDefaults()
	{
		DoubleOrBaseMock mock = new Stub();
		assertNotNull(mock);
		try
		{
			assertSafeDefault(mock.value());
		} catch (Exception | LinkageError _)
		{
			// Ignore NPEs and LinkageErrors from Bukkit singletons
		}
	}

	private static class Stub implements DoubleOrBaseMock
	{
	}
}
