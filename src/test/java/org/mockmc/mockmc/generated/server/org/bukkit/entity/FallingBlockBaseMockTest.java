// Auto-generated mechanical sanity test for FallingBlockBaseMock
package org.mockmc.mockmc.generated.server.org.bukkit.entity;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.mockmc.mockmc.generated.GeneratedTestBase;

@SuppressWarnings(
{"deprecation", "java:S1874"})
class FallingBlockBaseMockTest extends GeneratedTestBase
{
	@Test
	void testSafeDefaults()
	{
		FallingBlockBaseMock mock = new Stub();
		assertNotNull(mock);
		assertSafeDefault(mock.getMaterial());
		assertSafeDefault(mock.getBlockData());
		assertSafeDefault(mock.getBlockState());
		assertSafeDefault(mock.getSourceLoc());
	}

	private static class Stub implements FallingBlockBaseMock
	{
	}
}
