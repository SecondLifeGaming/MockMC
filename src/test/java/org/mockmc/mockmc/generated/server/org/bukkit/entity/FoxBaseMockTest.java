// Auto-generated mechanical sanity test for FoxBaseMock
package org.mockmc.mockmc.generated.server.org.bukkit.entity;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.mockmc.mockmc.generated.GeneratedTestBase;

class FoxBaseMockTest extends GeneratedTestBase
{
	@Test
	void testSafeDefaults()
	{
		FoxBaseMock mock = new Stub();
		assertNotNull(mock);
		assertSafeDefault(mock.getFoxType());
		assertSafeDefault(mock.getFirstTrustedPlayer());
		assertSafeDefault(mock.getSecondTrustedPlayer());
	}

	private static class Stub implements FoxBaseMock
	{
	}
}
