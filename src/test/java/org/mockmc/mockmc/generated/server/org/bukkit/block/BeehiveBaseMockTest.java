// Auto-generated mechanical sanity test for BeehiveBaseMock
package org.mockmc.mockmc.generated.server.org.bukkit.block;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.mockmc.mockmc.generated.GeneratedTestBase;

class BeehiveBaseMockTest extends GeneratedTestBase
{
	@Test
	void testSafeDefaults()
	{
		BeehiveBaseMock mock = new Stub();
		assertNotNull(mock);
		assertSafeDefault(mock.getFlower());
	}

	private static class Stub implements BeehiveBaseMock
	{
	}
}
