// Auto-generated mechanical sanity test for PatternTypeBaseMock
package org.mockmc.mockmc.generated.server.org.bukkit.block.banner;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.mockmc.mockmc.generated.GeneratedTestBase;

@SuppressWarnings(
{"deprecation", "removal", "java:S1874"})
class PatternTypeBaseMockTest extends GeneratedTestBase
{
	@Test
	void testSafeDefaults()
	{
		PatternTypeBaseMock mock = new Stub();
		assertNotNull(mock);
		assertSafeDefault(mock.getKey());
		assertSafeDefault(mock.key());
		assertSafeDefault(mock.getIdentifier());
	}

	private static class Stub implements PatternTypeBaseMock
	{
	}
}
