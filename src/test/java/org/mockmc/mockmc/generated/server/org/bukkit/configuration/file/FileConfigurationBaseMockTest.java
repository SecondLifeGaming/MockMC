// Auto-generated mechanical sanity test for FileConfigurationBaseMock
package org.mockmc.mockmc.generated.server.org.bukkit.configuration.file;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.mockmc.mockmc.generated.GeneratedTestBase;

class FileConfigurationBaseMockTest extends GeneratedTestBase
{
	@Test
	void testSafeDefaults()
	{
		FileConfigurationBaseMock mock = new Stub();
		assertNotNull(mock);
		assertSafeDefault(mock.options());
		assertSafeDefault(mock.saveToString());
	}

	private static class Stub implements FileConfigurationBaseMock
	{
	}
}
