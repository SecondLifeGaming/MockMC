// Auto-generated mechanical sanity test for TrimMaterialBaseMock
package org.mockmc.mockmc.generated.server.org.bukkit.inventory.meta.trim;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.mockmc.mockmc.generated.GeneratedTestBase;

@SuppressWarnings(
{"deprecation", "removal", "java:S1874"})
class TrimMaterialBaseMockTest extends GeneratedTestBase
{
	@Test
	void testSafeDefaults()
	{
		TrimMaterialBaseMock mock = new Stub();
		assertNotNull(mock);
		assertSafeDefault(mock.getKey());
		assertSafeDefault(mock.key());
		assertSafeDefault(mock.description());
		assertSafeDefault(mock.getTranslationKey());
	}

	private static class Stub implements TrimMaterialBaseMock
	{
	}
}
