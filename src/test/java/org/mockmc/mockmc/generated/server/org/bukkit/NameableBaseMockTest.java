// Auto-generated mechanical sanity test for NameableBaseMock
package org.mockmc.mockmc.generated.server.org.bukkit;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.mockmc.mockmc.generated.GeneratedTestBase;

@SuppressWarnings(
{"deprecation", "java:S1874"})
class NameableBaseMockTest extends GeneratedTestBase
{
	@Test
	void testSafeDefaults()
	{
		NameableBaseMock mock = new Stub();
		assertNotNull(mock);
		assertSafeDefault(mock.customName());
		assertSafeDefault(mock.getCustomName());
	}

	private static class Stub implements NameableBaseMock
	{
	}
}
