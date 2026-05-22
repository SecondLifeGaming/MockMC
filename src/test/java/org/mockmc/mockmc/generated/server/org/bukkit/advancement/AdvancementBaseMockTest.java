// Auto-generated mechanical sanity test for AdvancementBaseMock
package org.mockmc.mockmc.generated.server.org.bukkit.advancement;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.mockmc.mockmc.generated.GeneratedTestBase;

class AdvancementBaseMockTest extends GeneratedTestBase
{
	@Test
	void testSafeDefaults()
	{
		AdvancementBaseMock mock = new Stub();
		assertNotNull(mock);
		assertSafeDefault(mock.getParent());
		assertSafeDefault(mock.getRoot());
		assertSafeDefault(mock.displayName());
		assertSafeDefault(mock.getChildren());
		assertSafeDefault(mock.getCriteria());
		assertSafeDefault(mock.getRequirements());
		assertSafeDefault(mock.getDisplay());
	}

	private static class Stub implements AdvancementBaseMock
	{
	}
}
