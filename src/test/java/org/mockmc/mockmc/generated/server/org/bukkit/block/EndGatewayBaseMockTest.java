// Auto-generated mechanical sanity test for EndGatewayBaseMock
package org.mockmc.mockmc.generated.server.org.bukkit.block;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.mockmc.mockmc.generated.GeneratedTestBase;

class EndGatewayBaseMockTest extends GeneratedTestBase
{
	@Test
	void testSafeDefaults()
	{
		EndGatewayBaseMock mock = new Stub();
		assertNotNull(mock);
		assertSafeDefault(mock.getExitLocation());
	}

	private static class Stub implements EndGatewayBaseMock
	{
	}
}
