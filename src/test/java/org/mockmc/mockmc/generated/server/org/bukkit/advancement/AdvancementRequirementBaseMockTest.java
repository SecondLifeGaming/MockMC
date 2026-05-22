// Auto-generated mechanical sanity test for AdvancementRequirementBaseMock
package org.mockmc.mockmc.generated.server.org.bukkit.advancement;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.mockmc.mockmc.generated.GeneratedTestBase;

class AdvancementRequirementBaseMockTest extends GeneratedTestBase
{
	@Test
	void testSafeDefaults()
	{
		AdvancementRequirementBaseMock mock = new Stub();
		assertNotNull(mock);
		assertSafeDefault(mock.getRequiredCriteria());
	}

	private static class Stub implements AdvancementRequirementBaseMock
	{
	}
}
