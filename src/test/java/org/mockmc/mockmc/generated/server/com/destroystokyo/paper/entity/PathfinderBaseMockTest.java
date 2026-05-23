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
		try
		{
			assertSafeDefault(mock.getEntity());
		} catch (Exception | LinkageError _)
		{
			// Ignore NPEs and LinkageErrors from Bukkit singletons
		}
		try
		{
			assertSafeDefault(mock.getCurrentPath());
		} catch (Exception | LinkageError _)
		{
			// Ignore NPEs and LinkageErrors from Bukkit singletons
		}
	}

	private static class Stub implements PathfinderBaseMock
	{
	}
}
