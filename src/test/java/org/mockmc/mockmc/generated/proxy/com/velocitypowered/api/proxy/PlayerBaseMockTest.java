// Auto-generated mechanical sanity test for PlayerBaseMock
package org.mockmc.mockmc.generated.proxy.com.velocitypowered.api.proxy;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.mockmc.mockmc.generated.GeneratedTestBase;

class PlayerBaseMockTest extends GeneratedTestBase
{
	@Test
	void testSafeDefaults() throws Exception
	{
		PlayerBaseMock mock = new Stub();
		assertNotNull(mock);
		assertSafeDefault(mock.key());
		assertSafeDefault(mock.getUniqueId());
		assertSafeDefault(mock.getPlayerListHeader());
		assertSafeDefault(mock.getPlayerListFooter());
		assertSafeDefault(mock.getGameProfile());
		assertSafeDefault(mock.getUsername());
		assertSafeDefault(mock.getModInfo());
		assertSafeDefault(mock.getPlayerSettings());
		assertSafeDefault(mock.getCurrentServer());
		assertSafeDefault(mock.getEffectiveLocale());
		assertSafeDefault(mock.getGameProfileProperties());
		assertSafeDefault(mock.getTabList());
		assertSafeDefault(mock.getAppliedResourcePack());
		assertSafeDefault(mock.getPendingResourcePack());
		assertSafeDefault(mock.getAppliedResourcePacks());
		assertSafeDefault(mock.getPendingResourcePacks());
		assertSafeDefault(mock.getClientBrand());
	}

	private static class Stub implements PlayerBaseMock
	{
	}
}
