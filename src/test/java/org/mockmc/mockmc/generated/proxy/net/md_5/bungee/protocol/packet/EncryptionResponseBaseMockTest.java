// Auto-generated mechanical sanity test for EncryptionResponseBaseMock
package org.mockmc.mockmc.generated.proxy.net.md_5.bungee.protocol.packet;

import org.junit.jupiter.api.Test;
import org.mockmc.mockmc.generated.GeneratedTestBase;

public class EncryptionResponseBaseMockTest extends GeneratedTestBase
{
	@Test
	public void testSafeDefaults()
	{
		EncryptionResponseBaseMock mock = new EncryptionResponseBaseMock()
		{
		};
		assertNotNull(mock);
		assertSafeDefault(mock.toString());
		assertSafeDefault(mock.getVerifyToken());
		assertSafeDefault(mock.getEncryptionData());
		assertSafeDefault(mock.getSharedSecret());
	}
}
