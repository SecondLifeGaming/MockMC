// Auto-generated mechanical sanity test for SchoolableFishBaseMock
package org.mockmc.mockmc.generated.server.io.papermc.paper.entity;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.mockmc.mockmc.generated.GeneratedTestBase;

class SchoolableFishBaseMockTest extends GeneratedTestBase
{
	@Test
	void testSafeDefaults()
	{
		SchoolableFishBaseMock mock = new Stub();
		assertNotNull(mock);
		try
		{
			assertSafeDefault(mock.getSchoolLeader());
		} catch (Exception | LinkageError _)
		{
			// Ignore NPEs and LinkageErrors from Bukkit singletons
		}
	}

	private static class Stub implements SchoolableFishBaseMock
	{
	}
}
