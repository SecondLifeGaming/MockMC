// Auto-generated mechanical sanity test for QueryResultBaseMock
package org.mockmc.mockmc.generated.proxy.io.github.waterfallmc.waterfall;

import org.junit.jupiter.api.Test;
import org.mockmc.mockmc.generated.GeneratedTestBase;

public class QueryResultBaseMockTest extends GeneratedTestBase
{
	@Test
	public void testSafeDefaults()
	{
		QueryResultBaseMock mock = new QueryResultBaseMock()
		{
		};
		assertSafeDefault(mock.toString());
		assertSafeDefault(mock.getAddress());
		assertSafeDefault(mock.getVersion());
		assertSafeDefault(mock.getMotd());
		assertSafeDefault(mock.getWorldName());
		assertSafeDefault(mock.getPlayers());
		assertSafeDefault(mock.getGameType());
		assertSafeDefault(mock.getGameId());
	}
}
