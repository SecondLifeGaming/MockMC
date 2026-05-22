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
		assertSafeDefault(mock.getBoundingBox());
	}

	private static class Stub implements StructurePieceBaseMock
	{
	}
}
