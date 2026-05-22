// Auto-generated mechanical sanity test for BannerBaseMock
package org.mockmc.mockmc.generated.server.org.bukkit.block;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.mockmc.mockmc.generated.GeneratedTestBase;

class BannerBaseMockTest extends GeneratedTestBase
{
	@Test
	void testSafeDefaults()
	{
		BannerBaseMock mock = new Stub();
		assertNotNull(mock);
		assertSafeDefault(mock.getBaseColor());
		assertSafeDefault(mock.getPatterns());
	}

	private static class Stub implements BannerBaseMock
	{
	}
}
