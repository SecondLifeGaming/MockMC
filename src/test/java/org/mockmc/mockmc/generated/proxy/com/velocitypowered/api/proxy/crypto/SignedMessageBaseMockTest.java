// Auto-generated mechanical sanity test for SignedMessageBaseMock
package org.mockmc.mockmc.generated.proxy.com.velocitypowered.api.proxy.crypto;

import org.junit.jupiter.api.Test;
import org.mockmc.mockmc.generated.GeneratedTestBase;

public class SignedMessageBaseMockTest extends GeneratedTestBase
{
	@Test
	public void testSafeDefaults()
	{
		SignedMessageBaseMock mock = new SignedMessageBaseMock()
		{
		};
		assertNotNull(mock);
		assertSafeDefault(mock.getMessage());
		assertSafeDefault(mock.getSignerUuid());
	}
}
