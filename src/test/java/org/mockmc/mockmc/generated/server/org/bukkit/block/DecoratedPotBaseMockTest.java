// Auto-generated mechanical sanity test for DecoratedPotBaseMock
package org.mockmc.mockmc.generated.server.org.bukkit.block;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.mockmc.mockmc.generated.GeneratedTestBase;

@SuppressWarnings(
{"deprecation", "java:S1874"})
class DecoratedPotBaseMockTest extends GeneratedTestBase
{
	@Test
	void testSafeDefaults()
	{
		DecoratedPotBaseMock mock = new Stub();
		assertNotNull(mock);
		assertSafeDefault(mock.getInventory());
		assertSafeDefault(mock.getSnapshotInventory());
		assertSafeDefault(mock.getSherds());
		assertSafeDefault(mock.getShards());
	}

	private static class Stub implements DecoratedPotBaseMock
	{
	}
}
