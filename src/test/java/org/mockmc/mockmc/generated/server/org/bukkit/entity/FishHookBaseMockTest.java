// Auto-generated mechanical sanity test for FishHookBaseMock
package org.mockmc.mockmc.generated.server.org.bukkit.entity;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.mockmc.mockmc.generated.GeneratedTestBase;

class FishHookBaseMockTest extends GeneratedTestBase
{
	@Test
	void testSafeDefaults()
	{
		FishHookBaseMock mock = new Stub();
		assertNotNull(mock);
		assertSafeDefault(mock.getState());
		assertSafeDefault(mock.getHookedEntity());
	}

	private static class Stub implements FishHookBaseMock
	{
	}
}
