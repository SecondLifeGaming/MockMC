// Auto-generated mechanical sanity test for LimitedRegionBaseMock
package org.mockmc.mockmc.generated.server.org.bukkit.generator;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.mockmc.mockmc.generated.GeneratedTestBase;

class LimitedRegionBaseMockTest extends GeneratedTestBase
{
	@Test
	void testSafeDefaults()
	{
		LimitedRegionBaseMock mock = new Stub();
		assertNotNull(mock);
		assertSafeDefault(mock.getTileEntities());
		assertSafeDefault(mock.getWorld());
	}

	private static class Stub implements LimitedRegionBaseMock
	{
	}
}
