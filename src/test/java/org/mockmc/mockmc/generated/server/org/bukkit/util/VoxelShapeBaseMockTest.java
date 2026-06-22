// Auto-generated mechanical sanity test for VoxelShapeBaseMock
package org.mockmc.mockmc.generated.server.org.bukkit.util;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.mockmc.mockmc.generated.GeneratedTestBase;

@SuppressWarnings("all")
class VoxelShapeBaseMockTest extends GeneratedTestBase
{
	@Test
	void testSafeDefaults()
	{
		VoxelShapeBaseMock mock = new Stub();
		assertNotNull(mock);
		try
		{
			assertSafeDefault(mock.getBoundingBoxes());
		} catch (Exception | LinkageError _)
		{
			// Ignore NPEs and LinkageErrors from Bukkit singletons
		}
	}

	private static class Stub implements VoxelShapeBaseMock
	{
	}
}
