// Auto-generated mechanical sanity test for BossBaseMock
package org.mockmc.mockmc.generated.server.org.bukkit.entity;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.mockmc.mockmc.generated.GeneratedTestBase;

class BossBaseMockTest extends GeneratedTestBase
{
	@Test
	void testSafeDefaults()
	{
		BossBaseMock mock = new Stub();
		assertNotNull(mock);
		assertSafeDefault(mock.getBossBar());
	}

	private static class Stub implements BossBaseMock
	{
	}
}
