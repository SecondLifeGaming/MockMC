// Auto-generated mechanical sanity test for AnvilInventoryBaseMock
package org.mockmc.mockmc.generated.server.org.bukkit.inventory;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.mockmc.mockmc.generated.GeneratedTestBase;

@SuppressWarnings(
{"deprecation", "removal", "java:S1874"})
class AnvilInventoryBaseMockTest extends GeneratedTestBase
{
	@Test
	void testSafeDefaults()
	{
		AnvilInventoryBaseMock mock = new Stub();
		assertNotNull(mock);
		assertSafeDefault(mock.getResult());
		assertSafeDefault(mock.getRenameText());
		assertSafeDefault(mock.getFirstItem());
		assertSafeDefault(mock.getSecondItem());
	}

	private static class Stub implements AnvilInventoryBaseMock
	{
	}
}
