// Auto-generated mechanical sanity test for FaceAttachableBaseMock
package org.mockmc.mockmc.generated.server.org.bukkit.block.data;

import static org.junit.jupiter.api.Assertions.*;

import org.bukkit.block.data.FaceAttachable;
import org.junit.jupiter.api.Test;
import org.mockmc.mockmc.generated.GeneratedTestBase;

class FaceAttachableBaseMockTest extends GeneratedTestBase
{
	@Test
	void testSafeDefaults()
	{
		FaceAttachableBaseMock mock = new Stub();
		assertNotNull(mock);
		assertSafeDefault(mock.getAttachedFace());
	}

	private static class Stub implements FaceAttachableBaseMock
	{
		@Override
		public FaceAttachable clone()
		{
			return null;
		}
	}
}
