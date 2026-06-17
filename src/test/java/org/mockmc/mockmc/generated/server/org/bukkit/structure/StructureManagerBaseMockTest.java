// Auto-generated mechanical sanity test for StructureManagerBaseMock
package org.mockmc.mockmc.generated.server.org.bukkit.structure;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.mockmc.mockmc.generated.GeneratedTestBase;

class StructureManagerBaseMockTest extends GeneratedTestBase
{
	@Test
	void testSafeDefaults()
	{
		StructureManagerBaseMock mock = new Stub();
		assertNotNull(mock);
		try
		{
			assertSafeDefault(mock.createStructure());
		} catch (Exception | LinkageError _)
		{
			// Ignore NPEs and LinkageErrors from Bukkit singletons
		}
		try
		{
			assertSafeDefault(mock.getStructures());
		} catch (Exception | LinkageError _)
		{
			// Ignore NPEs and LinkageErrors from Bukkit singletons
		}
	}

	private static class Stub implements StructureManagerBaseMock
	{
	}
}
