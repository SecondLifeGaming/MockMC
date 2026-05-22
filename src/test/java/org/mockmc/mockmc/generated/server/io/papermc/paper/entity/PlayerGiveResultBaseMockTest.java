// Auto-generated mechanical sanity test for PlayerGiveResultBaseMock
package org.mockmc.mockmc.generated.server.io.papermc.paper.entity;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.mockmc.mockmc.generated.GeneratedTestBase;

class PlayerGiveResultBaseMockTest extends GeneratedTestBase
{
	@Test
	void testSafeDefaults()
	{
		PlayerGiveResultBaseMock mock = new Stub();
		assertNotNull(mock);
		assertSafeDefault(mock.drops());
		assertSafeDefault(mock.leftovers());
	}

	private static class Stub implements PlayerGiveResultBaseMock
	{
	}
}
