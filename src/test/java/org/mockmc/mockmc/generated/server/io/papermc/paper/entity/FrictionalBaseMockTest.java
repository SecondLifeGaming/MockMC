// Auto-generated mechanical sanity test for FrictionalBaseMock
package org.mockmc.mockmc.generated.server.io.papermc.paper.entity;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.mockmc.mockmc.generated.GeneratedTestBase;

class FrictionalBaseMockTest extends GeneratedTestBase
{
	@Test
	void testSafeDefaults()
	{
		FrictionalBaseMock mock = new Stub();
		assertNotNull(mock);
		try
		{
			assertSafeDefault(mock.getFrictionState());
		} catch (Exception | LinkageError e)
		{
			// Ignore NPEs and LinkageErrors from Bukkit singletons
		}
	}

	private static class Stub implements FrictionalBaseMock
	{
	}
}
