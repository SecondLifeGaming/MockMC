// Auto-generated mechanical sanity test for TrimPatternBaseMock
package org.mockmc.mockmc.generated.server.org.bukkit.inventory.meta.trim;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.mockmc.mockmc.generated.GeneratedTestBase;

@SuppressWarnings(
{"deprecation", "removal", "java:S1874"})
class TrimPatternBaseMockTest extends GeneratedTestBase
{
	@Test
	void testSafeDefaults()
	{
		TrimPatternBaseMock mock = new Stub();
		assertNotNull(mock);
		assertSafeDefault(mock.getKey());
		assertSafeDefault(mock.key());
		assertSafeDefault(mock.description());
		assertSafeDefault(mock.getTranslationKey());
	}

	private static class Stub implements TrimPatternBaseMock
	{
	}
}
