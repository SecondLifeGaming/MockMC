// Auto-generated mechanical sanity test for BossBarBaseMock
package org.mockmc.mockmc.generated.server.org.bukkit.boss;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.mockmc.mockmc.generated.GeneratedTestBase;

class BossBarBaseMockTest extends GeneratedTestBase
{
	@Test
	void testSafeDefaults()
	{
		BossBarBaseMock mock = new Stub();
		assertNotNull(mock);
		assertSafeDefault(mock.getColor());
		assertSafeDefault(mock.getPlayers());
		assertSafeDefault(mock.getTitle());
		assertSafeDefault(mock.getStyle());
	}

	private static class Stub implements BossBarBaseMock
	{
	}
}
