// Auto-generated mechanical sanity test for StructureSearchResultBaseMock
package org.mockmc.mockmc.generated.server.org.bukkit.util;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.mockmc.mockmc.generated.GeneratedTestBase;

class StructureSearchResultBaseMockTest extends GeneratedTestBase
{
	@Test
	void testSafeDefaults()
	{
		StructureSearchResultBaseMock mock = new Stub();
		assertNotNull(mock);
		assertSafeDefault(mock.getLocation());
		assertSafeDefault(mock.getStructure());
	}

	private static class Stub implements StructureSearchResultBaseMock
	{
	}
}
