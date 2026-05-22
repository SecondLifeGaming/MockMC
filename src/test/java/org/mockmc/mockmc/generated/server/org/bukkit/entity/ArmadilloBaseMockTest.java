// Auto-generated mechanical sanity test for ArmadilloBaseMock
package org.mockmc.mockmc.generated.server.org.bukkit.entity;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.mockmc.mockmc.generated.GeneratedTestBase;

class ArmadilloBaseMockTest extends GeneratedTestBase
{
	@Test
	void testSafeDefaults()
	{
		ArmadilloBaseMock mock = new Stub();
		assertNotNull(mock);
		assertSafeDefault(mock.getState());
	}

	private static class Stub implements ArmadilloBaseMock
	{
	}
}
