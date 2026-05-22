// Auto-generated mechanical sanity test for StructureBaseMock
package org.mockmc.mockmc.generated.server.org.bukkit.block;

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
		assertSafeDefault(mock.getRotation());
		assertSafeDefault(mock.getMetadata());
		assertSafeDefault(mock.getStructureName());
		assertSafeDefault(mock.getAuthor());
		assertSafeDefault(mock.getRelativePosition());
		assertSafeDefault(mock.getStructureSize());
		assertSafeDefault(mock.getMirror());
		assertSafeDefault(mock.getUsageMode());
	}

	private static class Stub implements StructureBaseMock
	{
	}
}
