// Auto-generated mechanical sanity test for BannerPatternLayersBaseMock
package org.mockmc.mockmc.generated.server.io.papermc.paper.datacomponent.item;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.mockmc.mockmc.generated.GeneratedTestBase;

@SuppressWarnings("all")
class BannerPatternLayersBaseMockTest extends GeneratedTestBase
{
	@Test
	void testSafeDefaults()
	{
		BannerPatternLayersBaseMock mock = new Stub();
		assertNotNull(mock);
		try
		{
			assertSafeDefault(mock.patterns());
		} catch (Exception | LinkageError _)
		{
			// Ignore NPEs and LinkageErrors from Bukkit singletons
		}
	}

	private static class Stub implements BannerPatternLayersBaseMock
	{
	}
}
