// Auto-generated mechanical sanity test for GameProfileBaseMock
package org.mockmc.mockmc.generated.proxy.com.velocitypowered.api.util;

import org.junit.jupiter.api.Test;
import org.mockmc.mockmc.generated.GeneratedTestBase;

public class GameProfileBaseMockTest extends GeneratedTestBase
{
	@Test
	public void testSafeDefaults()
	{
		GameProfileBaseMock mock = new GameProfileBaseMock()
		{
		};
		assertNotNull(mock);
		assertSafeDefault(mock.getName());
		assertSafeDefault(mock.toString());
		assertSafeDefault(mock.getProperties());
		assertSafeDefault(mock.getId());
		assertSafeDefault(mock.getUndashedId());
	}
}
