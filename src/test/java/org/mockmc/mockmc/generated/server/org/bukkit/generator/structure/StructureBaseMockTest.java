// Auto-generated mechanical sanity test for StructureBaseMock
package org.mockmc.mockmc.generated.server.org.bukkit.generator.structure;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.mockmc.mockmc.generated.GeneratedTestBase;

@SuppressWarnings(
{"deprecation", "removal", "java:S1874"})
class StructureBaseMockTest extends GeneratedTestBase
{
	@Test
	void testSafeDefaults()
	{
		StructureBaseMock mock = new Stub();
		assertNotNull(mock);
		assertSafeDefault(mock.getKey());
		assertSafeDefault(mock.key());
		assertSafeDefault(mock.getStructureType());
	}

	private static class Stub implements StructureBaseMock
	{
	}
}
