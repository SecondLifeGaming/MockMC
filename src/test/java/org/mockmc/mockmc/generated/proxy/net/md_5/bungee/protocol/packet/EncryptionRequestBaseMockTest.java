// Auto-generated mechanical sanity test for EncryptionRequestBaseMock
package org.mockmc.mockmc.generated.proxy.net.md_5.bungee.protocol.packet;

import org.junit.jupiter.api.Test;
import org.mockmc.mockmc.generated.GeneratedTestBase;

public class EncryptionRequestBaseMockTest extends GeneratedTestBase
{
	@Test
	public void testSafeDefaults()
	{
		EncryptionRequestBaseMock mock = new EncryptionRequestBaseMock()
		{
		};
		assertSafeDefault(mock.toString());
		assertSafeDefault(mock.getPublicKey());
		assertSafeDefault(mock.getVerifyToken());
		assertSafeDefault(mock.getServerId());
	}
}
