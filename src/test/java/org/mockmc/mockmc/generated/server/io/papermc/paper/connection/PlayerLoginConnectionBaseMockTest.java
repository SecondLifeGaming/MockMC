// Auto-generated mechanical sanity test for PlayerLoginConnectionBaseMock
package org.mockmc.mockmc.generated.server.io.papermc.paper.connection;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.mockmc.mockmc.generated.GeneratedTestBase;

class PlayerLoginConnectionBaseMockTest extends GeneratedTestBase
{
	@Test
	void testSafeDefaults()
	{
		PlayerLoginConnectionBaseMock mock = new Stub();
		assertNotNull(mock);
		assertSafeDefault(mock.getAuthenticatedProfile());
		assertSafeDefault(mock.getUnsafeProfile());
	}

	private static class Stub implements PlayerLoginConnectionBaseMock
	{
	}
}
