// Auto-generated mechanical sanity test for PlayerPublicKeyBaseMock
package org.mockmc.mockmc.generated.proxy.net.md_5.bungee.protocol.data;

import org.junit.jupiter.api.Test;
import org.mockmc.mockmc.generated.GeneratedTestBase;

public class PlayerPublicKeyBaseMockTest extends GeneratedTestBase
{
	@Test
	public void testSafeDefaults()
	{
		PlayerPublicKeyBaseMock mock = new PlayerPublicKeyBaseMock()
		{
		};
		assertNotNull(mock);
		assertSafeDefault(mock.toString());
		assertSafeDefault(mock.getKey());
		assertSafeDefault(mock.getSignature());
	}
}
