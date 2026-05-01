// Auto-generated mechanical sanity test for IdentifiedKeyBaseMock
package org.mockmc.mockmc.generated.proxy.com.velocitypowered.api.proxy.crypto;

import org.junit.jupiter.api.Test;
import org.mockmc.mockmc.generated.GeneratedTestBase;

public class IdentifiedKeyBaseMockTest extends GeneratedTestBase
{
	@Test
	public void testSafeDefaults()
	{
		IdentifiedKeyBaseMock mock = new IdentifiedKeyBaseMock()
		{
		};
		assertSafeDefault(mock.getSignedPublicKey());
		assertSafeDefault(mock.getSignatureHolder());
		assertSafeDefault(mock.getKeyRevision());
	}
}
