// Auto-generated mechanical sanity test for EvokerBaseMock
package org.mockmc.mockmc.generated.server.org.bukkit.entity;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.mockmc.mockmc.generated.GeneratedTestBase;

@SuppressWarnings(
{"deprecation", "java:S1874"})
class EvokerBaseMockTest extends GeneratedTestBase
{
	@Test
	void testSafeDefaults()
	{
		EvokerBaseMock mock = new Stub();
		assertNotNull(mock);
		assertSafeDefault(mock.getCurrentSpell());
		assertSafeDefault(mock.getWololoTarget());
	}

	private static class Stub implements EvokerBaseMock
	{
	}
}
