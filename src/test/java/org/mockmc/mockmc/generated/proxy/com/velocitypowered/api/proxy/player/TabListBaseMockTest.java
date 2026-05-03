// Auto-generated mechanical sanity test for TabListBaseMock
package org.mockmc.mockmc.generated.proxy.com.velocitypowered.api.proxy.player;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.mockmc.mockmc.generated.GeneratedTestBase;

public class TabListBaseMockTest extends GeneratedTestBase
{
	@Test
	public void testSafeDefaults() throws Exception
	{
		TabListBaseMock mock = new Stub();
		assertNotNull(mock);
		assertSafeDefault(mock.getEntries());
	}

	private static class Stub implements TabListBaseMock
	{
	}
}
