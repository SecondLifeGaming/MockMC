// Auto-generated mechanical sanity test for KineticWeaponBaseMock
package org.mockmc.mockmc.generated.server.io.papermc.paper.datacomponent.item;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.mockmc.mockmc.generated.GeneratedTestBase;

class KineticWeaponBaseMockTest extends GeneratedTestBase
{
	@Test
	void testSafeDefaults()
	{
		KineticWeaponBaseMock mock = new Stub();
		assertNotNull(mock);
		assertSafeDefault(mock.sound());
		assertSafeDefault(mock.dismountConditions());
		assertSafeDefault(mock.knockbackConditions());
		assertSafeDefault(mock.damageConditions());
		assertSafeDefault(mock.hitSound());
	}

	private static class Stub implements KineticWeaponBaseMock
	{
	}
}
