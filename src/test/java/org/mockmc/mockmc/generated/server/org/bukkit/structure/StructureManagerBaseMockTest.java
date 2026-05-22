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
		assertSafeDefault(mock.getStructures());
		assertSafeDefault(mock.createStructure());
	}

	private static class Stub implements StructureManagerBaseMock
	{
	}
}
