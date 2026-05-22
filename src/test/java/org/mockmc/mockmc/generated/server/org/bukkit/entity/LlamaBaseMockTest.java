// Auto-generated mechanical sanity test for LlamaBaseMock
package org.mockmc.mockmc.generated.server.org.bukkit.entity;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.mockmc.mockmc.generated.GeneratedTestBase;

class LlamaBaseMockTest extends GeneratedTestBase
{
	@Test
	void testSafeDefaults()
	{
		LlamaBaseMock mock = new Stub();
		assertNotNull(mock);
		assertSafeDefault(mock.getColor());
		assertSafeDefault(mock.getInventory());
		assertSafeDefault(mock.getCaravanHead());
		assertSafeDefault(mock.getCaravanTail());
	}

	private static class Stub implements LlamaBaseMock
	{
	}
}
