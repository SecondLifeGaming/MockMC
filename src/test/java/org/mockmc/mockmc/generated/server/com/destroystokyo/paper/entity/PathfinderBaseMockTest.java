// Auto-generated mechanical sanity test for PathfinderBaseMock
package org.mockmc.mockmc.generated.server.com.destroystokyo.paper.entity;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.mockmc.mockmc.generated.GeneratedTestBase;

class PathfinderBaseMockTest extends GeneratedTestBase
{
	@Test
	void testSafeDefaults()
	{
		PathfinderBaseMock mock = new Stub();
		assertNotNull(mock);
		assertSafeDefault(mock.getEntity());
		assertSafeDefault(mock.getCurrentPath());
	}

	private static class Stub implements PathfinderBaseMock
	{
	}
}
