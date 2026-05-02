// Auto-generated mechanical sanity test for KeySignedBaseMock
package org.mockmc.mockmc.generated.proxy.com.velocitypowered.api.proxy.crypto;

import org.junit.jupiter.api.Test;
import org.mockmc.mockmc.generated.GeneratedTestBase;

public class KeySignedBaseMockTest extends GeneratedTestBase
{
	@Test
	public void testSafeDefaults()
	{
		KeySignedBaseMock mock = new KeySignedBaseMock()
		{
		};
		assertNotNull(mock);
		assertSafeDefault(mock.getSignature());
		assertSafeDefault(mock.getSigner());
		assertSafeDefault(mock.getExpiryTemporal());
		assertSafeDefault(mock.getSalt());
	}
}
