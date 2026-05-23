// Auto-generated mechanical sanity test for UndefinedNullabilityBaseMock
package org.mockmc.mockmc.generated.server.org.bukkit;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.mockmc.mockmc.generated.GeneratedTestBase;

@SuppressWarnings(
{"deprecation", "java:S1874"})
class UndefinedNullabilityBaseMockTest extends GeneratedTestBase
{
	@Test
	void testSafeDefaults()
	{
		UndefinedNullabilityBaseMock mock = new Stub();
		assertNotNull(mock);
		try
		{
			assertSafeDefault(mock.value());
		} catch (Exception | LinkageError e)
		{
			// Ignore NPEs and LinkageErrors from Bukkit singletons
		}
	}

	private static class Stub implements UndefinedNullabilityBaseMock
	{
	}
}
