// Auto-generated mechanical sanity test for ParrotBaseMock
package org.mockmc.mockmc.generated.server.org.bukkit.entity;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.mockmc.mockmc.generated.GeneratedTestBase;

class ParrotBaseMockTest extends GeneratedTestBase
{
	@Test
	void testSafeDefaults()
	{
		ParrotBaseMock mock = new Stub();
		assertNotNull(mock);
		assertSafeDefault(mock.getVariant());
	}

	private static class Stub implements ParrotBaseMock
	{
	}
}
