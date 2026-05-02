// Auto-generated mechanical sanity test for PlayerBaseMock
package org.mockmc.mockmc.generated.proxy.com.velocitypowered.api.proxy;

import org.junit.jupiter.api.Test;
import org.mockmc.mockmc.generated.GeneratedTestBase;

public class PlayerBaseMockTest extends GeneratedTestBase
{
	@Test
	public void testSafeDefaults()
	{
		PlayerBaseMock mock = new PlayerBaseMock()
		{
		};
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
}
