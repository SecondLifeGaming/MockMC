// Auto-generated mechanical sanity test for EnderSignalBaseMock
package org.mockmc.mockmc.generated.server.org.bukkit.entity;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.mockmc.mockmc.generated.GeneratedTestBase;

class EnderSignalBaseMockTest extends GeneratedTestBase
{
	@Test
	void testSafeDefaults()
	{
		EnderSignalBaseMock mock = new Stub();
		assertNotNull(mock);
		assertSafeDefault(mock.getItem());
		assertSafeDefault(mock.getTargetLocation());
	}

	private static class Stub implements EnderSignalBaseMock
	{
	}
}
