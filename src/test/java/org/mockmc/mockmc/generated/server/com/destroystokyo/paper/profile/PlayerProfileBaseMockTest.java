// Auto-generated mechanical sanity test for PlayerProfileBaseMock
package org.mockmc.mockmc.generated.server.com.destroystokyo.paper.profile;

import static org.junit.jupiter.api.Assertions.*;

import com.destroystokyo.paper.profile.PlayerProfile;
import org.junit.jupiter.api.Test;
import org.mockmc.mockmc.generated.GeneratedTestBase;

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
		assertSafeDefault(mock.getProperties());
		assertSafeDefault(mock.getId());
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
