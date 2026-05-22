// Auto-generated mechanical sanity test for EndermanBaseMock
package org.mockmc.mockmc.generated.server.org.bukkit.entity;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.mockmc.mockmc.generated.GeneratedTestBase;

@SuppressWarnings(
{"deprecation", "removal", "java:S1874"})
class EndermanBaseMockTest extends GeneratedTestBase
{
	@Test
	void testSafeDefaults()
	{
		EndermanBaseMock mock = new Stub();
		assertNotNull(mock);
		assertSafeDefault(mock.getCarriedMaterial());
		assertSafeDefault(mock.getCarriedBlock());
	}

	private static class Stub implements EndermanBaseMock
	{
	}
}
