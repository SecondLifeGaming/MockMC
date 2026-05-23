// Auto-generated mechanical sanity test for NamespacedBaseMock
package org.mockmc.mockmc.generated.server.com.destroystokyo.paper;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.mockmc.mockmc.generated.GeneratedTestBase;

@SuppressWarnings(
{"deprecation", "removal", "java:S1874"})
class NamespacedBaseMockTest extends GeneratedTestBase
{
	@Test
	void testSafeDefaults()
	{
		NamespacedBaseMock mock = new Stub();
		assertNotNull(mock);
		try
		{
			assertSafeDefault(mock.getKey());
		} catch (Exception | LinkageError e)
		{
			// Ignore NPEs and LinkageErrors from Bukkit singletons
		}
		try
		{
			assertSafeDefault(mock.getNamespace());
		} catch (Exception | LinkageError e)
		{
			// Ignore NPEs and LinkageErrors from Bukkit singletons
		}
	}

	private static class Stub implements NamespacedBaseMock
	{
	}
}
