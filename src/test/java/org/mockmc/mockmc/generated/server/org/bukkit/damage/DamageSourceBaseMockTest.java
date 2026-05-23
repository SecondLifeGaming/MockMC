// Auto-generated mechanical sanity test for DamageSourceBaseMock
package org.mockmc.mockmc.generated.server.org.bukkit.damage;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.mockmc.mockmc.generated.GeneratedTestBase;

class DamageSourceBaseMockTest extends GeneratedTestBase
{
	@Test
	void testSafeDefaults()
	{
		DamageSourceBaseMock mock = new Stub();
		assertNotNull(mock);
		assertSafeDefault(mock.getCausingEntity());
		assertSafeDefault(mock.getDirectEntity());
		assertSafeDefault(mock.getDamageLocation());
		assertSafeDefault(mock.getSourceLocation());
		assertSafeDefault(mock.getDamageType());
	}

	private static class Stub implements DamageSourceBaseMock
	{
	}
}
