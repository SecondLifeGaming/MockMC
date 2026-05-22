// Auto-generated mechanical sanity test for TameableBaseMock
package org.mockmc.mockmc.generated.server.org.bukkit.entity;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.mockmc.mockmc.generated.GeneratedTestBase;

class TameableBaseMockTest extends GeneratedTestBase
{
	@Test
	void testSafeDefaults()
	{
		TameableBaseMock mock = new Stub();
		assertNotNull(mock);
		assertSafeDefault(mock.getOwner());
		assertSafeDefault(mock.getOwnerUniqueId());
	}

	private static class Stub implements TameableBaseMock
	{
	}
}
