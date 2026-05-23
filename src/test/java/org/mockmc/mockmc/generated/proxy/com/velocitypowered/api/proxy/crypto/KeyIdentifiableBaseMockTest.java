// Auto-generated mechanical sanity test for KeyIdentifiableBaseMock
package org.mockmc.mockmc.generated.proxy.com.velocitypowered.api.proxy.crypto;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.mockmc.mockmc.generated.GeneratedTestBase;

class KeyIdentifiableBaseMockTest extends GeneratedTestBase
{
	@Test
	void testSafeDefaults()
	{
		KeyIdentifiableBaseMock mock = new Stub();
		assertNotNull(mock);
		try
		{
			assertSafeDefault(mock.getIdentifiedKey());
		} catch (Exception | LinkageError e)
		{
			// Ignore NPEs and LinkageErrors from Bukkit singletons
		}
	}

	private static class Stub implements KeyIdentifiableBaseMock
	{
	}
}
