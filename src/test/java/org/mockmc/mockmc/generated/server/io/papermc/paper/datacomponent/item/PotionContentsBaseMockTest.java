// Auto-generated mechanical sanity test for PotionContentsBaseMock
package org.mockmc.mockmc.generated.server.io.papermc.paper.datacomponent.item;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.mockmc.mockmc.generated.GeneratedTestBase;

class PotionContentsBaseMockTest extends GeneratedTestBase
{
	@Test
	void testSafeDefaults()
	{
		PotionContentsBaseMock mock = new Stub();
		assertNotNull(mock);
		assertSafeDefault(mock.potion());
		assertSafeDefault(mock.customName());
		assertSafeDefault(mock.computeEffectiveColor());
		assertSafeDefault(mock.customColor());
		assertSafeDefault(mock.customEffects());
		assertSafeDefault(mock.allEffects());
	}

	private static class Stub implements PotionContentsBaseMock
	{
	}
}
