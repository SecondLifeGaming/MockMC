// Auto-generated mechanical sanity test for ComplexEntityPartBaseMock
package org.mockmc.mockmc.generated.server.org.bukkit.entity;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.mockmc.mockmc.generated.GeneratedTestBase;

class ComplexEntityPartBaseMockTest extends GeneratedTestBase
{
	@Test
	void testSafeDefaults()
	{
		ComplexEntityPartBaseMock mock = new Stub();
		assertNotNull(mock);
		assertSafeDefault(mock.getParent());
	}

	private static class Stub implements ComplexEntityPartBaseMock
	{
	}
}
