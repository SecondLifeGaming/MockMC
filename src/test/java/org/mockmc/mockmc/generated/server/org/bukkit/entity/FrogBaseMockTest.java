// Auto-generated mechanical sanity test for FrogBaseMock
package org.mockmc.mockmc.generated.server.org.bukkit.entity;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.mockmc.mockmc.generated.GeneratedTestBase;

class FrogBaseMockTest extends GeneratedTestBase
{
	@Test
	void testSafeDefaults()
	{
		FrogBaseMock mock = new Stub();
		assertNotNull(mock);
		assertSafeDefault(mock.getVariant());
		assertSafeDefault(mock.getTongueTarget());
	}

	private static class Stub implements FrogBaseMock
	{
	}
}
