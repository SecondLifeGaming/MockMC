// Auto-generated mechanical sanity test for AdvancementDisplayBaseMock
package org.mockmc.mockmc.generated.server.org.bukkit.advancement;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.mockmc.mockmc.generated.GeneratedTestBase;

@SuppressWarnings(
{"deprecation", "removal", "java:S1874"})
class AdvancementDisplayBaseMockTest extends GeneratedTestBase
{
	@Test
	void testSafeDefaults()
	{
		AdvancementDisplayBaseMock mock = new Stub();
		assertNotNull(mock);
		assertSafeDefault(mock.getType());
		assertSafeDefault(mock.getDescription());
		assertSafeDefault(mock.getTitle());
		assertSafeDefault(mock.getIcon());
	}

	private static class Stub implements AdvancementDisplayBaseMock
	{
	}
}
