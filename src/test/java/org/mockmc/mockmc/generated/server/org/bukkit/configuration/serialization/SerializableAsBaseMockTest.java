// Auto-generated mechanical sanity test for SerializableAsBaseMock
package org.mockmc.mockmc.generated.server.org.bukkit.configuration.serialization;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.mockmc.mockmc.generated.GeneratedTestBase;

@SuppressWarnings("all")
class SerializableAsBaseMockTest extends GeneratedTestBase
{
	@Test
	void testSafeDefaults()
	{
		SerializableAsBaseMock mock = new Stub();
		assertNotNull(mock);
		try
		{
			assertSafeDefault(mock.value());
		} catch (Exception | LinkageError _)
		{
			// Ignore NPEs and LinkageErrors from Bukkit singletons
		}
	}

	private static class Stub implements SerializableAsBaseMock
	{
	}
}
