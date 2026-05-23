// Auto-generated mechanical sanity test for IdentifiedKeyBaseMock
package org.mockmc.mockmc.generated.proxy.com.velocitypowered.api.proxy.crypto;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.mockmc.mockmc.generated.GeneratedTestBase;

class IdentifiedKeyBaseMockTest extends GeneratedTestBase
{
	@Test
	void testSafeDefaults()
	{
		IdentifiedKeyBaseMock mock = new Stub();
		assertNotNull(mock);
		try
		{
			assertSafeDefault(mock.getSignedPublicKey());
		} catch (Exception | LinkageError e)
		{
			// Ignore NPEs and LinkageErrors from Bukkit singletons
		}
		try
		{
			assertSafeDefault(mock.getSignatureHolder());
		} catch (Exception | LinkageError e)
		{
			// Ignore NPEs and LinkageErrors from Bukkit singletons
		}
		try
		{
			assertSafeDefault(mock.getKeyRevision());
		} catch (Exception | LinkageError e)
		{
			// Ignore NPEs and LinkageErrors from Bukkit singletons
		}
	}

	private static class Stub implements IdentifiedKeyBaseMock
	{
	}
}
