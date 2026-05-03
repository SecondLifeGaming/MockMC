// Auto-generated mechanical sanity test for SignedMessageBaseMock
package org.mockmc.mockmc.generated.proxy.com.velocitypowered.api.proxy.crypto;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.mockmc.mockmc.generated.GeneratedTestBase;

class SignedMessageBaseMockTest extends GeneratedTestBase
{
	@Test
	void testSafeDefaults() throws Exception
	{
		SignedMessageBaseMock mock = new Stub();
		assertNotNull(mock);
		assertSafeDefault(mock.getMessage());
		assertSafeDefault(mock.getSignerUuid());
	}

	private static class Stub implements SignedMessageBaseMock
	{
	}
}
