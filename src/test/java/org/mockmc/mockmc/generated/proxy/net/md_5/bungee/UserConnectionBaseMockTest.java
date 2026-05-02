// Auto-generated mechanical sanity test for UserConnectionBaseMock
package org.mockmc.mockmc.generated.proxy.net.md_5.bungee;

import org.junit.jupiter.api.Test;
import org.mockmc.mockmc.generated.GeneratedTestBase;

public class UserConnectionBaseMockTest extends GeneratedTestBase
{
	@Test
	public void testSafeDefaults()
	{
		UserConnectionBaseMock mock = new UserConnectionBaseMock()
		{
		};
		assertNotNull(mock);
		assertSafeDefault(mock.getName());
		assertSafeDefault(mock.toString());
		assertSafeDefault(mock.unsafe());
		assertSafeDefault(mock.getPermissions());
		assertSafeDefault(mock.getAddress());
		assertSafeDefault(mock.getDisplayName());
		assertSafeDefault(mock.getLocale());
		assertSafeDefault(mock.getServer());
		assertSafeDefault(mock.getUniqueId());
		assertSafeDefault(mock.getMainHand());
		assertSafeDefault(mock.getSkinParts());
		assertSafeDefault(mock.getScoreboard());
		assertSafeDefault(mock.getUUID());
		assertSafeDefault(mock.getGroups());
		assertSafeDefault(mock.getClientBrand());
		assertSafeDefault(mock.getChatMode());
		assertSafeDefault(mock.getCh());
		assertSafeDefault(mock.getExtraDataInHandshake());
		assertSafeDefault(mock.getRewriteId());
		assertSafeDefault(mock.getPendingConnects());
		assertSafeDefault(mock.getSettings());
		assertSafeDefault(mock.getDimension());
		assertSafeDefault(mock.getTabListHandler());
		assertSafeDefault(mock.getServerSentScoreboard());
		assertSafeDefault(mock.getSentBossBars());
		assertSafeDefault(mock.getChatSerializer());
		assertSafeDefault(mock.getForgeClientHandler());
		assertSafeDefault(mock.getSocketAddress());
		assertSafeDefault(mock.getPendingConnection());
		assertSafeDefault(mock.getModList());
		assertSafeDefault(mock.getReconnectServer());
		assertSafeDefault(mock.getPotions());
		assertSafeDefault(mock.getLastCommandTabbed());
		assertSafeDefault(mock.getEntityRewrite());
		assertSafeDefault(mock.getForgeServerHandler());
	}
}
