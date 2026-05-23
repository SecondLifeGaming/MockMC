// Auto-generated mechanical sanity test for TNTPrimedBaseMock
package org.mockmc.mockmc.generated.server.org.bukkit.entity;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.mockmc.mockmc.generated.GeneratedTestBase;

@SuppressWarnings(
{"deprecation", "java:S1874"})
class TNTPrimedBaseMockTest extends GeneratedTestBase
{
	@Test
	void testSafeDefaults()
	{
		TNTPrimedBaseMock mock = new Stub();
		assertNotNull(mock);
		assertSafeDefault(mock.getSource());
		assertSafeDefault(mock.getBlockData());
		assertSafeDefault(mock.getSourceLoc());
	}

	private static class Stub implements TNTPrimedBaseMock
	{
	}
}
