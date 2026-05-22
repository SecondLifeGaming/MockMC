// Auto-generated mechanical sanity test for AttachableBaseMock
package org.mockmc.mockmc.generated.server.org.bukkit.material;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.mockmc.mockmc.generated.GeneratedTestBase;

class AttachableBaseMockTest extends GeneratedTestBase
{
	@Test
	void testSafeDefaults()
	{
		AttachableBaseMock mock = new Stub();
		assertNotNull(mock);
		assertSafeDefault(mock.getAttachedFace());
	}

	private static class Stub implements AttachableBaseMock
	{
	}
}
