// Auto-generated mechanical sanity test for KeySignedBaseMock
package org.mockmc.mockmc.generated.proxy.com.velocitypowered.api.proxy.crypto;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.mockmc.mockmc.generated.GeneratedTestBase;

public class KeySignedBaseMockTest extends GeneratedTestBase
{
	@Test
	public void testSafeDefaults() throws Exception
	{
		KeySignedBaseMock mock = new Stub();
		assertNotNull(mock);
		assertSafeDefault(mock.getSignature());
		assertSafeDefault(mock.getSigner());
		assertSafeDefault(mock.getExpiryTemporal());
		assertSafeDefault(mock.getSalt());
	}

	private static class Stub implements KeySignedBaseMock
	{
	}
}
