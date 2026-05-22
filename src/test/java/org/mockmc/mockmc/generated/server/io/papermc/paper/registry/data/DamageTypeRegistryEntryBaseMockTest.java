// Auto-generated mechanical sanity test for DamageTypeRegistryEntryBaseMock
package org.mockmc.mockmc.generated.server.io.papermc.paper.registry.data;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.mockmc.mockmc.generated.GeneratedTestBase;

class DamageTypeRegistryEntryBaseMockTest extends GeneratedTestBase
{
	@Test
	void testSafeDefaults()
	{
		DamageTypeRegistryEntryBaseMock mock = new Stub();
		assertNotNull(mock);
		assertSafeDefault(mock.damageScaling());
		assertSafeDefault(mock.deathMessageType());
		assertSafeDefault(mock.damageEffect());
		assertSafeDefault(mock.messageId());
	}

	private static class Stub implements DamageTypeRegistryEntryBaseMock
	{
	}
}
