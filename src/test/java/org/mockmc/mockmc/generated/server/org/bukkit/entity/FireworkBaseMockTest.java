// Auto-generated mechanical sanity test for FireworkBaseMock
package org.mockmc.mockmc.generated.server.org.bukkit.entity;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.mockmc.mockmc.generated.GeneratedTestBase;

@SuppressWarnings(
{"deprecation", "java:S1874"})
class FireworkBaseMockTest extends GeneratedTestBase
{
	@Test
	void testSafeDefaults()
	{
		FireworkBaseMock mock = new Stub();
		assertNotNull(mock);
		assertSafeDefault(mock.getFireworkMeta());
		assertSafeDefault(mock.getItem());
		assertSafeDefault(mock.getSpawningEntity());
		assertSafeDefault(mock.getBoostedEntity());
		assertSafeDefault(mock.getAttachedTo());
	}

	private static class Stub implements FireworkBaseMock
	{
	}
}
