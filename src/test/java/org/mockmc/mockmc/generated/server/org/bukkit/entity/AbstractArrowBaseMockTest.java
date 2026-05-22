// Auto-generated mechanical sanity test for AbstractArrowBaseMock
package org.mockmc.mockmc.generated.server.org.bukkit.entity;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.mockmc.mockmc.generated.GeneratedTestBase;

@SuppressWarnings(
{"deprecation", "removal", "java:S1874"})
class AbstractArrowBaseMockTest extends GeneratedTestBase
{
	@Test
	void testSafeDefaults()
	{
		AbstractArrowBaseMock mock = new Stub();
		assertNotNull(mock);
		assertSafeDefault(mock.getItemStack());
		assertSafeDefault(mock.getHitSound());
		assertSafeDefault(mock.getItem());
		assertSafeDefault(mock.getPickupStatus());
		assertSafeDefault(mock.getAttachedBlock());
		assertSafeDefault(mock.getAttachedBlocks());
		assertSafeDefault(mock.getWeapon());
		assertSafeDefault(mock.getPickupRule());
	}

	private static class Stub implements AbstractArrowBaseMock
	{
	}
}
