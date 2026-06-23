// Auto-generated mechanical sanity test for AttachableBaseMock
package org.mockmc.mockmc.generated.server.org.bukkit.block.data;

import static org.junit.jupiter.api.Assertions.*;

import org.bukkit.block.data.Attachable;
import org.junit.jupiter.api.Test;
import org.mockmc.mockmc.generated.GeneratedTestBase;

@SuppressWarnings("all")
class AttachableBaseMockTest extends GeneratedTestBase
{
	@Test
	void testSafeDefaults()
	{
		AttachableBaseMock mock = new Stub();
		assertNotNull(mock);
	}

	private static class Stub implements AttachableBaseMock
	{
		@Override
		public Attachable clone()
		{
			return null;
		}
	}
}
