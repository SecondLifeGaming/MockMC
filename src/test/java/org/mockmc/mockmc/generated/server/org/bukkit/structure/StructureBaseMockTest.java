// Auto-generated mechanical sanity test for StructureBaseMock
package org.mockmc.mockmc.generated.server.org.bukkit.structure;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.mockmc.mockmc.generated.GeneratedTestBase;

class StructureBaseMockTest extends GeneratedTestBase
{
	@Test
	void testSafeDefaults()
	{
		StructureBaseMock mock = new Stub();
		assertNotNull(mock);
		assertSafeDefault(mock.getSize());
		assertSafeDefault(mock.getEntities());
		assertSafeDefault(mock.getPalettes());
	}

	private static class Stub implements StructureBaseMock
	{
	}
}
