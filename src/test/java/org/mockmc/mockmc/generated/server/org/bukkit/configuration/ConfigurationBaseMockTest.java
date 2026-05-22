// Auto-generated mechanical sanity test for ConfigurationBaseMock
package org.mockmc.mockmc.generated.server.org.bukkit.configuration;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.mockmc.mockmc.generated.GeneratedTestBase;

class ConfigurationBaseMockTest extends GeneratedTestBase
{
	@Test
	void testSafeDefaults()
	{
		ConfigurationBaseMock mock = new Stub();
		assertNotNull(mock);
		assertSafeDefault(mock.options());
		assertSafeDefault(mock.getDefaults());
	}

	private static class Stub implements ConfigurationBaseMock
	{
	}
}
