// Auto-generated mechanical sanity test for PlayerConfigurationConnectionBaseMock
package org.mockmc.mockmc.generated.server.io.papermc.paper.connection;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.mockmc.mockmc.generated.GeneratedTestBase;

class PlayerConfigurationConnectionBaseMockTest extends GeneratedTestBase
{
	@Test
	void testSafeDefaults()
	{
		PlayerConfigurationConnectionBaseMock mock = new Stub();
		assertNotNull(mock);
		assertSafeDefault(mock.getProfile());
		assertSafeDefault(mock.getAudience());
	}

	private static class Stub implements PlayerConfigurationConnectionBaseMock
	{
	}
}
