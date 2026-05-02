// Auto-generated mechanical sanity test for RespawnBaseMock
package org.mockmc.mockmc.generated.proxy.net.md_5.bungee.protocol.packet;

import org.junit.jupiter.api.Test;
import org.mockmc.mockmc.generated.GeneratedTestBase;

public class RespawnBaseMockTest extends GeneratedTestBase
{
	@Test
	public void testSafeDefaults()
	{
		RespawnBaseMock mock = new RespawnBaseMock()
		{
		};
		assertNotNull(mock);
		assertSafeDefault(mock.toString());
		assertSafeDefault(mock.getWorldName());
		assertSafeDefault(mock.getDimension());
		assertSafeDefault(mock.getLevelType());
		assertSafeDefault(mock.getDeathLocation());
	}
}
