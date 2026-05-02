// Auto-generated mechanical sanity test for TeamBaseMock
package org.mockmc.mockmc.generated.proxy.net.md_5.bungee.api.score;

import org.junit.jupiter.api.Test;
import org.mockmc.mockmc.generated.GeneratedTestBase;

public class TeamBaseMockTest extends GeneratedTestBase
{
	@Test
	public void testSafeDefaults()
	{
		TeamBaseMock mock = new TeamBaseMock()
		{
		};
		assertNotNull(mock);
		assertSafeDefault(mock.getName());
		assertSafeDefault(mock.toString());
		assertSafeDefault(mock.getDisplayName());
		assertSafeDefault(mock.getPlayers());
		assertSafeDefault(mock.getPrefix());
		assertSafeDefault(mock.getSuffix());
		assertSafeDefault(mock.getNameTagVisibility());
		assertSafeDefault(mock.getCollisionRule());
	}
}
