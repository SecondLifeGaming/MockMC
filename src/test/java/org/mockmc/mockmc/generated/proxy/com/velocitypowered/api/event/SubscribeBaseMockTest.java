// Auto-generated mechanical sanity test for SubscribeBaseMock
package org.mockmc.mockmc.generated.proxy.com.velocitypowered.api.event;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.mockmc.mockmc.generated.GeneratedTestBase;

@SuppressWarnings(
{"deprecation", "java:S1874"})
class SubscribeBaseMockTest extends GeneratedTestBase
{
	@Test
	void testSafeDefaults()
	{
		SubscribeBaseMock mock = new Stub();
		assertNotNull(mock);
		try
		{
			assertSafeDefault(mock.order());
		} catch (Exception | LinkageError _)
		{
			// Ignore NPEs and LinkageErrors from Bukkit singletons
		}
	}

	private static class Stub implements SubscribeBaseMock
	{
	}
}
