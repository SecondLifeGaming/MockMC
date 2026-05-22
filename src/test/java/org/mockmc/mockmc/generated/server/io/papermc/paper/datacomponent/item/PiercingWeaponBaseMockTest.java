// Auto-generated mechanical sanity test for PiercingWeaponBaseMock
package org.mockmc.mockmc.generated.server.io.papermc.paper.datacomponent.item;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.mockmc.mockmc.generated.GeneratedTestBase;

class PiercingWeaponBaseMockTest extends GeneratedTestBase
{
	@Test
	void testSafeDefaults()
	{
		PiercingWeaponBaseMock mock = new Stub();
		assertNotNull(mock);
		assertSafeDefault(mock.sound());
		assertSafeDefault(mock.hitSound());
	}

	private static class Stub implements PiercingWeaponBaseMock
	{
	}
}
