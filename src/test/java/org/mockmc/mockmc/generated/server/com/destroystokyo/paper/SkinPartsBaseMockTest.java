// Auto-generated mechanical sanity test for SkinPartsBaseMock
package org.mockmc.mockmc.generated.server.com.destroystokyo.paper;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.mockmc.mockmc.generated.GeneratedTestBase;

class SkinPartsBaseMockTest extends GeneratedTestBase
{
	@Test
	void testSafeDefaults()
	{
		SkinPartsBaseMock mock = new Stub();
		assertNotNull(mock);
		try
		{
			assertSafeDefault(mock.mutableCopy());
		} catch (Exception | LinkageError _)
		{
			// Ignore NPEs and LinkageErrors from Bukkit singletons
		}
	}

	private static class Stub implements SkinPartsBaseMock
	{
	}
}
