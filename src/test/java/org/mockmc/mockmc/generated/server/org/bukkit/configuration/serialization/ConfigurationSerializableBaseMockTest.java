// Auto-generated mechanical sanity test for ConfigurationSerializableBaseMock
package org.mockmc.mockmc.generated.server.org.bukkit.configuration.serialization;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.mockmc.mockmc.generated.GeneratedTestBase;

class ConfigurationSerializableBaseMockTest extends GeneratedTestBase
{
	@Test
	void testSafeDefaults()
	{
		ConfigurationSerializableBaseMock mock = new Stub();
		assertNotNull(mock);
		assertSafeDefault(mock.serialize());
	}

	private static class Stub implements ConfigurationSerializableBaseMock
	{
	}
}
