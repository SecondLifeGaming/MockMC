// Auto-generated mechanical sanity test for ExperienceOrbBaseMock
package org.mockmc.mockmc.generated.server.org.bukkit.entity;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.mockmc.mockmc.generated.GeneratedTestBase;

class ExperienceOrbBaseMockTest extends GeneratedTestBase
{
	@Test
	void testSafeDefaults()
	{
		ExperienceOrbBaseMock mock = new Stub();
		assertNotNull(mock);
		assertSafeDefault(mock.getSourceEntityId());
		assertSafeDefault(mock.getTriggerEntityId());
		assertSafeDefault(mock.getSpawnReason());
	}

	private static class Stub implements ExperienceOrbBaseMock
	{
	}
}
