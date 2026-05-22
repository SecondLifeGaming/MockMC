// Auto-generated mechanical sanity test for MinecraftExperimentalBaseMock
package org.mockmc.mockmc.generated.server.org.bukkit;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.mockmc.mockmc.generated.GeneratedTestBase;

class MinecraftExperimentalBaseMockTest extends GeneratedTestBase
{
	@Test
	void testSafeDefaults()
	{
		MinecraftExperimentalBaseMock mock = new Stub();
		assertNotNull(mock);
		assertSafeDefault(mock.value());
	}

	private static class Stub implements MinecraftExperimentalBaseMock
	{
	}
}
