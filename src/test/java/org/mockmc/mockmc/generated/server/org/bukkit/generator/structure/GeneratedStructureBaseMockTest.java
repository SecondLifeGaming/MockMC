// Auto-generated mechanical sanity test for GeneratedStructureBaseMock
package org.mockmc.mockmc.generated.server.org.bukkit.generator.structure;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.mockmc.mockmc.generated.GeneratedTestBase;

class GeneratedStructureBaseMockTest extends GeneratedTestBase
{
	@Test
	void testSafeDefaults()
	{
		GeneratedStructureBaseMock mock = new Stub();
		assertNotNull(mock);
		assertSafeDefault(mock.getBoundingBox());
		assertSafeDefault(mock.getStructure());
		assertSafeDefault(mock.getPieces());
	}

	private static class Stub implements GeneratedStructureBaseMock
	{
	}
}
