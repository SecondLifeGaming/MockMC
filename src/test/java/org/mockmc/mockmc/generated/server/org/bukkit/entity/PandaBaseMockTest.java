// Auto-generated mechanical sanity test for PandaBaseMock
package org.mockmc.mockmc.generated.server.org.bukkit.entity;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.mockmc.mockmc.generated.GeneratedTestBase;

class PandaBaseMockTest extends GeneratedTestBase
{
	@Test
	void testSafeDefaults()
	{
		PandaBaseMock mock = new Stub();
		assertNotNull(mock);
		assertSafeDefault(mock.getMainGene());
		assertSafeDefault(mock.getHiddenGene());
		assertSafeDefault(mock.getCombinedGene());
	}

	private static class Stub implements PandaBaseMock
	{
	}
}
