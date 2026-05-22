// Auto-generated mechanical sanity test for SkullBaseMock
package org.mockmc.mockmc.generated.server.org.bukkit.block;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.mockmc.mockmc.generated.GeneratedTestBase;

@SuppressWarnings(
{"deprecation", "removal", "java:S1874"})
class SkullBaseMockTest extends GeneratedTestBase
{
	@Test
	void testSafeDefaults()
	{
		SkullBaseMock mock = new Stub();
		assertNotNull(mock);
		assertSafeDefault(mock.getOwner());
		assertSafeDefault(mock.getRotation());
		assertSafeDefault(mock.customName());
		assertSafeDefault(mock.getPlayerProfile());
		assertSafeDefault(mock.getOwningPlayer());
		assertSafeDefault(mock.getProfile());
		assertSafeDefault(mock.getOwnerProfile());
		assertSafeDefault(mock.getNoteBlockSound());
		assertSafeDefault(mock.getSkullType());
	}

	private static class Stub implements SkullBaseMock
	{
	}
}
