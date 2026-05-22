// Auto-generated mechanical sanity test for PlayerGameConnectionBaseMock
package org.mockmc.mockmc.generated.server.io.papermc.paper.connection;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.mockmc.mockmc.generated.GeneratedTestBase;

class PlayerGameConnectionBaseMockTest extends GeneratedTestBase
{
	@Test
	void testSafeDefaults()
	{
		PlayerGameConnectionBaseMock mock = new Stub();
		assertNotNull(mock);
		assertSafeDefault(mock.getPlayer());
	}

	private static class Stub implements PlayerGameConnectionBaseMock
	{
	}
}
