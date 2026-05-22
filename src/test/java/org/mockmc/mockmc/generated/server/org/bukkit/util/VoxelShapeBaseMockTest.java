// Auto-generated mechanical sanity test for VoxelShapeBaseMock
package org.mockmc.mockmc.generated.server.org.bukkit.util;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.mockmc.mockmc.generated.GeneratedTestBase;

class VoxelShapeBaseMockTest extends GeneratedTestBase
{
	@Test
	void testSafeDefaults()
	{
		VoxelShapeBaseMock mock = new Stub();
		assertNotNull(mock);
		assertSafeDefault(mock.getBoundingBoxes());
	}

	private static class Stub implements VoxelShapeBaseMock
	{
	}
}
