// Auto-generated mechanical sanity test for RabbitBaseMock
package org.mockmc.mockmc.generated.server.org.bukkit.entity;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.mockmc.mockmc.generated.GeneratedTestBase;

class RabbitBaseMockTest extends GeneratedTestBase
{
	@Test
	void testSafeDefaults()
	{
		RabbitBaseMock mock = new Stub();
		assertNotNull(mock);
		assertSafeDefault(mock.getRabbitType());
	}

	private static class Stub implements RabbitBaseMock
	{
	}
}
