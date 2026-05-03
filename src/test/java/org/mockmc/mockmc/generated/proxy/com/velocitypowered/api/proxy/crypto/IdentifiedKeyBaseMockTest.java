// Auto-generated mechanical sanity test for IdentifiedKeyBaseMock
package org.mockmc.mockmc.generated.proxy.com.velocitypowered.api.proxy.crypto;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.mockmc.mockmc.generated.GeneratedTestBase;

public class IdentifiedKeyBaseMockTest extends GeneratedTestBase
{
	@Test
	public void testSafeDefaults() throws Exception
	{
		IdentifiedKeyBaseMock mock = new Stub();
		assertNotNull(mock);
		assertSafeDefault(mock.getSignedPublicKey());
		assertSafeDefault(mock.getSignatureHolder());
		assertSafeDefault(mock.getKeyRevision());
	}

	private static class Stub implements IdentifiedKeyBaseMock
	{
	}
}
