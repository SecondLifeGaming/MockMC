// Auto-generated mechanical sanity test for PlayerProfileBaseMock
package org.mockmc.mockmc.generated.server.org.bukkit.profile;

import static org.junit.jupiter.api.Assertions.*;

import org.bukkit.profile.PlayerProfile;
import org.junit.jupiter.api.Test;
import org.mockmc.mockmc.generated.GeneratedTestBase;

@SuppressWarnings(
{"deprecation", "java:S1874"})
class PlayerProfileBaseMockTest extends GeneratedTestBase
{
	@Test
	void testSafeDefaults()
	{
		PlayerProfileBaseMock mock = new Stub();
		assertNotNull(mock);
		assertSafeDefault(mock.getName());
		assertSafeDefault(mock.clone());
		assertSafeDefault(mock.update());
		assertSafeDefault(mock.getUniqueId());
		assertSafeDefault(mock.getTextures());
	}

	private static class Stub implements PlayerProfileBaseMock
	{
		@Override
		public PlayerProfile clone()
		{
			return null;
		}
	}
}
