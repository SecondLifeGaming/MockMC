// Auto-generated mechanical sanity test for BungeeTaskBaseMock
package org.mockmc.mockmc.generated.proxy.net.md_5.bungee.scheduler;

import org.junit.jupiter.api.Test;
import org.mockmc.mockmc.generated.GeneratedTestBase;

public class BungeeTaskBaseMockTest extends GeneratedTestBase
{
	@Test
	public void testSafeDefaults()
	{
		BungeeTaskBaseMock mock = new BungeeTaskBaseMock()
		{
		};
		assertNotNull(mock);
		assertSafeDefault(mock.toString());
		assertSafeDefault(mock.getOwner());
		assertSafeDefault(mock.getTask());
		assertSafeDefault(mock.getSched());
		assertSafeDefault(mock.getRunning());
	}
}
