// Auto-generated mechanical sanity test for MinecartBaseMock
package org.mockmc.mockmc.generated.server.org.bukkit.entity;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.mockmc.mockmc.generated.GeneratedTestBase;

@SuppressWarnings(
{"deprecation", "removal", "java:S1874"})
class MinecartBaseMockTest extends GeneratedTestBase
{
	@Test
	void testSafeDefaults()
	{
		MinecartBaseMock mock = new Stub();
		assertNotNull(mock);
		assertSafeDefault(mock.getFlyingVelocityMod());
		assertSafeDefault(mock.getDisplayBlock());
		assertSafeDefault(mock.getDisplayBlockData());
		assertSafeDefault(mock.getDerailedVelocityMod());
		assertSafeDefault(mock.getMinecartMaterial());
	}

	private static class Stub implements MinecartBaseMock
	{
	}
}
