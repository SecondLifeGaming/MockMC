// Auto-generated mechanical sanity test for LeashableBaseMock
package org.mockmc.mockmc.generated.server.io.papermc.paper.entity;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.mockmc.mockmc.generated.GeneratedTestBase;

class LeashableBaseMockTest extends GeneratedTestBase
{
	@Test
	void testSafeDefaults()
	{
		LeashableBaseMock mock = new Stub();
		assertNotNull(mock);
		assertSafeDefault(mock.getLeashHolder());
	}

	private static class Stub implements LeashableBaseMock
	{
	}
}
