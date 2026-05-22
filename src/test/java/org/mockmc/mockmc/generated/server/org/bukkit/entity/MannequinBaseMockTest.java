// Auto-generated mechanical sanity test for MannequinBaseMock
package org.mockmc.mockmc.generated.server.org.bukkit.entity;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.mockmc.mockmc.generated.GeneratedTestBase;

class MannequinBaseMockTest extends GeneratedTestBase
{
	@Test
	void testSafeDefaults()
	{
		MannequinBaseMock mock = new Stub();
		assertNotNull(mock);
		assertSafeDefault(mock.getDescription());
		assertSafeDefault(mock.getProfile());
		assertSafeDefault(mock.getEquipment());
		assertSafeDefault(mock.getMainHand());
		assertSafeDefault(mock.getSkinParts());
	}

	private static class Stub implements MannequinBaseMock
	{
	}
}
