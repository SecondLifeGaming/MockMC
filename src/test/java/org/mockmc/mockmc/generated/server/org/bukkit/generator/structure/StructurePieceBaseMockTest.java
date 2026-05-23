// Auto-generated mechanical sanity test for StructurePieceBaseMock
package org.mockmc.mockmc.generated.server.org.bukkit.generator.structure;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.mockmc.mockmc.generated.GeneratedTestBase;

class StructurePieceBaseMockTest extends GeneratedTestBase
{
	@Test
	void testSafeDefaults()
	{
		StructurePieceBaseMock mock = new Stub();
		assertNotNull(mock);
		try
		{
			assertSafeDefault(mock.getBoundingBox());
		} catch (Exception | LinkageError e)
		{
			// Ignore NPEs and LinkageErrors from Bukkit singletons
		}
	}

	private static class Stub implements StructurePieceBaseMock
	{
	}
}
