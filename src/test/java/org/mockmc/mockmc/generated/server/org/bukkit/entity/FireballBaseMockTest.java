// Auto-generated mechanical sanity test for FireballBaseMock
package org.mockmc.mockmc.generated.server.org.bukkit.entity;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.mockmc.mockmc.generated.GeneratedTestBase;

@SuppressWarnings(
{"deprecation", "java:S1874"})
class FireballBaseMockTest extends GeneratedTestBase
{
	@Test
	void testSafeDefaults()
	{
		FireballBaseMock mock = new Stub();
		assertNotNull(mock);
		assertSafeDefault(mock.getDirection());
		assertSafeDefault(mock.getPower());
		assertSafeDefault(mock.getAcceleration());
	}

	private static class Stub implements FireballBaseMock
	{
	}
}
