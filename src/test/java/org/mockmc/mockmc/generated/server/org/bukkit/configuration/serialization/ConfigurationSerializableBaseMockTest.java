// Auto-generated mechanical sanity test for ConfigurationSerializableBaseMock
package org.mockmc.mockmc.generated.server.org.bukkit.configuration.serialization;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.mockmc.mockmc.generated.GeneratedTestBase;

@SuppressWarnings("all")
class ConfigurationSerializableBaseMockTest extends GeneratedTestBase
{
	@Test
	void testSafeDefaults()
	{
		ConfigurationSerializableBaseMock mock = new Stub();
		assertNotNull(mock);
		try
		{
			assertSafeDefault(mock.serialize());
		} catch (Exception | LinkageError _)
		{
			// Ignore NPEs and LinkageErrors from Bukkit singletons
		}
	}

	private static class Stub implements ConfigurationSerializableBaseMock
	{
	}
}
