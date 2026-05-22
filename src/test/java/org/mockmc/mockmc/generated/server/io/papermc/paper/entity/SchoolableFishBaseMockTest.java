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
		assertSafeDefault(mock.getSchoolLeader());
	}

	private static class Stub implements SchoolableFishBaseMock
	{
	}
}
