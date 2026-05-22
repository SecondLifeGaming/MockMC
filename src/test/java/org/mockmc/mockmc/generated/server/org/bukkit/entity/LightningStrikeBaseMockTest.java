// Auto-generated mechanical sanity test for LightningStrikeBaseMock
package org.mockmc.mockmc.generated.server.org.bukkit.entity;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.mockmc.mockmc.generated.GeneratedTestBase;

@SuppressWarnings(
{"deprecation", "removal", "java:S1874"})
class LightningStrikeBaseMockTest extends GeneratedTestBase
{
	@Test
	void testSafeDefaults()
	{
		LightningStrikeBaseMock mock = new Stub();
		assertNotNull(mock);
		assertSafeDefault(mock.spigot());
		assertSafeDefault(mock.getCausingEntity());
		assertSafeDefault(mock.getCausingPlayer());
	}

	private static class Stub implements LightningStrikeBaseMock
	{
	}
}
