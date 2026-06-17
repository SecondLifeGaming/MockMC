// Auto-generated mechanical sanity test for PaperShearableBaseMock
package org.mockmc.mockmc.generated.server.io.papermc.paper.entity;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.mockmc.mockmc.generated.GeneratedTestBase;

class PaperShearableBaseMockTest extends GeneratedTestBase
{
	@Test
	void testSafeDefaults()
	{
		PaperShearableBaseMock mock = new Stub();
		assertNotNull(mock);
		try
		{
			assertSafeDefault(mock.getHandle());
		} catch (Exception | LinkageError _)
		{
			// Ignore NPEs and LinkageErrors from Bukkit singletons
		}
	}

	private static class Stub implements PaperShearableBaseMock
	{
	}
}
