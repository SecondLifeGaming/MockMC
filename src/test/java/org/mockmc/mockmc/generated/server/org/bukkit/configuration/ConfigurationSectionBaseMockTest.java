// Auto-generated mechanical sanity test for ConfigurationSectionBaseMock
package org.mockmc.mockmc.generated.server.org.bukkit.configuration;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.mockmc.mockmc.generated.GeneratedTestBase;

class ConfigurationSectionBaseMockTest extends GeneratedTestBase
{
	@Test
	void testSafeDefaults()
	{
		ConfigurationSectionBaseMock mock = new Stub();
		assertNotNull(mock);
		assertSafeDefault(mock.getName());
		assertSafeDefault(mock.getParent());
		assertSafeDefault(mock.getRoot());
		assertSafeDefault(mock.getCurrentPath());
		assertSafeDefault(mock.getDefaultSection());
	}

	private static class Stub implements ConfigurationSectionBaseMock
	{
	}
}
