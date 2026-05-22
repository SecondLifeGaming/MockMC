// Auto-generated mechanical sanity test for AdvancementRequirementsBaseMock
package org.mockmc.mockmc.generated.server.org.bukkit.advancement;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.mockmc.mockmc.generated.GeneratedTestBase;

class AdvancementRequirementsBaseMockTest extends GeneratedTestBase
{
	@Test
	void testSafeDefaults()
	{
		AdvancementRequirementsBaseMock mock = new Stub();
		assertNotNull(mock);
		assertSafeDefault(mock.getRequirements());
	}

	private static class Stub implements AdvancementRequirementsBaseMock
	{
	}
}
